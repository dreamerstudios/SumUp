package com.example.ali_r.sumup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Ali-R on 22/09/2015.
 */
public class LevelsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);

        final RadioButton Five = (RadioButton) findViewById(R.id.level_five);

        //buttonEffect(x1);
        Five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(LevelsActivity.this, MainActivity.class));
            }
        });

        final RadioButton six = (RadioButton) findViewById(R.id.level_six);

        //buttonEffect(x1);
        six.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(LevelsActivity.this, LevelSix.class));
            }
        });

        final RadioButton seven = (RadioButton) findViewById(R.id.level_seven);

        //buttonEffect(x1);
        seven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(LevelsActivity.this, LevelSeven.class));
            }
        });
        final RadioButton eight = (RadioButton) findViewById(R.id.level_eight);

        //buttonEffect(x1);
        eight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(LevelsActivity.this, LevelEight.class));
            }
        });
        final RadioButton nine = (RadioButton) findViewById(R.id.level_nine);

        //buttonEffect(x1);
        nine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(LevelsActivity.this, LevelNine.class));
            }
        });

    }





    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}
