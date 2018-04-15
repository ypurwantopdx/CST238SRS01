package com.example.yennywright.happybirthdaytoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> Months = Arrays.asList("january", "february", "march", "april", "may",
            "june", "july", "august", "september", "october",
            "november", "december");
    List<PersonDatabase> personDatabaseList;
    int EntryCount = 0;
    //public static String EXTRA_MESSAGE = "com.example.yennywright.happybirthdaytoyou.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameText = findViewById(R.id.edittext_name_input);
        String nameTextStr = nameText.getText().toString();
        final boolean nameBool = NameTextValidator(nameTextStr);

        EditText monthText = findViewById(R.id.edittext_month_input);
        String monthTextStr = monthText.getText().toString();
        final boolean monthBool = MonthTextValidator(monthTextStr);

        EditText dayText = findViewById(R.id.edittext_day_input);
        String dayTextStr = dayText.getText().toString();
        final boolean dayBool = DayTextValidator(monthTextStr, dayTextStr);

        if (nameBool == true && monthBool == true && dayBool == true) {

            Button submitButton = findViewById(R.id.button_submittion);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    submitInfo(nameTextStr, monthTextStr, dayTextStr);
                }
            });
        }

        Button resetButton = findViewById(R.id.button_resetstorage);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetStorage(view);
            }
        });
    }

    public boolean NameTextValidator(String inName) {
        if (inName == null) {
            Toast.makeText(getApplicationContext(), "Name field is empty.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    public boolean MonthTextValidator(String inMonth) {
        if (inMonth == null) {
            Toast.makeText(getApplicationContext(), "Month field is empty.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        if (Months.contains(inMonth.toLowerCase()) == false) {
            Toast.makeText(getApplicationContext(), "Month is out of range.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    public boolean DayTextValidator(String inMonth, String inDay) {
        if (inDay == null) {
            Toast.makeText(getApplicationContext(), "Day field is empty", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        if (inMonth.toLowerCase().equals("january")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("february")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 28) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("march")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("april")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 30) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("may")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("june")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 30) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("july")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("august")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("september")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 30) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("october")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("november")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 30) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("december")) {
            if (Integer.parseInt(inDay) < 1 || Integer.parseInt(inDay) > 31) {
                Toast.makeText(getApplicationContext(), "Day is out of range.", Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        }
        return true;
    }

    public void submitInfo(String nameTextStr, String monthTextStr, String dayTextStr) {

        //save data to persistent storage
        final DateSet newDate = new DateSet(monthTextStr, dayTextStr);
        PersonDatabase person = new PersonDatabase(nameTextStr, newDate);
        personDatabaseList.add(person);
        EntryCount++;

        //search equal data
        if (personDatabaseList.stream().anyMatch(o -> o.getUserBirthdate().equals(newDate))) {
            TextView outView = findViewById(R.id.textview_output_mssg);
            outView.setText(person.getUserName());
        }

        TextView outCountView = findViewById(R.id.textview_count_display);
        outCountView.setText(EntryCount);
    }

    public void resetStorage(View view){
        personDatabaseList.clear();
        EntryCount = 0;

        TextView textView = findViewById(R.id.textview_count_display);
        textView.setText("Empty");
        Toast.makeText(getApplicationContext(), "Storage is cleared.", Toast.LENGTH_SHORT)
                .show();
    }

}
