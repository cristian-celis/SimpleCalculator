package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var typeOperation = ""
    private var totalNumbers = ""
    private var listButtons = arrayListOf<Button>()
    private var newNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ctrl + shift + v -> ver el historial de copiado

        textView = findViewById(R.id.numbers)

        loadButtons()
        loadNumberInTextView()
    }

    private fun loadButtons() {
        val listId = listOf(
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine,
            R.id.zero,
            R.id.point,
            R.id.addBtt,
            R.id.subsBtt,
            R.id.multiBtt,
            R.id.divideBtt,
            R.id.equalsBtt
        )
        findViewById<Button>(R.id.trashBtt).setOnClickListener{
            clear()
        }

        getButtonsById(listId)
    }

    private fun getButtonsById(buttonIdList: List<Int>){
        for (i in buttonIdList) {
            listButtons.add(getButtonById(i))
        }
    }

    private fun getButtonById(buttonId: Int): Button {
        return findViewById(buttonId)
    }

    private fun loadNumberInTextView() {
        for (i in listButtons) {
            i.setOnClickListener {
                addToTextView(i.text.toString())
            }
        }
    }

    private var numberToOperate: ArrayList<Int> = ArrayList()

    fun addToTextView(number: String) {
        totalNumbers += number
        textView.text = totalNumbers
        val numeric = number[0]
        if(numeric.isDigit()){
            newNumber += number
        }else{
            if (numeric == '='){
                numberToOperate.add(newNumber.toInt())
                calculate()
            }
            else if(!numeric.isDigit()){
                numberToOperate.add(newNumber.toInt())
                typeOperation = numeric + ""
            }
            newNumber = ""
        }
    }

    fun calculate() {
        var result = 0
        if (numberToOperate.size > 0) {
            val number1 = numberToOperate[0]
            val number2 = numberToOperate[1]

            when (typeOperation) {
                "+" -> result = number1 + number2
                "-" -> result = number1 - number2
                "*" -> result = number1 * number2
                "/" -> result = number1 / number2
            }
            totalNumbers += result
        } else {
            Toast.makeText(this, "You must put an operation", Toast.LENGTH_SHORT).show()
            totalNumbers = ""
        }
        textView.text = totalNumbers
    }

    fun clear(){
        typeOperation = ""
        numberToOperate.clear()
        totalNumbers = ""
        textView.text = totalNumbers
    }
}