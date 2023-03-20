package com.example.appliedproject;

import java.util.Arrays;

class MyPitchComparator {

    static MyPitchDifference retrieveNote(float pitch) {
        MyTuning tuning = firstFragment.getCurrentTuning();
        int referencePitch = firstFragment.getReferencePitch();

        MyNote[] tuningNotes = tuning.getNotes();
        MyNote[] notes;

        if (firstFragment.isAutoModeEnabled()) {
            notes = tuningNotes;
        } else {
            notes = new MyNote[]{tuningNotes[MainActivity.getReferencePosition()]};
        }

        MyNoteFrequencyCalculator noteFrequencyCalculator =
                new MyNoteFrequencyCalculator(referencePitch);

        Arrays.sort(notes, (o1, o2) ->
                Double.compare(noteFrequencyCalculator.getFrequency(o1),
                        noteFrequencyCalculator.getFrequency(o2)));

        double minCentDifference = Float.POSITIVE_INFINITY;
        MyNote closest = notes[0];
        for (MyNote note : notes) {
            double frequency = noteFrequencyCalculator.getFrequency(note);
            double centDifference = 1200d * log2(pitch / frequency);

            if (Math.abs(centDifference) < Math.abs(minCentDifference)) {
                minCentDifference = centDifference;
                closest = note;
            }
        }

        return new MyPitchDifference(closest, minCentDifference);
    }

    private static double log2(double number) {
        return Math.log(number) / Math.log(2);
    }
}
