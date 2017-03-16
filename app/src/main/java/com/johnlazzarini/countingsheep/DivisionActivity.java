package com.johnlazzarini.countingsheep;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DivisionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        TextView dividingNaturalsTextView = (TextView)findViewById(R.id.divide_naturals_id);
        dividingNaturalsTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeDivideNaturals = new Intent(DivisionActivity.this, DivideNaturalsActivity.class);
                startActivity(changeDivideNaturals);
            }
        });

        TextView divideIntegersTextView = (TextView)findViewById(R.id.divide_integers_id);
        divideIntegersTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeDivideIntegers = new Intent(DivisionActivity.this, DivideIntegersActivity.class);
                startActivity(changeDivideIntegers);
            }
        });
    }
}
