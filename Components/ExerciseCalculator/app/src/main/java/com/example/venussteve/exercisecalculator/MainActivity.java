package com.example.venussteve.exercisecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText e1,e2;
    TextView calv;
    TextView totalcal;
    private Button calculate;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList1;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;
    AutoCompleteTextView autoCompleteTextView;
    private EditText txtInput;
    float num1 = 0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AutoComplete textView
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.activity);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        autoCompleteTextView.setAdapter(ad);
        init();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String res = (String)parent.getItemAtPosition(position);
                String[] ser = res.split(":");
                float ser1= Float.parseFloat(ser[1]);
                num1 = ser1;
            }
        });


        //ListView
        ListView listView = (ListView)findViewById(R.id.listv);
        String[] items = {};
        arrayList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);


        final List<String> list = new ArrayList<String>();

        txtInput = (EditText)findViewById(R.id.activity);
        Button btAdd = (Button)findViewById(R.id.btadd);
        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (txtInput.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Activity cannot be Blank", Toast.LENGTH_LONG).show();
                    txtInput.setError("Activity cannot be Blank");
                    return;
                }else if (e2.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Weight cannot be Blank", Toast.LENGTH_LONG).show();
                    e2.setError("Weight cannot be Blank");
                    return;
                } else if (e1.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Duration cannot be blank", Toast.LENGTH_LONG).show();
                    e1.setError("Duration cannot be blank");
                    return;
                }  else if (calv.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "calories burned cannot be 0", Toast.LENGTH_LONG).show();
                    calv.setError("Click on the Calculate button");
                    return;
                } else {
                    String newItem = txtInput.getText().toString();
                    String cb = calv.getText().toString();

                    arrayList.add(newItem + "   " + "calories burned" + " - " + cb);
                  //  arrayList1.add(cb);
                   adapter.notifyDataSetChanged();
                    list.add(cb);
                    float sum = 0;
                    for(int i=0 ;i<list.size();i++) {
                        sum += Float.parseFloat(list.get(i));

                    }


                    totalcal.setText("" + sum);
                  //  adapter1.notifyDataSetChanged();
                    txtInput.setText(null);
                    e2.setText(null);
                    calv.setText(null);
                    e1.setText(null);
                }
            }

        });


   }
    private void init() {
        calculate = (Button) findViewById(R.id.calc);
        // e1 = (EditText) findViewById(R.id.editText);
        totalcal = (TextView) findViewById(R.id.total);
        e1 = (EditText) findViewById(R.id.Time);
        e2 = (EditText) findViewById(R.id.weight);
        calv = (TextView) findViewById(R.id.calorie);
       // e4 = (EditText) findViewById(R.id.editText4);
        //e5 = (EditText) findViewById(R.id.editText5);
        // OnClickListener btnListener = new OnClickListener();
        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                float num2 = Float.parseFloat(e2.getText().toString());
                float num3 = Float.parseFloat(e1.getText().toString());
                float mul = (float) ((num1 * 3.5 * num2)/200);
                float mul1 = num3 * mul;
                String result = String.format("%.2f", mul);
                String result1 = String.format("%.2f", mul1);
                calv.setText("" + result1);

            }

        });

    }

    }

     /*   if (e2.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Weight cannot be Blank", Toast.LENGTH_LONG).show();
            e2.setError("Weight cannot be Blank");
            return;
        } else if (e3.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Activity duration cannot be Blank", Toast.LENGTH_LONG).show();
            e3.setError("Activity duration cannot be Blank");
            return;
        } else {
            // float num1 = Float.parseFloat(e1.getText().toString());
            float num2 = Float.parseFloat(e2.getText().toString());
            float num3 = Float.parseFloat(e3.getText().toString());
            float mul = (float) ((num1 * 3.5 * num2)/200);
            mul1 = num3 * mul;
            String result = String.format("%.2f", mul);
            String result1 = String.format("%.2f", mul1);
            e4.setText("" + result);
            e5.setText("" + result1);


        }

    }
    */


