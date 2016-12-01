package samples.richardlee.dummycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand = null;
    private String pendingOperation = "=";

    static final String PENDING_OPERATION = "pendindOperation";
    static final String CURRENT_OPERAND = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        final View.OnClickListener newNumberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(newNumberListener);
        button1.setOnClickListener(newNumberListener);
        button2.setOnClickListener(newNumberListener);
        button3.setOnClickListener(newNumberListener);
        button4.setOnClickListener(newNumberListener);
        button5.setOnClickListener(newNumberListener);
        button6.setOnClickListener(newNumberListener);
        button7.setOnClickListener(newNumberListener);
        button8.setOnClickListener(newNumberListener);
        button9.setOnClickListener(newNumberListener);
        buttonDot.setOnClickListener(newNumberListener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        try {
            pendingOperation = savedInstanceState.getString(PENDING_OPERATION, "=");
            displayOperation.setText(pendingOperation);

            operand = savedInstanceState.getDouble(CURRENT_OPERAND, 0.0);

            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(PENDING_OPERATION, pendingOperation);
        outState.putDouble(CURRENT_OPERAND, operand);
        super.onSaveInstanceState(outState);
    }

    private void performOperation(Double value, String operation) {
        if (null == operand) {
            operand = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand = 0.0;
                    } else {
                        operand /= value;
                    }
                    break;
                case "*":
                    operand *= value;
                    break;
                case "-":
                    operand -= value;
                    break;
                case "+":
                    operand += value;
                    break;
            }
        }

        result.setText(operand.toString());
        newNumber.setText("");
    }
}
