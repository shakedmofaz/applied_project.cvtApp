package com.example.appliedproject;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class MyTunerView extends View {

    private MyCanvasPainter canvasPainter;
    private MyPitchDifference pitchDifference;

    public MyTunerView(Context context) {
        super(context);
        canvasPainter = MyCanvasPainter.with(getContext());
    }

    public MyTunerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        canvasPainter = MyCanvasPainter.with(getContext());
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasPainter.paint(pitchDifference).on(canvas);
    }

    public void setPitchDifference(MyPitchDifference pitchDifference) {
        this.pitchDifference = pitchDifference;
    }
}