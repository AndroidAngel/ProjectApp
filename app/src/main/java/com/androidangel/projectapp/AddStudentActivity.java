package com.androidangel.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidangel.projectapp.data.Student;
import com.androidangel.projectapp.database.StudentDbHelper;

public class AddStudentActivity extends AppCompatActivity {



    private EditText mNameET;
    private EditText mStudentNumberET;
    private EditText mAgeSpinner;
    private EditText mGenderET;
    private EditText mAddressET;
    private EditText mBirthdayET;
    private EditText mYearLevelET;
    private EditText mParentNameET;
    private EditText mImageUrlET;
    private EditText mHomeroomET;
    private EditText mContactNo;



    Button submitBtn;
    StudentDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        mStudentNumberET = findViewById(R.id.studentnoedit);
        mNameET = findViewById(R.id.nameedit);
        mAgeSpinner = findViewById(R.id.ageSpinner);
        mGenderET = findViewById(R.id.genderedittext);
        mYearLevelET = findViewById(R.id.yearleveledittext);
        mHomeroomET = findViewById(R.id.homeroomedittext);
        mAddressET = findViewById(R.id.addressedittext);
        mParentNameET = findViewById(R.id.parentnameedittext);
        mContactNo = findViewById(R.id.contactnoedittext);
        mBirthdayET = findViewById(R.id.birthdayedittext);
        mImageUrlET = findViewById(R.id.imageurlviewedit);
        submitBtn = findViewById(R.id.submit);

        dbHelper = new StudentDbHelper(this);
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //call the save student method
                        saveStudent();
                    }
                });

            }
    private void saveStudent(){

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
                dbHelper = new StudentDbHelper(this);

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
                Student student = new Student(studentNumber, name, age , gender, yearLevel, homeRoom,
                        address, parentName, contactNo, birthday, image);

                dbHelper.saveNewStudent(student);

                goBackHome();

}
    private void goBackHome(){
        startActivity(new Intent(AddStudentActivity.this, MainActivity.class));
    }
}


