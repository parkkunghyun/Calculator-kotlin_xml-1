package org.techtown.calculator_kotlin_xml_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    // append("1") 하면 nullable이어서 물음ㅍ를 추가해애ㅑ함!
    // ? 를 쓰면 널이면 실행 안되고 널 아니면 실행됨
    // 그리고 계속 뒤에 들어감!
    fun onDigit(view: View) {
        //Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text) // onClick으로 버튼 아이디 텍스트 등 모든 속성전부가 들어옴!
        // view에는 텍스트 속성이 없다! 그래서 정확하게 명시하기

    }
    fun onClear(view: View) {
        tvInput?.text = ""
    }

}