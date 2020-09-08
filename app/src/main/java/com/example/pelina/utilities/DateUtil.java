package com.example.pelina.utilities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String DASH_DATE = "yyyy-MM-dd";
    private static final String REGEX_DASH_DATE = "^[\\d]{4}/[\\d]{2}/[\\d]{2}$";


    @NonNull
    public static String formatDashDate(Date date) {
        if (date == null)
            return "";
        else
            return new SimpleDateFormat(DASH_DATE, Locale.US).format(date);

    }

    @Nullable
    public static Date parseSimpleDate(String date) {
        if (date == null || !date.matches(REGEX_DASH_DATE))
            return null;
        try {
            return new SimpleDateFormat(DASH_DATE, Locale.US).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
