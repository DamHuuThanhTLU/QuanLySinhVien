package com.example.qlsinhvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qlsinhvien.ActivityStudent;
import com.example.qlsinhvien.ActivitySubjects;
import com.example.qlsinhvien.R;
import com.example.qlsinhvien.model.Student;
import com.example.qlsinhvien.model.Subject;

import java.util.ArrayList;

public class adapterStudent extends BaseAdapter {

    private ActivityStudent context;
    private ArrayList<Student> ArrayListStudent;

    public adapterStudent(ActivityStudent context, ArrayList<Student> arrayListStudent) {
        this.context = context;
        ArrayListStudent = arrayListStudent;
    }

    @Override
    public int getCount() {
        return ArrayListStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.liststudent, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.TextViewStudentName);
        TextView txtcode = (TextView) convertView.findViewById(R.id.TextViewCode);

        ImageButton imgbtnDelete = (ImageButton) convertView.findViewById(R.id.studentdelete);
        ImageButton imgbtnUpdate = (ImageButton) convertView.findViewById(R.id.studentupdate);
        ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.studentinformation);

        Student student = ArrayListStudent.get(position);

        txtName.setText(student.getStudent_name());
        txtcode.setText(student.getStudent_code());

        int id = student.getId_student();
        imgbtnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);

            }
        });
        imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });
        return convertView;
    }
}
