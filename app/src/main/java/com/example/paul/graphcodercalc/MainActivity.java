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
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private EditText formula;
    private GraphView graph;
    private Boolean flagKeyboard = false;
    private GestureLibrary mLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formula = findViewById(R.id.editText);
        graph = findViewById(R.id.graph);

        // graph settings
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Y");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("X");


        //gestures
        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mLibrary.load()) {
            finish();
        }
        GestureOverlayView gestures = findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);
        gestures.setGestureColor(Color.TRANSPARENT);
        gestures.setUncertainGestureColor(Color.TRANSPARENT);

        //numbers keyboards

        Button btn1 = findViewById(R.id.bt1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "1");
            }
        });

        Button btn2 = findViewById(R.id.bt2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "2");
            }
        });

        Button btn3 = findViewById(R.id.bt3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "3");
            }
        });

        Button btn4 = findViewById(R.id.bt4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "4");
            }
        });

        Button btn5 = findViewById(R.id.bt5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "5");
            }
        });

        Button btn6 = findViewById(R.id.bt6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "6");
            }
        });

        Button btn7 = findViewById(R.id.bt7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "7");
            }
        });

        Button btn8 = findViewById(R.id.bt8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "8");
            }
        });

        Button btn9 = findViewById(R.id.bt9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "9");
            }
        });

        Button btn0 = findViewById(R.id.bt0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "0");
            }
        });

        Button btnPlus = findViewById(R.id.btPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "+");
            }
        });

        Button btnMinus = findViewById(R.id.btMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "-");
            }
        });

        Button btnTimes = findViewById(R.id.btTimes);
        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "*");
            }
        });

        Button btnDivide = findViewById(R.id.btDivide);
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "/");
            }
        });

        Button btnPercent = findViewById(R.id.btPercent);
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "%");
            }
        });

        Button btnDot = findViewById(R.id.btDot);
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), ".");
            }
        });


        //controls keyboard

        Button btnConfirm = findViewById(R.id.btConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new Thread(new Runnable() {
                  @Override
                  public void run() {
                      drawGraph();
                  }
              }).start();
                formula.setSelection(formula.getText().length());
                }

            });

        Button btnBackspace = findViewById(R.id.btBackspace);
        btnBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = formula.getText().length();
                if (length > 0) {
                    formula.getText().delete(length - 1, length);
                }
            }
        });

        Button btnLeftArray = findViewById(R.id.btArrLeft);
        btnLeftArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = formula.getSelectionStart();
                if (position > 0) {
                    position = position - 1;
                    formula.setSelection(position);
                }
            }
        });

        Button btnRightArray = findViewById(R.id.btArrRight);
        btnRightArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = formula.getSelectionStart();
                if (position < formula.getText().length()) {
                    position = position + 1;
                    formula.setSelection(position);
                }
            }
        });

        Button btnKeybrdNumbers = findViewById(R.id.btLayoutNumbers);
        btnKeybrdNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagKeyboard = false;
                changeKeyboard();
            }
        });

        Button btnKeybrdFunction = findViewById(R.id.btLayoutFunctions);
        btnKeybrdFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagKeyboard = true;
                changeKeyboard();
            }
        });


        //functions keyboard
        Button btnSin = findViewById(R.id.btSin);
        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "sin");
            }
        });

        Button btnCos = findViewById(R.id.btCos);
        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "cos");
            }
        });

        Button btnTan = findViewById(R.id.btTan);
        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "tan");
            }
        });

        Button btnASin = findViewById(R.id.btASin);
        btnASin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "asin");
            }
        });

        Button btnACos = findViewById(R.id.btACos);
        btnACos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "acos");
            }
        });

        Button btnATan = findViewById(R.id.btATan);
        btnATan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "atan");
            }
        });

        Button btnLN = findViewById(R.id.btLN);
        btnLN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "log");
            }
        });

        Button btnLog10 = findViewById(R.id.btLog10);
        btnLog10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "log10");
            }
        });

        Button btnPower = findViewById(R.id.btPow);
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "^");
            }
        });

        Button btnSqrt = findViewById(R.id.btSqrt);
        btnSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "sqrt");
            }
        });

        Button btnOpenParenthesis = findViewById(R.id.btOpnPar);
        btnOpenParenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "(");
            }
        });

        Button btnCloseParenthesis = findViewById(R.id.btClsPar);
        btnCloseParenthesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), ")");
            }
        });

        Button btnX = findViewById(R.id.btX);
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "x");
            }
        });

        Button btnPi = findViewById(R.id.btPI);
        btnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "π");
            }
        });

        Button btnEuler = findViewById(R.id.btEuler);
        btnEuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "e");
            }
        });

        Button btnAbs = findViewById(R.id.btAbs);
        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.getText().insert(formula.getSelectionStart(), "abs");
            }
        });


    }

    //work on confirm button
    public void drawGraph() {

        String myFormula = formula.getText().toString();
        Expression expression;

        double x = -10.0, y;
        DataPoint[] points = new DataPoint[10000];
        try {
            for (int i = 0; i < 10000; i++) {

                expression = new ExpressionBuilder(myFormula)
                        .variables("x")
                        .build()
                        .setVariable("x", x);
                y = expression.evaluate();
                points[i] = new DataPoint(x, y);
                x = x + 0.01;
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
            graph.addSeries(series);
        } catch (RuntimeException re){
            clearGraph();
        }


    }

    public void clearGraph() {
        graph.removeAllSeries();
    }

    public void changeKeyboard() {
        if (!flagKeyboard) {
            findViewById(R.id.functionsLayout).setVisibility(View.GONE);
            findViewById(R.id.numbersLayout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.functionsLayout).setVisibility(View.VISIBLE);
            findViewById(R.id.numbersLayout).setVisibility(View.GONE);
        }
    }


    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            if (prediction.name.equals("swipeRight")) {
                Intent intent = new Intent(this, NumeralSystemActivity.class);
                startActivity(intent);
            }
        }
    }
}
