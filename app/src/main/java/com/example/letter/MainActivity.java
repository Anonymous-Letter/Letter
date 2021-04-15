package com.example.letter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.letter.fragments.ComposeFragment;
import com.example.letter.fragments.HomeFragment;
import com.example.letter.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Button btnLogout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout = findViewById(R.id.btnLogout);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        // TODO: update fragment
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    private void goLoginActivity() {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}