package com.example.appliedproject;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.appliedproject.ui.main.SectionsPagerAdapter;
import com.example.appliedproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /* firstFragment fragmentA = new firstFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_a, fragmentA);
        fragmentTransaction.commit();

        secondFragment fragmentB = new secondFragment();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.add(R.id.container_b, fragmentB);
        fragmentTransaction2.commit();*/

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // Change the text of the first tab to "Tuner"
        TabLayout.Tab tab1 = tabs.getTabAt(0);
        if (tab1 != null) {
            tab1.setText("Tuner");
        }

        // Change the text of the second tab to "Information"
        TabLayout.Tab tab2 = tabs.getTabAt(1);
        if (tab2 != null) {
            tab2.setText("Information");
        }

        /*FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Use your support!", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Alright!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Perform some action when the "OK" button is clicked
                    }
                });
                snackbar.show();
            }
        });*/
    }
}
