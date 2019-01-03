package com.example.paul.graphcodercalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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


        Button btn1 = findViewById(R.id.bt1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("1");
            }
        });

        Button btn2 = findViewById(R.id.bt2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("2");
            }
        });

        Button btn3 = findViewById(R.id.bt3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("3");
            }
        });

        Button btn4 = findViewById(R.id.bt4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("4");
            }
        });

        Button btn5 = findViewById(R.id.bt5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("5");
            }
        });

        Button btn6 = findViewById(R.id.bt6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("6");
            }
        });

        Button btn7 = findViewById(R.id.bt7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("7");
            }
        });

        Button btn8 = findViewById(R.id.bt8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("8");
            }
        });

        Button btn9 = findViewById(R.id.bt9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("9");
            }
        });

        Button btn0 = findViewById(R.id.bt0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("0");
            }
        });

        Button btnPlus = findViewById(R.id.btPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("+");
            }
        });

        Button btnMinus = findViewById(R.id.btMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("-");
            }
        });

        Button btnTimes = findViewById(R.id.btTimes);
        btnTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("*");
            }
        });

        Button btnDivide = findViewById(R.id.btDivide);
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("/");
            }
        });

        Button btnPercent = findViewById(R.id.btPercent);
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append("%");
            }
        });

        Button btnDot = findViewById(R.id.btDot);
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formula.append(".");
            }
        });


    }

    //work on confirm button
    public void drawGraph(){

        String myFormula = formula.getText().toString();
        Expression expression;

        double x = -10.0, y;
        DataPoint[] points = new DataPoint[1000];
        for(int i= 0; i<1000; i++){

            expression = new ExpressionBuilder(myFormula)
                    .variables("x")
                    .build()
                    .setVariable("x", x);
            y = expression.evaluate();
            points[i] = new DataPoint(x, y );
            x = x+0.1;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        graph.addSeries(series);

    }


}
