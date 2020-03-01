package com.example.listonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> carList = new ArrayList<>();

    private static void getCars(){
        carList.add("AlfaRomeo");
        carList.add("BMW");
        carList.add("Corvette");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCars();
        final ListView myListView = (ListView) findViewById(R.id.myListView);
        final ArrayAdapter<String> aa;
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,carList);
        myListView.setAdapter(aa);

        Button addCar = (Button) findViewById(R.id.add);
        Button editCar = (Button) findViewById(R.id.edit);
        Button removeCar = (Button) findViewById(R.id.remove);
        Button toinen = (Button) findViewById(R.id.second);
        final EditText line = (EditText) findViewById(R.id.editText);

        addCar.setOnClickListener(new Button.OnClickListener() {
           public void onClick(View arg0) {
               String mark = line.getText().toString();
               carList.add(mark);
               myListView.setAdapter(aa);
           }
        });
        editCar.setOnClickListener(new Button.OnClickListener(){
           public void onClick (View arg0) {
               myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       String selectedCar = carList.get(position);
                       Toast.makeText(getApplicationContext(),"Car Selected : " + selectedCar, Toast.LENGTH_LONG).show();
                       line.setText(selectedCar);
                   }
               });
           }
        });

        removeCar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       String removeCar = carList.get(position);
                       aa.remove(removeCar);
                       aa.notifyDataSetChanged();
                    }
                });
            }
        });
        toinen.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Second Activity Selected !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent);
            }
        });

    }
}
