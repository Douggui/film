package com.example.filmliste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private ImageView imageView;
    private TextView t,d,dure;
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        t=findViewById(R.id.t);
        d=findViewById(R.id.d);
        dure=findViewById(R.id.dure);
        imageView=findViewById(R.id.imageView);
        retour=findViewById(R.id.retour);


        t.append(getIntent().getExtras().getString("titre"));
        d.append(getIntent().getExtras().getString("description"));
        dure.append(String.valueOf(getIntent().getExtras().getInt("duree"))+" mn");
        imageView.setImageResource(getIntent().getExtras().getInt("image"));

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);

            }
        });
    }
}