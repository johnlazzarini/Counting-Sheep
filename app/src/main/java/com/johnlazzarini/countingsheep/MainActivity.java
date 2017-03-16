package com.johnlazzarini.countingsheep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView additionTextView = (TextView) findViewById(R.id.addition_id);
        additionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent additionChange = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(additionChange);
            }
        });

        TextView subtractionTextView = (TextView) findViewById(R.id.subtraction_id);
        subtractionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent subtractionChange = new Intent(MainActivity.this, SubtractionActivity.class);
                startActivity(subtractionChange);
            }
        });

        TextView multiplicationView = (TextView) findViewById(R.id.multiplication_id);
        multiplicationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent multiplicationChange = new Intent(MainActivity.this, MultiplicationActivity.class);
                startActivity(multiplicationChange);
            }
        });

        TextView divisionView = (TextView) findViewById(R.id.division_id);
        divisionView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent divisionChange = new Intent(MainActivity.this, DivisionActivity.class);
                startActivity(divisionChange);
            }
        });
    }
}
