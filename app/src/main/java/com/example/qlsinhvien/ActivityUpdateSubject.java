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

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText udTitle,udCredit,udTime,udPlace;
    Button buttonUpdate;

    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);

        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        udCredit = findViewById(R.id.EditTextUpdateCredits);
        udPlace = findViewById(R.id.EditTextUpdatePlace);
        udTime = findViewById(R.id.EditTextUpdateTime);
        udTitle = findViewById(R.id.EditTextUpdateSubjectTitle);
        buttonUpdate = findViewById(R.id.buttonUpdateSubject);

        udTitle.setText(title);
        udCredit.setText(credit+"");
        udTime.setText(time);
        udPlace.setText(place);

        database = new database(this);


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id);
            }
        });
    }
    //Dialog Update
    private void DialogUpdate(int id) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogupdate);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYesUpdateSubject);
        Button btnNo = dialog.findViewById(R.id.buttonNoUpdateSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subjecttitle = udTitle.getText().toString();
                String credits = udCredit.getText().toString();
                String time = udTime.getText().toString();
                String place = udPlace.getText().toString();

                Subject subject = updatesubject();

                if(subjecttitle.equals("") || credits.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityUpdateSubject.this,"Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {

                    database.UpdateSubject(subject,id);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(ActivityUpdateSubject.this,ActivitySubjects.class);

                    startActivity(intent);
                    Toast.makeText(ActivityUpdateSubject.this,"more success",Toast.LENGTH_SHORT).show();
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
    private Subject updatesubject(){

        String subjecttitle = udTitle.getText().toString();
        int credits = Integer.parseInt(udCredit.getText().toString());
        String time = udTime.getText().toString();
        String place = udPlace.getText().toString();

        Subject subject = new Subject(subjecttitle,credits,time,place);
        return subject;
    }
}