package com.example.yennywright.happybirthdaytoyou;

public class PersonDatabase {
    private String userName;
    private DateSet userBirthday;

    public String getUserName(){
        return userName;
    }

    public DateSet getUserBirthday() {
        return userBirthday;
    }

    public PersonDatabase (String inName, DateSet inDate){
        userName = inName;
        userBirthday = inDate;
    }
}
