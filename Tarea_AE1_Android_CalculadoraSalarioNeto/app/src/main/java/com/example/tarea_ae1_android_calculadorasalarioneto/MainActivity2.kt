package com.example.tarea_ae1_android_calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // Configuración de manejo de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperación de valores enviados desde la actividad principal
        val salarioNetoMensual = intent.getDoubleExtra("salarioNetoMensual", 0.0)
        val grupoProfesional = intent.getStringExtra("grupoProfesional") ?: "No definido"
        val totalMinimos = intent.getDoubleExtra("totalMinimos", 0.0)
        val minimoGeneral = intent.getDoubleExtra("minimoGeneral", 0.0)
        val minimoRangoEdad = intent.getDoubleExtra("minimoRangoEdad", 0.0)
        val minimoHijos = intent.getDoubleExtra("minimoHijos", 0.0)
        val minimoGradoDiscap = intent.getDoubleExtra("minimoGradoDiscap", 0.0)
        val salario = intent.getDoubleExtra("salario", 0.0)
        val seguridadSoc = intent.getDoubleExtra("seguridadSoc", 0.0)
        val tipoImpositivo = intent.getDoubleExtra("tipoImpositivo", 0.0)
        val irpf = intent.getDoubleExtra("irpf", 0.0)
        val valueNumPagas = intent.getIntExtra("valueNumPagas", 0)
        val salarioNetoAnual = intent.getDoubleExtra("salarioNetoAnual", 0.0)

        // Configuración del botón "Volver"
        val buttonVolver = findViewById<Button>(R.id.buttonVolver)
        buttonVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Añade flags para limpiar el historial y reiniciar la actividad principal
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Finaliza esta actividad para que sea destruida
        }

        // Mostrar las variables seleccionadas en el primer ScrollView
        textVariablesSeleccionadas(
            totalMinimos,
            minimoGeneral,
            minimoRangoEdad,
            minimoHijos,
            minimoGradoDiscap,
            seguridadSoc
        )

        // Mostrar los resultados del cálculo en el segundo ScrollView
        textResultadosCalculos(
            salario,
            irpf,
            tipoImpositivo,
            salarioNetoMensual,
            salarioNetoAnual,
            grupoProfesional
        )
    }

    //Muestra las variables seleccionadas en el primer ScrollView
    private fun textVariablesSeleccionadas(
        totalMinimos: Double,
        minimoGeneral: Double,
        minimoRangoEdad: Double,
        minimoHijos: Double,
        minimoGradoDiscap: Double,
        seguridadSoc: Double
    ) {
        val textVariablesSeleccionadas =
            findViewById<TextView>(R.id.text2VariablesSeleccionadas)
        textVariablesSeleccionadas.text = """
            Total Mínimos: ${formatDouble(totalMinimos)}
            Mínimo General: ${formatDouble(minimoGeneral)}
            Mínimo Rango Edad: ${formatDouble(minimoRangoEdad)}
            Mínimo Hijos: ${formatDouble(minimoHijos)}
            Mínimo Grado Discapacidad: ${formatDouble(minimoGradoDiscap)}
            Seguridad Social: ${formatDouble(seguridadSoc)}
        """.trimIndent()
    }

    //Muestra los resultados del cálculo en el segundo ScrollView
    private fun textResultadosCalculos(
        salario: Double,
        irpf: Double,
        tipoImpositivo: Double,
        salarioNetoMensual: Double,
        salarioNetoAnual: Double,
        grupoProfesional: String
    ) {
        val textResultadosCalculos = findViewById<TextView>(R.id.text2ResultadosCalculos)
        textResultadosCalculos.text = """
            Salario Bruto: ${formatDouble(salario)} €
            IRPF: ${formatDouble(irpf)} €
            Tipo Impositivo: ${formatDouble(tipoImpositivo * 100)}%
            Salario Neto Mensual: ${formatDouble(salarioNetoMensual)} €
            Salario Neto Anual: ${formatDouble(salarioNetoAnual)} €
            Grupo Profesional: $grupoProfesional
        """.trimIndent()

        val textoCalculoFinal = findViewById<TextView>(R.id.textoCalculoFinal)
        textoCalculoFinal.text = getString(
            R.string.textoCalculoFinal,
            formatDouble(salarioNetoMensual),
            grupoProfesional
        )
    }

    //Formatea un valor double a dos decimales
    private fun formatDouble(value: Double): String {
        return String.format("%.2f", value)
    }
}
