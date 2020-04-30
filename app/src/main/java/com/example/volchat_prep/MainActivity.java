package com.example.volchat_prep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textview.MaterialTextView;


public class MainActivity extends AppCompatActivity {

    SharedPreferences onBoardPreference;
    Animation top_anim,bottom_anim, rotate;
    LottieAnimationView splashView;
    MaterialTextView vol_text, vol_subtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);

        top_anim = AnimationUtils.loadAnimation(this, R.anim.splash_top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);

        splashView = findViewById(R.id.splash);
        vol_subtext = findViewById(R.id.vol_sutext);
        vol_text = findViewById(R.id.vol_text);

        splashView.setAnimation(rotate);
        splashView.setAnimation(top_anim);

        vol_text.setAnimation(bottom_anim);
        vol_subtext.setAnimation(bottom_anim);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onBoardPreference = getSharedPreferences("onBoardSreeen", MODE_PRIVATE);
                    boolean isFirstTime = onBoardPreference.getBoolean("firstTime", true);

                    if(isFirstTime){
                        SharedPreferences.Editor editor = onBoardPreference.edit();
                        editor.putBoolean("firstTime", false);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, onboarding.class);
                        startActivityForResult(intent,1000);
                    }
                    else{
                        Intent signInIntent = new Intent(MainActivity.this, login.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(MainActivity.this, splashView, ViewCompat.getTransitionName(splashView));
                        startActivityForResult(signInIntent, 2000, options.toBundle());
                    }

                }
            }, 5000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Toast.makeText(this, "Onboarding success", Toast.LENGTH_SHORT).show();
        }
        if(requestCode == 2000){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        }
    }
}
