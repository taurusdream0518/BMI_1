package com.example.bmi_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText editTextName,editTextAge,editTextHeihgt,editTextWeight;
    private Button buttonCancel,buttonStart;
    private String name;
    private String age,height,weight;
    private Intent intent;
    private TextView textViewResult;
    private final int ErrorCode =10;
    private final int RequestCode =100;
    private final int RerutnCode = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Log.d("main","main-onCreate");

        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextAge = (EditText) findViewById(R.id.editText_age);
        editTextHeihgt = (EditText) findViewById(R.id.editText_height);
        editTextWeight = (EditText) findViewById(R.id.editText_weight);

        editTextName.setText("");

        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonStart = (Button) findViewById(R.id.button_start);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextAge.setText("");
                editTextHeihgt.setText("");
                editTextWeight.setText("");
                textViewResult.setText("");
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextName.getText().toString();
                age = editTextAge.getText().toString();
                if(name.length() == 0){
                    Toast.makeText(context,"Please input name.",Toast.LENGTH_SHORT).show();
                    name = "unkown";
                }

                if(age.length() == 0){
                    Toast.makeText(context,"Please input age.",Toast.LENGTH_SHORT).show();
                    age = "0";
                }
                height = editTextHeihgt.getText().toString();
                weight = editTextWeight.getText().toString();

                if(height.length() == 0 || weight.length() == 0){
                    Toast.makeText(context,"Please input height & weight.",Toast.LENGTH_SHORT).show();
                }else{
                    double heightValue = Double.parseDouble(height);
                    double weightValue = Double.parseDouble(weight);
                    Log.d("main","heightValue = "+heightValue);
                    Log.d("main","weightValue = "+weightValue);

                    intent = new Intent(context,BMIActivity.class);
//                    intent.putExtra("name",name);
//                    intent.putExtra("age",age);
//                    intent.putExtra("height",heightValue);
//                    intent.putExtra("weight",weightValue);
//                    startActivity(intent);

                    Bundle data = new Bundle();
                    data.putString("name",name);
                    data.putString("age",age);
                    data.putDouble("height",heightValue);
                    data.putDouble("weight",weightValue);
                    intent.putExtras(data);
//                    startActivity(intent);
                    startActivityForResult(intent, RequestCode);


                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("main","requestCode = "+requestCode);
        Log.d("main","resultCode = "+resultCode);

        if(requestCode == RequestCode){
            switch (resultCode){
                case ErrorCode:
                    String message = data.getStringExtra("error");
                    textViewResult.setText(message);
                    break;
                case RerutnCode:
                    textViewResult.setText(data.getStringExtra("bmistring")+"\n");
                    textViewResult.append(data.getStringExtra("bmiresult")+"\n");

                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("main","main-onStrat");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main","main-onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("main","main-onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("main","main-onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main","main-onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("main","main-onRestart");
    }
}