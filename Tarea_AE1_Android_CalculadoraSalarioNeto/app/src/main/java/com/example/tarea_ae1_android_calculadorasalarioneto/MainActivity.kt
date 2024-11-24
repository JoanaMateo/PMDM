package com.example.tarea_ae1_android_calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Constantes
    companion object {
        private const val SEGURIDAD_SOC_CONTRATO_0 = 0.0635
        private const val SEGURIDAD_SOC_CONTRATO_1 = 0.064
        private const val TIPO_IMPOSITIVO_1 = 0.095
        private const val TIPO_IMPOSITIVO_2 = 0.12
        private const val TIPO_IMPOSITIVO_3 = 0.15
        private const val TIPO_IMPOSITIVO_4 = 0.185
        private const val TIPO_IMPOSITIVO_5 = 0.24
        private const val MINIMO_GENERAL = 5550.0
        private const val MINIMO_RANGO_EDAD_0 = 0.0
        private const val MINIMO_RANGO_EDAD_1 = 6700.0
        private const val MINIMO_RANGO_EDAD_2 = 8100.0
        private const val MINIMO_GRADO_DISCAP_0 = 0.0
        private const val MINIMO_GRADO_DISCAP_1 = 3000.0
        private const val MINIMO_GRADO_DISCAP_2 = 9000.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toast para mostrar mensajes
        val mensajeToastVacio = Toast.makeText(this, R.string.mensajeToastVacio, Toast.LENGTH_SHORT)

        // Declaración de vistas
        val salarioBruto = findViewById<EditText>(R.id.salarioBruto)
        val numHijos = findViewById<EditText>(R.id.numHijos)
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)

        val numPagas = findViewById<RadioGroup>(R.id.numPagas)
        val tipoContrato = findViewById<RadioGroup>(R.id.tipoContrato)
        val rangoEdad = findViewById<RadioGroup>(R.id.rangoEdad)
        val gradoDiscap = findViewById<RadioGroup>(R.id.gradoDiscap)

        val tipoEstado = findViewById<Spinner>(R.id.tipoEstado)
        val grupoProfesional = findViewById<Spinner>(R.id.grupoProfesional)

        // Variables de estado
        var selectedItemTE = ""
        var selectedItemGP = ""
        var valueNumPagas = -1
        var positionRangoEdad = -1
        var positionGradoDiscap = -1
        var positionTipoContrato = -1

        // Lista de elementos para los spinners
        val itemsTE = listOf("Seleccione una opción", "Soltero", "Casado", "Divorciado", "Viudo")
        val itemsGP = listOf(
            "Seleccione una opción",
            "Ingenieros y Licenciados",
            "Técnico",
            "Ayudantes no titulados",
            "Administración pública",
            "Trabajador menor de dieciocho años"
        )

        // Configuración de spinners
        setupSpinner(tipoEstado, itemsTE) { position, item ->
            selectedItemTE = if (position == 0) "" else item
        }

        setupSpinner(grupoProfesional, itemsGP) { position, item ->
            selectedItemGP = if (position == 0) "" else item
        }

        // Configuración de RadioGroups
        setupRadioGroup(numPagas) { selectedText, _ ->
            valueNumPagas = selectedText.toIntOrNull() ?: -1
        }

        setupRadioGroup(tipoContrato) { _, position ->
            positionTipoContrato = position
        }

        setupRadioGroup(rangoEdad) { _, position ->
            positionRangoEdad = position
        }

        setupRadioGroup(gradoDiscap) { _, position ->
            positionGradoDiscap = position
        }
        // Botón Calcular
        buttonCalcular.setOnClickListener {
            // Validaciones
            if (salarioBruto.text.toString().isEmpty() ||
                numHijos.text.toString().isEmpty() ||
                valueNumPagas == -1 ||
                positionRangoEdad == -1 ||
                positionGradoDiscap == -1 ||
                positionTipoContrato == -1 ||
                selectedItemTE.isEmpty() ||
                selectedItemGP.isEmpty()
            ) {
                mensajeToastVacio.show()
            } else {

                val salario = salarioBruto.text.toString().toDoubleOrNull() ?: 0.0
                val hijos = numHijos.text.toString().toIntOrNull() ?: 0
                val seguridadSoc = getSeguridadSocial(positionTipoContrato)
                val tipoImpositivo = getTipoImpositivo(salario)
                val minimoRangoEdad = getMinimoRangoEdad(positionRangoEdad)
                val minimoHijos = getMinimoHijos(hijos)
                val minimoGradoDiscap = getMinimoGradoDiscap(positionGradoDiscap)

                val totalMinimos = MINIMO_GENERAL + minimoRangoEdad + minimoHijos + minimoGradoDiscap
                val baseImponible = salario - (salario * seguridadSoc) - totalMinimos
                val irpf = baseImponible * tipoImpositivo
                val salarioNetoAnual = salario - irpf
                val salarioNetoMensual = if (valueNumPagas > 0) salarioNetoAnual / valueNumPagas else 0.0

                val actividad2 = Intent(this, MainActivity2::class.java)
                actividad2.putExtra("salarioNetoMensual", salarioNetoMensual)
                actividad2.putExtra("grupoProfesional", selectedItemGP)
                actividad2.putExtra("totalMinimos", totalMinimos)
                actividad2.putExtra("minimoGeneral", MINIMO_GENERAL)
                actividad2.putExtra("minimoRangoEdad", minimoRangoEdad)
                actividad2.putExtra("minimoHijos", minimoHijos)
                actividad2.putExtra("minimoGradoDiscap", minimoGradoDiscap)
                actividad2.putExtra("salario", salario)
                actividad2.putExtra("seguridadSoc", seguridadSoc)
                actividad2.putExtra("tipoImpositivo", tipoImpositivo)
                actividad2.putExtra("irpf", irpf)
                actividad2.putExtra("valueNumPagas", valueNumPagas)
                actividad2.putExtra("salarioNetoAnual", salarioNetoAnual)

                startActivity(actividad2)
            }
        }

        // Listener para cambiar el padding según los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Configuración de Spinner
    private fun setupSpinner(spinner: Spinner, items: List<String>, callback: (Int, String) -> Unit) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                callback(position, parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    // Configuración de RadioGroup
    private fun setupRadioGroup(radioGroup: RadioGroup, callback: (String, Int) -> Unit) {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
            val position = group.indexOfChild(selectedRadioButton)
            callback(selectedRadioButton.text.toString(), position)
        }
    }

    // Obtener seguridad social según tipo de contrato
    private fun getSeguridadSocial(position: Int): Double {
        return when (position) {
            0 -> SEGURIDAD_SOC_CONTRATO_0
            1 -> SEGURIDAD_SOC_CONTRATO_1
            else -> 0.0
        }
    }

    // Obtener tipo impositivo según salario
    private fun getTipoImpositivo(salario: Double): Double {
        return when {
            salario <= 12_450.0 -> TIPO_IMPOSITIVO_1
            salario <= 20_200.0 -> TIPO_IMPOSITIVO_2
            salario <= 35_200.0 -> TIPO_IMPOSITIVO_3
            salario <= 60_000.0 -> TIPO_IMPOSITIVO_4
            else -> TIPO_IMPOSITIVO_5
        }
    }

    // Obtener mínimo por rango de edad
    private fun getMinimoRangoEdad(position: Int): Double {
        return when (position) {
            0 -> MINIMO_RANGO_EDAD_0
            1 -> MINIMO_RANGO_EDAD_1
            2 -> MINIMO_RANGO_EDAD_2
            else -> 0.0
        }
    }

    // Obtener mínimo por número de hijos
    private fun getMinimoHijos(hijos: Int): Double {
        return when (hijos) {
            0 -> 0.0
            1 -> 2400.0
            2 -> 2700.0
            3 -> 4000.0
            else -> 4500.0 * hijos
        }
    }

    // Obtener mínimo por grado de discapacidad
    private fun getMinimoGradoDiscap(position: Int): Double {
        return when (position) {
            0 -> MINIMO_GRADO_DISCAP_0
            1 -> MINIMO_GRADO_DISCAP_1
            2 -> MINIMO_GRADO_DISCAP_2
            else -> 0.0
        }
    }
}