package com.dovantuan.assignment1.custom_textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class Bold extends AppCompatTextView {
    public Bold(@NonNull Context context) {
        super(context);
        setFonts();
    }

    public Bold(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFonts();
    }

    public Bold(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFonts();
    }

    private void setFonts() {
        Typeface typeface = Utils.getTekoboldTypeface(getContext());
        setTypeface(typeface);
    }
}
