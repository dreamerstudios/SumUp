package com.example.ali_r.sumup;

//Dreamers Studio Bitches!!!!!!!!!//
//Dreamers Studio Bitches!!!!!!!!!//
//Dreamers Studio Bitches!!!!!!!!!//
//Dreamers Studio Bitches!!!!!!!!!//
//Dreamers Studio Bitches!!!!!!!!!//

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TableLayout table; //grid
    TableLayout table2; //queue
    TableRow Row2;
    TextView t;
    int rows;
    int cols;
    int sum;
    int totalSum;


    Random rand = new Random();
    TableRow row;

    int[][] newarray = new int[20][20];
    TextView[][] textarray = new TextView[9][9];
    Display display;
    TextView[] textarray2 = new TextView[9];
    int[] QueueArray = new int[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        table = (TableLayout) findViewById(R.id.TableLayout01);
        table2 = (TableLayout) findViewById(R.id.TableLayout2);
        Row2 = (TableRow) findViewById(R.id.TableRow2);
        display = getWindowManager().getDefaultDisplay();
        final TextView asu = (TextView)findViewById(R.id.spaces);
        asu.setText(Html.fromHtml("<font color='gray'>SPACES: </font><b>30</b><font color='gray'>/81</font>"));


        for (int r = 0; r < 9; r++) {
            row = new TableRow(this);
            row.setGravity(Gravity.CENTER);
            for (int c = 0; c < 9; c++) {

                t = new TextView(this);
                textarray[r][c]=t;
                textarray[r][c].setBackgroundColor(Color.WHITE);

                row.addView(textarray[r][c]);

                int width = ((display.getWidth() * 8) / 100);
                int height = ((display.getWidth() * 8) / 100);


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


        generateScarmbGrid(9, 9, 30); //create grid - 30 is the number of spaces in the grid
        createQueue();// create Queue


         final TextView asj = (TextView)findViewById(R.id.Timer);

        new CountDownTimer(480000, 1){

            public void onTick(long millisUntilFinished) {
        asj.setText(Html.fromHtml("<font color='gray'>TIME: </font><b>"+millisUntilFinished/1000+"</b>"));
                        asj.setGravity(Gravity.CENTER);
            }

            public void onFinish() {
                asj.findViewById(R.id.Timer);
                asj.setText(Html.fromHtml("<font color='red'>TIME IS UP!!!!</font>"));
                setX();

            }
        }.start();

        for (rows = 0; rows < 9; rows++) {
            for (cols = 0; cols < 9; cols++) {

                final int j = rows;
                final int i = cols;

                textarray[j][i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (textarray[j][i].getText().toString()=="") {
                            sum = 0;

                            for (int r = j - 1; r <= j + 1; r++) {
                                for (int c = i - 1; c <= i + 1; c++) {
                                    if (r < 0 || r > 9 - 1) continue;
                                    if (c < 0 || c > 9 - 1) continue;
                                    if (r == j && c == i) continue;

                                    sum = sum + newarray[r][c];
                                }
                            }
                            totalSum = sum % 10;
                            System.out.println(totalSum);

                        if (totalSum == QueueArray[0]) {
                            for (int r = j - 1; r <= j + 1; r++) {
                                for (int c = i - 1; c <= i + 1; c++) {
                                    if (r < 0 || r > 9 - 1) continue;
                                    if (c < 0 || c > 9 - 1) continue;
                                    textarray[r][c].setText("");
                                    textarray[r][c].setBackgroundColor(Color.WHITE);
                                    textarray[r][c].performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                                    newarray[r][c] = 0;
                                }
                            }
                            InsertQueueVal();
                        }
                        else if (totalSum != QueueArray[0]) {
                            //console.log(totalSum);
                            newarray[j][i] = QueueArray[0];
                            textarray[j][i].setText(QueueArray[0] + "");
                            textarray[j][i].setTextColor(Color.RED);
                            InsertQueueVal();
                            textarray[j][i].setBackgroundColor(Color.parseColor("#9E9E9E"));

                        }
                            asu.setText(Html.fromHtml("<font color='gray'>SPACES: </font><b>"+GetSpaces()+"</b><font color='gray'>/81</font>"));
                        }

                }});

            }


        }


    }

    @Override
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
                int number = rand.nextInt((9 - 1) + 1) + 1;
                int number2 = rand.nextInt((9 - 1) + 1) + 1;
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

            int width1 = ((display.getWidth() * 8) / 100);
            int height1 = ((display.getWidth() * 8) / 100);

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
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if (textarray[i][j].getText().toString()==""){
                    spaces=spaces+1;

                }
            }
        }

        return spaces;
    }

    public void setX() {

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if (textarray[i][j].getText().toString()==""){
                    textarray[i][j].setText("X");
                    textarray[i][j].setTextColor(Color.RED);
                    textarray[i][j].setBackgroundColor(Color.parseColor("#9E9E9E"));


                }
            }
        }


    }
    }


