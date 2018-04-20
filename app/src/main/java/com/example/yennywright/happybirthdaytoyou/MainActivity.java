package com.example.yennywright.happybirthdaytoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Person> personList;
    List<String> months = Arrays.asList("january", "february", "march", "april", "may",
                    "june", "july", "august", "september", "october", "november", "december",
            "jan", "feb", "mar", "apr", "jun", "jul", "aug", "sept", "oct", "nov", "dec",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

    TextView outFinalMessageBox;
    TextView countDisplayBox;
    int EntryCount = 0;
    EditText nameText;
    EditText monthText;
    EditText dayText;
    Button submitButton;
    Button resetButton;
    String finalName;
    String finalMonth;
    Integer finalDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        outFinalMessageBox = findViewById(R.id.textview_output_mssg);
        countDisplayBox = findViewById(R.id.textview_count_display);

        //NAME field
        nameText = findViewById(R.id.edittext_name_input);
        nameText.addTextChangedListener(new TextValidator(nameText) {
            @Override
            public void validate(TextView textView, String text) {
                String userName = nameText.getText().toString();
                if(nameValidator(userName)){
                    finalName = userName;
                }
            }
        });

        //MONTH field
        monthText = findViewById(R.id.edittext_month_input);
        monthText.addTextChangedListener(new TextValidator(monthText) {
            @Override
            public void validate(TextView textView, String text) {
                String userMonth = monthText.getText().toString();
                if(monthValidator(userMonth)){
                    finalMonth = userMonth;
                }
            }
        });

        //DAY field
        dayText = findViewById(R.id.edittext_day_input);
        dayText.addTextChangedListener(new TextValidator(dayText) {
            @Override
            public void validate(TextView textView, String text) {
                String finalDayStr = dayText.getText().toString();
                if(dayValidator(finalDayStr)){
                    finalDay = Integer.parseInt(finalDayStr);
                }
            }
        });

        //initializing buttons
        //SUBMIT button
        submitButton = findViewById(R.id.button_submittion);
        submitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(finalName == null){
                    nameText.setError("Please enter your name.");
                }
                if(finalMonth == null){
                    monthText.setError("Please enter month.");
                }
                if(finalDay == null){
                    dayText.setError("Please enter day.");
                }

                if (dateIsValid(finalMonth, finalDay)) {
                    DateSet newDate = new DateSet(finalMonth, finalDay);
                    Person newPerson = new Person(finalName, newDate);

                    //adding data to persistent storage
                    personList.add(newPerson);
                    EntryCount = personList.size();
                    countDisplayBox.setText(EntryCount);

                    //compare dateset to find similar dates then display the names
                    for(Person p: personList) {
                        if (p.getUserBirthday().equals(newDate)) {

                            String displayResult = p.getUserName() + newPerson.getUserName();
                            outFinalMessageBox.setText("Happy Birthday: " + displayResult);
                        }
                    }
                }
            }
        });

        //RESET button
        resetButton = findViewById(R.id.button_resetstorage);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                resetStorage();
            }
        });
    }

    public void resetStorage() {
        personList.clear();
        EntryCount = personList.size();

        countDisplayBox.setText(EntryCount);
        outFinalMessageBox.setText("Storage is empty.");
    }

    public boolean isAlphanumeric(String str)
    {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        return true;
    }

    public boolean nameValidator(String inName)
    {
        if(!isAlphanumeric(inName)) {
            nameText.setError("Accept Alphanumeric only.");
            return false;
        }
        return true;
    }

    public boolean monthValidator(String inMonth)
    {
        if(months.contains(inMonth.toLowerCase()) == false){
            monthText.setError("Input is invalid.");
            return false;
        }
        return true;
    }

    public boolean dayValidator(String inDay)
    {
        if(!isNumeric(inDay)) {
            dayText.setError("Accept integer only.");
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String inDay)
    {
        return inDay.matches("-?\\d+(.\\d+)?");
    }


    public boolean dateIsValid(String inMonth, int inDay)
    {
        boolean valid = false;
        String inMonthLowCase = inMonth.toLowerCase();
        if(inMonthLowCase.matches("january")|| inMonthLowCase.matches("jan")||inMonth == "1"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("february")||inMonthLowCase.matches("feb")||inMonth == "2"){
            if(inDay >= 1 && inDay <= 29){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("march")||inMonthLowCase.matches("mar")||inMonth == "3"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("april")||inMonthLowCase.matches("apr")||inMonth == "4"){
            if(inDay >= 1 && inDay <= 30){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("may")||inMonth == "5"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("june")||inMonthLowCase.matches("jun")||inMonth == "6"){
            if(inDay >= 1 && inDay <= 30){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("july")||inMonthLowCase.matches("jul")||inMonth == "7"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("august")||inMonthLowCase.matches("aug")||inMonth == "8"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("september")||inMonthLowCase.matches("sept")||inMonth == "9"){
            if(inDay >= 1 && inDay <= 30){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("october")||inMonthLowCase.matches("oct")||inMonth == "10"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("november")||inMonthLowCase.matches("nov")||inMonth == "11"){
            if(inDay >= 1 && inDay <= 30){
                valid = true;
            }
        }
        else if(inMonthLowCase.matches("december")||inMonthLowCase.matches("dec")||inMonth == "12"){
            if(inDay >= 1 && inDay <= 31){
                valid = true;
            }
        }
        return valid;
    }
}