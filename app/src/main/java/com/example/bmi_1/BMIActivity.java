package com.example.bmi_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BMIActivity extends AppCompatActivity {

    private TextView textViewData;
    private Intent intent;
    private String name,age;
    private double height,weight;
    private double heightValue,bmiValue;
    private Button buttonBack;
    private int ErrorCode = 10;
    private final int RerutnCode = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        setTitle("BMI");

        textViewData = (TextView) findViewById(R.id.textView_bmi);
        textViewData.setText("");

        intent = getIntent();
//        name = intent.getStringExtra("name");
//        age = intent.getStringExtra("age");
//        height = intent.getDoubleExtra("height",100);
//        weight = intent.getDoubleExtra("weight",30);
        Bundle data = intent.getExtras();
        name = data.getString("name");
        age = data.getString("age");
        height = data.getDouble("height");
        weight = data.getDouble("weight");

        Log.d("bmi","name ="+name);
        Log.d("bmi","age = "+age);
        Log.d("bmi","height = "+height);
        Log.d("bmi","weight = "+weight);

        if(height >220 || height <40){
            Intent errorIntent = new Intent();
            errorIntent.putExtra("error","Height is error");
            setResult(ErrorCode, errorIntent);
            finish();
        }

        heightValue = height/100.0;
        bmiValue = (double) weight/(heightValue*heightValue);
        Log.d("bmi","bmi value = "+bmiValue);
        NumberFormat nf = new DecimalFormat("##.00");
        final String bmiString = nf.format(bmiValue);
        Log.d("bmi","bmi value = "+bmiString);

        textViewData.setText("name :"+name+"\n");
        textViewData.append("age : "+age+"\n");
        textViewData.append("BMI = "+bmiString+"\n");

        final String bmiResult = getBMIResult(bmiValue);
        textViewData.append(bmiResult);

        buttonBack = (Button) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("bmistring",bmiString);
                returnIntent.putExtra("bmiresult",bmiResult);
                setResult(RerutnCode,returnIntent);
                finish();
            }
        });



    }

    private String getBMIResult(double bmiValue) {
        String message;

        if(bmiValue > 0 && bmiValue <20)
            message = "BMI is low";
        else if(bmiValue >=20 && bmiValue <26)
            message = "BMI is standard";
        else if(bmiValue >= 26 && bmiValue <30)
            message = "BMI is high";
        else
            message = "BMI is too high";

        return  message;
    }
}