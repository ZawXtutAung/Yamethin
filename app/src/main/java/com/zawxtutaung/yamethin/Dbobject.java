package com.zawxtutaung.yamethin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Dell on 12/9/2016.
 */

public class Dbobject {
    private Context context;
    private static MydatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public Dbobject(Context context) {
        this.context = context;
        dbHelper = new MydatabaseHelper(context);
        this.db = dbHelper.getReadableDatabase();

    }
    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
}
