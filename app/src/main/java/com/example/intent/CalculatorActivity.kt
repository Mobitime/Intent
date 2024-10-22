package com.example.intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculatorActivity : AppCompatActivity() {
    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private var result: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById(R.id.etNumber2)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnSubtract : Button = findViewById(R.id.btnSubtract)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnSendResult: Button = findViewById(R.id.btnSendResult)

        btnAdd.setOnClickListener { performOperation("+")}
        btnSubtract.setOnClickListener { performOperation("-")}
        btnMultiply.setOnClickListener { performOperation("*")}
        btnDivide.setOnClickListener { performOperation("/")}

        btnSendResult.setOnClickListener{
            val intent = Intent()
            intent.putExtra("result", result.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        }
    private fun performOperation(operation: String){
        val number1 = etNumber1.text.toString().toDoubleOrNull()
        val number2 = etNumber2.text.toString().toDoubleOrNull()

        if (number1 == null || number2 == null){
            Toast.makeText(this, "Введите корректные числа", Toast.LENGTH_LONG).show()
            return
        }
        result = when (operation){
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> {
                if (number2 == 0.0){
                    Toast.makeText(this, "Деление на ноль невозможно", Toast.LENGTH_LONG).show()
                    return
                }
                number1 / number2
            }
            else -> 0.0
        }
        Toast.makeText(this, "Результат: $result", Toast.LENGTH_LONG).show()
    }


}
