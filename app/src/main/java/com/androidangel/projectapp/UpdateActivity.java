package com.androidangel.projectapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidangel.projectapp.data.Student;
import com.androidangel.projectapp.database.StudentDbHelper;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    private static final String TAG = "UpdateActivity";

    private EditText mNameET;
    private EditText mStudentNumberET;
    private EditText mAgeSpinner;
    private EditText mGenderET;
    private EditText mAddressET;
    private TextView mBirthdayET;
    private EditText mYearLevelET;
    private EditText mParentNameET;
    private EditText mImageUrlET;
    private EditText mHomeroomET;
    private EditText mContactNo;

    private Button mUpdateBtn;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private StudentDbHelper dbHelper;
    private long receivedStudentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        mNameET = findViewById(R.id.nameeditupdate);
        mStudentNumberET = findViewById(R.id.studentnoeditupdate);
        mAgeSpinner = findViewById(R.id.ageedittextupdate);
        mGenderET = findViewById(R.id.genderedittextupdate);
        mYearLevelET = findViewById(R.id.yearleveledittextupdate);
        mHomeroomET = findViewById(R.id.homeroomedittextupdate);
        mAddressET = findViewById(R.id.addressedittextupdate);
        mParentNameET = findViewById(R.id.parentnameedittextupdate);
        mContactNo = findViewById(R.id.contactnoedittextupdate);
        mBirthdayET = findViewById(R.id.birthdayedittextupdate);
        mImageUrlET = findViewById(R.id.imageurlvieweditupdate);

        mUpdateBtn = findViewById(R.id.submitupdate);

        dbHelper = new StudentDbHelper(this);

        try {
            receivedStudentId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Student queriedStudent = dbHelper.getStudent(receivedStudentId);


        mNameET.setText(queriedStudent.getName());
        mStudentNumberET.setText(queriedStudent.getStudentNumber());
        mAgeSpinner.setText(queriedStudent.getAge());
        mGenderET.setText(queriedStudent.getGender());
        mYearLevelET.setText(queriedStudent.getYearLevel());
        mHomeroomET.setText(queriedStudent.getHomeRoom());
        mAddressET.setText(queriedStudent.getAddress());
        mParentNameET.setText(queriedStudent.getParentName());
        mContactNo.setText(queriedStudent.getContactNo());
        mBirthdayET.setText(queriedStudent.getBirthday());
        mImageUrlET.setText(queriedStudent.getImage());


        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();

            }
        });
        mBirthdayET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mBirthdayET.setText(date);
            }
        };


    }

    private void updateStudent() {
        String studentNumber = mStudentNumberET.getText().toString();
        String name = mNameET.getText().toString().trim();
        String age = mAgeSpinner.getText().toString().trim();
        String gender = mGenderET.getText().toString().trim();
        String yearLevel = mYearLevelET.getText().toString().trim();
        String homeRoom = mHomeroomET.getText().toString().trim();
        String address = mAddressET.getText().toString().trim();
        String parentName = mParentNameET.getText().toString().trim();
        String contactNo = mContactNo.getText().toString().trim();
        String birthday = mBirthdayET.getText().toString().trim();
        String image = mImageUrlET.getText().toString().trim();

        if(name.isEmpty()){
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }
        if(studentNumber.isEmpty()){
            Toast.makeText(this, "You must enter a student number", Toast.LENGTH_SHORT).show();
        }
        if(age.isEmpty()){
            Toast.makeText(this, "You must enter an age", Toast.LENGTH_SHORT).show();
        }
        if(gender.isEmpty()){
            Toast.makeText(this, "You must enter a gender", Toast.LENGTH_SHORT).show();
        }
        if(yearLevel.isEmpty()){
            Toast.makeText(this, "You must enter a Grade Level", Toast.LENGTH_SHORT).show();
        }
        if(homeRoom.isEmpty()){
            Toast.makeText(this, "You must enter a Section", Toast.LENGTH_SHORT).show();
        }
        if(address.isEmpty()){
            Toast.makeText(this, "You must enter an Address", Toast.LENGTH_SHORT).show();
        }
        if(parentName.isEmpty()){
            Toast.makeText(this, "You must enter a Student parent name", Toast.LENGTH_SHORT).show();
        }
        if(contactNo.isEmpty()){
            Toast.makeText(this, "You must enter a Contact Number", Toast.LENGTH_SHORT).show();
        }
        if(birthday.isEmpty()){
            Toast.makeText(this, "You must enter a Student Birthday", Toast.LENGTH_SHORT).show();
        }
        if(image.isEmpty()){
            Toast.makeText(this, "You must enter an Image Link", Toast.LENGTH_SHORT).show();
        }
        Student updatedStudent = new Student(studentNumber, name, age, gender, yearLevel, homeRoom,
                address, parentName, contactNo, birthday, image);

        dbHelper.updateStudentRecord(receivedStudentId, this, updatedStudent);

        goBackHome();
    }
    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));


    }
}