package com.example.elsa.tpapplimobile_bdd_beckerelsa;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Comment;

import java.util.List;

/**
 * Created by Elsa on 15/03/2017.
 */

public class ChapitresBDD {

    private SQLiteDatabase bdd;
    private ChapitreBaseSQLite chapitres;
    private String[] allColumns = { ChapitreBaseSQLite.COL_ID,
            ChapitreBaseSQLite.COL_NOM, ChapitreBaseSQLite.COL_DESC };

    public ChapitresBDD(Context context){
        chapitres = new ChapitreBaseSQLite(context);
    }

    public void open() throws SQLException { //Throws pour dire que ça genere une exception si pas de try catch
        bdd = chapitres.getWritableDatabase();
    }

    public void close() {
        chapitres.close();
    }

    public Chapitres insertChapter(String chapter, String desc){
        ContentValues values = new ContentValues();
        values.put(ChapitreBaseSQLite.COL_NOM, chapter);
        values.put(ChapitreBaseSQLite.COL_DESC, desc);

        long insertID = bdd.insertOrThrow(ChapitreBaseSQLite.Table_Chapitres, null, values);

        Cursor cursor = bdd.query(ChapitreBaseSQLite.Table_Chapitres, allColumns,
                ChapitreBaseSQLite.COL_ID + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();
        Chapitres newChapitre = cursorToChapitre(cursor);
        cursor.close();
        return newChapitre;
    }

    public long insertChapter(Chapitres chapitres){
        ContentValues values = new ContentValues();
        values.put(ChapitreBaseSQLite.COL_NOM, chapitres.getChapter());
        values.put(ChapitreBaseSQLite.COL_DESC, chapitres.getDesc());
        return bdd.insert(ChapitreBaseSQLite.Table_Chapitres, null, values);
    }


    private Chapitres cursorToChapitre(Cursor cursor){
        Chapitres chapitres = new Chapitres();
        chapitres.setId(cursor.getLong(0));
        chapitres.setChapter(cursor.getString(1));
        chapitres.setDesc(cursor.getString(2));
        return chapitres;
    }

    public void removeChapter(Chapitres chapitres){
        long id = chapitres.getId();

        System.out.println("Chapter deleted with id : "+ id);
        bdd.delete(ChapitreBaseSQLite.Table_Chapitres, ChapitreBaseSQLite.COL_ID + " = " + id, null);
    }

    public List<Chapitres> getAllChapters(){
        List<Chapitres> chapitres = new ArrayList<Chapitres>();

        Cursor cursor = bdd.query(ChapitreBaseSQLite.Table_Chapitres, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Chapitres chapitre = cursorToChapitre(cursor);
            chapitres.add(chapitre);
            cursor.moveToNext();
        }

        //fermer le curseur
        cursor.close();
        return chapitres;
    }

}

