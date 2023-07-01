package com.example.qlsinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsinhvien.database.database;
import com.example.qlsinhvien.model.Subject;

public class ActivityAddSubjects extends AppCompatActivity {

    Button buttonAddSubject;
    EditText edtSubjectTitle,edtCredits,edtTime,edtPlace;
    database database;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);


        buttonAddSubject = findViewById(R.id.buttonAddSubject);
        edtCredits = findViewById(R.id.EditTextCredits);
        edtPlace = findViewById(R.id.EditTextPlace);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);
        edtTime = findViewById(R.id.EditTextTime);

        database = new database(this);

        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }
    //Dialog Add
    private void DialogAdd() {


        Dialog dialog  =  new Dialog(this);


        dialog.setContentView(R.layout.dialogadd);


        dialog.setCanceledOnTouchOutside(false);


        Button btnYes = dialog.findViewById(R.id.buttonYesAddSubject);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjecttitle = edtSubjectTitle.getText().toString();
                String credits = edtCredits.getText().toString();
                String time = edtTime.getText().toString();
                String place = edtPlace.getText().toString();

                if(subjecttitle.equals("") || credits.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityAddSubjects.this,"Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {
                    Subject subject = CreatSubject();

                    database.AddSubjects(subject);

                    Intent intent = new Intent(ActivityAddSubjects.this,ActivitySubjects.class);

                    startActivity(intent);
                    Toast.makeText(ActivityAddSubjects.this,"more success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


    private Subject CreatSubject(){

        String subjecttitle = edtSubjectTitle.getText().toString();
        int credits = Integer.parseInt(edtCredits.getText()+"");
        String time = edtTime.getText().toString();
        String place = edtPlace.getText().toString();

        Subject subject = new Subject(subjecttitle,credits,time,place);
        return subject;
    }


}