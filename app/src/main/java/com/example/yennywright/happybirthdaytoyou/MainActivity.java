package com.example.yennywright.happybirthdaytoyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<PersonDatabase> personDatabaseList;
    List<String> shortMonth = Arrays.asList("february");
    List<String> mediumMonth = Arrays.asList("april", "june", "september", "november");
    List<String> longMonth = Arrays.asList("january", "march", "may", "july",
                                            "august", "october", "december");
    int EntryCount = 0;
    EditText nameText;
    Spinner monthText;
    Spinner dayText;
    String monthSelected;
    String daySelected;
    Button submitButton;
    Button resetButton;
    TextView outputFinalMessageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing Views
        outputFinalMessageBox = findViewById(R.id.message_text);
        nameText = findViewById(R.id.name_input);
        nameText.addTextChangedListener(new TextValidator(nameText) {
            @Override
            public void validate(TextView nameText, String text) {
                if(!isAlphanumeric(text)){
                    nameText.setError("Invalid input.");
                }
                else if (text == null){
                    nameText.setError("Please enter you name.");
                }
            }
        });

        //Initializing Spinners
        monthText = findViewById(R.id.month_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthText.setAdapter(adapter1);

        dayText = findViewById(R.id.day_spinner);
        //Event listener for monthSpinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                MainActivity.this, R.array.dayDefault_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayText.setAdapter(adapter2);

        monthText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id){
                monthSelected = monthText.getSelectedItem().toString();

                if(shortMonth.contains(monthSelected.toLowerCase()))
                {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                            MainActivity.this, R.array.day29_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dayText.setAdapter(adapter2);
                }
                else if(mediumMonth.contains(monthSelected.toLowerCase()))
                {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                            MainActivity.this, R.array.day30_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dayText.setAdapter(adapter2);
                }
                else //(longMonth.contains(monthSelected))
                {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                            MainActivity.this, R.array.day31_array, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dayText.setAdapter(adapter2);
                }
                daySelected = dayText.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView){
                //Do nothing
            }
        });

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfo(nameText.getText().toString(), monthSelected, Integer.parseInt(daySelected));
            }
        });

        resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStorage(v);
            }
        });
    }

     public void submitInfo(String inNameStr, String inMonthStr, int inDayStr) {

        DateSet newDate = new DateSet(inMonthStr, inDayStr);
        PersonDatabase newPerson = new PersonDatabase(inNameStr, newDate);

        //adding data to persistent storage
        personDatabaseList.add(newPerson);
        EntryCount++;

        //if DATESET has equal value
        if (personDatabaseList.contains(newDate)) {
            //TODO: print out the two names with the same birth date
            outputFinalMessageBox.setText("Print out names here.");
        }

        //display the entry count
        TextView outCountView = findViewById(R.id.count_display);
        outCountView.setText(EntryCount);
    }

    public void resetStorage(View view) {
        personDatabaseList.clear();
        EntryCount = 0;

        TextView clearCount = findViewById(R.id.count_display);
        clearCount.setText(EntryCount);
        TextView clearMessage = findViewById(R.id.message_text);
        clearMessage.setText("Storage has been emptied.");
    }

    public boolean isAlphanumeric(String str)
    {
        char[] charArray = str.toCharArray();
        for(char c:charArray){
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        return true;
    }
}
