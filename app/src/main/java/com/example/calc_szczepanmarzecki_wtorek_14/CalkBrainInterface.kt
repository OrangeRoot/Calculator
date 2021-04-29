package com.example.calc_szczepanmarzecki_wtorek_14

interface CalkBrainInterface {

    public fun addNumber(number: String)

    public fun addOperation(operation: String)

    public fun evaluate():Double

    public fun whatNext():Int

    public fun sqrtEvaluate(value:String):String

    public fun percentEvaluate(value:String):String

}