package com.zawxtutaung.yamethin;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import san.zgyi.uni.SanZtoU;

/**
 * Created by zawxtutaung on 1/4/2017.
 */
public class PagodaDataView extends AppCompatActivity {
    Toolbar toolbar;

    TextView titletext,textname,body;
    ImageView imageView;
    private int imageget;

    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String fontsave;
    private int colorchange;

    private String titleuni;
    private String titlezaegyi;

    private String bodyuni;
    private String bodyzawgyi;

    private Typeface typefaceuni,typefacezawgyi;

    private StringBuffer stringBuffer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagoda_view);
        toolbar= (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titletext= (TextView) findViewById(R.id.titletool);
        imageView= (ImageView) findViewById(R.id.pgi);
        body= (TextView) findViewById(R.id.bodytext);

        colorchangetheme();
        toolbar.setBackgroundColor(colorchange);
        Bundle bundle=getIntent().getExtras();

        SanZtoU sanZtoU=new SanZtoU(this,this);

        String titlestring=bundle.getString("title");
        titleuni= (String) sanZtoU.ZawGyiToUni(titlestring,true);
        titlezaegyi= (String) sanZtoU.ZawGyiToUni(titlestring,false);

        String bodystring=readFile(bundle.getString("body"));
        bodyuni= (String) sanZtoU.ZawGyiToUni(bodystring,true);
        bodyzawgyi= (String) sanZtoU.ZawGyiToUni(bodystring,false);

        imageget=bundle.getInt("imageget");
        imageView.setImageResource(imageget);

//         typefacezawgyi=Typeface.createFromAsset(getAssets(),"fonts/zg.ttf");
//         typefaceuni=Typeface.createFromAsset(getAssets(),"fonts/mm3.ttf");


        readSharepre();
        if (fontsave.contains("zawgyi")){
            body.setText(bodystring);
            titletext.setText(titlestring);
        }else {
            if (fontsave.contains("unicode")){

                body.setText(bodyuni);
                titletext.setText(titleuni);
            }else {
                body.setText(bodystring);
                titletext.setText(titlestring);
            }
        }

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
           onBackPressed();
            finish();
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
    public String readFile(String textfilename){
        AssetManager assetManager=getAssets();
        stringBuffer=new StringBuffer();
        try {
            InputStream inputStream=assetManager.open(textfilename);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String s=null;
            while((s=bufferedReader.readLine())!=null){
                stringBuffer.append(s+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
