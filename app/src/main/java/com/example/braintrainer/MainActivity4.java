package com.example.braintrainer;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.gridlayout.widget.GridLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.widget.Toast;
public class MainActivity4 extends AppCompatActivity
{
    int x,y,z;
    Button playbutton,play_again,quit;
    GridLayout gridLayout;
    TextView score,timer,question,answer;
    Button button1,button2,button3,button4;
    CountDownTimer countDownTimer;
    int[] arr = new int[20];
    boolean click=false;
    int a,b;
    int score_num=0;
    int start,end;
    int numofques=0;
    public int getRandomNumber(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void timerFunc()
    {

        countDownTimer=new CountDownTimer(30100,1000)
        {
            @Override
            public void onTick(long l)
            {
                timer.setText(Integer.toString((int)l/1000)+" s");
            }
            @Override
            public void onFinish()
            {
                answer.setText("DONE!!");
                answer.setVisibility(View.VISIBLE);
                play_again.setVisibility(View.VISIBLE);
                quit.setVisibility(View.VISIBLE);
                click=true;

                play_again.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity4.class);
                        startActivity(i);
                        finish();
                    }
                });
                quit.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                        {
                            NotificationChannel chnnelID=new NotificationChannel("Notification","My notification", NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager manager=getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(chnnelID);
                        }
                        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity4.this,"Notification")
                                .setSmallIcon(R.drawable.nrml)
                                .setContentTitle("Brain Trainer")
                                .setContentText("Score :"+score_num+" Questions : "+numofques+"\ntime : "+30+"sec")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true);

                        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(1, builder.build());
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity4.this);
                        builder1.setTitle("QUIT")
                                .setMessage("Are you sure?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        Toast.makeText(MainActivity4.this, "Selected option is yes", Toast.LENGTH_LONG).show();
                                        Intent yes = new Intent(getApplicationContext(), MainActivity2.class);
                                        startActivity(yes);
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        Toast.makeText(MainActivity4.this, "Selected option is No", Toast.LENGTH_SHORT).show();
                                        Intent no = new Intent(getApplicationContext(), MainActivity4.class);
                                        startActivity(no);
                                        finish();
                                    }
                                });
                        AlertDialog dialog = builder1.create();
                        dialog.show();
                    }
                });
            }
        }.start();
    }
    public void playAgainFunc(View view)
    {
        startGame();
        timerFunc();
        score.setText("0/0");
        score_num=0;
        numofques=0;
        play_again.setVisibility(View.INVISIBLE);
        answer.setVisibility(View.INVISIBLE);
        quit.setVisibility(View.INVISIBLE);
    }
    public void startGame()
    {
        click=false;
        play_again.setVisibility(View.INVISIBLE);
        a=getRandomNumber(-200,200);
        b=getRandomNumber(-200,200);
        question.setText("( "+Integer.toString(a)+" )"+" + "+"( "+Integer.toString(b)+" )");
        int pos_right=getRandomNumber(0,3);
        for(int i=0;i<4;i++)
        {
            if(i==pos_right)
            {
                arr[i]=a+b;
            }
            else
            {
                int wrong=getRandomNumber(-200,200);
                while(wrong==(a+b))
                {
                    wrong=getRandomNumber(-200,200);
                }
                arr[i]=wrong;
            }
        }
        button1.setText(Integer.toString(arr[0]));
        button2.setText(Integer.toString(arr[1]));
        button3.setText(Integer.toString(arr[2]));
        button4.setText(Integer.toString(arr[3]));
    }
    public void playGame(View view)
    {
        playbutton.setVisibility(View.INVISIBLE);
        score.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        quit.setVisibility(View.INVISIBLE);
        startGame();
        timerFunc();
    }
    public void correctAns(View view)
    {
        numofques++;
        if(!click)
        {
            int val = Integer.parseInt(view.getTag().toString());
            if (arr[val-1]==(a+b))
            {
                answer.setText("CORRECT");
                answer.setVisibility(View.VISIBLE);
                score_num++;
            }
            else
            {
                answer.setText("INCORRECT");
                answer.setVisibility(View.VISIBLE);
            }
            click=true;
            score.setText(Integer.toString(score_num)+"/"+Integer.toString(numofques));
            startGame();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        playbutton=findViewById(R.id.playbutton);
        score=findViewById(R.id.score);
        timer=findViewById(R.id.timer);
        question=findViewById(R.id.question);
        answer=findViewById(R.id.answer);
        quit=findViewById(R.id.quit);
        gridLayout=(androidx.gridlayout.widget.GridLayout)findViewById(R.id.gridLayout);
        play_again=findViewById(R.id.playagain);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        score.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        answer.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        quit.setVisibility(View.INVISIBLE);
    }
}
