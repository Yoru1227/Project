package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    static FragmentManager fragmentManager;
    static AccDatabase accDatabase;
    AccDao accDao;
    LiveData<List<Account>> allAccsLive;
    NavController navController;
    NavigationView navigationView;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accDatabase = Room.databaseBuilder(this, AccDatabase.class, "account_database")
                .allowMainThreadQueries()
                .build();
        accDao = accDatabase.getAccDao();
        Account admin = new Account();
        admin.setAcc("admin");
        admin.setPwd("admin");
        admin.setPhone("0000000000");
        admin.setEmail("admin");
        admin.setUsername("admin");
        accDao.insertAccs(admin);
        allAccsLive = accDao.getAllAccsLives();





        //////////////////////////////

    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"start",Toast.LENGTH_SHORT).show();

        navController = Navigation.findNavController(this, R.id.fragment);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        appBarConfiguration = new AppBarConfiguration.Builder(navigationView.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        /*
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.homeFragment:
                        return true;
                    case R.id.settingsFragment:
                        Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.aboutFragment:
                        Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

         */

        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration(navController.getGraph()).build();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}