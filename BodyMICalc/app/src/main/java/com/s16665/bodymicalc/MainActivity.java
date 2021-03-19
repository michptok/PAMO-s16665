package com.s16665.bodymicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button Calculate;
    private EditText WeightInput, HeightInput;
    private TextView ResultValue, WeightInputError, HeightInputError, BmiStage;
    private LinearLayout ResultSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calculate = findViewById(R.id.bmiCalcButton);
        WeightInput = findViewById(R.id.weightInput);
        WeightInputError = findViewById(R.id.inputWeightError);
        HeightInput = findViewById(R.id.heightInput);
        HeightInputError = findViewById(R.id.inputHeightError);
        ResultSection = findViewById(R.id.resultSection);
        ResultValue = findViewById(R.id.bmiResultValue);
        BmiStage = findViewById(R.id.bmiStage);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueW = WeightInput.getText().toString().trim();
                String valueH = HeightInput.getText().toString().trim();

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

                if (valueW.length() == 0 || valueH.length() == 0) {
                    ResultSection.setVisibility(View.GONE);
                    ResultValue.setText("-");

                } else {
                    WeightInputError.setVisibility(View.GONE);
                    HeightInputError.setVisibility(View.GONE);
                    float W = Float.parseFloat(valueW);
                    float H = Float.parseFloat(valueH);
                    float newH = H / 100;
                    float bmi = W / (newH * newH);
                    ResultValue.setText(String.format("%.2f", bmi));
                    ResultSection.setVisibility(View.VISIBLE);

                    if (bmi < 16) {
                        BmiStage.setText("wygłodzenie");

                    } else if (bmi >= 16 && bmi < 17) {
                        BmiStage.setText("wychudzenie");

                    } else if (bmi >= 17 && bmi < 18.5) {
                        BmiStage.setText("niedowaga");

                    } else if (bmi >= 18.5 && bmi < 25) {
                        BmiStage.setText("pożądana masa ciała");

                    } else if (bmi >= 25 && bmi < 30) {
                        BmiStage.setText("nadwaga");

                    } else if (bmi >= 30 && bmi < 35) {
                        BmiStage.setText("otyłość I st.");

                    } else if (bmi >= 35 && bmi < 40) {
                        BmiStage.setText("otyłość II st.");

                    } else if (bmi >= 40) {
                        BmiStage.setText("otyłość III st.");

                    }

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
            }
        });
    }
}