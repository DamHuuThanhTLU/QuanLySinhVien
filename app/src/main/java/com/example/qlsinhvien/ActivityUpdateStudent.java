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

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText edtUpdateName, edtUpdateCode, edtUpdateBirthday;
    RadioButton rdMale,rdFemale;
    Button btnupdateStudent;
    database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtUpdateBirthday = findViewById(R.id.EditTextUpdateDateOfBirth);
        edtUpdateCode = findViewById(R.id.EditTextUpdateSudentCode);
        edtUpdateName = findViewById(R.id.EditTextUpdateStudentName);
        rdFemale = findViewById(R.id.radiobuttonUpdateFemale);
        rdMale = findViewById(R.id.radiobuttonUpdateMale);
        btnupdateStudent = findViewById(R.id.buttonUpdateStudent);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        int id_subject = intent.getIntExtra("id_subject",0);

        edtUpdateName.setText(name);
        edtUpdateCode.setText(code);
        edtUpdateBirthday.setText(birthday);
        
        if(sex.equals("Male")){
            rdMale.setChecked(true);
            rdFemale.setChecked(false);
        }
        else {
            rdMale.setChecked(false);
            rdFemale.setChecked(true);
        }

        database = new database(this);

        btnupdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_subject);
            }

        });
    }
    private void DialogUpdate(int id,int id_subject) {
    //Tạo đối tượng cửa sổ dialog
    Dialog dialog  =  new Dialog(this);

    //Nạp layout vào
        dialog.setContentView(R.layout.dialogupdatestudent);

    //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

    //Ánh xạ
    Button btnYes = dialog.findViewById(R.id.buttonYesUpdateStudent);
    Button btnNo = dialog.findViewById(R.id.buttonNoUpdateStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = edtUpdateName.getText().toString();
            String code = edtUpdateCode.getText().toString();
            String birthday = edtUpdateBirthday.getText().toString();

            Student student = updateStudent();

            if(name.equals("") || code.equals("") || birthday.equals("") ){
                Toast.makeText(ActivityUpdateStudent.this,"Did not enter enough information",Toast.LENGTH_SHORT).show();
            }
            else {
                database.UpdateStudent(student,id);
                //Khởi tạo lại activity main
                Intent intent = new Intent(ActivityUpdateStudent.this,ActivityStudent.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                Toast.makeText(ActivityUpdateStudent.this,"more success",Toast.LENGTH_SHORT).show();
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
    private Student updateStudent(){

        String name = edtUpdateName.getText().toString();
        String code = edtUpdateCode.getText().toString();
        String birthday = edtUpdateBirthday.getText().toString();
        String sex="";
        if(rdMale.isChecked()){
            sex =  "Male";
        }
        else {
            sex = "Female";
        }
        Student student = new Student(name,sex,code,birthday);
        return student;
    }
}