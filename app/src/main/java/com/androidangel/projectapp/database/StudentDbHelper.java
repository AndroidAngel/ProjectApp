package com.androidangel.projectapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.androidangel.projectapp.data.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentDbHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 3;
    public static String TABLE_NAME = "Student";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STUDENT_NUMBER = "studentNumber";
    public static final String COLUMN_YEAR_LEVEL = "yearLevel";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_HOMEROOM = "homeRoom";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_PARENT_NAME = "parentName";
    public static final String COLUMN_CONTACT_NO = "contactNo";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_PRESENT_DAY = "presentDay";


    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_STUDENT_NUMBER + " TEXT NOT NULL, " +
                COLUMN_YEAR_LEVEL + " TEXT NOT NULL, " +
                COLUMN_AGE + " TEXT NOT NULL, " +
                COLUMN_HOMEROOM + " TEXT NOT NULL, " +
                COLUMN_BIRTHDAY + " TEXT NOT NULL, " +
                COLUMN_GENDER + " TEXT," +
                COLUMN_PARENT_NAME + " TEXT NOT NULL, " +
                COLUMN_CONTACT_NO + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL, " +
                COLUMN_PRESENT_DAY + " TEXT," +
                COLUMN_IMAGE + " BLOB NOT NULL);"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + " ;");
        this.onCreate(db);
    }

        public void saveNewStudent(Student student) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, student.getName());
            values.put(COLUMN_STUDENT_NUMBER, student.getStudentNumber());
            values.put(COLUMN_AGE, student.getAge());
            values.put(COLUMN_GENDER, student.getGender());
            values.put(COLUMN_YEAR_LEVEL, student.getYearLevel());
            values.put(COLUMN_HOMEROOM, student.getHomeRoom());
            values.put(COLUMN_ADDRESS, student.getAddress());
            values.put(COLUMN_PARENT_NAME, student.getParentName());
            values.put(COLUMN_CONTACT_NO, student.getContactNo());
            values.put(COLUMN_BIRTHDAY, student.getBirthday());
            values.put(COLUMN_PRESENT_DAY, student.getPresentDay());
            values.put(COLUMN_IMAGE, student.getImage());

            // insert
            db.insert(TABLE_NAME,null, values);
            db.close();
        }


    public List<Student> studentList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NAME + " ASC ";
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }
        List<Student> studentLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student;
        if (cursor.moveToFirst()) {
            do {
                student = new Student();
                student.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                student.setStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)));
                student.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                student.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                student.setYearLevel(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR_LEVEL)));
                student.setHomeRoom(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEROOM)));
                student.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                student.setParentName(cursor.getString(cursor.getColumnIndex(COLUMN_PARENT_NAME)));
                student.setContactNo(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NO)));
                student.setBirthday(cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDAY)));
                student.setPresentDay(cursor.getString(cursor.getColumnIndex(COLUMN_PRESENT_DAY)));
                student.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                studentLinkedList.add(student);
            } while (cursor.moveToNext());
        }


        return studentLinkedList;
    }

    /**Query only 1 record**/
    public Student getStudent(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);
        Student receivedStudent = new Student();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedStudent.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            receivedStudent.setStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)));
            receivedStudent.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
            receivedStudent.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
            receivedStudent.setYearLevel(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR_LEVEL)));
            receivedStudent.setHomeRoom(cursor.getString(cursor.getColumnIndex(COLUMN_HOMEROOM)));
            receivedStudent.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
            receivedStudent.setParentName(cursor.getString(cursor.getColumnIndex(COLUMN_PARENT_NAME)));
            receivedStudent.setContactNo(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NO)));
            receivedStudent.setBirthday(cursor.getString(cursor.getColumnIndex(COLUMN_BIRTHDAY)));
            receivedStudent.setPresentDay(cursor.getString(cursor.getColumnIndex(COLUMN_PRESENT_DAY)));
            receivedStudent.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
        }
        return receivedStudent;

    }

    /**
     * delete record
     **/
    public void deleteStudentRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**
     * update record
     **/
    public void updateStudentRecord(long studentId, Context context, Student updatedStudent) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE  " + TABLE_NAME + " SET name ='"
                + updatedStudent.getName()
                + "', studentNumber ='" + updatedStudent.getStudentNumber()
                + "', name ='" + updatedStudent.getName()
                + "', age ='" + updatedStudent.getAge()
                + "', gender ='" + updatedStudent.getGender()
                + "', yearLevel ='" + updatedStudent.getYearLevel()
                + "', homeRoom ='" + updatedStudent.getHomeRoom()
                + "', address ='" + updatedStudent.getAddress()
                + "', parentName ='" + updatedStudent.getParentName()
                + "', contactNo ='" + updatedStudent.getContactNo()
                + "', birthday ='" + updatedStudent.getBirthday()
                + "', image ='" + updatedStudent.getImage()
               + "', presentDay ='" + updatedStudent.getPresentDay()
                + "'  WHERE _id='" + studentId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();

    }

//    public static String stringSeparator = "__,__";
//        Date dateList = new Date();
//        public static String convertArrayToString(String[] array){
//            String dateList = "";
//            for (int i = 0;i<array.length; i++) {
//                if(i<array.length-1){
//                    dateList = dateList + stringSeparator;
//
//                }
//            }
//            return dateList;
//    }
//    public static String[] convertStringToArray(String dateList){
//        String[] arr = dateList.split(stringSeparator);
//        return arr;
//    }
    }


