package com.example.volchat_prep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class onboarding extends AppCompatActivity {

    private onBoardingAdapter adapter;
   static LinearLayout onBoardIndicatorlayout;
   private MaterialButton onBoardAction;


    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        setOnboardingitem();
        final ViewPager2 onBoardViewPager = findViewById(R.id.onboardview_pager);
        onBoardViewPager.setAdapter(adapter);
        onBoardIndicatorlayout = findViewById(R.id.onboarding_indicator_layout);
        onBoardAction = findViewById(R.id.onboardActionBtn);
        setupIndicator();
        setCurrentIndicator(0);
        onBoardViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
        onBoardAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onBoardViewPager.getCurrentItem() +1 < adapter.getItemCount()){
                    onBoardViewPager.setCurrentItem(onBoardViewPager.getCurrentItem() +1);
                }
                else{
                    Intent intent = new Intent(onboarding.this, login.class);
                    startActivity(intent);
                }
            }
        });


    }

    public void setOnboardingitem(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem instantMsg = new OnboardingItem();
        instantMsg.setTitle("Instant Message Delivery");
        instantMsg.setDescription("Send and receive instant message to anyone anywhere in the world");
        instantMsg.setImage(R.drawable.instant_messaging);
        onboardingItems.add(instantMsg);

        OnboardingItem groupMsg = new OnboardingItem();
        groupMsg.setTitle("Group  Meetings");
        groupMsg.setDescription("Create and organise group with unlimited members with ease");
        groupMsg.setImage(R.drawable.group_disc);
        onboardingItems.add(groupMsg);

        OnboardingItem sittingLady = new OnboardingItem();
        sittingLady.setTitle("Get connedted to to friends and family");
        sittingLady.setDescription("Stay reachable and available to your friends ad families");
        sittingLady.setImage(R.drawable.share);
        onboardingItems.add(sittingLady);

        OnboardingItem photos = new OnboardingItem();
        photos.setTitle("Quality voice and video calls");
        photos.setDescription("Jump in your callers moment through HD quality voice and video calls");
        photos.setImage(R.drawable.voice_call);
        onboardingItems.add(photos);
        adapter = new onBoardingAdapter(onboardingItems);
    }

    public void setupIndicator(){
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(  getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            onBoardIndicatorlayout.addView(indicators[i]);

        }
    }

    public void setCurrentIndicator(int index){
        int childCount = onBoardIndicatorlayout.getChildCount();
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade);
        for(int i =0; i < childCount; i++){
            ImageView imageView = (ImageView) onBoardIndicatorlayout.getChildAt(i);
            if(i == index){
                imageView.setAnimation(fade_in);
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(), R.drawable.onboarding_indicator_active
                        )
                );
            }
            else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(), R.drawable.onboarding_indicator_inactive
                        )
                );
            }
            onBoardAction.setText("Next");

        }

        if(index == adapter.getItemCount()-1){
            onBoardAction.setText("Start");
        }
    }
}
