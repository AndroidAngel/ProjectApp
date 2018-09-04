package com.androidangel.projectapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidangel.projectapp.model.Student;
import com.androidangel.projectapp.database.StudentDbHelper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.nameP)
    TextView namePTV;
    @BindView(R.id.studentnoP)
    TextView studentNoPTV;
    @BindView(R.id.sectionP)
    TextView homeRoomPTV;
    @BindView(R.id.gradeP)
    TextView yearLevelPTV;
    @BindView(R.id.ageP)
    TextView agePTV;
    @BindView(R.id.genderP)
    TextView genderPTV;
    @BindView(R.id.birthdayP)
    TextView birthdayPTV;
    @BindView(R.id.contactnoP)
    TextView contactNoPTV;
    @BindView(R.id.addressP)
    TextView addressPTV;
    @BindView(R.id.parentnameP)
    TextView parentNamePTV;
    @BindView(R.id.profileP)
    ImageView profilePTV;

    private StudentDbHelper dbHelper;
    private long receivedStudentId;

    private Context mContext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        dbHelper = new StudentDbHelper(this);
        try {
            receivedStudentId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Student queryStudent = dbHelper.getStudent(receivedStudentId);

        namePTV.setText(queryStudent.getName());
        studentNoPTV.setText(queryStudent.getStudentNumber());
        homeRoomPTV.setText(queryStudent.getHomeRoom());
        yearLevelPTV.setText(queryStudent.getYearLevel());
        agePTV.setText(queryStudent.getAge());
        genderPTV.setText(queryStudent.getGender());
        birthdayPTV.setText(queryStudent.getBirthday());
        contactNoPTV.setText(queryStudent.getContactNo());
        addressPTV.setText(queryStudent.getAddress());
        parentNamePTV.setText(queryStudent.getParentName());
        Picasso.with(mContext).load(queryStudent.getImage()).into(profilePTV);



    }





}
