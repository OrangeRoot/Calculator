package com.example.calc_szczepanmarzecki_wtorek_14

import android.widget.Button
import kotlin.math.sqrt

class CalkBrain : CalkBrainInterface{
    var numbers = mutableListOf<Double>()
    var operations = mutableListOf<String>()
    val order:Array<String> = arrayOf("x","÷","+","-",)
    override fun addNumber(number: String){
        val numberDou = number.toDouble()
        numbers.add(0,numberDou)
        println(numberDou)
    }

    override fun addOperation(operation: String){
        operations.add(0,operation)
        println(operation)
    }

    override fun evaluate(): Double {
        if(numbers.isEmpty()){
            return 0.0
        }
        println(operations)
        println(numbers)
        var result:Double=0.0
        while (operations.isNotEmpty()) {
            var index = whatNext()
            when (operations[index]) {
                "+" -> numbers[index] = numbers[index+1] + numbers[index]
                "-" -> numbers[index] = numbers[index+1] - numbers[index]
                "x" -> numbers[index] = numbers[index+1] * numbers[index]
                "÷" -> numbers[index] = numbers[index+1] / numbers[index]
                else -> {println("nicniepasuje")}
            }
            operations.removeAt(index)
            numbers.removeAt(index+1)
        }
        result = numbers[0]
        numbers.removeAt(0)
        return result
    }
    override fun whatNext(): Int {
        for (sign in order) {
            if( operations.indexOf(sign)!=-1){
                return operations.indexOf(sign)
            }

        }
        return -1
    }

    override fun sqrtEvaluate(value:String):String{
        return (sqrt(value.toDouble())).toString()
    }

    override fun percentEvaluate(value:String):String{
        return (100*value.toDouble()).toString()
    }




}