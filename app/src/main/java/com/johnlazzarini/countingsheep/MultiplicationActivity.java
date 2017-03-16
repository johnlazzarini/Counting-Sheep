package com.johnlazzarini.countingsheep;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MultiplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        TextView multiplyingNaturalsTextView = (TextView)findViewById(R.id.multiply_naturals_id);
        multiplyingNaturalsTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeMultiplyNaturals = new Intent(MultiplicationActivity.this, MultiplyNaturalsActivity.class);
                startActivity(changeMultiplyNaturals);
            }
        });

        TextView multiplyIntegersTextView = (TextView)findViewById(R.id.multiply_integers_id);
        multiplyIntegersTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent changeMultiplyIntegers = new Intent(MultiplicationActivity.this, MultiplyIntegersActivity.class);
                startActivity(changeMultiplyIntegers);
            }
        });
    }
}
