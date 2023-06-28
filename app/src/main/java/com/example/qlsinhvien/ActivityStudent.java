package com.example.qlsinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsinhvien.adapter.adapterStudent;
import com.example.qlsinhvien.adapter.adapterSubjects;
import com.example.qlsinhvien.database.database;
import com.example.qlsinhvien.model.Student;
import com.example.qlsinhvien.model.Subject;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    Toolbar toolbarStudent;
    ListView listViewStudent;

    ArrayList<Student> ArrayListStudent;
    database database;
    adapterStudent adapterStudent;
    int id_subject = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbarStudent = findViewById(R.id.toolbarStudent);
        listViewStudent = findViewById(R.id.listviewStudent);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject",0);

        //hỗ trợ toolbar
        setSupportActionBar(toolbarStudent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListStudent = new ArrayList<>();

        ArrayListStudent.clear();

        //đối tượng sinh viên id môn học = ?
        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()) {

            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday = cursor.getString(4);

            ArrayListStudent.add(new Student(id, name, sex, code, birthday,id_sub));

        }
        adapterStudent = new adapterStudent(ActivityStudent.this, ArrayListStudent);
        listViewStudent.setAdapter(adapterStudent);

        cursor.moveToFirst();
        cursor.close();
    }
    //Nạp một menu add vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent,menu);
        return true;
    }
    //Bắt sự kiện khi click vào Add
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuAddStudent:
                //Chuyển tới màn hình thêm môn học
                Intent intent = new Intent(this,ActivityAddStudent.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(this,ActivitySubjects.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Phương thức xóa student
    public void delete(final int id_student){
        DialogDeleteStudent(id_student);
    }

    public void update(final int id_student){

        Cursor cursor = database.getDataStudent(id_subject);

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);

            if(id == id_student){

                Intent intent = new Intent(ActivityStudent.this,ActivityUpdateStudent.class);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);

                intent.putExtra("id",id_student);
                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }
        }


    }
    public void information(final int pos){


        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(ActivityStudent.this, ActivityInformationStudent.class);
                intent.putExtra("id", pos);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }
        }

    }

    //Dialog delete
    private void DialogDeleteStudent(int id_student) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeletestudent);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = new database(ActivityStudent.this);
                //Xóa trong SQL
                database.DeleteStudent(id_student);
                //Cập nhật lại listview
                Intent intent = new Intent(ActivityStudent.this,ActivityStudent.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                

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
    @Override
    public void onBackPressed() {
        counter++;
        if (counter >=1){
            Intent intent = new Intent(this,ActivitySubjects.class);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }
}