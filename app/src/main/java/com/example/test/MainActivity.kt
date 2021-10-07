package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var calc: Calc = Calc() // создаем экземпляр класса Calc
    var rating: Int = 0 // оценка

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generate() //вызываем функцию generate
        result.setOnClickListener { result() } // обрабатывем клик на кнопку Результат
    }

    private fun generate() { // функция которая генерирует примеры
        for (i in 0..4) {  // проверяем знак, если деление, то подгоняем первое число под второе, чтобы ответ был целочисленным и в промежутке от -10 до 10
            if (calc.oper[i] == "/")
                calc.a[i] = calc.b[i]?.times(Random.nextInt(-10, 10))
        }
        tv1.text = "1) (${calc.a[0].toString()}) ${calc.oper[0]} (${calc.b[0]})" //выводим на экран примеры
        tv2.text = "2) (${calc.a[1].toString()}) ${calc.oper[1]} (${calc.b[1]})"
        tv3.text = "3) (${calc.a[2].toString()}) ${calc.oper[2]} (${calc.b[2]})"
        tv4.text = "4) (${calc.a[3].toString()}) ${calc.oper[3]} (${calc.b[3]})"
        tv5.text = "5) (${calc.a[4].toString()}) ${calc.oper[4]} (${calc.b[4]})"
    }


    private fun result() { // функция, которая подсчитывает оценку
        rating = 0 // обнуляем оценку при каждом вызове
        val d = arrayOfNulls<String>(5)   // массив правильных ответов
        for (i in 0..4) {
            d[i] = calc.result(calc.a[i], calc.b[i], calc.oper[i]).toString()  // заполняем массив
        }
        if (et1.text.toString() == d[0])  rating++  //считываем данные с editText, проверяем правильность и подсчитываем оценку
        if (et2.text.toString() == d[1])  rating++
        if (et3.text.toString() == d[2])  rating++
        if (et4.text.toString() == d[3])  rating++
        if (et5.text.toString() == d[4])  rating++
        tv_result.text = "Оценка: $rating"  //выводим оценку на экран
    }

}