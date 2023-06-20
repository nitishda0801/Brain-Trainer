package com.example.braintrainer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class registration extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        auth=FirebaseAuth.getInstance();
        EditText email=findViewById(R.id.remail);
        EditText pass=findViewById(R.id.rpwd);
        Button b1=findViewById(R.id.idBtnRegister);
        Button b2=findViewById(R.id.gologin);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registration.this,login.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=email.getText().toString();
                String p=pass.getText().toString();
                if(!e.equals("") && !p.equals(("")))
                {
                    auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(registration.this, "Registeration successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registration.this,MainActivity.class));
                            }
                            else {
                                Toast.makeText(registration.this, "Registeration Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registration.this,registration.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(registration.this, "Email or password cant be empty!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registration.this,registration.class));
                }
            }
        });
    }
}