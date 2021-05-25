package com.s16665.bodymicalc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class BmiFragment : Fragment() {
    private var WeightInput: EditText? = null
    private var HeightInput: EditText? = null
    private var AgeInput: EditText? = null
    private var ResultValue: TextView? = null
    private var WeightInputError: TextView? = null
    private var HeightInputError: TextView? = null
    private var AgeInputError: TextView? = null
    private var GenderInputError: TextView? = null
    private var DailyKcal: TextView? = null
    private var BmiStage: TextView? = null
    private var ResultSection: LinearLayout? = null
    private var BmiCalcButtonParent: LinearLayout? = null
    private var ScrollViewY: ScrollView? = null
    private var GenderRadioGroup: RadioGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.bmi_fragment, container, false)
        val calculate = v.findViewById<Button?>(R.id.bmiCalcButton)
        ScrollViewY = v.findViewById<ScrollView?>(R.id.scrollView)
        WeightInput = v.findViewById<EditText?>(R.id.weightInput)
        WeightInputError = v.findViewById<TextView?>(R.id.inputWeightError)
        HeightInput = v.findViewById<EditText?>(R.id.heightInput)
        HeightInputError = v.findViewById<TextView?>(R.id.inputHeightError)
        AgeInput = v.findViewById<EditText?>(R.id.ageInput)
        AgeInputError = v.findViewById<TextView?>(R.id.inputAgeError)
        GenderRadioGroup = v.findViewById<RadioGroup?>(R.id.genderInput)
        GenderInputError = v.findViewById<TextView?>(R.id.inputGenderError)
        BmiCalcButtonParent = v.findViewById<LinearLayout?>(R.id.bmiCalcButtonParent)
        ResultValue = v.findViewById<TextView?>(R.id.bmiResultValue)
        BmiStage = v.findViewById<TextView?>(R.id.bmiStage)
        DailyKcal = v.findViewById<TextView?>(R.id.dailyKcal)
        ResultSection = v.findViewById<LinearLayout?>(R.id.resultSection)


        calculate!!.setOnClickListener(View.OnClickListener {
            val valueW: String = WeightInput?.getText()?.toString()!!.trim { it <= ' ' }
            val valueH: String = HeightInput?.getText()?.toString()!!.trim { it <= ' ' }
            val valueA: String = AgeInput?.getText()?.toString()!!.trim { it <= ' ' }
            val valueG = GenderRadioGroup?.getCheckedRadioButtonId()
            if (valueW.length == 0) {
                WeightInputError?.setVisibility(View.VISIBLE)
            } else {
                WeightInputError?.setVisibility(View.INVISIBLE)
            }
            if (valueH.length == 0) {
                HeightInputError?.setVisibility(View.VISIBLE)
            } else {
                HeightInputError?.setVisibility(View.INVISIBLE)
            }
            if (valueA.length == 0) {
                AgeInputError?.setVisibility(View.VISIBLE)
            } else {
                AgeInputError?.setVisibility(View.INVISIBLE)
            }
            if (valueG == -1) {
                GenderInputError?.setVisibility(View.VISIBLE)
            } else {
                GenderInputError?.setVisibility(View.INVISIBLE)
            }
            if (valueW.length == 0 || valueH.length == 0 || valueA.length == 0 || valueG == -1) {
                ResultSection?.setVisibility(View.GONE)
                ResultValue?.setText("-")
                BmiCalcButtonParent?.setPadding(0, 10, 0, 300)
            } else {
                WeightInputError?.setVisibility(View.GONE)
                HeightInputError?.setVisibility(View.GONE)
                AgeInputError?.setVisibility(View.GONE)
                GenderInputError?.setVisibility(View.GONE)
                BmiCalcButtonParent?.setPadding(0, 10, 0, 10)
                val stagePreLabel = "Kategoria: "
                val W: Float = valueW.toFloat()
                val H: Float = valueH.toFloat()
                val newH = H / 100
                val bmi = W / (newH * newH)
                ResultValue?.setText("BMI: " + String.format("%.2f", bmi))
                if (bmi < 16) {
                    BmiStage?.setText(stagePreLabel + "wygłodzenie")
                } else if (bmi >= 16 && bmi < 17) {
                    BmiStage?.setText(stagePreLabel + "wychudzenie")
                } else if (bmi >= 17 && bmi < 18.5) {
                    BmiStage?.setText(stagePreLabel + "niedowaga")
                } else if (bmi >= 18.5 && bmi < 25) {
                    BmiStage?.setText(stagePreLabel + "pożądana masa ciała")
                } else if (bmi >= 25 && bmi < 30) {
                    BmiStage?.setText(stagePreLabel + "nadwaga")
                } else if (bmi >= 30 && bmi < 35) {
                    BmiStage?.setText(stagePreLabel + "otyłość I st.")
                } else if (bmi >= 35 && bmi < 40) {
                    BmiStage?.setText(stagePreLabel + "otyłość II st.")
                } else if (bmi >= 40) {
                    BmiStage?.setText(stagePreLabel + "otyłość III st.")
                }
                val A: Float = valueA.toFloat()
                var kcalNeeded = 0f
                if (valueG == R.id.genderF) {
                    kcalNeeded = 655.1f + 9.563f * W + 1.85f * H - 4.676f * A
                } else if (valueG == R.id.genderM) {
                    kcalNeeded = 66.5f + 13.75f * W + 5.003f * H - 6.775f * A
                }
                DailyKcal?.setText("kcal: " + String.format("%.2f", kcalNeeded))
                ResultSection?.setVisibility(View.VISIBLE)
                ScrollViewY?.post(Runnable { ScrollViewY?.scrollTo(0, ScrollViewY!!.getBottom()) })
            }

//                wygłodzenie	                do 16,0	niedowaga
//                wychudzenie	            16,0–16,99
//                niedowaga	                17,0–18,49
//                pożądana masa ciała	    18,5–24,99	optimum
//                nadwaga	                25,0–29,99	nadwaga
//                otyłość I stopnia	        30,0–34,99	otyłość
//                otyłość II stopnia (duża)	35,0–39,99
//                otyłość III st.(chorobliwa)	od 40,0

//                Very severely underweight		    15		        0.60
//                Severely underweight	            15	    16	    0.60	0.64
//                Underweight	                    16	    18.5	0.64	0.74
//                Normal (healthy weight)	        18.5    25	    0.74	1.0
//                Overweight	                    25  	30  	1.0	    1.2
//                Obese Class I (Moderately obese)	30	    35	    1.2	    1.4
//                Obese Class II (Severely obese)	35	    40	    1.4	    1.6
//                Obese Class III (Very sev obese)	40		        1.6

//                PPM (mężczyźni) = SWE (spoczynkowy wydatek energetyczny kcal)
//                = 66,5 + (13,75 x masa ciała [kg]) + (5,003 x wzrost [cm]) – (6,775 x [wiek])
//                PPM (kobiety) = SWE (spoczynkowy wydatek energetyczny kcal)
//                = 655,1 + (9,563 x masa ciała [kg]) + (1,85 x wzrost [cm]) – (4,676 x [wiek])
        })
        return v
    }
}