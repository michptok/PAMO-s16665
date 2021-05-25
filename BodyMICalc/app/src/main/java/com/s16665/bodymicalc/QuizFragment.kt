package com.s16665.bodymicalc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class QuizFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.quiz_fragment, container, false)
        val checkResult = v.findViewById<Button?>(R.id.quizResultButton)
        val a1 = v.findViewById<RadioButton?>(R.id.a_1_true)
        val a2 = v.findViewById<RadioButton?>(R.id.a_2_false)
        val a3 = v.findViewById<RadioButton?>(R.id.a_3_false)
        val a4 = v.findViewById<RadioButton?>(R.id.a_4_false)
        val a5 = v.findViewById<RadioButton?>(R.id.a_5_false)
        val a6 = v.findViewById<RadioButton?>(R.id.a_6_false)
        val g1 = v.findViewById<RadioGroup?>(R.id.q_1_group)
        val g2 = v.findViewById<RadioGroup?>(R.id.q_2_group)
        val g3 = v.findViewById<RadioGroup?>(R.id.q_3_group)
        val g4 = v.findViewById<RadioGroup?>(R.id.q_4_group)
        val g5 = v.findViewById<RadioGroup?>(R.id.q_5_group)
        val g6 = v.findViewById<RadioGroup?>(R.id.q_6_group)
        val resultValue = v.findViewById<TextView?>(R.id.quizResultValue)
        val resultSection = v.findViewById<LinearLayout?>(R.id.resultSection)
        checkResult?.setOnClickListener(View.OnClickListener {
            if (g1?.getCheckedRadioButtonId() == -1) {
                return@OnClickListener
            }
            if (g2?.getCheckedRadioButtonId() == -1) {
                return@OnClickListener
            }
            if (g3?.getCheckedRadioButtonId() == -1) {
                return@OnClickListener
            }
            if (g4?.getCheckedRadioButtonId() == -1) {
                return@OnClickListener
            }
            if (g5?.getCheckedRadioButtonId() == -1) {
                return@OnClickListener
            }
            if (g6?.getCheckedRadioButtonId() == -1) {
                return@OnClickListener
            }
            var result = 0
            if (a1!!.isChecked()) {
                result++
            }
            if (a2!!.isChecked()) {
                result++
            }
            if (a3!!.isChecked()) {
                result++
            }
            if (a4!!.isChecked()) {
                result++
            }
            if (a5!!.isChecked()) {
                result++
            }
            if (a6!!.isChecked()) {
                result++
            }
            if (result == 6) {
                resultValue?.setText("Wszystkie " + String.format("%d", result) + "! Gratulacje!")
            } else {
                resultValue?.setText(String.format("%d", result) + "/6")
                resultValue?.setText(java.lang.String.format("%d", result) + "/6")

            }
            resultSection?.setVisibility(View.VISIBLE)
        })
        return v
    }
}