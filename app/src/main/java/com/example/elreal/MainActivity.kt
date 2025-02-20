package com.example.elreal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.elreal.R.id

class MainActivity : AppCompatActivity() {

    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(id.display)

        // Botones de números
        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)
        val button0: Button = findViewById(R.id.button_0)



        // Botones de operaciones
        val buttonAdd: Button = findViewById(id.suma)
        val buttonSubtract: Button = findViewById(id.resta)
        val buttonMultiply: Button = findViewById(id.multiplicacion)
        val buttonDivide: Button = findViewById(id.division)
        val buttonEquals: Button = findViewById(id.igual)
        val buttonClear: Button = findViewById(id.borrar)

        // Asignar acciones a los botones
        val buttons = listOf(button1, button2, button3, button4, button5, button6, button7, button8, button9, button0)

        for (button in buttons) {
            button.setOnClickListener {
                currentNumber += (it as Button).text
                display.text = currentNumber
            }
        }

        // Operaciones
        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("×") }
        buttonDivide.setOnClickListener { setOperator("÷") }

        // Calcular resultado
        buttonEquals.setOnClickListener {
            if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty() && operator.isNotEmpty()) {
                val result = calculateResult(previousNumber.toDouble(), currentNumber.toDouble(), operator)
                display.text = result.toString()
                currentNumber = result.toString()
                previousNumber = ""
                operator = ""
            }
        }

        // Borrar pantalla
        buttonClear.setOnClickListener {
            currentNumber = ""
            previousNumber = ""
            operator = ""
            display.text = "0"
        }
    }

    // Configura la operación
    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            previousNumber = currentNumber
            currentNumber = ""
            operator = op
        }
    }

    // Realiza la operación matemática
    private fun calculateResult(num1: Double, num2: Double, op: String): Double {
        return when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
    }
}
