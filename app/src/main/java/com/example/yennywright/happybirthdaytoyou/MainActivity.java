package com.example.yennywright.happybirthdaytoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<PersonDatabase> personDatabaseList;
    List<String> allMonthFullWord = Arrays.asList("january", "february", "march", "april", "may",
                    "june", "july", "august", "september", "october", "november", "december");
    List<String> allMonthAbr = Arrays.asList("jan", "feb", "mar", "apr", "may",
                                            "jun", "jul", "aug", "sept", "oct", "nov", "dec");
    List<String> allMonthInNumber = Arrays.asList("1", "2", "3", "4", "5",
                                                        "6", "7", "8", "9", "10", "11", "12");
    List<String> shortMonth = Arrays.asList("february");
    List<String> mediumMonth = Arrays.asList("april", "june", "september", "november");
    List<String> longMonth = Arrays.asList("january", "march", "may", "july", "august",
            "october", "december");
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

        nameText = findViewById(R.id.edittext_name_input);
        nameText.addTextChangedListener(new TextValidator(nameText) {
            @Override
            public void validate(TextView textView, String text) {
                nameValidator(text);
                finalName = nameText.getText().toString();
            }
        });

        monthText = findViewById(R.id.edittext_month_input);
        monthText.addTextChangedListener(new TextValidator(monthText) {
            @Override
            public void validate(TextView textView, String text) {
                monthValidator(text);
                finalMonth = nameText.getText().toString();

            }
        });

        dayText = findViewById(R.id.edittext_day_input);
        dayText.addTextChangedListener(new TextValidator(dayText) {
            @Override
            public void validate(TextView textView, String text) {
                dayValidator(text);
                String finalDayStr = dayText.getText().toString();
                finalDay = Integer.parseInt(finalDayStr);
            }
        });

        //initializing buttons
        submitButton = findViewById(R.id.button_submittion);
        setOnClick(submitButton, finalName, finalMonth, finalDay);

        resetButton = findViewById(R.id.button_resetstorage);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                resetStorage(view);
            }
        });
    }

    private void setOnClick(Button inButton, String inName, String inMonth, int inDay)
    {
        inButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateIsValidator(inName, inDay)) {
                    DateSet newDate = new DateSet(inMonth, inDay);
                    PersonDatabase person = new PersonDatabase(inName, newDate);

                    //adding data to persistent storage
                    personDatabaseList.add(person);
                    EntryCount++;

                    //TODO: compare dateset to find similar dates then display the names

                    countDisplayBox = findViewById(R.id.textview_count_display);
                    countDisplayBox.setText(personDatabaseList.size());
                }
            }
        });
    }

    public void resetStorage(View view) {
        personDatabaseList.clear();
        EntryCount = personDatabaseList.size();

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

    public void nameValidator(String inName)
    {
        if(!isAlphanumeric(inName)) {
            nameText.setError("Accept Alphanumeric only.");
        }
    }

    public void monthValidator(String inMonth)
    {
        if(allMonthInNumber.contains(inMonth) == false && allMonthAbr.contains(inMonth) == false
                && allMonthFullWord.contains(inMonth) == false){
            monthText.setError("Input is invalid.");
        }
    }

    public void dayValidator(String inDay)
    {
        if(isAlphanumeric(inDay)) {
            dayText.setError("Accept integer only.");
        }
    }


    public boolean dateIsValidator(String inMonth, int inDay)
    {
        boolean valid = false;
        if(shortMonth.contains(inMonth)){
            if(inDay < 1 && inDay > 29){
                valid = true;
            }
        }
        else if(mediumMonth.contains(inMonth)){
            if(inDay < 1 && inDay > 30){
                valid = true;
            }
        }
        else if(longMonth.contains(inMonth)){
            if(inDay < 1 && inDay > 31){
                valid = true;
            }
        }

        return valid;
    }


}