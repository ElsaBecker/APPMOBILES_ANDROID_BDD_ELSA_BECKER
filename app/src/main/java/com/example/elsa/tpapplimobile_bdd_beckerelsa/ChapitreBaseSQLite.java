package com.example.elsa.tpapplimobile_bdd_beckerelsa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Elsa on 15/03/2017.
 */

public class ChapitreBaseSQLite extends SQLiteOpenHelper {

    public static final String Table_Chapitres = "CHAPITRES";
    public static final String COL_ID = "ID";
    public static final String COL_NOM = "NOM";
    public static final String COL_DESC = "DESCRIPTION";

    private static final String BDD_name = "chapitre.db";
    private static final int BDD_version = 1;

    public static final String CREATE_BDD = "CREATE TABLE " + Table_Chapitres + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NOM + " TEXT NOT NULL, "+ COL_DESC + " TEXT NOT NULL);";


    public ChapitreBaseSQLite(Context context){
        super (context, BDD_name, null, BDD_version);
    }


    //2 Overrides nécessaires pour que la classe fonctionne
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(ChapitreBaseSQLite.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion
                    + ", which will destro all old data");

        db.execSQL("DROP TABLE " + Table_Chapitres);
        onCreate(db);
    }
}
