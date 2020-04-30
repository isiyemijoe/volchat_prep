package com.example.volchat_prep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textview.MaterialTextView;

public class splash extends AppCompatActivity {

    Animation top_anim,bottom_anim;
    LottieAnimationView splashView;
    MaterialTextView vol_text, vol_subtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        top_anim = AnimationUtils.loadAnimation(this, R.anim.splash_top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);

        splashView = findViewById(R.id.splash);
        vol_subtext = findViewById(R.id.vol_sutext);
        vol_text = findViewById(R.id.vol_text);
        splashView.setAnimation(top_anim);

        vol_text.setAnimation(bottom_anim);
        vol_subtext.setAnimation(bottom_anim);
    }
}
