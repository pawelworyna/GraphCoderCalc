package com.example.paul.graphcodercalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText formula;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formula = findViewById(R.id.editText);
        graph = findViewById(R.id.graph);

        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
        graph.getGridLabelRenderer().setVerticalAxisTitle("Y");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("X");


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

        Button btnConfirm = findViewById(R.id.btConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawGraph();
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
                if(position > 0){
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
                if(position<formula.getText().length()) {
                    position = position + 1;
                    formula.setSelection(position);
                }
            }
        });



    }

    //work on confirm button
    public void drawGraph(){

        String myFormula = formula.getText().toString();
        Expression expression;

        double x = -10.0, y;
        DataPoint[] points = new DataPoint[1000];
        if (!myFormula.isEmpty()) {
            for (int i = 0; i < 1000; i++) {

                expression = new ExpressionBuilder(myFormula)
                        .variables("x")
                        .build()
                        .setVariable("x", x);
                y = expression.evaluate();
                points[i] = new DataPoint(x, y);
                x = x + 0.1;
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
            graph.addSeries(series);
        } else {
            clearGraph();
        }



    }

    public void clearGraph(){
        graph.removeAllSeries();
    }


}
