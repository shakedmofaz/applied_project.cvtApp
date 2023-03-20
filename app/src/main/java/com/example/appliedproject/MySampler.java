package com.example.appliedproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySampler {

    static MyPitchDifference calculateAverageDifference(List<MyPitchDifference> samples) {
        MyNote mostFrequentNote = extractMostFrequentNote(samples);
        List<MyPitchDifference> filteredSamples = filterByNote(samples, mostFrequentNote);

        double deviationSum = 0;
        int sameNoteCount = 0;
        for (MyPitchDifference pitchDifference : filteredSamples) {
            deviationSum += pitchDifference.deviation;
            sameNoteCount++;
        }

        if (sameNoteCount > 0) {
            double averageDeviation = deviationSum / sameNoteCount;

            return new MyPitchDifference(mostFrequentNote, averageDeviation);
        }

        return null;
    }

    static List<MyPitchDifference> filterByNote(List<MyPitchDifference> samples, MyNote note) {
        List<MyPitchDifference> filteredSamples = new ArrayList<>();

        for (MyPitchDifference sample : samples) {
            if (sample.closest == note) {
                filteredSamples.add(sample);
            }
        }

        return filteredSamples;
    }

    static MyNote extractMostFrequentNote(List<MyPitchDifference> samples) {
        Map<MyNote, Integer> noteFrequencies = new HashMap<>();

        for (MyPitchDifference pitchDifference : samples) {
            MyNote closest = pitchDifference.closest;
            if (noteFrequencies.containsKey(closest)) {
                Integer count = noteFrequencies.get(closest);
                noteFrequencies.put(closest, count + 1);
            } else {
                noteFrequencies.put(closest, 1);
            }
        }

        MyNote mostFrequentNote = null;
        int mostOccurrences = 0;
        for (MyNote note : noteFrequencies.keySet()) {
            Integer occurrences = noteFrequencies.get(note);
            if (occurrences > mostOccurrences) {
                mostFrequentNote = note;
                mostOccurrences = occurrences;
            }
        }

        return mostFrequentNote;
    }
}

