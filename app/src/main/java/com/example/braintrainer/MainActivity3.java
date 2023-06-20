package com.example.braintrainer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity3 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.img);
        getSupportActionBar().setTitle("        INSTRUCTIONS");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Button start = (Button) findViewById(R.id.button6);
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ProgressDialog pd;
                pd= new ProgressDialog(MainActivity3.this);
                pd.setMessage("Loading...");
                pd.setTitle("Loading.....");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                pd.setCancelable(false);
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        try {
                            Thread.sleep(1000);
                            Intent i = new Intent(getApplicationContext(),MainActivity4.class);
                            startActivity(i);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    }
                }).start();

            }
        });
        Button back = (Button) findViewById(R.id.buttonback);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(this,login.class));
                finish();
            case R.id.about:
                Toast.makeText(this, "about Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rate:
                Toast.makeText(this, "rate Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "Play Brain Trainer Game and increase your analytical thinking.Visit my youtube channel:https://www.youtube.com/@softwarethings";
                String sub = "Brain Trainer";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
                myIntent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
                break;
        }
        return super.onOptionsItemSelected(item);
        
    }
}