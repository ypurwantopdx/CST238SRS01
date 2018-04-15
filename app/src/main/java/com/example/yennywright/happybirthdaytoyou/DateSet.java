package com.example.yennywright.happybirthdaytoyou;

public class DateSet {
    private String birthMonth;
    private String birthDay;

    public void setBirthMonth(String inMonth){
        birthMonth = inMonth;
    }

    public void setBirthDay(String inDay){
        birthDay = inDay;
    }

    public DateSet (String inMonth, String inDay){
        birthMonth = inMonth;
        birthDay = inDay;
    }
}
