package com.example.ali_r.sumup;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Ali-R on 29/08/2015.
 */
public class InstructionThree extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.instruction_three);

        final Button x3 =  (Button) findViewById(R.id.Next_Page2);
        buttonEffect(x3);
        x3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                db.getWritableDatabase();
                Contact newa5 = new Contact();
                newa5 = db.getContact(2);

                if(Integer.parseInt(newa5._phone_number) == 6) {
                    startActivity(new Intent(InstructionThree.this, LevelSix.class));
                    db.close();
                }
                else if(Integer.parseInt(newa5._phone_number) == 7) {
                    startActivity(new Intent(InstructionThree.this, LevelSeven.class));
                    db.close();
                }

                else if(Integer.parseInt(newa5._phone_number) == 8) {
                    startActivity(new Intent(InstructionThree.this, LevelEight.class));
                    db.close();
                }
                else if(Integer.parseInt(newa5._phone_number) == 9) {
                    startActivity(new Intent(InstructionThree.this, LevelNine.class));
                    db.close();
                }
                else{
                    startActivity(new Intent(InstructionThree.this, MainActivity.class));
                    db.close();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
