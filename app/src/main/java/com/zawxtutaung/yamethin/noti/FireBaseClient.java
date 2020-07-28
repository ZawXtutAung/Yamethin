package com.zawxtutaung.yamethin.noti;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zawxtutaung on 1/12/2017.
 */
public class FireBaseClient {
    Context c;
    String DB_URL;
    RecyclerView rv;
    Firebase fire;
    ArrayList<Movier> movies = new ArrayList<>();
    MyAdapter adapter;
    private ProgressBar progressBar;

    public FireBaseClient(Context c, String dbUrl, RecyclerView rv,ProgressBar progressBar) {
       this.progressBar=progressBar;
        this.c = c;
        this.DB_URL = dbUrl;
        this.rv = rv;
        Firebase.setAndroidContext(c);
        fire = new Firebase(DB_URL);
    }

    public void saveOnline(String name, String url,String body,String date) {
        Movier m = new Movier();
        m.setName(name);
        m.setUrl(url);
        m.setBody(body);
        m.setDate(date);


        fire.child("Movie").push().setValue(m);
    }

    public void refreshData() {
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });
    }

    private void getUpdates(DataSnapshot dataSnapshot) {
        movies.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Movier m=new Movier();
            m.setName(ds.getValue(Movier.class).getName());
            m.setUrl(ds.getValue(Movier.class).getUrl());
            m.setBody(ds.getValue(Movier.class).getBody());
            m.setDate(ds.getValue(Movier.class).getDate());

            movies.add(m);
        }
        Collections.reverse(movies);
        if(movies.size()>0)
        {
            adapter=new MyAdapter(c,movies);
            rv.setAdapter(adapter);
           adapter.setOnCustomClick(new Newitem());
        progressBar.setVisibility(View.INVISIBLE);

        }else {
            Toast.makeText(c,"No data", Toast.LENGTH_SHORT).show();
        }
    }

    private class Newitem implements MyAdapter.OnCustomClick {
        @Override
        public void onCustomItemClick(View v, int position,String date,String title,String body,String url) {
            Intent intent=new Intent(c,NewView.class);
            intent.putExtra("date",date);
            intent.putExtra("title",title);
            intent.putExtra("body",body);
            intent.putExtra("url",url);
           c.startActivity(intent);


        }
    }
}