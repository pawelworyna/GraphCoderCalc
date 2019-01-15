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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class NumeralSystemActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary mLibrary;
    private EditText mathFormula;
    private int numeralSystemId = 10;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button btnE;
    private Button btnF;
    private Button btn8;
    private Button btn9;

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

        //numbers keyboards

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "1");
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "2");
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "3");
            }
        });

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "4");
            }
        });

        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "5");
            }
        });

        Button btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "6");
            }
        });

        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "7");
            }
        });

        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numeralSystemId != 8)
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "8");
            }
        });

        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "9");
            }
        });

        Button btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "0");
            }
        });

        Button btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "+");
            }
        });

        Button btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "-");
            }
        });

        Button btnTimes = findViewById(R.id.btnTimes);
        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "*");
            }
        });

        Button btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "/");
            }
        });

        btnA = findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "A");
            }
        });

        btnB = findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "B");
            }
        });

        btnC = findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "C");
            }
        });

        btnD = findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "D");
            }
        });

        btnE = findViewById(R.id.btnE);
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "E");
            }
        });

        btnF = findViewById(R.id.btnF);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathFormula.getText().insert(mathFormula.getSelectionStart(), "F");
            }
        });


        //controls keyboard

        Button btnConfirm = findViewById(R.id.btConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              try {

                                                  if (numeralSystemId == 16) {
                                                      mathFormula.setText(Integer.toHexString(calculate(numeralSystemId)).toUpperCase());
                                                  } else if (numeralSystemId == 8) {
                                                      mathFormula.setText(Integer.toOctalString(calculate(numeralSystemId)));
                                                  } else {
                                                      mathFormula.setText(String.valueOf(calculate(numeralSystemId)));
                                                  }
                                              } catch (IllegalStateException | NumberFormatException ise) {
                                                  mathFormula.setText("");
                                              }

                                          }
                                      });


        Button btnBackspace = findViewById(R.id.btBackspace);
        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = mathFormula.getText().length();
                if (length > 0) {
                    mathFormula.getText().delete(length - 1, length);
                }
            }
        });

        Button btnLeftArray = findViewById(R.id.btArrLeft);
        btnLeftArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mathFormula.getSelectionStart();
                if (position > 0) {
                    position = position - 1;
                    mathFormula.setSelection(position);
                }
            }
        });

        Button btnRightArray = findViewById(R.id.btArrRight);
        btnRightArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mathFormula.getSelectionStart();
                if (position < mathFormula.getText().length()) {
                    position = position + 1;
                    mathFormula.setSelection(position);
                }
            }
        });




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
                    changeKeyboard();
                    break;
                }
            case R.id.radioButtonHEX:
                if (checked) {

                    mathFormula.setText(convertToHex(mathFormula.getText().toString(),numeralSystemId));
                    numeralSystemId = 16;
                    changeKeyboard();
                    break;
                }
                default:
                    if (checked) {
                        mathFormula.setText(convertToDec(mathFormula.getText().toString(),numeralSystemId));
                        numeralSystemId = 10;
                        changeKeyboard();

                    }
        }
    }

    public String convertToHex(String number, int fromNumeralSystem) {
        if(isInputValidate(number)) {
            return Long.toHexString(Long.parseLong(number,fromNumeralSystem)).toUpperCase();
        }
        else{
            return "";
        }




    }

    public String convertToOct(String number, int fromNumeralSystem) {
        if(isInputValidate(number)){
            return Long.toOctalString(Long.parseLong(number,fromNumeralSystem));
        } else{
            return "";
        }


    }

    public String convertToDec(String number, int fromNumeralSystem) {
        if(isInputValidate(number)) {
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

    public Integer calculate(int systemNumId) {
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

        return ((int) expression.evaluate());

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


    public boolean isInputValidate(String input) {
        return input.matches("\\w+");
    }

    public void changeKeyboard() {
        if (numeralSystemId == 16) {
            btn8.setEnabled(true);
            btn9.setEnabled(true);
            btnA.setEnabled(true);
            btnB.setEnabled(true);
            btnC.setEnabled(true);
            btnD.setEnabled(true);
            btnE.setEnabled(true);
            btnF.setEnabled(true);
        } else if (numeralSystemId == 10) {
            btn8.setEnabled(true);
            btn9.setEnabled(true);
            btnA.setEnabled(false);
            btnB.setEnabled(false);
            btnC.setEnabled(false);
            btnD.setEnabled(false);
            btnE.setEnabled(false);
            btnF.setEnabled(false);
        } else {
            btn8.setEnabled(false);
            btn9.setEnabled(false);
            btnA.setEnabled(false);
            btnB.setEnabled(false);
            btnC.setEnabled(false);
            btnD.setEnabled(false);
            btnE.setEnabled(false);
            btnF.setEnabled(false);
        }
    }
}
