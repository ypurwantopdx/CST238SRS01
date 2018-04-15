package com.example.yennywright.happybirthdaytoyou;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Validator {
    List<String> Months = Arrays.asList("january", "february", "march", "april", "may",
            "june", "july", "august", "september", "october",
            "november", "december");

    Activity context;

    public final TextWatcher nameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable inName) {
            if (inName == null) {
               Toast.makeText(context.getApplicationContext(), "Name field is empty.",
                       Toast.LENGTH_SHORT).show();
            }
        }
    };

    public final TextWatcher monthTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable inMonth) {
            if (inMonth == null) {
                Toast.makeText(context.getApplicationContext(), "Month field is empty.",
                        Toast.LENGTH_SHORT).show();
            }

            if (Months.contains(inMonth) == false) {    //probable BUG for not casting toLower
                Toast.makeText(context.getApplicationContext(), "Month is out of range.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    public final TextWatcher dayTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable inDay) {
            if (inDay == null) {
                Toast.makeText(context.getApplicationContext(), "Month field is empty.",
                        Toast.LENGTH_SHORT).show();
            }

            Scanner scan = new Scanner(System.in);
            while (!scan.hasNextInt())
            {
                scan.next();
                Toast.makeText(context.getApplicationContext(), "Please enter integer values.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    //Date sets validator
    public boolean DateSetValidator(String inMonth, int inDay) {
        if (inDay == 0) {
            Toast.makeText(context.getApplicationContext(), "Day field is empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        if (inMonth.toLowerCase().equals("january")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("february")) {
            if (inDay < 1 || inDay > 28) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("march")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("april")) {
            if (inDay< 1 || inDay > 30) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("may")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("june")) {
            if (inDay < 1 || inDay > 30) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("july")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("august")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("september")) {
            if (inDay < 1 || inDay > 30) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("october")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("november")) {
            if (inDay < 1 || inDay > 30) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (inMonth.toLowerCase().equals("december")) {
            if (inDay < 1 || inDay > 31) {
                Toast.makeText(context.getApplicationContext(), "Day is out of range.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
