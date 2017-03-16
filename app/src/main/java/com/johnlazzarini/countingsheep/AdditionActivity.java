package com.johnlazzarini.countingsheep;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdditionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        TextView addingNaturalsTextView = (TextView)findViewById(R.id.add_naturals_id);
        addingNaturalsTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeAddNaturals = new Intent(AdditionActivity.this, AddNaturalsActivity.class);
                startActivity(changeAddNaturals);
            }
        });

        TextView addingIntegersTextView = (TextView)findViewById(R.id.add_integers_id);
        addingIntegersTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeAddIntegers = new Intent(AdditionActivity.this, AddIntegersActivity.class);
                startActivity(changeAddIntegers);
            }
        });
    }
}
