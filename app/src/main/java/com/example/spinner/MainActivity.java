package com.example.spinner;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner names;
    TextView result;
    ArrayAdapter<String> adapter;
    ArrayList<String> listOfNames;
    Button deleteButton, addButton;
    EditText Name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfNames = new ArrayList<>();
        listOfNames.add("Name Here: ");
        listOfNames.add("Pepsi");
        listOfNames.add("Pol");
        listOfNames.add("Che");
        listOfNames.add("Tin");
        listOfNames.add("Renz");
        listOfNames.add("Lou");
        listOfNames.add("Chan");
        listOfNames.add("Vhen");
        listOfNames.add("Jher");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        names = findViewById(R.id.sprName);
        names.setAdapter(adapter);

        result = findViewById(R.id.tvResult);
        deleteButton = findViewById(R.id.btnDelete);
        Name = findViewById(R.id.edtName);
        addButton = findViewById(R.id.btnAdd);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = Name.getText().toString().trim();
                if (!newName.isEmpty()) {
                    listOfNames.add(newName);
                    adapter.notifyDataSetChanged();
                    Name.setText(""); // Clear the EditText after adding the name
                    Toast.makeText(MainActivity.this, "Name added successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedIndex = names.getSelectedItemPosition();
                if (selectedIndex > 0) {
                    listOfNames.remove(selectedIndex);
                    adapter.notifyDataSetChanged();
                    names.setSelection(0); // Select the first item after deletion
                } else {
                    Toast.makeText(MainActivity.this, "Please select a name to delete.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showSelectedName();
    }

    public void showSelectedName() {
        names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout containerNames = findViewById(R.id.containerNames);
                if (i > 0) {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText("Name: " + listOfNames.get(i));
                    containerNames.addView(textView);
                    Toast.makeText(getApplicationContext(), " " + listOfNames.get(i), Toast.LENGTH_SHORT).show();
                } else {
                    // Clear the ScrollView when no name is selected
                    containerNames.removeAllViews();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Implement as needed
            }
        });
    }
}
