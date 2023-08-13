package dev.radicheski.calculator;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private final List<Integer> BUTTONS_IDS = List.of(
            R.id.buttonClear, R.id.buttonSign, R.id.buttonPercent, R.id.buttonDecimal,
            R.id.buttonDivision, R.id.buttonMultiplication,
            R.id.buttonSubtraction, R.id.buttonAddition, R.id.buttonEqual,
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
    );

    private final List<Button> BUTTONS = new ArrayList<>(BUTTONS_IDS.size());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        for (int id: BUTTONS_IDS) {
            Button button = (Button) findViewById(id);
            button.setOnClickListener(this::onClick);
            BUTTONS.add(button);
        }
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button0) {
            textView.setText("0");
        } else if (id == R.id.button1) {
            textView.setText("1");
        } else if (id == R.id.button2) {
            textView.setText("2");
        } else if (id == R.id.button3) {
            textView.setText("3");
        } else if (id == R.id.button4) {
            textView.setText("4");
        } else if (id == R.id.button5) {
            textView.setText("5");
        } else if (id == R.id.button6) {
            textView.setText("6");
        } else if (id == R.id.button7) {
            textView.setText("7");
        } else if (id == R.id.button8) {
            textView.setText("8");
        } else if (id == R.id.button9) {
            textView.setText("9");
        } else if (id == R.id.buttonClear) {
            textView.setText("C");
        } else if (id == R.id.buttonSign) {
            textView.setText("±");
        } else if (id == R.id.buttonPercent) {
            textView.setText("%");
        } else if (id == R.id.buttonDecimal) {
            textView.setText(",");
        } else if (id == R.id.buttonDivision) {
            textView.setText("÷");
        } else if (id == R.id.buttonMultiplication) {
            textView.setText("x");
        } else if (id == R.id.buttonSubtraction) {
            textView.setText("-");
        } else if (id == R.id.buttonAddition) {
            textView.setText("+");
        } else if (id == R.id.buttonEqual) {
            textView.setText("=");
        }

    }
}
