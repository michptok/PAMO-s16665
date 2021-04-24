package com.s16665.bodymicalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quiz_fragment, container, false);
        Button checkResult = v.findViewById(R.id.quizResultButton);
        RadioButton a1 = v.findViewById(R.id.a_1_true);
        RadioButton a2 = v.findViewById(R.id.a_2_false);
        RadioButton a3 = v.findViewById(R.id.a_3_false);
        RadioButton a4 = v.findViewById(R.id.a_4_false);
        RadioButton a5 = v.findViewById(R.id.a_5_false);
        RadioButton a6 = v.findViewById(R.id.a_6_false);

        RadioGroup g1 = v.findViewById(R.id.q_1_group);
        RadioGroup g2 = v.findViewById(R.id.q_2_group);
        RadioGroup g3 = v.findViewById(R.id.q_3_group);
        RadioGroup g4 = v.findViewById(R.id.q_4_group);
        RadioGroup g5 = v.findViewById(R.id.q_5_group);
        RadioGroup g6 = v.findViewById(R.id.q_6_group);


        TextView resultValue = v.findViewById(R.id.quizResultValue);
        LinearLayout resultSection = v.findViewById(R.id.resultSection);


        checkResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (g1.getCheckedRadioButtonId() == -1) {
                    return;
                }

                if (g2.getCheckedRadioButtonId() == -1) {
                    return;
                }

                if (g3.getCheckedRadioButtonId() == -1) {
                    return;
                }

                if (g4.getCheckedRadioButtonId() == -1) {
                    return;
                }

                if (g5.getCheckedRadioButtonId() == -1) {
                    return;
                }

                if (g6.getCheckedRadioButtonId() == -1) {
                    return;
                }

                int result = 0;

                if (a1.isChecked()) {
                    result++;
                }

                if (a2.isChecked()) {
                    result++;
                }

                if (a3.isChecked()) {
                    result++;
                }

                if (a4.isChecked()) {
                    result++;
                }

                if (a5.isChecked()) {
                    result++;
                }

                if (a6.isChecked()) {
                    result++;
                }

                if (result == 6) {
                    resultValue.setText("Wszystkie " + String.format("%d", result) + "! Gratulacje!");
                } else {
                    resultValue.setText(String.format("%d", result));

                }
                resultSection.setVisibility(View.VISIBLE);

            }
        });
        return v;
    }
}
