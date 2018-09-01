package com.androidangel.projectapp.data;

public class Student {

    private long id;
    private String studentNumber;
    private String name;
    private String age;
    private String gender;
    private String yearLevel;
    private String homeRoom;
    private String address;
    private String parentName;
    private String contactNo;
    private String birthday;
    private String image;
    private String presentDays;



    public Student() {
    }
    public Student(String studentNumber, String name, String age, String gender,
                   String yearLevel, String homeRoom, String address, String parentName,
                   String contactNo, String birthday, String image) {

        this.studentNumber = studentNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.yearLevel = yearLevel;
        this.homeRoom = homeRoom;
        this.address = address;
        this.parentName = parentName;
        this.contactNo = contactNo;
        this.birthday = birthday;
        this.image = image;

        }
         public Student(String studentNumber, String name, String gender,
                String yearLevel, String homeRoom, String address, String parentName,
                String contactNo, String birthday, String image) {

        this.studentNumber = studentNumber;
             this.name = name;
             this.gender = gender;
             this.yearLevel = yearLevel;
             this.homeRoom = homeRoom;
             this.address = address;
             this.parentName = parentName;
             this.contactNo = contactNo;
             this.birthday = birthday;
             this.image = image;
    }
    public Student(String presentDays){
        this.presentDays = presentDays;
    }

    public String getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(String presentDays) {
        this.presentDays = presentDays;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public String getHomeRoom() {
        return homeRoom;
    }

    public String getAddress() {
        return address;
    }

    public String getParentName() {
        return parentName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getImage() {
        return image;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHomeRoom(String homeRoom) {
        this.homeRoom = homeRoom;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
}


}
