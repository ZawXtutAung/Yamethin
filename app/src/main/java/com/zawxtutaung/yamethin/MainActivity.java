package com.zawxtutaung.yamethin;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.zawxtutaung.yamethin.map.MapAtivity;
import com.zawxtutaung.yamethin.noti.UsereadFg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence dtitle;
    private CharSequence title;
    private String[] sarray={"Home","New","Setting","About"};
    private Integer[] iarray={R.drawable.homeic,R.drawable.reader,R.drawable.sett,R.drawable.iii};
    private ArrayList<ListData> listarray;
    Toolbar toolbar;
    private ImageButton imgbt,mapbtn;
    TextView toolTitle;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RelativeLayout relativeLayout;
    boolean listCheck=true;
    SharedPreferences sharedPreferences,savethemeshare,savefragmentsetting,openbtn;
    SharedPreferences.Editor editor;
    String saveString,colorsaveString,savefragset;
    private int colorchange;
    public int tooltitlep;

    ImageView drawimg;



    //changeRecyclerView changerecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    DbBackend dbBackend;
    List<Contact> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        listView= (ListView) findViewById(R.id.left_drawer);
        toolbar= (Toolbar) findViewById(R.id.my_awesome_toolbar);
        imgbt= (ImageButton) findViewById(R.id.customsearch);
        mapbtn= (ImageButton) findViewById(R.id.mapbutton);
        mainRecyclerAdapter=new MainRecyclerAdapter();
        drawimg= (ImageView) findViewById(R.id.myimage);

        dbBackend=new DbBackend(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        showdrawerimg();

         imgbt.setOnClickListener(new ImageButton.OnClickListener(){
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainActivity.this,FilterView.class);
                 startActivity(intent);


             }
         });
mapbtn.setOnClickListener(new ImageButton.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent mIntent=new Intent(MainActivity.this, MapAtivity.class);
        startActivity(mIntent);
    }
});


        relativeLayout= (RelativeLayout) findViewById(R.id.myrelat);
        frameLayout= (FrameLayout) findViewById(R.id.content_frame);

        //changecolor
        colorchangetheme();
        toolbar.setBackgroundColor(colorchange);
        relativeLayout.setBackgroundColor(colorchange);
        imgbt.setBackgroundColor(colorchange);
        mapbtn.setBackgroundColor(colorchange);

        toolTitle= (TextView) findViewById(R.id.titletool);
        listarray=new ArrayList<>();
        for (int i=0;i<sarray.length;i++){
            ListData listData=new ListData(sarray[i],iarray[i]);
            listarray.add(listData);
        }
        DrawerListAdapter drawerListAdapter=new DrawerListAdapter(getApplicationContext(),listarray);
        listView.setAdapter(drawerListAdapter);
        listView.setOnItemClickListener(new DrawItemClick());
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
             toolTitle.setText(sarray[tooltitlep]);
           //     setTitle(title);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolTitle.setText("Yamethin Directory");
                invalidateOptionsMenu();
                if (listCheck==true){
                    listView.getChildAt(tooltitlep).setBackgroundColor(getResources().getColor(R.color.layoutbgcolor));
                    listCheck=false;
                }
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        toolTitle.setText("Yamethin Directory");
        if (savedInstanceState==null){
                selectItem(0);
        }}



    private class DrawItemClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
             tooltitlep=position;
            for (int i=0;i<listView.getChildCount();i++) {
                if (position==i) {
                    listView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.layoutbgcolor));
                }else {
                    listView.getChildAt(i).setBackgroundColor(colorchange);
                }
            }
        }
    }
    private void selectItem(int position){
        if (position==0) {

                imgbt.setVisibility(View.VISIBLE);
                mapbtn.setVisibility(View.VISIBLE);
              //  saveSharepre();
                clearsavefrag();
                clearbopenbtn();
                Fragment fragment = new MainpagerView();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment,"feagehome");
                fragmentTransaction.commit();


        }
        if (position==1) {
            imgbt.setVisibility(View.GONE);
            mapbtn.setVisibility(View.GONE);
            clearSharepre();
            clearsavefrag();
            clearbopenbtn();

            Fragment fragment = new UsereadFg();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment,"newfg");
            fragmentTransaction.commit();

        }
        if (position==2) {

            imgbt.setVisibility(View.GONE);
            mapbtn.setVisibility(View.GONE);
            clearSharepre();
            clearsavefrag();
            clearbopenbtn();
            Fragment fragment = new SettingFg();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment,"fragsetting");
            fragmentTransaction.commit();

        }
        if (position==3){
            imgbt.setVisibility(View.GONE);
            mapbtn.setVisibility(View.GONE);
            clearSharepre();
            clearbopenbtn();
            Fragment fragment = new AboutFg();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment,"frageabout");
            fragmentTransaction.commit();

        }
        listView.setItemChecked(position,true);
        setTitle(sarray[position]);
        drawerLayout.closeDrawer(relativeLayout);
    }
    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        toolTitle.setText(title);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    public void onBackPressed() {
//        readSharepre();
//        if (saveString.contains("savefrag")){
//            listView.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.layoutbgcolor));
//            listView.getChildAt(1).setBackgroundColor(colorchange);
//            listView.getChildAt(2).setBackgroundColor(colorchange);
//            listView.getChildAt(3).setBackgroundColor(colorchange);
//            clearbopenbtn();
//            clearsavefrag();
//            selectItem(0);
//        }else {
            final Dialog exitdi=new Dialog(MainActivity.this);
            exitdi.requestWindowFeature(Window.FEATURE_NO_TITLE);
            exitdi.setContentView(R.layout.exit_layout);
            exitdi.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            final Button btnno=(Button)exitdi.findViewById(R.id.no);
            btnno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exitdi.dismiss();
                }
            });
            final Button btnyes = (Button)exitdi. findViewById(R.id.yes);
            btnyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exitdi.dismiss();
                    finish();
                    clearSharepre();

                }
            });

            exitdi.show();

    }
