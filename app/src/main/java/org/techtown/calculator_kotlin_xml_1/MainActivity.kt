package org.techtown.calculator_kotlin_xml_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    // 마지막에 숫자인지
    var lastNumeric: Boolean = false
    // 마지막에 소수인지
    var lastDot: Boolean = false

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

        lastNumeric = true // 마지막이 숫자
        lastDot = false
    }
    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view : View) {
        // 일단 =를 눌렀을때 마지막이 숫자인지 확인하기!
        if(lastNumeric) {
            //text는 CharSequence여서 String과는 차이가 좀 있다
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    // -99면 99로 변환시킴!
                    tvValue = tvValue.substring(1)
                }
                //
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]// 99 -> -99
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                else if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]// 99 -> -99
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("X")) {
                    var splitValue = tvValue.split("X")
                    var one = splitValue[0]// 99 -> -99
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]// 99 -> -99
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                // 산술 상의 에러 - logcat에서 보이게 함
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value: String) : Boolean {
        // -로 시작하면 안에 코드 실행
        // 맨앞에 -이면 그건 제외하고 나머지때 연산자가 있는가
        return if(value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onDecimalPoint(view: View) {
        // 마지막 정보값이 점이면 다시 찍을 필요가 없으므로 그것만 확인하기!
        if(lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false // 이제 마지막이 소수임!
            lastDot = true
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if(value.contains(".0")) { // 33.0 99.0
            value = result.substring(0, result.length - 2)
        }
        return value
    }
}