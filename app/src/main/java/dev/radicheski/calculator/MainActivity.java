package dev.radicheski.calculator;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {

    private double numerator = 0;
    private double denominator = 1;
    private boolean decimal = false;
    private double answer = 0;

    private TextView textView;

    private final List<Integer> BUTTONS_IDS = List.of(
            R.id.buttonClear, R.id.buttonSign, R.id.buttonPercent, R.id.buttonDecimal,
            R.id.buttonDivision, R.id.buttonMultiplication,
            R.id.buttonSubtraction, R.id.buttonAddition, R.id.buttonEqual,
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
    );

    private final Map<Integer, Runnable> functions = new HashMap<>() {{
        put(R.id.buttonClear, MainActivity.this::clear);
        put(R.id.buttonSign, MainActivity.this::changeSign);
        put(R.id.buttonPercent, MainActivity.this::percent);
        put(R.id.buttonDecimal, MainActivity.this::setDecimal);
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
        Runnable function = functions.get(view.getId());
        if (Objects.nonNull(function)) function.run();
        textView.setText(String.format("%f", numerator / denominator));
    }

    private void appendNumber(int number) {
        numerator *= 10;
        numerator += number;
        if (decimal) denominator *= 10;
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
}