//    public void saveSharepre(){
//        sharedPreferences=getSharedPreferences("savefragplace",0);
//        editor=sharedPreferences.edit();
//        editor.putString("savefarg","savefrag");
//        editor.commit();
//    }
    public void readSharepre(){
        sharedPreferences=getSharedPreferences("savefragplace",0);
        savethemeshare=getSharedPreferences("savethemecolor",0);
        savefragmentsetting=getSharedPreferences("savefrag",0);

        saveString=sharedPreferences.getString("savefarg","");
        colorsaveString=savethemeshare.getString("colorsave","");
        savefragset=savefragmentsetting.getString("savefragmentsetting","");


    }
    public void clearSharepre(){
        sharedPreferences=getSharedPreferences("savefragplace",0);
        sharedPreferences.edit().clear().commit();
    }
    public void clearsavefrag(){
        savefragmentsetting=getSharedPreferences("savefrag",0);
        savefragmentsetting.edit().clear().commit();
    }

    public void clearbopenbtn(){
        openbtn=getSharedPreferences("openbtn",0);
        openbtn.edit().clear().commit();
    }

    public void colorchangetheme(){
        readSharepre();
        if (colorsaveString.contains("bluelow")){
            colorchange=getResources().getColor(R.color.bluelow);
        }else {
            if (colorsaveString.contains("blue")){
                colorchange=getResources().getColor(R.color.blue);
            }else {
                if (colorsaveString.contains("brown")){
                    colorchange=getResources().getColor(R.color.brown);
                }else {
                    if (colorsaveString.contains("high")){
                        colorchange=getResources().getColor(R.color.bluehigh);
                    }else {
                        if (colorsaveString.contains("greena")){
                            colorchange=getResources().getColor(R.color.greena);
                        }else {
                            if (colorsaveString.contains("orange")){
                                colorchange=getResources().getColor(R.color.orange);
                            }else {
                                if (colorsaveString.contains("blacka")){
                                    colorchange=getResources().getColor(R.color.blacka);
                                }else {
                                    colorchange=getResources().getColor(R.color.bluelow);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
private void  showdrawerimg(){
    FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
    StorageReference storageReferencedrawerimg=firebaseStorage.getReferenceFromUrl("gs://yamethin-6f538.appspot.com").child("directoryadsdrawerimg.jpg");
    Glide.with(this)
            .using(new FirebaseImageLoader())
            .load(storageReferencedrawerimg)
            .into(drawimg);
}

}
