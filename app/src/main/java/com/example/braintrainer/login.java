package com.example.braintrainer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity
{
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        auth=FirebaseAuth.getInstance();
        EditText email=findViewById(R.id.le);
        EditText pass=findViewById(R.id.lp);

        Button b1=findViewById(R.id.bl);
        Button b2=findViewById(R.id.goreg);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,registration.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String e=email.getText().toString();
                String p=pass.getText().toString();
                if(!e.equals("") && !p.equals(("")))
                {
                    auth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(login.this, "lOGIN successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,MainActivity2.class));
                            }
                            else
                            {
                                Toast.makeText(login.this, "Login Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,MainActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(login.this, "Email or password cant be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

}
}