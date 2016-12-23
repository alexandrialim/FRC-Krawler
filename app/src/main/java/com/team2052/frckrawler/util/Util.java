package com.team2052.frckrawler.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.TypedValue;

import com.team2052.frckrawler.R;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Util {
    public static int getPixelsFromDp(Context c, int dipValue) {
        Resources r = c.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }


    public static String generateUniqueHash(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(128, random).toString(32);
    }
}
