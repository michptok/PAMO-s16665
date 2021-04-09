package com.s16665.bodymicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText WeightInput, HeightInput, AgeInput;
    private TextView ResultValue, WeightInputError, HeightInputError, AgeInputError, GenderInputError, DailyKcal, BmiStage;
    private LinearLayout ResultSection, BmiCalcButtonParent;
    private ScrollView ScrollViewY;
    private RadioGroup GenderRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculate = findViewById(R.id.bmiCalcButton);

        ScrollViewY = findViewById(R.id.scrollView);

        WeightInput = findViewById(R.id.weightInput);
        WeightInputError = findViewById(R.id.inputWeightError);
        HeightInput = findViewById(R.id.heightInput);
        HeightInputError = findViewById(R.id.inputHeightError);
        AgeInput = findViewById(R.id.ageInput);
        AgeInputError = findViewById(R.id.inputAgeError);

        GenderRadioGroup = findViewById(R.id.genderInput);
        GenderInputError = findViewById(R.id.inputGenderError);

        BmiCalcButtonParent = findViewById(R.id.bmiCalcButtonParent);
        ResultValue = findViewById(R.id.bmiResultValue);
        BmiStage = findViewById(R.id.bmiStage);
        DailyKcal = findViewById(R.id.dailyKcal);
        ResultSection = findViewById(R.id.resultSection);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueW = WeightInput.getText().toString().trim();
                String valueH = HeightInput.getText().toString().trim();
                String valueA = AgeInput.getText().toString().trim();
                int valueG = GenderRadioGroup.getCheckedRadioButtonId();

                if (valueW.length() == 0) {
                    WeightInputError.setVisibility(View.VISIBLE);
                } else {
                    WeightInputError.setVisibility(View.INVISIBLE);
                }

                if (valueH.length() == 0) {
                    HeightInputError.setVisibility(View.VISIBLE);
                } else {
                    HeightInputError.setVisibility(View.INVISIBLE);
                }

                if (valueA.length() == 0) {
                    AgeInputError.setVisibility(View.VISIBLE);
                } else {
                    AgeInputError.setVisibility(View.INVISIBLE);
                }

                if (valueG == -1) {
                    GenderInputError.setVisibility(View.VISIBLE);
                } else {
                    GenderInputError.setVisibility(View.INVISIBLE);
                }

                if (valueW.length() == 0 || valueH.length() == 0 || valueA.length() == 0 || valueG == -1) {
                    ResultSection.setVisibility(View.GONE);
                    ResultValue.setText("-");
                    BmiCalcButtonParent.setPadding(0,10,0,300);


                } else {
                    WeightInputError.setVisibility(View.GONE);
                    HeightInputError.setVisibility(View.GONE);
                    AgeInputError.setVisibility(View.GONE);
                    GenderInputError.setVisibility(View.GONE);
                    BmiCalcButtonParent.setPadding(0,10,0,10);


                    String stagePreLabel = "Kategoria: ";
                    float W = Float.parseFloat(valueW);
                    float H = Float.parseFloat(valueH);
                    float newH = H / 100;
                    float bmi = W / (newH * newH);
                    ResultValue.setText("BMI: " + String.format("%.2f", bmi));

                    if (bmi < 16) {
                        BmiStage.setText(stagePreLabel + "wygłodzenie");

                    } else if (bmi >= 16 && bmi < 17) {
                        BmiStage.setText(stagePreLabel + "wychudzenie");

                    } else if (bmi >= 17 && bmi < 18.5) {
                        BmiStage.setText(stagePreLabel + "niedowaga");

                    } else if (bmi >= 18.5 && bmi < 25) {
                        BmiStage.setText(stagePreLabel + "pożądana masa ciała");

                    } else if (bmi >= 25 && bmi < 30) {
                        BmiStage.setText(stagePreLabel + "nadwaga");

                    } else if (bmi >= 30 && bmi < 35) {
                        BmiStage.setText(stagePreLabel + "otyłość I st.");

                    } else if (bmi >= 35 && bmi < 40) {
                        BmiStage.setText(stagePreLabel + "otyłość II st.");

                    } else if (bmi >= 40) {
                        BmiStage.setText(stagePreLabel + "otyłość III st.");
                    }


                    float A = Float.parseFloat(valueA);
                    float kcalNeeded = 0;

                    if (valueG == R.id.genderF) {
                        kcalNeeded = 655.1f + (9.563f * W) + (1.85f * W) - (4.676f * A);
                    } else if (valueG == R.id.genderM) {
                            kcalNeeded = 66.5f + (13.75f * W) + (5.003f * W) - (6.775f * A);
                    }


                    DailyKcal.setText("kcal: " + String.format("%.2f", kcalNeeded));

                    ResultSection.setVisibility(View.VISIBLE);
                    ScrollViewY.post(new Runnable() {
                        @Override
                        public void run() {
                            ScrollViewY.scrollTo(0, ScrollViewY.getBottom());
                        }
                    });


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
            }
        });
    }
}