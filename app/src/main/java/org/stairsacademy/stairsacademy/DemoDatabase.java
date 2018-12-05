package org.stairsacademy.stairsacademy;

import java.util.ArrayList;
import java.util.List;

public class DemoDatabase {

    List<String> user = new ArrayList<String>();

    List<String> password = new ArrayList<String>();

    List<String> classes = new ArrayList<String>();

    List<String> student = new ArrayList<String>();

    List<String> postion = new ArrayList<String>();

    public DemoDatabase(){
        addData();
    }

    public void addData(){
        user.add("graeme");password.add("12345");postion.add("admin");
        user.add("danyal");password.add("54321");postion.add("admin");

        user.add("tom");password.add("98765");postion.add("teacher");
        user.add("sam");password.add("56789");postion.add("teacher");

        user.add("harry");password.add("15963");postion.add("afterSchoolTeacher");
        user.add("thurm");password.add("36951");postion.add("afterSchoolTeacher");

        classes.add("Math");
        classes.add("English");
        classes.add("Music");

        student.add("Josh");
        student.add("Drake");
        student.add("Henry");
        student.add("Hillary");
        student.add("Jennifer");
        student.add("Rana");

    }

}
