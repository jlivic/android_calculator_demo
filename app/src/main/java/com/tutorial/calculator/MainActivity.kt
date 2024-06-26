package com.tutorial.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var input: TextView
    private lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        input = findViewById(R.id.input)
        output = findViewById(R.id.output)

        val buttonClear: Button = findViewById(R.id.button_clear)
        buttonClear.setOnClickListener {
            input.text = ""
            output.text = ""
        }

        val buttonBracketLeft: Button = findViewById(R.id.button_bracket_left)
        buttonBracketLeft.setOnClickListener{
            input.text = addToInputText("(")
        }

        val buttonBracketRight: Button = findViewById(R.id.button_bracket_right)
        buttonBracketRight.setOnClickListener{
            input.text = addToInputText(")")
        }

        val button_0: Button = findViewById(R.id.button_0)
        button_0.setOnClickListener{
            input.text = addToInputText("0")
        }

        val button_1: Button = findViewById(R.id.button_1)
        button_1.setOnClickListener{
            input.text = addToInputText("1")
        }

        val button_2: Button = findViewById(R.id.button_2)
        button_2.setOnClickListener{
            input.text = addToInputText("2")
        }

        val button_3: Button = findViewById(R.id.button_3)
        button_3.setOnClickListener{
            input.text = addToInputText("3")
        }

        val button_4: Button = findViewById(R.id.button_4)
        button_4.setOnClickListener{
            input.text = addToInputText("4")
        }

        val button_5: Button = findViewById(R.id.button_5)
        button_5.setOnClickListener{
            input.text = addToInputText("5")
        }

        val button_6: Button = findViewById(R.id.button_6)
        button_6.setOnClickListener{
            input.text = addToInputText("6")
        }

        val button_7: Button = findViewById(R.id.button_7)
        button_7.setOnClickListener{
            input.text = addToInputText("7")
        }

        val button_8: Button = findViewById(R.id.button_8)
        button_8.setOnClickListener{
            input.text = addToInputText("8")
        }

        val button_9: Button = findViewById(R.id.button_9)
        button_9.setOnClickListener{
            input.text = addToInputText("9")
        }

        val button_dot: Button = findViewById(R.id.button_dot)
        button_dot.setOnClickListener{
            input.text = addToInputText(".")
        }

        val button_division: Button = findViewById(R.id.button_division)
        button_division.setOnClickListener{
            input.text = addToInputText("/")
        }

        val button_multiply: Button = findViewById(R.id.button_multiply)
        button_multiply.setOnClickListener{
            input.text = addToInputText("*")
        }

        val button_subtraction: Button = findViewById(R.id.button_subtraction)
        button_subtraction.setOnClickListener{
            input.text = addToInputText("-")
        }

        val button_addition: Button = findViewById(R.id.button_addition)
        button_addition.setOnClickListener{
            input.text = addToInputText("+")
        }

        val button_equals: Button = findViewById(R.id.button_equals)
        button_equals.setOnClickListener{
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${input.text}$buttonValue"
    }

    private fun showResult() {
        try {
            val expression = input.text.toString()
            val operators = listOf('+', '-', '*', '/')
            var operator: Char? = null

            // Find the operator
            for (op in operators) {
                if (expression.contains(op)) {
                    operator = op
                    break
                }
            }

            // If no operator found, show error
            if (operator == null) {
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
                return
            }

            // Split the expression into left and right parts
            val parts = expression.split(operator)
            if (parts.size != 2) {
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
                return
            }

            val leftValue = parts[0].toDoubleOrNull()
            val rightValue = parts[1].toDoubleOrNull()

            // If either left or right value is not a valid number, show error
            if (leftValue == null || rightValue == null) {
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
                return
            }

            // Calculate the result based on the operator
            val result = when (operator) {
                '+' -> leftValue + rightValue
                '-' -> leftValue - rightValue
                '*' -> leftValue * rightValue
                '/' -> {
                    if (rightValue == 0.0) {
                        output.text = "Error" // Division by zero
                        output.setTextColor(ContextCompat.getColor(this, R.color.red))
                        return
                    }
                    leftValue / rightValue
                }
                else -> throw IllegalArgumentException("Unknown operator: $operator")
            }

            // Display the result
            output.text = result.toString()
            output.setTextColor(ContextCompat.getColor(this, R.color.green))
        } catch (e: Exception) {
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this, R.color.red))

        }
    }
}