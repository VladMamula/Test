package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var calc: Calc = Calc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generate()
    }

    private fun generate() {
        for (i in 0..4) {
            if (calc.oper[i] == "/")
                calc.a[i] = calc.b[i]?.times(Random.nextInt(-10, 10))
        }
            tv1.text = "1) (${calc.a[0].toString()}) ${calc.oper[0]} (${calc.b[0]})"
            tv2.text = "2) (${calc.a[1].toString()}) ${calc.oper[1]} (${calc.b[1]})"
            tv3.text = "3) (${calc.a[2].toString()}) ${calc.oper[2]} (${calc.b[2]})"
            tv4.text = "4) (${calc.a[3].toString()}) ${calc.oper[3]} (${calc.b[3]})"
            tv5.text = "5) (${calc.a[4].toString()}) ${calc.oper[4]} (${calc.b[4]})"
    }


}