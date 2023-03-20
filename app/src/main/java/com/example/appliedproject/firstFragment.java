package com.example.appliedproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appliedproject.databinding.ActivityMainBinding;
import com.example.appliedproject.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstFragment} factory method to
 * create an instance of this fragment.
 */
public class firstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public static final String PREFS_FILE = "prefs_file";
    public static final String USE_SCIENTIFIC_NOTATION = "use_scientific_notation";
    public static final String CURRENT_TUNING = "current_tuning";
    protected static final String REFERENCE_PITCH = "reference_pitch";
    private static final String TAG_LISTENER_FRAGMENT = "listener_fragment";
    private static final String USE_DARK_MODE = "use_dark_mode";

    public static boolean isAutoModeEnabled() {
        return true;
    }

    public firstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // cythara
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MyTuning chromaticTuner = new MyChromaticTuning();
        updateTuner(chromaticTuner);




        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "use your support!", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_red_dark)); // set the action button color
                snackbar.show();
            }
        });
        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldValue, int newValue) {
        String tag = String.valueOf(picker.getTag());
        if ("reference_pitch_picker".equalsIgnoreCase(tag)) {
            final SharedPreferences preferences = getSharedPreferences(PREFS_FILE,
                    MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(REFERENCE_PITCH, newValue);
            editor.apply();

            setReferencePitch();

            TunerView tunerView = this.findViewById(R.id.pitch);
            tunerView.invalidate();
        } else if ("note_picker".equalsIgnoreCase(tag)) {
            isAutoModeEnabled = newValue == 0;

            referencePosition = newValue;

            recreate();
        }
    }

    private void startRecording() {
        SubTunerFragment fragmentManager = getSupportFragmentManager();
        SubTunerFragment listenerFragment = (ListenerFragment)
                fragmentManager.findFragmentByTag(TAG_LISTENER_FRAGMENT);

        if (listenerFragment == null) {
            listenerFragment = new ListenerFragment();
            fragmentManager
                    .beginTransaction()
                    .add(listenerFragment, TAG_LISTENER_FRAGMENT)
                    .commit();
        }
    }
}