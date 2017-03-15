package com.example.elsa.tpapplimobile_bdd_beckerelsa;

/**
 * Created by Elsa on 15/03/2017.
 */

public class Chapitres {

    private long id;
    private String chapter;
    private String desc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // Utilisé par l'ArrayAdapter dans la ListView
    @Override
    public String toString() {
        String affiche="Nom : "+ getChapter()+", Description : "+ getDesc();
        return affiche;
    }

    public Chapitres(String chapter, String desc) {
        this.chapter = chapter;
        this.desc = desc;
    }

    public Chapitres(){}
}
