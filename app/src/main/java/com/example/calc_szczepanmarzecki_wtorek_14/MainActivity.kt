package com.example.calc_szczepanmarzecki_wtorek_14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

// Szczepan Marzecki - Wszyskie podpunkty poza 5 (oraz 7 ale ten wyklucza podpunkt 9 który mam)

class MainActivity : AppCompatActivity() {
    private lateinit var window: TextView
    private lateinit var clearButton: Button
    private lateinit var eqButton: Button
    private lateinit var dotButton: Button
    private lateinit var percentButton: Button
    private lateinit var sqrButton: Button
    private lateinit var numbers: Array<Button>
    private lateinit var operations: Array<Button>
    private var lastNumeric = false
    private var lastOperation = false
    private var dotUsed = false
    private var appStarted = false
    private val CalkulatorBrain: CalkBrainInterface = CalkBrain()
    private var windowText = ""
    set(value) {
            setDisplay(value)
        field = value
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window = findViewById(R.id.windowId)
        dotButton = findViewById(R.id.dotId)
        eqButton = findViewById(R.id.operationEqId)
        clearButton = findViewById(R.id.clearId)
        percentButton = findViewById(R.id.percentId)
        sqrButton = findViewById(R.id.sqrId)

        numbers = arrayOf(
                findViewById(R.id.number0Id),
                findViewById(R.id.number1Id),
                findViewById(R.id.number2Id),
                findViewById(R.id.number3Id),
                findViewById(R.id.number4Id),
                findViewById(R.id.number5Id),
                findViewById(R.id.number6Id),
                findViewById(R.id.number7Id),
                findViewById(R.id.number8Id),
                findViewById(R.id.number9Id))

        operations = arrayOf(
                findViewById(R.id.operationAddId),
                findViewById(R.id.operationSubId),
                findViewById(R.id.operationMultiId),
                findViewById(R.id.operationDivId))

        percentButton.setOnClickListener{operationPercent()}
        sqrButton.setOnClickListener{operationSqr()}
        dotButton.setOnClickListener{addDot()}
        eqButton.setOnClickListener{operationEq()}
        clearButton.setOnClickListener{clear()}
        numbers.forEach { number -> number.setOnClickListener{someNumber -> numberPressed(someNumber as Button)} }
        operations.forEach { operation -> operation.setOnClickListener{someOperation -> operationPressed(someOperation as Button)} }


    }

    private fun operationSqr() {
        if(windowText!=""){
            windowText = CalkulatorBrain.sqrtEvaluate(windowText)
        }
    }

    private fun operationPercent() {
        if (windowText != "") {
            windowText = CalkulatorBrain.percentEvaluate(windowText)
        }
    }
    private fun setDisplay(value:String){
        window.text = value
    }
    private fun addDot() {
        if(!dotUsed and lastNumeric and  !windowText.contains(".")) {
            windowText += "."
            dotUsed = true
        }
    }

    private fun operationEq() {

        if (lastNumeric) {
            if (windowText != "") {
                CalkulatorBrain.addNumber(windowText)
            }
        }

        windowText = CalkulatorBrain.evaluate().toString()
        lastNumeric = true
        lastOperation = false
    }

    private fun clear() {
        if (windowText.isNotEmpty()) {
            if (windowText.last() == '.') {
                dotUsed = false
            }
            windowText = windowText.dropLast(1)
        }
    }


    private fun operationPressed(operation: Button) {
        if(windowText.isNotEmpty()){
            if (!lastOperation) {
                lastNumeric = false
                lastOperation = true
                dotUsed = false
                CalkulatorBrain.addNumber(windowText)
                windowText = operation.text as String
            } else {
                windowText = operation.text as String
            }
        }
    }


    private fun numberPressed(number: Button) {
        println("number")
        appStarted = true
        if (lastOperation) {
            lastOperation = false
            CalkulatorBrain.addOperation(windowText)
            windowText = ""

        }
        lastNumeric = true
        windowText += number.text
    }
}
