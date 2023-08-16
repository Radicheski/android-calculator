package dev.radicheski.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private double numerator = 0;
    private double denominator = 1;
    private boolean decimal = false;
    private double answer = 0;

    private TextView textView;

    private final Map<Integer, Runnable> functions = new HashMap<>() {{
        put(R.id.buttonClear, MainActivity.this::clear);
        put(R.id.buttonSign, MainActivity.this::changeSign);
        put(R.id.buttonPercent, MainActivity.this::percent);
        put(R.id.buttonDecimal, MainActivity.this::setDecimal);
        put(R.id.buttonDivision, MainActivity.this::divide);
        put(R.id.buttonMultiplication, MainActivity.this::multiply);
        put(R.id.buttonSubtraction, MainActivity.this::subtract);
        put(R.id.buttonAddition, MainActivity.this::add);
        put(R.id.buttonEqual, () -> { /* TODO */ } );
        put(R.id.button0, () -> appendNumber(0));
        put(R.id.button1, () -> appendNumber(1));
        put(R.id.button2, () -> appendNumber(2));
        put(R.id.button3, () -> appendNumber(3));
        put(R.id.button4, () -> appendNumber(4));
        put(R.id.button5, () -> appendNumber(5));
        put(R.id.button6, () -> appendNumber(6));
        put(R.id.button7, () -> appendNumber(7));
        put(R.id.button8, () -> appendNumber(8));
        put(R.id.button9, () -> appendNumber(9));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        for (int id: functions.keySet()) {
            Button button = (Button) findViewById(id);
            button.setOnClickListener(this::onClick);
        }
    }

    private void onClick(View view) {
        Runnable function = functions.get(view.getId());
        if (Objects.nonNull(function)) function.run();
        textView.setText(String.format("%f", numerator / denominator));
    }

    private void appendNumber(int number) {
        numerator *= 10;
        numerator += number;
        if (decimal) denominator *= 10;
        textView.setText(String.format("%f", numerator / denominator));
    }

    private void setDecimal() {
        decimal = true;
    }

    private void percent() {
        numerator /= 100;
    }

    private void clear() {
        numerator = 0;
        denominator = 1;
        decimal = false;
    }

    private void changeSign() {
        numerator *= -1;
    }

    private void divide() {
        answer /= (numerator / denominator);
        showAnswer();
    }

    private void multiply() {
        answer *= (numerator / denominator);
        showAnswer();
    }

    private void add() {
        answer += (numerator / denominator);
        showAnswer();
    }

    private void subtract() {
        answer -= (numerator / denominator);
        showAnswer();
    }

    private void showAnswer() {
        clear();
        textView.setText(String.format("%f", answer));
    }
}
