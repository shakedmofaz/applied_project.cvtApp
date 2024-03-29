package com.example.appliedproject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.appliedproject.dsp.AudioDispatcher;
import com.example.appliedproject.dsp.io.android.AudioDispatcherFactory;
//import com.example.appliedproject.dsp.pitch.PitchDetectionHandler;
//import com.example.appliedproject.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.AudioDispatcher;
//import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
import java.util.ArrayList;
import java.util.List;

public class SubTunerFragment extends Fragment  {

    private static final int SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = 1024 * 4;
    private static final int OVERLAP = 768 * 4;
    private static final int MIN_ITEMS_COUNT = 15;
    static boolean IS_RECORDING;
    private static List<MyPitchDifference> pitchDifferences = new ArrayList<>();
    private static TaskCallbacks taskCallbacks;
    private PitchListener pitchListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            taskCallbacks = (TaskCallbacks) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement TaskCallbacks");
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            taskCallbacks = (TaskCallbacks) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        pitchListener = new PitchListener();
        pitchListener.execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        taskCallbacks = null;
        pitchListener.cancel(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        pitchListener.cancel(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (pitchListener.isCancelled()) {
            pitchListener = new PitchListener();
            pitchListener.execute();
        }
    }

    interface TaskCallbacks {

        void onProgressUpdate(MyPitchDifference percent);
    }

    private static class PitchListener extends AsyncTask<Void, MyPitchDifference, Void> {

        private AudioDispatcher audioDispatcher;

        @Override
        protected Void doInBackground(Void... params) {
            PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {

                if (isCancelled()) {
                    stopAudioDispatcher();
                    return;
                }

                if (!IS_RECORDING) {
                    IS_RECORDING = true;
                    publishProgress();
                }

                float pitch = pitchDetectionResult.getPitch();

                if (pitch != -1) {
                    MyPitchDifference pitchDifference = MyPitchComparator.retrieveNote(pitch);

                    pitchDifferences.add(pitchDifference);

                    if (pitchDifferences.size() >= MIN_ITEMS_COUNT) {
                        MyPitchDifference average =
                                MySampler.calculateAverageDifference(pitchDifferences);

                        publishProgress(average);

                        pitchDifferences.clear();
                    }
                }
            };

            PitchProcessor pitchProcessor = new PitchProcessor(PitchEstimationAlgorithm.FFT_YIN,
                    SAMPLE_RATE,
                    BUFFER_SIZE, pitchDetectionHandler);

            audioDispatcher = AudioDispatcherFactory.fromDefaultMicrophone(SAMPLE_RATE,
                    BUFFER_SIZE, OVERLAP);

            audioDispatcher.addAudioProcessor(pitchProcessor);

            audioDispatcher.run();

            return null;
        }

        @Override
        protected void onCancelled(Void result) {
            stopAudioDispatcher();
        }

        @Override
        protected void onProgressUpdate(MyPitchDifference... pitchDifference) {
            if (taskCallbacks != null) {
                if (pitchDifference.length > 0) {
                    taskCallbacks.onProgressUpdate(pitchDifference[0]);
                } else {
                    taskCallbacks.onProgressUpdate(null);
                }
            }
        }

        private void stopAudioDispatcher() {
            if (audioDispatcher != null && !audioDispatcher.isStopped()) {
                audioDispatcher.stop();
                IS_RECORDING = false;
            }
        }
    }
}