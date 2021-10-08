package com.example.test

import kotlin.random.Random

class Calc {
    var a= arrayOfNulls<Int>(5) // массив первых чисел примеров
    var b= arrayOfNulls<Int>(5) // массив вторых чисел примеров
    var kf:Array<Double> = arrayOf(1.0,1.0,1.0,1.0,1.0) //массив баллов, который показывает пользовались ли подсказкой
    var oper:Array<String> = arrayOf("+","-","*","/","+") //массив математ. операций

    init {
        for (i in 0..4) { // заполняем массивы рандомными числами от -99 до 99
            a[i] = Random.nextInt(-99, 99)
            b[i] = Random.nextInt(-99,99)
            oper.shuffle() // перемешиваем мат. операции
        }
    }

    fun result(a:Int?, b: Int?, str:String): Int { // функция для возвращения ответа примера
        when (str) {
            "+" -> if (a != null && b != null) { // проверяем значения чтобы они были не null
                return a+ b                      // возвращаем результат и так со всеми операциями
            }
            "-" -> if (a != null && b != null ) {
                return a-b
            }
            "*" -> if (a != null && b != null) {
                return a*b
            }
            "/" -> if (a != null && b != null) {
                return a/b
            }
        }
        return 0
    }

}