package com.example.qlsinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityInformationStudent extends AppCompatActivity {

    TextView edtName,edtCode,edtBirthday,edtSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);

        edtName = findViewById(R.id.txtStudentName);
        edtSex = findViewById(R.id.txtStudentSex);
        edtBirthday = findViewById(R.id.txtStudentDateOfBirth);
        edtCode = findViewById(R.id.txtStudentCode);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birhday = intent.getStringExtra("birthday");

        edtCode.setText(code);
        edtBirthday.setText(birhday);
        edtSex.setText(sex);
        edtName.setText(name);
    }
}