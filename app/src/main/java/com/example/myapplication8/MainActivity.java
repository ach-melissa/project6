package com.example.myapplication8;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare public variables for UI elements
    public EditText etCoefficientA, etCoefficientB, etCoefficientC;
    public Button btnSolve, btnInitialize;
    public TextView tvResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        etCoefficientA = findViewById(R.id.etCoefficientA);
        etCoefficientB = findViewById(R.id.etCoefficientB);
        etCoefficientC = findViewById(R.id.etCoefficientC);
        btnSolve = findViewById(R.id.btnSolve);
        btnInitialize = findViewById(R.id.btnInitialize);
        tvResult = findViewById(R.id.tvResult);

        // Set listener for the Solve button
        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveEquation();
            }
        });

        // Set listener for the Initialize button (reset fields)
        btnInitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeForm();
            }
        });
    }

    // Method to solve the quadratic equation
    public void solveEquation() {
        // Get coefficients as strings
        String aStr = etCoefficientA.getText().toString();
        String bStr = etCoefficientB.getText().toString();
        String cStr = etCoefficientC.getText().toString();

        // Check if any coefficient is missing
        if (TextUtils.isEmpty(aStr) || TextUtils.isEmpty(bStr) || TextUtils.isEmpty(cStr)) {
            Toast.makeText(this, "Please enter all coefficients", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert strings to doubles
        double a = Double.parseDouble(aStr);
        double b = Double.parseDouble(bStr);
        double c = Double.parseDouble(cStr);

        // Handle case where 'a' is zero (not a quadratic equation)
        if (a == 0) {
            Toast.makeText(this, "This is not a quadratic equation!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the discriminant
        double discriminant = b * b - 4 * a * c;

        // Initialize result string
        String result;

        // Solve based on the discriminant
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            result = String.format("Roots are:\n x₁ = %.2f \n x₂ = %.2f", root1, root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            result = String.format("Root is:\n x = %.2f", root);
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            result = String.format("Roots are:\n x₁ = %.2f + %.2fi \n x₂ = %.2f - %.2fi", realPart, imaginaryPart, realPart, imaginaryPart);
        }

        // Display the result
        tvResult.setText(result);
    }

    // Method to initialize/clear the input fields
    public void initializeForm() {
        etCoefficientA.setText("");
        etCoefficientB.setText("");
        etCoefficientC.setText("");
        tvResult.setText("Result will be displayed here");
    }
}
