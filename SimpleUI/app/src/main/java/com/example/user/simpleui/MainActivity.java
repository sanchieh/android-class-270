package com.example.user.simpleui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_DRINK_MENU_ACTIVITY = 0;

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String selectedTea = "blcak tea";

    List<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        listView = (ListView)findViewById(R.id.listView);
        spinner = (Spinner)findViewById(R.id.spinner);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    submit(v);
                    return true;
                }

                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                                                      selectedTea = radioButton.getText().toString();
                                                  }
                                              });

            setupListView();

            setupSpinner();
        }

            public void setupListView(){

                OrderAdapter adapter = new OrderAdapter(this, orders);
                listView.setAdapter(adapter);
            }

            public void setupSpinner()
            {
                String[] data = getResources().getStringArray(R.array.storeInfos);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data);
                spinner.setAdapter(adapter);
            }





    public void submit(View view)
    {
        String text = editText.getText().toString();

        textView.setText(text);

        Order order = new Order();
        order.note = text;
        order.drinkName = selectedTea;
        order.storeInfo = (String)spinner.getSelectedItem();

        orders.add(order);

        setupListView();

        editText.setText("");
    }

    public void goToMenu(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this, DrinkMenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DRINK_MENU_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_DRINK_MENU_ACTIVITY)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "§¹¦¨µæ³æ", Toast.LENGTH_SHORT).show();
                textView.setText(data.getStringExtra("results"));
            }
        }
    }




}

