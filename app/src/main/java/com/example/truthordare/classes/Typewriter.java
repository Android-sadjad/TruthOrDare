package com.example.truthordare.classes;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

public class Typewriter extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence text;
    private int indexCounter;
    private long delay = 5000;

    public Typewriter(Context context) {
        super(context);
    }
    public Typewriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private final Handler handler = new Handler();
    private final Runnable characterAdderRunnable = new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, indexCounter++));
            if(indexCounter <= text.length()) {
                handler.postDelayed(characterAdderRunnable, delay);
            }
        }
    };

    public void animateText(CharSequence text) {
        this.text = text;
        indexCounter = 0;
        setText("");
        handler.removeCallbacks(characterAdderRunnable);
        handler.postDelayed(characterAdderRunnable, delay);
    }
    public void setCharacterDelay(long delay) {
        this.delay = delay;
    }
}
