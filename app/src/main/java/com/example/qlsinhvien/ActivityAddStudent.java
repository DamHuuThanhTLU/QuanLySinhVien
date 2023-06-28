package com.example.qlsinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.qlsinhvien.database.database;
import com.example.qlsinhvien.model.Student;
import com.example.qlsinhvien.model.Subject;

public class ActivityAddStudent extends AppCompatActivity {

    Button buttonAddStudent;
    EditText editTextStudentName,editTextStudentCode,editTextDateOfBirth;
    RadioButton radioButtonMale,radioButtonFemale;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddStudent = findViewById(R.id.buttonAddStudent);

        editTextDateOfBirth = findViewById(R.id.EditTextDateOfBirth);
        editTextStudentCode = findViewById(R.id.EditTextSudentCode);
        editTextStudentName = findViewById(R.id.EditTextStudentName);

        radioButtonFemale = findViewById(R.id.radiobuttonFemale);
        radioButtonMale = findViewById(R.id.radiobuttonMale);

        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject",0);

        database = new database(this);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });
    }
    //Dialog Add
    private void DialogAdd(int id_subject) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogaddstudent);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextStudentName.getText().toString();
                String code = editTextStudentCode.getText().toString();
                String birthday = editTextDateOfBirth.getText().toString();
                String sex="";
                if(radioButtonMale.isChecked()){
                    sex =  "Male";
                }
                else if(radioButtonFemale.isChecked()){
                    sex = "Female";
                }

                if(name.equals("") || code.equals("") || birthday.equals("") || sex.equals("")){
                    Toast.makeText(ActivityAddStudent.this,"Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {
                    Student student = CreatStudent(id_subject);

                    database.AddStudent(student);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(ActivityAddStudent.this,ActivityStudent.class);
                    //finish();
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(ActivityAddStudent.this,"more success",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Nếu no thì đóng dialog
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //show dialog lên activity
        dialog.show();
    }

    //Create Subject
    private Student CreatStudent(int id_subject){

        String name = editTextStudentName.getText().toString();
        String code = editTextStudentCode.getText().toString();
        String birthday = editTextDateOfBirth.getText().toString();
        String sex="";
        if(radioButtonMale.isChecked()){
            sex =  "Male";
        }
        else if(radioButtonFemale.isChecked()){
            sex = "Female";
        }

        Student student = new Student(name,sex,code,birthday,id_subject);
        return student;
    }
}