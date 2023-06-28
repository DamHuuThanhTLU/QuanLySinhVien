package com.example.qlsinhvien.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.qlsinhvien.ActivityInformationSubject;
import com.example.qlsinhvien.ActivitySubjects;
import com.example.qlsinhvien.R;
import com.example.qlsinhvien.database.database;
import com.example.qlsinhvien.model.Subject;

import java.util.ArrayList;

public class adapterSubjects extends BaseAdapter {
    private ActivitySubjects context;
    //context
    //private Context context;;
    //Mảng subject
    private ArrayList<Subject> ArrayListSubject;

    public adapterSubjects(ActivitySubjects context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    //    public adapterSubjects(Context context, ArrayList<Subject> arrayListSubject) {
//        this.context = context;
//        ArrayListSubject = arrayListSubject;
//    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listsubject, null);

        TextView TextViewSubjectTitle =(TextView) convertView.findViewById(R.id.TextViewSubjectTitle);
        TextView TextViewCredits = (TextView) convertView.findViewById(R.id.TextViewCredits);

        ImageButton imgbtnDelete = (ImageButton) convertView.findViewById(R.id.subjectdelete);
        ImageButton imgbtnUpdate = (ImageButton) convertView.findViewById(R.id.subjecupdate);
        ImageButton imgbtnInformation = (ImageButton) convertView.findViewById(R.id.subjecinformation);

        Subject subject = ArrayListSubject.get(position);

        TextViewCredits.setText(subject.getNumber_of_credits()+"");
        TextViewSubjectTitle.setText(subject.getSubject_title());

        int id = subject.getId_subject();
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
