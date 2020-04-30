package com.example.volchat_prep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

import static androidx.recyclerview.widget.RecyclerView.*;

public class onBoardingAdapter extends RecyclerView.Adapter<onBoardingAdapter.onBoardingViewholder>{
    private List<OnboardingItem> mData;

    public onBoardingAdapter(List<OnboardingItem> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public onBoardingViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,parent,false);
        return new onBoardingViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull onBoardingViewholder holder, int position) {
        holder.setOnBoardData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class onBoardingViewholder extends ViewHolder {


        private GifImageView image;
        private TextView title;
        private  TextView description;

        public onBoardingViewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.onboarding_image);
            title = itemView.findViewById(R.id.onboard_title);
            description = itemView.findViewById(R.id.onboarding_desc);
        }

        void setOnBoardData(OnboardingItem item){
            title.setText(item.getTitle());
            image.setImageResource(item.getImage());
            description.setText(item.getDescription());

        }
    }
}
