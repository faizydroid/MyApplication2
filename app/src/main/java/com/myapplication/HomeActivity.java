package com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button z = (Button) findViewById(R.id.bt1);
        Button y = (Button) findViewById(R.id.bt2);
        Button x = (Button) findViewById(R.id.bt3);



        z.setOnClickListener(new View.OnClickListener() {
                                 public void onClick (View v)
                                 {
                                     Intent intent = new Intent(HomeActivity.this, ProductListOne.class);
                                     startActivity(intent);

                                 }
                             }

        );


        y.setOnClickListener(new View.OnClickListener() {
                                 public void onClick (View v)
                                 {
                                     Intent intent = new Intent(HomeActivity.this, ProductListTwo.class);
                                     startActivity(intent);

                                 }
                             }

        );
        x.setOnClickListener(new View.OnClickListener() {
                                 public void onClick (View v)
                                 {
                                     Intent intent = new Intent(HomeActivity.this, FavoriteListActivty.class);
                                     startActivity(intent);

                                 }
                             }

        );

    }


}
