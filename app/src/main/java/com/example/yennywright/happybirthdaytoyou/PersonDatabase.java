package com.example.yennywright.happybirthdaytoyou;

public class PersonDatabase {
    private String userName;
    private DateSet userBirthdate;

    public void setUserName(String inName){
        userName = inName;
    }

    public void setUserMonth(DateSet inDate){
        userBirthdate = inDate;
    }

    public String getUserName(){
        return userName;
    }

    public DateSet getUserBirthdate() {
        return userBirthdate;
    }

    public PersonDatabase (String inName, DateSet inDate){
        userName = inName;
        userBirthdate = inDate;
    }
}
