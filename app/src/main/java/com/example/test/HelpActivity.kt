package com.example.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {

    companion object {
        const val Key: String = "KEY" // значение ключа по которому передаем данные
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        btn_helpYes.setOnClickListener { // обрабатываем клик на кнопку Да
            val res: Int = intent.getIntExtra(Key,0)  // получаем переданное значение из другой активити
            textHelp.text = "В ответе есть цифра ${Math.abs(res) % 10}"  // выводим на экран последнюю цифру ответа
            setResult(RESULT_OK)
            btnBack.visibility = View.VISIBLE  //  показываем кнопку Вернуться
            btn_helpNo.visibility = View.INVISIBLE // и убираем кнопку Нет
        }

        btnBack.setOnClickListener { finish() } // обрабатываем клик на кнопку Вернуться

        btn_helpNo.setOnClickListener { // обрабатываем клик на кнопку Нет
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}