package com.zawxtutaung.yamethin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import san.zgyi.uni.SanZtoU;

/**
 * Created by Dell on 12/9/2016.
 */

public class ShowsingleView extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    DbBackend mydatabaseHelper;
    Contact contact;
    List<Contact> contactList;
    SingleRecyclerAdapter animalsAdapter;
    private TextView titletext;
    private ImageView icon;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private SanZtoU sanZtoU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showsingle);
        toolbar= (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sanZtoU=new SanZtoU(this,this);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mydatabaseHelper=new DbBackend(this);
        colorchangetheme();
        toolbar.setBackgroundColor(colorchange);
        titletext= (TextView) findViewById(R.id.titletool);
        icon= (ImageView) findViewById(R.id.ivIcon);
        Bundle bundle=getIntent().getExtras();
        titletext.setText(bundle.getString("title"));
        contactList=mydatabaseHelper.getListsinglecargate(bundle.getString("tablename"));
        mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        animalsAdapter = new SingleRecyclerAdapter(getApplicationContext(),contactList,sanZtoU,bundle.getInt("sendimg"));
        mRecyclerView.setAdapter(animalsAdapter);
       animalsAdapter.setSingleOnCustomClick(new SingeleClick());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
        //    NavUtils.navigateUpFromSameTask(this);
//            Intent ii=new Intent(ShowsingleView.this,FilterView.class);
//            startActivity(ii);
            onBackPressed();
           // finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private class SingeleClick implements SingleRecyclerAdapter.SingleOnCustomClick {
        @Override
        public void singleonCustomItemClick(View v,String bussinesname,String phoneone,String phonetwo) {
            DialogPhone fragment=new DialogPhone();
            Bundle bundle=new Bundle();
            bundle.putString("name",bussinesname);
            bundle.putString("phone",phoneone);
            bundle.putString("phtwo",phonetwo);
            fragment.setArguments(bundle);
            fragment.show(getSupportFragmentManager(),"dialog");
            fragment.setCancelable(false);

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
}

