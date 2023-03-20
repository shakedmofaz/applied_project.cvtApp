package com.example.appliedproject;

import android.os.Parcel;
import android.os.Parcelable;


public class MyPitchDifference implements Parcelable {

    public static final Creator<MyPitchDifference> CREATOR = new Creator<MyPitchDifference>() {
        public MyPitchDifference createFromParcel(Parcel in) {
            return new MyPitchDifference(in);
        }

        public MyPitchDifference[] newArray(int size) {
            return new MyPitchDifference[size];
        }
    };

    final MyNote closest;
    final double deviation;

    MyPitchDifference(MyNote closest, double deviation) {
        this.closest = closest;
        this.deviation = deviation;
    }

    private MyPitchDifference(Parcel in) {
        MyTuning tuning = firstFragment.getCurrentTuning();
        closest = tuning.findNote(in.readString());
        deviation = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(closest.getName().name());
        dest.writeDouble(deviation);
    }
}
