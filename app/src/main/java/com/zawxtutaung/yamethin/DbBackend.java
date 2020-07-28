package com.zawxtutaung.yamethin;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 12/9/2016.
 */

public class DbBackend extends Dbobject {
    int name;
    int phoneone;
    int adress;
    int phonetwo;
    int id;
    int title;
    int image;
    int i=0;
    int ii=0;
    private Integer[]  imgforarray={R.drawable.car,R.drawable.car,R.drawable.carservic,R.drawable.carservic,R.drawable.schoo,R.drawable.shops,R.drawable.oo,R.drawable.worka,R.drawable.shops,R.drawable.shops,R.drawable.shops,
            R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.acyc,R.drawable.cycb,R.drawable.cycb,R.drawable.buty,R.drawable.carservic,R.drawable.videocamera,R.drawable.shops,
            R.drawable.phshop,R.drawable.offiic,R.drawable.shops,R.drawable.hoa,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops,
            R.drawable.buty,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.hoa,R.drawable.shops,R.drawable.shops

    };
    private Integer[] imgemgercy={R.drawable.fire,R.drawable.acb,R.drawable.embcar,R.drawable.hospital,R.drawable.bloods,R.drawable.offiic,R.drawable.epc,R.drawable.books,R.drawable.offiic};
    public DbBackend(Context context) {
        super(context);
    }


    public List<Contact> getListcartegory(String tablename){
        Contact contact=null;
        List<Contact> contactList=new ArrayList<>();
        Cursor cursor=this.getDbConnection().rawQuery(tablename,null);
        cursor.moveToFirst();
        name=cursor.getColumnIndex("name");
        title=cursor.getColumnIndex("title");

        while (!cursor.isAfterLast()){

                contact = new Contact(cursor.getString(name), cursor.getString(title));

            contactList.add(contact);

            cursor.moveToNext();
        }
        cursor.close();

        return contactList;
    }
    public List<Contact> getListcartegoryone(String tablename){
               Contact contact=null;
        List<Contact> contactList=new ArrayList<>();
        Cursor cursor=this.getDbConnection().rawQuery(tablename,null);
        cursor.moveToFirst();
        name=cursor.getColumnIndex("name");
        title=cursor.getColumnIndex("title");
        image=cursor.getColumnIndex("image");
        while (!cursor.isAfterLast()){
            contact = new Contact(cursor.getString(name),cursor.getString(title));

            contactList.add(contact);
            cursor.moveToNext();
        }
        cursor.close();

        return contactList;
    }
    public List<Contact> getFilterlist(String tablename){
        Contact contact=null;
        List<Contact> contactList=new ArrayList<>();
        Cursor cursor=this.getDbConnection().rawQuery(tablename,null);
      //  cursor.moveToFirst();
        name=cursor.getColumnIndex("name");
        title=cursor.getColumnIndex("title");
        image=cursor.getColumnIndex("image");
       if (cursor.moveToFirst()){
           do{
               contact = new Contact(cursor.getString(name),cursor.getString(title));

               contactList.add(contact);
           }while (cursor.moveToNext());
       }


        return contactList;
    }
    public List<Contact> getListemergency(String tablename){
        Contact contact=null;
        List<Contact> contactList=new ArrayList<>();
        Cursor cursor=this.getDbConnection().rawQuery(tablename,null);
        cursor.moveToFirst();
        name=cursor.getColumnIndex("name");
        id=cursor.getColumnIndex("_id");

        while (!cursor.isAfterLast()){

                contact = new Contact(cursor.getString(name), cursor.getString(title),imgemgercy[ii]);


            contactList.add(contact);
            ii++;
            cursor.moveToNext();
        }
        cursor.close();

        return contactList;
    }
    public List<Contact> getListsinglecargate(String tablename){
        Contact contact=null;
        List<Contact> contactList=new ArrayList<>();
        Cursor cursor=this.getDbConnection().rawQuery(tablename,null);
        cursor.moveToFirst();
        name=cursor.getColumnIndex("name");
        phoneone=cursor.getColumnIndex("phoneone");
        phonetwo=cursor.getColumnIndex("phonetwo");
        adress=cursor.getColumnIndex("adress");


        while (!cursor.isAfterLast()){
            contact=new Contact(cursor.getString(name),cursor.getString(phoneone),cursor.getString(adress),cursor.getString(phonetwo));

            contactList.add(contact);

            cursor.moveToNext();
        }
        cursor.close();

        return contactList;
    }
}


