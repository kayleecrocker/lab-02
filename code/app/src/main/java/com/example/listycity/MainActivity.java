package com.example.listycity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText newCityInput;
    Button addCity;
    Button deleteCity;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_listView);
        newCityInput = findViewById(R.id.newCity_editText);
        addCity = findViewById(R.id.addCity_Button);
        deleteCity = findViewById(R.id.deleteCity_Button);
        confirm = findViewById(R.id.confirm_button);
        newCityInput.setVisibility(GONE);
        confirm.setVisibility(GONE);


        String [] cities = {"Edmonton", "Vancouver", "Moscow", "Berlin", "Tokyo", "Paris", "Toronto", "London", "New York"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newCityInput.setVisibility(VISIBLE);
                confirm.setVisibility(VISIBLE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = newCityInput.getText().toString();
                if (!input.isEmpty()) {
                    dataList.add(input);
                    cityAdapter.notifyDataSetChanged();
                    newCityInput.setText("");
                }
                newCityInput.setVisibility(GONE);
                confirm.setVisibility(GONE);
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.deleteMessage_TextView).setVisibility(VISIBLE);
                cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dataList.remove(position);
                        cityAdapter.notifyDataSetChanged();
                        cityList.setOnItemClickListener(null);
                        findViewById(R.id.deleteMessage_TextView).setVisibility(GONE);
                    }
                });
            }
        });
    }
}