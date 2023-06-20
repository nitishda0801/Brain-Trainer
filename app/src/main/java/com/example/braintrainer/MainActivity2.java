package com.example.braintrainer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.img);
        getSupportActionBar().setTitle("        DIFFICULTY LEVELS");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Button easy = (Button) findViewById(R.id.button2);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(i);
            }
        });

        Button medium = (Button) findViewById(R.id.button3);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(i);
            }
        });
        Button hard = (Button) findViewById(R.id.button4);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(i);
            }
        });

        Button exit = (Button) findViewById(R.id.button5);
        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
            case R.id.about:
                Toast.makeText(this, "about Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                startActivity(new Intent(this,login.class));
                finish();
                break;
            case R.id.rate:
                Toast.makeText(this, "rate Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                //Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show();
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