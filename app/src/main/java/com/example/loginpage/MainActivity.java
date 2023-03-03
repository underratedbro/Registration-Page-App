package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Username, password, Repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.password);
        Repassword = (EditText) findViewById(R.id.Repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String user = Username.getText().toString();
        String pass = password.getText().toString();
        String repass = Repassword.getText().toString();

        if(user.equals("") ||pass.equals("") ||repass.equals(""))
            Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

        else {
            if (pass.equals(repass)){
                Boolean checkuser = DB.checkusername(user);
                if (checkuser==false){
                  Boolean insert = DB.insertData(user, pass);
                  if (insert==true){
                      Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                      Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                      startActivity(intent);
                  } else {
                      Toast.makeText(MainActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                  }
                }
                else {
                    Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(MainActivity.this, "Password Is Not Matching", Toast.LENGTH_SHORT).show();
            }
        }
    }
});

signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

    }
});

    }
}