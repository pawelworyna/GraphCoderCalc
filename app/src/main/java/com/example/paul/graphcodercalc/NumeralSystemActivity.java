package com.example.paul.graphcodercalc;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

public class NumeralSystemActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary mLibrary;
    private EditText mathFormula;
    private int numeralSystemId = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeral_system);
        //gestures
        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mLibrary.load()) {
            finish();
        }
        GestureOverlayView gestures = findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
        gestures.setGestureColor(Color.TRANSPARENT);
        gestures.setUncertainGestureColor(Color.TRANSPARENT);
        mathFormula = findViewById(R.id.mathFormula);






    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonOCT:
                if (checked) {
                    mathFormula.setText(convertToOct(mathFormula.getText().toString(),numeralSystemId));
                    numeralSystemId = 8;
                    break;
                }
            case R.id.radioButtonHEX:
                if (checked) {

                    mathFormula.setText(convertToHex(mathFormula.getText().toString(),numeralSystemId));
                    numeralSystemId = 16;
                    break;
                }
                default:
                    if (checked) {
                        mathFormula.setText(convertToDec(mathFormula.getText().toString(),numeralSystemId));
                        numeralSystemId = 10;

                    }
        }
    }

    public String convertToHex(String number, int fromNumeralSystem) {
        if(!number.equals("")) {
            return Long.toHexString(Long.parseLong(number,fromNumeralSystem)).toUpperCase();
        }
        else{
            return "";
        }




    }

    public String convertToOct(String number, int fromNumeralSystem) {
        if(!number.equals("")){
            return Long.toOctalString(Long.parseLong(number,fromNumeralSystem));
        } else{
            return "";
        }


    }

    public String convertToDec(String number, int fromNumeralSystem) {
        if(!number.equals("")) {
            return String.valueOf(Long.parseLong(number, fromNumeralSystem));
        } else {
            return "";
        }



    }

    public String convertExpressionToDec(String expression, int numSystemId) {
        StringBuilder buildExpression = new StringBuilder();
        String[] numbers = expression.split("[+*/\\-]");
        String[] signs = expression.split("\\w");
        ArrayList<String> numbersList = new ArrayList<>();
        ArrayList<String> signsList = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            numbersList.add(numbers[i]);
        }
        for (int i = 0; i < signs.length; i++) {
            if(!signs[i].equals("")){
                signsList.add(signs[i]);
            }
        }

        Iterator<String> numbersIterator = numbersList.iterator();
        Iterator<String> signsIterator = signsList.iterator();
        while (numbersIterator.hasNext()) {
            buildExpression.append(Integer.parseInt((numbersIterator.next().trim()), numSystemId));
            if (signsIterator.hasNext()) {
                buildExpression.append(signsIterator.next());
            }

        }

        return buildExpression.toString();
    }

    public String calculate(int systemNumId) {
        String formula = mathFormula.getText().toString();
        Expression expression;
        if (systemNumId == 8) {
            expression = new ExpressionBuilder(convertExpressionToDec(formula, systemNumId))
                    .build();
        } else if(systemNumId == 16) {
                expression = new ExpressionBuilder(convertExpressionToDec(formula, systemNumId))
                        .build(); }

                else {
            expression = new ExpressionBuilder(formula).build();
            }

        return String.valueOf(expression.evaluate());

    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            if (prediction.name.equals("swipeLeft")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
