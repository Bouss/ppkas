package com.tp1.bousseaud.ppkas.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Util {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static Date stringToDate(String dateAsString, String formatAsString) {
        SimpleDateFormat format = new SimpleDateFormat(formatAsString, Locale.FRANCE);
        Date date = null;

        try {
            date = format.parse(dateAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String readTextFileFromAssets(String fileName, Context context) {
        AssetManager am = context.getAssets();
        StringBuilder builder = new StringBuilder();
        String line;

        try {
            InputStream is = am.open(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((line = in.readLine()) != null) {
                builder.append(line);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
