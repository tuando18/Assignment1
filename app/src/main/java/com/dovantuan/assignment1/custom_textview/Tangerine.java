package com.dovantuan.assignment1.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class Tangerine extends AppCompatTextView {
    public Tangerine(@NonNull Context context) {
        super(context);
        setFonts();
    }

    public Tangerine(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFonts();
    }

    public Tangerine(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFonts();
    }

    private void setFonts() {
        Typeface typeface = Utils.getTangerineTypeface(getContext());
        setTypeface(typeface);
    }
}
