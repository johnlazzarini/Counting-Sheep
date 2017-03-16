package com.johnlazzarini.countingsheep;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubtractionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        TextView subtractingNaturalsTextView = (TextView)findViewById(R.id.subtract_naturals_id);
        subtractingNaturalsTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeSubtractNaturals = new Intent(SubtractionActivity.this, SubtractNaturalsActivity.class);
                startActivity(changeSubtractNaturals);
            }
        });

        TextView subtractIntegersTextView = (TextView)findViewById(R.id.subtract_integers_id);
        subtractIntegersTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeSubtractIntegers = new Intent(SubtractionActivity.this, SubtractIntegersActivity.class);
                startActivity(changeSubtractIntegers);
            }
        });
    }
}
