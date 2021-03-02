package com.gmail.carlos.map524_lab2_carlosfalconett;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import java.util.Arrays;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {
    String colorList[] = {};
    String selectedColor[] = {};
    Button buyNow;
    RadioButton core1;
    RadioButton core2;
    RadioButton core3;
    CheckBox mouse;
    CheckBox keyboard;
    CheckBox screen;
    AlertDialog.Builder builder;
    Switch now;

    Spinner colorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorSpinner = (Spinner)findViewById(R.id.color_spinner);
        builder = new AlertDialog.Builder(this);
        buyNow = (Button) findViewById(R.id.button1);
        core1 = (RadioButton) findViewById(R.id.core1);
        core2 = (RadioButton) findViewById(R.id.core2);
        core3 = (RadioButton) findViewById(R.id.core3);
        mouse = (CheckBox) findViewById(R.id.accessory1);
        keyboard = (CheckBox) findViewById(R.id.accessory2);
        screen = (CheckBox) findViewById(R.id.accessory3);
        now = (Switch) findViewById(R.id.switch1);

        colorList = new String[] {"Green", "Red", "Blue", "Yellow"};
        selectedColor = new String[10];

        Arrays.fill(selectedColor, "");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_row_design, R.id.colorTextID, colorList);
        colorSpinner.setAdapter(arrayAdapter);

        buyNow.setOnClickListener(v -> {
            String selectedColor =  colorSpinner.getSelectedItem().toString();
            if(checkValidity()) {
                Toast.makeText(getApplicationContext(), R.string.order_message, Toast.LENGTH_SHORT).show();

                String core = "the core within your pc is ";
                String accessories = " and along with it comes";
                String shippingText = "";

                if(core1.isChecked() && !core2.isChecked() && !core3.isChecked()){
                    core += "intel i5";
                }
                else if(!core1.isChecked() && core2.isChecked() && !core3.isChecked()){
                    core += "intel i7";
                }
                else {
                    core += "intel i9";
                }
                if(!hasAccessories()){
                    accessories = "";
                }
                if(mouse.isChecked()){
                    accessories += " a mouse";
                }
                if(keyboard.isChecked()){
                    if(accessories.equals(" and along with it comes")){
                        accessories += " a keyboard";
                    } else{
                        accessories += ", keyboard";
                    }

                }
                if(screen.isChecked()){
                    if(accessories.equals(" and along with it comes")){
                        accessories += " a screen";
                    } else{
                        accessories += ", screen";
                    }

                }
                if(now.isChecked()){
                    shippingText += "the pc should be arriving later today.";
                } else {
                    shippingText += "it should take 7000 business days for the arrival of your pc.";
                }

                builder.setMessage("The colour is " + selectedColor + ", " + core + accessories + " and " + shippingText)
                        .setCancelable(true)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Thanks");
                alert.show();
            }
            else {
                Toast.makeText(getApplicationContext(), R.string.incomplete_message, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public boolean checkValidity() {
        boolean valid;

        if(core1.isChecked() || core2.isChecked() || core3.isChecked()){
            valid = true;
        }
        else {
            valid = false;
        }

        return valid;
    }

    public boolean hasAccessories() {
        boolean check = true;

        if(!mouse.isChecked() && !keyboard.isChecked() && !screen.isChecked()) {
            check = false;
        }

        return check;
    }
}