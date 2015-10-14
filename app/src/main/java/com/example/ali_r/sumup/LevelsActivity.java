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

    DatabaseHandler db = new DatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);

        RadioButton Five = (RadioButton) findViewById(R.id.level_five);
        RadioButton six = (RadioButton) findViewById(R.id.level_six);
        RadioButton seven = (RadioButton) findViewById(R.id.level_seven);
        RadioButton eight = (RadioButton) findViewById(R.id.level_eight);
        RadioButton nine = (RadioButton) findViewById(R.id.level_nine);
        db.getReadableDatabase();
        Contact newa5 = new Contact();
        newa5 = db.getContact(2);

        if (Integer.parseInt(newa5._phone_number) == 5) {
            Five.setEnabled(true);
            six.setEnabled(false);
            seven.setEnabled(false);
            eight.setEnabled(false);
            nine.setEnabled(false);
            Five.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                }
            });

        } else if (Integer.parseInt(newa5._phone_number) == 6) {
            Five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(false);
            eight.setEnabled(false);
            nine.setEnabled(false);
            Five.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                }
            });

            six.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, LevelSix.class));
                }
            });

        } else if (Integer.parseInt(newa5._phone_number) == 7) {
            Five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);
            eight.setEnabled(false);
            nine.setEnabled(false);
            Five.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                }
            });

            six.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, LevelSix.class));
                }
            });

            //buttonEffect(x1);
            seven.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, LevelSeven.class));
                }
            });

        } else if (Integer.parseInt(newa5._phone_number) == 8) {
            Five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);
            eight.setEnabled(true);
            nine.setEnabled(false);
            Five.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                }
            });

            six.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, LevelSix.class));
                }
            });

            //buttonEffect(x1);
            seven.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LevelsActivity.this, LevelSeven.class));
                }
            });
            //buttonEffect(x1);
            eight.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LevelsActivity.this, LevelEight.class));
                }
            });
        } else if (Integer.parseInt(newa5._phone_number) == 9) {
            Five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);
            eight.setEnabled(true);
            nine.setEnabled(true);
            Five.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    startActivity(new Intent(LevelsActivity.this, MainActivity.class));
                }
            });
            six.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LevelsActivity.this, LevelSix.class));
                }
            });
            //buttonEffect(x1);
            seven.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LevelsActivity.this, LevelSeven.class));
                }
            });
            //buttonEffect(x1);
            eight.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LevelsActivity.this, LevelEight.class));
                }
            });
            //buttonEffect(x1);
            nine.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(LevelsActivity.this, LevelNine.class));
                }
            });
        }
        db.close();
        //buttonEffect(x1);
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
