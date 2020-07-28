package com.zawxtutaung.yamethin.noti;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.zawxtutaung.yamethin.R;

import san.zgyi.uni.SanZtoU;

/**
 * Created by Dell on 1/16/2017.
 */

public class NewView extends AppCompatActivity {

    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String fontsave;
    private int colorchange;

    private String titleuni;
    private String titlezaegyi;

    private String bodyuni;
    private String bodyzawgyi;

    private TextView dateTv,titleTv,bodyTv;

    private ImageView movieimg;

    Toolbar toolbar;
    TextView titletext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_view_custon);
        toolbar= (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titletext= (TextView) findViewById(R.id.titletool);
           colorchangetheme();
        toolbar.setBackgroundColor(colorchange);
      titletext.setText("Yamethin New");

        Bundle bundle=getIntent().getExtras();
         dateTv= (TextView) findViewById(R.id.date);
         titleTv= (TextView) findViewById(R.id.title);
         bodyTv= (TextView) findViewById(R.id.body);
        movieimg= (ImageView) findViewById(R.id.movieImage);

        PicassoClient.downloadImage(getApplicationContext(),bundle.getString("url"),movieimg);

        SanZtoU sanZtoU=new SanZtoU(this,this);

        String titlestring=bundle.getString("title");
        titleuni= (String) sanZtoU.ZawGyiToUni(titlestring,true);
        titlezaegyi= (String) sanZtoU.ZawGyiToUni(titlestring,false);

        String bodystring=bundle.getString("body");
        bodyuni= (String) sanZtoU.ZawGyiToUni(bodystring,true);
        bodyzawgyi= (String) sanZtoU.ZawGyiToUni(bodystring,false);

        dateTv.setText(bundle.getString("date"));

        readSharepre();
        if (fontsave.contains("zawgyi")){

            titleTv.setText(titlestring);
            bodyTv.setText(bodystring);
        }else {
            if (fontsave.contains("unicode")){

                titleTv.setText(titleuni);
                bodyTv.setText(bodyuni);
            }else {
                titleTv.setText(titlestring);
                bodyTv.setText(bodystring);
            }
        }


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
        fontsave=themecolorshare.getString("savefont","");

    }

    //    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//       View v= inflater.inflate(R.layout.new_view_custon, container, false);
//
//
//        return v;
  //  }
}
