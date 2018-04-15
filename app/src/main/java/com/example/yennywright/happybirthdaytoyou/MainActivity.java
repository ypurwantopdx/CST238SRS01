package com.example.yennywright.happybirthdaytoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<PersonDatabase> personDatabaseList;
    Validator validator;
    int EntryCount = 0;
    //public static String EXTRA_MESSAGE = "com.example.yennywright.happybirthdaytoyou.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views
        EditText nameText = findViewById(R.id.edittext_name_input);
        String nameTextStr = nameText.getText().toString();
        nameText.addTextChangedListener(validator.nameTextWatcher);

        EditText monthText = findViewById(R.id.edittext_month_input);
        monthText.addTextChangedListener(validator.monthTextWatcher);
        String monthTextStr = monthText.getText().toString();

        EditText dayText = findViewById(R.id.edittext_day_input);
        dayText.addTextChangedListener(validator.dayTextWatcher);
        String dayTextStr = dayText.getText().toString();

       //initializing buttons
        Button submitButton = findViewById(R.id.button_submittion);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInfo(nameTextStr, monthTextStr, dayTextStr);
            }
        });

        Button resetButton = findViewById(R.id.button_resetstorage);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                resetStorage(view);
            }
        });
    }

    public void submitInfo(String nameTextStr, String monthTextStr, String dayTextStr) {

        //save data to persistent storage
        final DateSet newDate = new DateSet(monthTextStr, dayTextStr);
        PersonDatabase person = new PersonDatabase(nameTextStr, newDate);
        personDatabaseList.add(person);
        EntryCount++;

        //search equal data
        if (personDatabaseList.stream().anyMatch(o -> o.getUserBirthday().equals(newDate))) {
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
        Toast.makeText(getApplicationContext(), "Storage is cleared.",
                Toast.LENGTH_SHORT).show();
    }
}
