package com.example.abeshackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.abeshackathon.Fragments.Dashboard;
import com.example.abeshackathon.Fragments.Medical;
import com.example.abeshackathon.Fragments.Profile;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.abeshackathon.Retro.gson;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;

    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Dashboard");
        setTitleColor(R.color.black);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.menu_grey);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hview=navigationView.getHeaderView(0);
        TextView drawerName = (TextView) hview.findViewById(R.id.drawerName);
        TextView drawerId = (TextView) hview.findViewById(R.id.drawerId);
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String string=sharedPreferences.getString("logindata","");
        Log.e("datacheck",string);
        Type type=new TypeToken<Loginresponse>() {
        }.getType();
        Loginresponse loginresponse=gson.fromJson(string,type);
        drawerName.setText(loginresponse.getStatus().toUpperCase());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                Class fragmentClass = null;
                Menu menu = navigationView.getMenu();
                switch (menuItem.getItemId()) {

                    case R.id.nav_dashboard:
                        fragmentClass = Dashboard.class;
                        break;

                    case R.id.medical:
                        fragmentClass= Medical.class;
                        break;

                    case R.id.Profile:
                        fragmentClass= Profile.class;
                        break;
                    case R.id.Logout:
                        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
                        finish();
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    default:
                        fragmentClass = Dashboard.class;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                replaceFragment(fragment);
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = Dashboard.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        replaceFragment(fragment);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.flContent);
                if (f != null) {
                    updateTitleAndDrawer(f);
                }

            }
        });
    }

    private void updateTitleAndDrawer(Fragment f) {
        String fragClassName = f.getClass().getName();
        if (fragClassName.equals(Dashboard.class.getName())) {
            navigationView.getMenu().getItem(0).setChecked(true);
            setTitle("Dashboard");
        }
    }


    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.flContent, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.horn, menu);
//
//        return super.onPrepareOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.buzzer: {
//
//                final MediaPlayer mp = MediaPlayer.create(this, R.raw.alarm_beeps);
//                mp.start();
//
//                invalidateOptionsMenu();
//                break;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
            @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
