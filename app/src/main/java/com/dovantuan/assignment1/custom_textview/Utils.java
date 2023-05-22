package com.dovantuan.assignment1.custom_textview;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private static Typeface tekoboldTypeface;

    private static Typeface tangerineTypeface;
    private static Typeface oxyyyyTypeface;



    public static Typeface getTekoboldTypeface(Context context) {
        if (tekoboldTypeface == null) {
            tekoboldTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/alfaSlabOne-Regular.ttf");
        }
        return tekoboldTypeface;
    }
    public static Typeface getTangerineTypeface(Context context) {
        if (tangerineTypeface == null) {
            tangerineTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/tangerine-Regular.ttf");
        }
        return tangerineTypeface;
    }
    public static Typeface getOxyyyyTypeface(Context context) {
        if (oxyyyyTypeface == null) {
            oxyyyyTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/oxyyyy.ttf");
        }
        return oxyyyyTypeface;
    }
}
