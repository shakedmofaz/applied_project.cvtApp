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

public class MainActivity extends AppCompatActivity implements BridgeFragmentInterface,
        SubTunerFragment.TaskCallbacks{


    private ActivityMainBinding binding;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private firstFragment firstFragment;
    private static final String TAG_LISTENER_FRAGMENT = "fragment_sub_tuner";

    @Override
    public void onProgressUpdate(MyPitchDifference pitchDifference) {

        // Show the MainFragment by changing the selected item of the ViewPager
        viewPager.setCurrentItem(0);

        firstFragment = (com.example.appliedproject.firstFragment) sectionsPagerAdapter.getItem(0);
        // Call the method from MainFragment
        firstFragment.onProgressUpdate(pitchDifference);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
        /* firstFragment fragmentA = new firstFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_a, fragmentA);
        fragmentTransaction.commit();

        secondFragment fragmentB = new secondFragment();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.add(R.id.container_b, fragmentB);
        fragmentTransaction2.commit();*/

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        //ViewPager viewPager = binding.viewPager;
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        //TabLayout tabs = binding.tabs;
        TabLayout tabs = findViewById(R.id.tabs);
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
    }

    @Override
    public void openSubFragmentEvent(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SubTunerFragment listenerFragment = (SubTunerFragment)
                fragmentManager.findFragmentByTag(TAG_LISTENER_FRAGMENT);

        if (listenerFragment == null) {
            listenerFragment = new SubTunerFragment();
            fragmentManager
                    .beginTransaction()
                    .add(listenerFragment, TAG_LISTENER_FRAGMENT)
                    .commit();
        }
    }
}
