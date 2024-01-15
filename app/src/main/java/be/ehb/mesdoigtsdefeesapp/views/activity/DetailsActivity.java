package be.ehb.mesdoigtsdefeesapp.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String title = getIntent().getStringExtra("title");
        binding.tvTitle.setText(title);

        String content = getIntent().getStringExtra("content");
        binding.tvContent.setText(content);

        String size = getIntent().getStringExtra("size");
        binding.tvSize.setText(size);

    }
}