package com.example.elreal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentNumber = ""
    private var previousNumber = ""
    private var operator = ""
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

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
        val buttonAdd: Button = findViewById(R.id.button_plus)
        val buttonSubtract: Button = findViewById(R.id.button_minus)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonDivide: Button = findViewById(R.id.button_division)
        val buttonEquals: Button = findViewById(R.id.button_igual)
        val buttonClear: Button = findViewById(R.id.button_ac)
        val buttonBorrar: Button = findViewById(R.id.button_borrar)

        // Botones para funciones especiales
        val buttonPi: Button = findViewById(R.id.button_pi)
        val buttonRaiz: Button = findViewById(R.id.button_raiz)
        val buttonPotencia: Button = findViewById(R.id.button_potencia)
        val buttonFactorial: Button = findViewById(R.id.button_admiracion)


        val buttons = listOf(button1, button2, button3, button4, button5, button6, button7, button8, button9, button0)
        buttons.forEach { button ->
            button.setOnClickListener {
                currentNumber += (it as Button).text
                display.text = currentNumber
            }
        }

        buttonBorrar.setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = currentNumber.substring(0, currentNumber.length - 1)
                display.text = if (currentNumber.isEmpty()) "0" else currentNumber
            }
        }


        buttonPi.setOnClickListener { appendPi() }
        buttonRaiz.setOnClickListener { calculateSquareRoot() }
        buttonPotencia.setOnClickListener { setOperator("^") }
        buttonFactorial.setOnClickListener { calculateFactorial() }

        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("×") }
        buttonDivide.setOnClickListener { setOperator("÷") }

        buttonEquals.setOnClickListener {
            if (previousNumber.isNotEmpty() && currentNumber.isNotEmpty() && operator.isNotEmpty()) {
                val result = calculateResult(previousNumber.toDouble(), currentNumber.toDouble(), operator)
                display.text = result
                currentNumber = result
                previousNumber = ""
                operator = ""
            }
        }

        buttonClear.setOnClickListener {
            currentNumber = ""
            previousNumber = ""
            operator = ""
            display.text = "0"
        }
    }

    private fun appendPi() {
        currentNumber += Math.PI.toString().take(10) // Limit Pi to 10 digits
        display.text = currentNumber
    }

    private fun calculateSquareRoot() {
        if (currentNumber.isNotEmpty()) {
            val result = Math.sqrt(currentNumber.toDouble())
            display.text = result.toString()
            currentNumber = result.toString()
        }
    }

    private fun calculateFactorial() {
        if (currentNumber.isNotEmpty() && currentNumber.toIntOrNull() != null) {
            val result = factorial(currentNumber.toInt())
            display.text = result.toString()
            currentNumber = result.toString()
        }
    }

    private fun factorial(n: Int): Long {
        return if (n >= 1) n * factorial(n - 1) else 1
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            previousNumber = currentNumber
            currentNumber = ""
            operator = op
        }
    }

    private fun calculateResult(num1: Double, num2: Double, op: String): String {
        val result = when (op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            "÷" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            "^" -> Math.pow(num1, num2)
            else -> 0.0
        }


        return if (result % 1.0 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }
    }

}
