package com.example.princ.bmicalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textResult,textBMI,ResultWeight,Result,textNormalBMI;
    EditText editAge,editWeight,editFeet,editInch;
    Button buttonCalculate;
    int age,weight,heightF,heightI,totHeight;
    String D = "";

    private Boolean IsNullOrEmpty(String str)
    {
        return (str == null || str.isEmpty());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);

        textResult = (TextView) findViewById(R.id.textResult);
        textBMI = (TextView) findViewById(R.id.textBMI);
        ResultWeight = (TextView) findViewById(R.id.ResultWeight);
        Result = (TextView) findViewById(R.id.Result);
        textNormalBMI = (TextView) findViewById(R.id.textNormalBMI);

        editAge = (EditText) findViewById(R.id.editAge);
        editWeight = (EditText) findViewById(R.id.editWeight);
        editFeet = (EditText) findViewById(R.id.editFeet);
        editInch = (EditText) findViewById(R.id.editInch);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsNullOrEmpty(editAge.getText().toString()) || IsNullOrEmpty(editWeight.getText().toString()) ||
                        IsNullOrEmpty(editFeet.getText().toString()) || IsNullOrEmpty(editInch.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Invalid Inputs", Toast.LENGTH_LONG).show();
                } else {
                    if (Integer.parseInt(editAge.getText().toString()) < 18) {
                        Toast.makeText(MainActivity.this, "Age is Less Than 18", Toast.LENGTH_SHORT).show();
                        textResult.setVisibility(View.INVISIBLE);
                        textBMI.setVisibility(View.INVISIBLE);
                        ResultWeight.setVisibility(View.INVISIBLE);
                        Result.setVisibility(View.INVISIBLE);
                        textResult.setVisibility(View.INVISIBLE);
                        textNormalBMI.setVisibility(View.INVISIBLE);
                    }else if(Integer.parseInt(editAge.getText().toString()) > 110){
                        editAge.setError("Age should not be > 110");
                        textResult.setVisibility(View.INVISIBLE);
                        textBMI.setVisibility(View.INVISIBLE);
                        ResultWeight.setVisibility(View.INVISIBLE);
                        Result.setVisibility(View.INVISIBLE);
                        textResult.setVisibility(View.INVISIBLE);
                        textNormalBMI.setVisibility(View.INVISIBLE);
                    }
                    else {
                        textResult.setVisibility(View.VISIBLE);
                        textBMI.setVisibility(View.VISIBLE);
                        ResultWeight.setVisibility(View.VISIBLE);
                        Result.setVisibility(View.VISIBLE);
                        textResult.setVisibility(View.VISIBLE);
                        textNormalBMI.setVisibility(View.VISIBLE);

                        age = Integer.parseInt(editAge.getText().toString());
                        weight = Integer.parseInt(editWeight.getText().toString());
                        heightF = Integer.parseInt(editFeet.getText().toString());
                        heightI = Integer.parseInt(editInch.getText().toString());
                        totHeight = heightF * 12 + heightI;
                        double diff=0;

                        double BMI = (703.0 * (((double)weight) / Math.pow((double)totHeight, 2)));
                        BMI = Double.parseDouble(String.format("%.2f", BMI));

                        if (BMI < 18.5) {
                            textBMI.setText("BMI = " + BMI+" ");
                            ResultWeight.setText(R.string.Underweight);
                            ResultWeight.setTextColor(Color.RED);
                            diff = ((18.5/703.0) * Math.pow((double)totHeight, 2))-Double.parseDouble(editWeight.getText().toString());
                            Result.setText(String.format("%s %.1f %s","You will need to gain ",diff," lbs to reach a BMI of 18.5"));
                        } else if (BMI >= 18.5 && BMI <25) {
                            textBMI.setText("BMI = " + BMI+" ");
                            ResultWeight.setText(R.string.Normal);
                            ResultWeight.setTextColor(Color.GREEN);
                            Result.setText(R.string.GoodWork);
                        } else if (BMI >= 25 && BMI < 30) {
                            textBMI.setText("BMI = " + BMI+" ");
                            ResultWeight.setText(R.string.Overweight);
                            ResultWeight.setTextColor(Color.parseColor("#FFA500"));
                            diff = Double.parseDouble(editWeight.getText().toString())-((25.0/703.0) * Math.pow((double)totHeight, 2));
                            Result.setText(String.format("%s %.1f %s","You will need to lose ",diff," lbs to reach a BMI of 25"));
                        } else if (BMI >= 30) {
                            textBMI.setText("BMI = " + BMI+" ");
                            ResultWeight.setText(R.string.Obese);
                            ResultWeight.setTextColor(Color.parseColor("#FFA500"));
                            diff = Double.parseDouble(editWeight.getText().toString())-((25.0/703.0) * Math.pow((double)totHeight, 2));
                            Result.setText(String.format("%s %.1f %s","You will need to lose ",diff," lbs to reach a BMI of 25"));
                        }
                    }
                }

            }
        });


    }

}
