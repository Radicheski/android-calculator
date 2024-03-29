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
    private Runnable operation = MainActivity.this::add;

    private TextView textView;

    private final Map<Integer, Runnable> functions = new HashMap<>() {{
        put(R.id.buttonClear, MainActivity.this::clear);
        put(R.id.buttonSign, MainActivity.this::changeSign);
        put(R.id.buttonPercent, MainActivity.this::percent);
        put(R.id.buttonDecimal, MainActivity.this::setDecimal);
        put(R.id.buttonDivision, () -> setOperation(MainActivity.this::divide));
        put(R.id.buttonMultiplication, () -> setOperation(MainActivity.this::multiply));
        put(R.id.buttonSubtraction, () -> setOperation(MainActivity.this::subtract));
        put(R.id.buttonAddition, () -> setOperation(MainActivity.this::add));
        put(R.id.buttonEqual, MainActivity.this::equals);
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
    }

    private void appendNumber(int number) {
        numerator *= 10;
        numerator += number;
        if (decimal) denominator *= 10;
        showInput();
    }

    private void setDecimal() {
        decimal = true;
    }

    private void setOperation(Runnable operation) {
        if (Objects.nonNull(this.operation)) this.operation.run();
        this.operation = operation;
        showAnswer();
        clearInput();
    }

    private void equals() {
        if (Objects.nonNull(this.operation)) this.operation.run();
        double answer = this.answer;
        clear();
        textView.setText(formatted(answer));
    }

    private void percent() {
        numerator /= 100;
        showInput();
    }

    private void clear() {
        clearAnswer();
        clearInput();
        showInput();
    }

    private void clearAnswer() {
        answer = 0;
        operation = this::add;
    }

    private void clearInput() {
        numerator = 0;
        denominator = 1;
        decimal = false;
    }

    private void changeSign() {
        denominator *= -1;
        showInput();
    }

    private void divide() {
        answer /= (numerator / denominator);
    }

    private void multiply() {
        answer *= (numerator / denominator);
    }

    private void add() {
        answer += (numerator / denominator);
    }

    private void subtract() {
        answer -= (numerator / denominator);
    }

    private void showAnswer() {
        textView.setText(formatted(answer));
    }

    private void showInput() {
        textView.setText(formatted(numerator / denominator));
    }

    private String formatted(double number) {
        return String.format("%.15f", number).replaceAll(",?0*$", "");
    }
}
