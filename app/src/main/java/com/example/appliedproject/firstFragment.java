package com.example.appliedproject;

import static android.content.Context.MODE_PRIVATE;

import static androidx.core.app.ActivityCompat.recreate;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.appliedproject.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;
import com.shawnlin.numberpicker.NumberPicker;
import com.shawnlin.numberpicker.NumberPicker.OnValueChangeListener;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstFragment} factory method to
 * create an instance of this fragment.
 */
public class firstFragment extends Fragment implements SubTunerFragment.TaskCallbacks,
        MaterialSpinner.OnItemSelectedListener, OnValueChangeListener {

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
    private static boolean isAutoModeEnabled = true;

    public firstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        MyTunerView customView = binding.customView;  ?????
        // cythara
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_second, container, false);

        requireActivity().setContentView(R.layout.activity_main);

        MyTuning chromaticTuner = new MyChromaticTuning();
        //updateTuner(chromaticTuner);






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


    public static int getReferencePosition() {
        return referencePosition - 1; //to account for the position of the AUTO option
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tuning chromaticTuner = new ChromaticTuning();
        updateTuner(chromaticTuner);*/

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.RECORD_AUDIO);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestRecordAudioPermission();
        } else {
            startRecording();
        }

        //enableTheme();

        requireActivity().setContentView(R.layout.fragment_first);

        setTuning();
        setReferencePitch();

        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

       // Toolbar myToolbar = findViewById(R.id.my_toolbar);
       // myToolbar.setTitle(R.string.app_name);
       // myToolbar.showOverflowMenu();
       // setSupportActionBar(myToolbar);
    }



    @Override
    public void onProgressUpdate(MyPitchDifference pitchDifference) {
        MyTunerView tunerView = this.view.findViewById(R.id.pitch);

        tunerView.setPitchDifference(pitchDifference);
        tunerView.invalidate();
    }


    //@Override
   // public void onBackPressed() {
    //    moveTaskToBack(true);
   // }
 //This means that when the user presses the back button while this activity is in the foreground,
    // the app will be minimized and the user will return to the home screen or the previous app they were using.
    // The activity will remain in the background and can be resumed later by the user or by another part of the app.



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startRecording();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle(R.string.permission_required);
                    alertDialog.setMessage(getString(R.string.microphone_permission_required));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
                            (dialog, which) -> {
                                dialog.dismiss();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    requireActivity().finishAffinity();
                                } else {
                                    requireActivity().finish();
                                }
                            });
                    alertDialog.show();
                }
            }
        }
    }



    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        final SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_FILE,
                MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CURRENT_TUNING, position);
        editor.apply();

        tuningPosition = position;

        isAutoModeEnabled = true;
        referencePosition = 0;

        recreate();
    }

    private void recreate() {
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

            MyTunerView tunerView = this.view.findViewById(R.id.pitch);
            tunerView.invalidate();
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