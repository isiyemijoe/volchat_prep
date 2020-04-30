package com.example.volchat_prep;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    MaterialButton signup_btn, forgot_passBtn, signin_btn;
    TextInputLayout email_tv, password_tv;
    TextView logo_name, logo_text;
    ImageView logo;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setEnterTransition(fade);

        signup_btn = findViewById(R.id.signin_signup_btn);
        forgot_passBtn = findViewById(R.id.forgot_password);
        signin_btn = findViewById(R.id.button);
        email_tv = findViewById(R.id.signin_email);
        password_tv = findViewById(R.id.signin_password);
        logo = findViewById(R.id.logo_image);
        logo_name = findViewById(R.id.logo_name);
        logo_text = findViewById(R.id.textView2);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(login.this, signup.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String >(logo,"squadlogo");
                pairs[1] = new Pair<View, String >(logo_name,"logo_text");
                pairs[2] = new Pair<View, String >(logo_text,"logo_small_text");
                pairs[3] = new Pair<View, String >(email_tv,"trans_email");
                pairs[4] = new Pair<View, String >(password_tv,"trans_password");
                pairs[5] = new Pair<View, String >(signup_btn,"trans_signbtn");
                pairs[6] = new Pair<View, String >(signup_btn,"trans_signnextbtn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
                        startActivity(signupIntent, options.toBundle());
                }
                else {
                    startActivity(signupIntent);
                }
            }
        });
    }
}
