package com.zawxtutaung.yamethin;

/**
 * Created by Dell on 12/10/2016.
 */

public class ListData {
    private String listtext;
    private int listimg;

    public ListData(String listtext, int listimg) {
        this.listtext = listtext;
        this.listimg = listimg;
    }

    public String getListtext() {
        return listtext;
    }

    public void setListtext(String listtext) {
        this.listtext = listtext;
    }

    public int getListimg() {
        return listimg;
    }

    public void setListimg(int listimg) {
        this.listimg = listimg;
    }
}
