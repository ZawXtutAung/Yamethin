package com.zawxtutaung.yamethin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Dell on 12/11/2016.
 */

public class FilterView extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    DbBackend mydatabaseHelper;
    Contact contact;
    List<Contact> contactList;
    FilterRecyclerAdapter animalsAdapter;
    private SearchView searchView;
    String findviewtext;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;

    private Integer[]  imgforarray={R.drawable.car,R.drawable.car,R.drawable.carservic,R.drawable.carservic,R.drawable.schoo,R.drawable.shops,R.drawable.oo,R.drawable.worka,R.drawable.shops,R.drawable.shops,R.drawable.shops,
            R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.acyc,R.drawable.cycb,R.drawable.cycb,R.drawable.buty,R.drawable.carservic,R.drawable.videocamera,R.drawable.shops,
            R.drawable.phshop,R.drawable.offiic,R.drawable.shops,R.drawable.hoa,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops,
            R.drawable.buty,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.hoa,R.drawable.shops,R.drawable.shops

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_layout);
        toolbar= (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mydatabaseHelper=new DbBackend(this);
         searchView= (SearchView) findViewById(R.id.customsearch);
        setupSearchView();
      // contactList=mydatabaseHelper.getListcartegoryone("SELECT * FROM category");
        mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        animalsAdapter = new FilterRecyclerAdapter(this);
     // mRecyclerView.setAdapter(animalsAdapter);
        animalsAdapter.setOnCustomClickfilter(new  MainfilterClick());
        colorchangetheme();
        toolbar.setBackgroundColor(colorchange);
        searchView.setBackgroundColor(colorchange);


     //   new execute();
    }

    private void setupSearchView(){

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint(Html.fromHtml("<font color=#ffffff>Search Cartegory</font>"));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

            String filters = "SELECT * FROM category WHERE name LIKE '%" + newText + "%'";
            findviewtext = newText;
            contactList = mydatabaseHelper.getFilterlist(filters);
            animalsAdapter.filter(contactList, imgforarray);
            mRecyclerView.setAdapter(animalsAdapter);

        return true;
    }




    private class MainfilterClick implements FilterRecyclerAdapter.OnCustomClick {
//        @Override
//        public void onCustomItemClick(View v, String textstring,String titleString) {

            @Override
            public void onCustomItemClick(View v, String textstring, String titleString,int position) {
                switch (Integer.parseInt(textstring)){
                    case 1:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM cargate");
                        break;
                    case 2:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM carstore");
                        break;
                    case 3:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM carworkshop");
                        break;
                    case 4:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM bawdar");
                        break;
                    case 5:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM konmarsai");
                        break;
                    case 6:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM seepan");
                        break;
                    case 7:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM satee");
                        break;
                    case 8:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM store");
                        break;
                    case 9:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saroatsai");
                        break;
                    case 10:
                        gointent(titleString,imgforarray[position],"SELECT * FROM saryaykayiyar");
                        break;
                    case 11:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM sarthauksai");
                        break;
                    case 12:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saitpyoyaysay");
                        break;
                    case 13:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM sansai");
                        break;
                    case 14:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saikaiayawysai");
                        break;
                    case 15:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saikaiaposai");
                        break;
                    case 16:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saikaipyinsai");
                        break;
                    case 17:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM sanpinhnyatsai");
                        break;
                    case 18:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM twinkone");
                        break;
                    case 19:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM datpontvideo");
                        break;
                    case 20:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM pohtaisai");
                        break;
                    case 21:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM phoneyaungwaisai");
                        break;
                    case 22:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM bank");
                        break;
                    case 23:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM matetusai");
                        break;
                    case 24:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM talkhokan");
                        break;
                    case 25:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM mounkphatsat");
                        break;
                    case 26:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM hlyatsispyitsesai");
                        break;
                    case 27:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM hlyatsispyintsai");
                        break;
                    case 28:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM thinkansai");
                        break;
                    case 29:
                        gointent(titleString,imgforarray[position],"SELECT * FROM ahlapyinsai");
                        break;
                    case 30:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM aeinsaukpyitsesai");
                        break;
                    case 31:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM aphyawyamakarsai");
                        break;
                    case 32:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saysai");
                        break;
                    case 33:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM saykan");
                        break;
                    case 34:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM shwesai");
                        break;
                    case 35:
                        gointent(titleString,imgforarray[Integer.parseInt(textstring)],"SELECT * FROM thaukyaythank");
                        break;

                }


           // Toast.makeText(getApplicationContext(),textstring,Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(FilterView.this,ShowsingleView.class);
//            intent.putExtra("title",titleString);
//            intent.putExtra("sendimg",imgforarray[Integer.parseInt(textstring)]);
//            startActivity(intent);
        }
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
    public void readSharepre(){
        themecolorshare=getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");

    }

    public void gointent(String title,int sendimg,String tablename){
        Intent intent = new Intent(FilterView.this, ShowsingleView.class);
        intent.putExtra("title", title);
        intent.putExtra("sendimg",sendimg);
        intent.putExtra("tablename",tablename);
        startActivity(intent);
    }
    public void showtoast(){
        Toast.makeText(this,"No Data This Title",Toast.LENGTH_SHORT).show();
    }


    private class DatabaseTask  extends AsyncTask<String,String,List<Contact>>{
        @Override
        protected List<Contact> doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

    }
}

