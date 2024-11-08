package com.example.connectfour;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting the content view
        setContentView(R.layout.activity_main);

        // creating the toolbar
        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        // initializing bottom navigation view and nav host fragment
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);

        // checks if the nav host fragment is null
        if (navHostFragment != null) {

            // instantiating nav controller
            NavController navController = navHostFragment.getNavController();

            // instantiating app bar configuration
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.boardFragment, R.id.gameOptionsFragment
            ).build();

            // calling NavigationUI.setupActionBarWithNavController
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // calling NavigationUI.setupWithNavController
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                // checks if the id is board fragment
                if (destination.getId() == R.id.boardFragment)
                    toolbar.setTitle("Connect Four");
                // checks if the id is game options fragment
                else if (destination.getId() == R.id.gameOptionsFragment)
                    toolbar.setTitle("Options");
            });
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}