package com.example.ali_r.sumup;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.pm.ResolveInfo;

import java.util.List;
import java.util.Random;
/**
 * Created by Ali-R on 22/09/2015.
 */
public class LevelSeven extends AppCompatActivity {
    TableLayout table; //grid
    TableLayout table2; //queue
    TableRow Row2;
    TextView t;
    int rows;
    int cols;
    int sum;
    int totalSum;
    int oldScore;
    boolean totalsummation;
    boolean levelcheck;

    static int length;
    Random rand = new Random();
    TableRow row;

    boolean timerrunning = true;
    int[][] newarray;
    TextView[][] textarray;
    Display display;
    TextView[] textarray2 = new TextView[9];
    int[] QueueArray = new int[9];
    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        length=7;


        newarray = new int[20][20];
        textarray = new TextView[length][length];
        textarray2 = new TextView[9];

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.level_seven);
        table = (TableLayout) findViewById(R.id.LevelSevenTableLayout01);
        table2 = (TableLayout) findViewById(R.id.LevelSevenTableLayout2);
        Row2 = (TableRow) findViewById(R.id.LevelSevenTableRow2);
        display = getWindowManager().getDefaultDisplay();
        final TextView asu = (TextView) findViewById(R.id.LevelSevenspaces);

        db.getWritableDatabase();
        Contact newa4 = new Contact();
        newa4 = db.getContact(2);
        if(Integer.parseInt(newa4._phone_number) < 7) {
            newa4._phone_number = "7";
            db.updateContact(newa4);
        }
        db.close();

        for (int r = 0; r < length; r++) {
            row = new TableRow(this);
            row.setGravity(Gravity.CENTER);
            for (int c = 0; c < length; c++) {

                t = new TextView(this);
                textarray[r][c] = t;
                textarray[r][c].setBackgroundColor(Color.WHITE);

                row.addView(textarray[r][c]);

                int width = ((display.getWidth() * 11) / 100);
                int height = ((display.getWidth() * 11) / 100);


                TableRow.LayoutParams params =
                        (TableRow.LayoutParams) t.getLayoutParams();

                params.span = 1;
                params.setMargins(5, 5, 5, 5);
                params.height = height;
                params.width = width;
                textarray[r][c].setPadding(1, 1, 1, 1);

                textarray[r][c].setLayoutParams(params);
            }
            table.addView(row,
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }


        generateScarmbGrid(length, length, (int) (length * length * 0.38)); //create grid - 30 is the number of spaces in the grid
        asu.setText(Html.fromHtml("<font color='gray'>SCORE: </font><b>" + (GetSpaces() + 61) + "</b><font color='gray'>/255</font>"));

        db.getWritableDatabase();
        Contact newa2 = new Contact();
        newa2 = db.getContact(1);

        if((GetSpaces()+61)>Integer.parseInt(newa2._phone_number)){
        newa2._phone_number=String.valueOf((GetSpaces() + 61));
        db.updateContact(newa2);}

        db.close();
        createQueue();// create Queue


        final TextView asj = (TextView) findViewById(R.id.LevelSevenTimer);


        CountDownTimer countd = new CountDownTimer(400000, 500) {


            boolean hello = true;

            @Override


            public void onTick(long millisUntilFinished)                     {

                if(GetSpaces()==length*length){

                    onFinish();

                }
                else if(GetSpaces()==0){
                    onFinish();
                }
                else {
                    asj.setText(Html.fromHtml("<font color='gray'>TIME: </font><b>" + millisUntilFinished / 1000 + "</b>"));
                    asj.setGravity(Gravity.CENTER);

                }
                    /*if (hello == false) {

                        System.out.println("bitchesssssssssss");
                    }*/

            }

            @Override
            public void onFinish() {
                if (GetSpaces() == (length*length)) {
                    asj.setText(Html.fromHtml("<font color='green'>LEVEL 8 X 8 UNLOCKED!</font>"));

                    //db.addContact(new Contact("yes", String.valueOf(GetSpaces())));

                    this.cancel();
                    new CountDownTimer(2000, 1000) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {
                            startActivity(new Intent(LevelSeven.this, LevelEight.class));
                            //levelcheck=true;

                            //db.addLevel(new Contact("Level", "6"));

                            //-startActivity(new Intent(MainActivity.this, LevelSix.class));
                            finish();
                        }
                    }.start();
                    //startActivity(new Intent(MainActivity.this, LevelSix.class));
                    //levelcheck=true;

                    //db.addLevel(new Contact("Level", "6"));

                    //-startActivity(new Intent(MainActivity.this, LevelSix.class));
                    //finish();

                }
                else if(GetSpaces()==0){
                    asj.setText(Html.fromHtml("<font color='red'>NO MORE MOVES LEFT </font>"));
                    this.cancel();
                }
                else {
                    asj.setText(Html.fromHtml("<font color='red'>TIME IS UP!!!!</font>"));
                    hello = false;
                    //System.out.println("onFinishBitch");
                    setX();
                }

            }

        }.start();

        for (rows = 0; rows < length; rows++) {
            for (cols = 0; cols < length; cols++) {

                final int j = rows;
                final int i = cols;

                textarray[j][i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (textarray[j][i].getText().toString() == "" && !isEmpty() ) {
                            sum = 0;

                            for (int r = j - 1; r <= j + 1; r++) {
                                for (int c = i - 1; c <= i + 1; c++) {
                                    if (r < 0 || r > length - 1) continue;
                                    if (c < 0 || c > length - 1) continue;
                                    if (r == j && c == i) continue;

                                    sum = sum + newarray[r][c];
                                }
                            }
                            totalSum = sum % 10;
                            //System.out.println(totalSum);

                            if (totalSum == QueueArray[0]) {
                                for (int r = j - 1; r <= j + 1; r++) {
                                    for (int c = i - 1; c <= i + 1; c++) {
                                        if (r < 0 || r > length - 1) continue;
                                        if (c < 0 || c > length - 1) continue;
                                        textarray[r][c].setText("");
                                        textarray[r][c].setBackgroundColor(Color.WHITE);
                                        textarray[r][c].performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                                        newarray[r][c] = 0;
                                    }
                                    totalsummation=true;
                                }
                                InsertQueueVal();
                            } else if (totalSum != QueueArray[0]) {
                                //console.log(totalSum);
                                newarray[j][i] = QueueArray[0];
                                textarray[j][i].setText(QueueArray[0] + "");
                                textarray[j][i].setTextColor(Color.RED);
                                InsertQueueVal();
                                textarray[j][i].setBackgroundColor(Color.parseColor("#9E9E9E"));
                                totalsummation=false;

                            }
                            db.getWritableDatabase();
                            Contact newa1 = new Contact();
                            newa1 = db.getContact(1);
                            if((GetSpaces()+61)>Integer.parseInt(newa1._phone_number)){
                                newa1._phone_number=String.valueOf((GetSpaces()+61));
                                db.updateContact(newa1);
                                //System.out.println(newa1._phone_number.toString());
                                db.close();
                                ActivityCompat.invalidateOptionsMenu(LevelSeven.this);}
                            asu.setText(Html.fromHtml("<font color='gray'>SCORE: </font><b>" +(GetSpaces()+61)+ "</b><font color='gray'>/255</font>"));
                        }

                    }
                });

            }


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MainMenu yeabay = new MainMenu();

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        // TextView x19 = (TextView) findViewById(R.id.highscore);
        TextView tv = new TextView(this);
        /*int larger;
        List<Contact> contacts = db.getAllContacts();
        int[] dataarray = new int[contacts.size() + 1];
        int i = 0;
        dataarray[0] = 0;
        for (Contact cn : contacts) {

            dataarray[i + 1] = Integer.parseInt(cn.getPhoneNumber());

            i++;
        }

        larger = dataarray[0];

        for (int j = 1; j < dataarray.length; j++) {
            if (dataarray[j] > larger) {
                larger = dataarray[j];
                //System.out.println("here " + largest);
            }
            //System.out.println("here " + largest);
        }*/

        db.getWritableDatabase();

        tv.setText(Html.fromHtml("<font color='gray'>HISCORE: </font><b>" + db.getContact(1).getPhoneNumber().toString() + "</b><font color='gray'>/255</font>"));
        tv.setTextColor(Color.WHITE);
        //tv.setOnClickListener(this);
        tv.setPadding(5, 0, 5, 0);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextSize(18);
        // MenuItem menuItem = menu.findItem(R.id.menu_main);
        //menuItem.setTitle("ADDO 81");
        menu.add(0, R.menu.menu_main, 1, "").setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        db.close();
        //MenuInflater mif = getMenuInflater();
        //mif.inflate(R.menu.main_actionbar, menu);
        //return super.onCreateOptionsMenu(menu);

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

    public void generateScarmbGrid(int row1, int col, int num) {

        for (int r = 0; r < row1; ++r) {
            row = new TableRow(this);
            for (int c = 0; c < col; ++c) {
                int value = rand.nextInt((9 - 0) + 1) + 0;

                newarray[r][c] = value;
                textarray[r][c].setText(value + "");
                textarray[r][c].setBackgroundColor(Color.parseColor("#9E9E9E"));
                textarray[r][c].setGravity(Gravity.CENTER);
            }
        }
        for (int k = 0; k < num; ++k) {
            int number = rand.nextInt((length - 1) + 1) + 1;
            int number2 = rand.nextInt((length - 1) + 1) + 1;
            if (textarray[number-1][number2-1].getText().toString() == "") {
                k = k - 1;
            } else {
                textarray[number-1][number2-1].setText("");
                textarray[number-1][number2-1].setBackgroundColor(Color.WHITE);
                newarray[number-1][number2-1] = 0;

            }

        }
    }

    public void createQueue(){
        display = getWindowManager().getDefaultDisplay();
        for (int k = 0; k < 9; k++) {
            TextView t1 = new TextView(this);
            int randomNum2 = rand.nextInt((9 - 0) + 1) + 0;
            textarray2[k]=t1;
            textarray2[k].setText(randomNum2 + "");
            QueueArray[k]=randomNum2;
            textarray2[k].setBackgroundColor(Color.WHITE);
            Row2.addView(textarray2[k]);

            int width1 = ((display.getWidth() * 9) / 100);
            int height1 = ((display.getWidth() * 9) / 100);

            TableRow.LayoutParams params =
                    (TableRow.LayoutParams) t1.getLayoutParams();

            params.span = 1;

            params.setMargins(5, 5, 5, 5);
            params.height = height1;
            params.width = width1;
            textarray2[k].setPadding(1, 1, 1, 1);
            textarray2[k].setGravity(Gravity.CENTER);
            textarray2[k].setLayoutParams(params);

        }
        textarray2[0].setTextColor(Color.RED);
        textarray2[0].setTextSize(20);
    }

    public void InsertQueueVal(){
        int p = rand.nextInt((9 - 0) + 1) + 0;
        for (int t = 0; t < 8; ++t) {

            QueueArray[t] = QueueArray[t+1];
            textarray2[t].setText(QueueArray[t]+"");


        }

        QueueArray[8] = p;
        textarray2[8].setText(p + "");

    }
    public int GetSpaces(){
        int spaces=0;
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                if (textarray[i][j].getText().toString()=="" ){
                    spaces=spaces+1;

                }
            }
        }

        return spaces;
    }

    public int GetX(){
        int CountX=0;
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                if (textarray[i][j].getText().toString()=="X"){
                    CountX=CountX+1;

                }
            }
        }

        return CountX;
    }

    public void setX() {

        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                if (textarray[i][j].getText().toString()==""){
                    textarray[i][j].setText("X");
                    textarray[i][j].setTextColor(Color.RED);
                    textarray[i][j].setBackgroundColor(Color.parseColor("#9E9E9E"));


                }
            }
        }


        //System.out.println("here"+GetSpaces());
        ;
    }

    public boolean isEmpty(){
        if(GetSpaces()==length*length){
            return true;
        }
        else {
            return false;
        }

    }

}