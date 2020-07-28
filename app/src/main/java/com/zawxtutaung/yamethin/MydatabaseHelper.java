package com.zawxtutaung.yamethin;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Dell on 11/28/2016.
 */

public class MydatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "emcryyamethin";
    private static final int DATABASE_VERSION = 5;
    public MydatabaseHelper(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);

    }
}
