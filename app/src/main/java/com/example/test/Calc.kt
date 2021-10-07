package com.example.test

import kotlin.random.Random

class Calc {
    var a= arrayOfNulls<Int>(5)
    var b= arrayOfNulls<Int>(5)
    var oper:Array<String> = arrayOf("+","-","*","/","+")

    init {
        for (i in 0..4) {
            a[i] = Random.nextInt(-99, 99)
            b[i] = Random.nextInt(-99,99)
            oper.shuffle()
        }
    }

}