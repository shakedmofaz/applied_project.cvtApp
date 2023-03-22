package com.example.appliedproject;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appliedproject.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstFragment} factory method to
 * create an instance of this fragment.
 */
public class firstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public static final int RECORD_AUDIO_PERMISSION = 0;
    public static final String PREFS_FILE = "prefs_file";
    public static final String USE_SCIENTIFIC_NOTATION = "use_scientific_notation";
    public static final String CURRENT_TUNING = "current_tuning";
    protected static final String REFERENCE_PITCH = "reference_pitch";
    private static final String TAG_LISTENER_FRAGMENT = "listener_fragment";
    private static final String USE_DARK_MODE = "use_dark_mode";
    private static int referencePitch;
    private static int referencePosition;
    private static int tuningPosition = 0;
    private View view;
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

        MyTunerView customView = binding.customView;
        // cythara
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_second, container, false);

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
            final SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_FILE,
                    MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(REFERENCE_PITCH, newValue);
            editor.apply();

            setReferencePitch();

            MyTunerView tunerView = this.findViewById(R.id.pitch);
            MyTunerView.invalidate();
        } else if ("note_picker".equalsIgnoreCase(tag)) {
            isAutoModeEnabled = newValue == 0;

            referencePosition = newValue;

            recreate();
        }
    }

    private void startRecording() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
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

    private void setTuning() {
        final SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_FILE,
                MODE_PRIVATE);
        tuningPosition = preferences.getInt(CURRENT_TUNING, 0);

        int textColorDark = getResources().getColor(R.color.colorTextDark);

        MaterialSpinner spinner = view.findViewById(R.id.tuning);
        MaterialSpinnerAdapter<String> adapter = new MaterialSpinnerAdapter<>(this,
                Arrays.asList(getResources().getStringArray(R.array.tunings)));

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelectedIndex(tuningPosition);
    }


    private void setReferencePitch() {
        final SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_FILE,
                MODE_PRIVATE);
        referencePitch = preferences.getInt(REFERENCE_PITCH, 440);
    }

    private void requestRecordAudioPermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.RECORD_AUDIO},
                RECORD_AUDIO_PERMISSION);
    }
}