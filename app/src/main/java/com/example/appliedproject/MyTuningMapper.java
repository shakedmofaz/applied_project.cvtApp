package com.example.appliedproject;

import android.util.Log;

public class MyTuningMapper {
    private static final int CHROMATIC_TUNING_POSITION = 0;


    static MyTuning getTuningFromPosition(int position) {
        switch (position) {
            case CHROMATIC_TUNING_POSITION:
                return new MyChromaticTuning();

            default:
                Log.w("com.github.cythara", "Unknown position for tuning dropdown list");
                return new MyChromaticTuning();
        }
    }
}
