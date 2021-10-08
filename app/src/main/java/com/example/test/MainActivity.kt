package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var calc: Calc = Calc() // создаем экземпляр класса Calc
    var rating: Double = 0.0 // оценка

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generate() //вызываем функцию generate
        result.setOnClickListener { result() } // обрабатывем клик на кнопку Результат
        btnhelp1.setOnClickListener(this)  // обрабатываем клики
        btnhelp2.setOnClickListener(this)  // на кнопки с подсказками
        btnhelp3.setOnClickListener(this)
        btnhelp4.setOnClickListener(this)
        btnhelp5.setOnClickListener(this)
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


    override fun onClick(v: View?) {
        val intentHelp = Intent(this, HelpActivity::class.java)
        when (v) {
            btnhelp1 -> {
                intentHelp.putExtra(HelpActivity.Key, calc.result(calc.a[0], calc.b[0], calc.oper[0]))   // если была нажата определенная кнопка
                startActivityForResult(intentHelp, 1)                                        // передаем ответ нужного примера в другое активити
                btnhelp1.visibility = View.INVISIBLE                                                     // и убираем кнопку с экрана и так со всеми кнопками подсказок
            }
            btnhelp2 -> {
                intentHelp.putExtra(HelpActivity.Key, calc.result(calc.a[1], calc.b[1], calc.oper[1]))
                startActivityForResult(intentHelp, 2)
                btnhelp2.visibility = View.INVISIBLE
            }
            btnhelp3 -> {
                intentHelp.putExtra(HelpActivity.Key, calc.result(calc.a[2], calc.b[2], calc.oper[2]))
                startActivityForResult(intentHelp, 3)
                btnhelp3.visibility = View.INVISIBLE
            }
            btnhelp4 -> {
                intentHelp.putExtra(HelpActivity.Key, calc.result(calc.a[3], calc.b[3], calc.oper[3]))
                startActivityForResult(intentHelp, 4)
                btnhelp4.visibility = View.INVISIBLE
            }
            btnhelp5 -> {
                intentHelp.putExtra(HelpActivity.Key, calc.result(calc.a[4], calc.b[4], calc.oper[4]))
                startActivityForResult(intentHelp,5)
                btnhelp5.visibility = View.INVISIBLE
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> calc.kf[0] = if (resultCode == RESULT_CANCELED) 1.0 else 0.5   //заполняем массив, исходя из того
            2 -> calc.kf[1] = if (resultCode == RESULT_CANCELED) 1.0 else 0.5   // была ли использована подсказка
            3 -> calc.kf[2] = if (resultCode == RESULT_CANCELED) 1.0 else 0.5
            4 -> calc.kf[3] = if (resultCode == RESULT_CANCELED) 1.0 else 0.5
            5 -> calc.kf[4] = if (resultCode == RESULT_CANCELED) 1.0 else 0.5
        }
    }

    private fun result() { // функция, которая подсчитывает оценку
        rating = 0.0 // обнуляем оценку при каждом вызове
        val d = arrayOfNulls<String>(5)   // массив правильных ответов
        for (i in 0..4) {
            d[i] = calc.result(calc.a[i], calc.b[i], calc.oper[i]).toString()  // заполняем массив
        }
        if (et1.text.toString() == d[0]) rating += calc.kf[0]  //считываем данные с editText, проверяем правильность и подсчитываем оценку
        if (et2.text.toString() == d[1]) rating += calc.kf[1]
        if (et3.text.toString() == d[2]) rating += calc.kf[2]
        if (et4.text.toString() == d[3]) rating += calc.kf[3]
        if (et5.text.toString() == d[4]) rating += calc.kf[4]
        tv_result.text = "Оценка: $rating"  //выводим оценку на экран
    }


}