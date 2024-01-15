package be.ehb.mesdoigtsdefeesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.ehb.mesdoigtsdefeesapp.databinding.ActivityMainBinding;
import be.ehb.mesdoigtsdefeesapp.views.fragments.HomeFragment;
import be.ehb.mesdoigtsdefeesapp.views.fragments.LessonFragment;
import be.ehb.mesdoigtsdefeesapp.views.fragments.PostFragment;

public class MainActivity extends AppCompatActivity {



    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();  // <-- Add this line

        // Specify the top-level destinations in the app's navigation graph
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }

    public void navigateToHome(View view) {
        navController.navigate(R.id.fragment_home);
    }

    public void navigateToPosts(View view) {
        navController.navigate(R.id.postFragment);
    }

    public void navigateToLessons(View view) {
        navController.navigate(R.id.lessonFragment);
    }
}
