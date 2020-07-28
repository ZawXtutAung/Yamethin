package com.zawxtutaung.yamethin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import san.zgyi.uni.SanZtoU;

import static android.view.View.VISIBLE;

/**
 * Created by Dell on 1/24/2017.
 */

public class AboutFg extends Fragment implements View.OnClickListener {
    private View v;
    Button zxaph, ktph, pshph,fbpage;
    LinearLayout linearLayoutkt, linearLayoutzxa;
    Context context;
    ImageButton zawphcall,zawsms,zawsendsms;
    ImageButton kophcall,kosms,kosendsms;
    String kt;
    Toolbar toolbar;
    TextView titletext;
    EditText zawedt,koedt;

    TextView txone,txtwo;

    String txonez,txoneu,txtwoz,txtwou;

    private boolean checkzawsms=true;
    private boolean checkkosms=true;

    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String fontsave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.about_layout, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        kophcall= (ImageButton) v.findViewById(R.id.phk);
        kosms = (ImageButton)v. findViewById(R.id.ktsms);
        kosendsms= (ImageButton) v.findViewById(R.id.sendkosms);

        zawphcall= (ImageButton) v.findViewById(R.id.phzaw);
        zawsms = (ImageButton) v.findViewById(R.id.zxasms);
        zawsendsms= (ImageButton) v.findViewById(R.id.sendzawsms);

        zawedt= (EditText) v.findViewById(R.id.zawedt);
        koedt= (EditText) v.findViewById(R.id.koedt);

        txone= (TextView) v.findViewById(R.id.txone);
        txtwo= (TextView) v.findViewById(R.id.txtwo);

//        toolbar= (Toolbar)v. findViewById(R.id.abto);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        titletext= (TextView) findViewById(R.id.titletool);
//        titletext.setText("About");

        zxaph = (Button)v. findViewById(R.id.zxaph);
        ktph = (Button) v.findViewById(R.id.ktph);

        pshph = (Button)v. findViewById(R.id.pstph);
        fbpage = (Button) v.findViewById(R.id.fbpage);

        linearLayoutkt = (LinearLayout) v.findViewById(R.id.ktlly);
        linearLayoutzxa = (LinearLayout)v. findViewById(R.id.zxally);

        zawphcall.setOnClickListener(this);
        zawsendsms.setOnClickListener(this);
        zawsms.setOnClickListener(this);

        kophcall.setOnClickListener(this);
        kosms.setOnClickListener(this);
        kosendsms.setOnClickListener(this);

        zxaph.setOnClickListener(this);
        ktph.setOnClickListener(this);
        fbpage.setOnClickListener(this);

        SanZtoU sanZtoU=new SanZtoU(getContext(), (MainActivity) getContext());
        String fortxone="application ျဖစ္ေျမာက္ေရးအတြက္ ကူညီေပးေသာ\nသူငယ္ခ်င္းမ်ား/ညီကိုမ်ား အားအထူးေက်းဇူးတင္ပါသည္";
        String fortxtwo="ျမန္မာေဖာင့္ခ်ိန္းျခင္းအတြက္ ကိုစံကိုကိုရဲ့ Library\nကိုယူသံုးထားပါတယ္။ေက်းဇူးပါကုိုစံကိုကို။" +
                "\n" +
                "\n"+
                        "ရမည္းသင္းသမုိင္းႏွင့္ဘုရားသမိုင္းမ်ားကို ဆင္က်ံဳး\nဆရာေတာ္ဘုရားၾကီး၏ ရမည္းသင္းခရိုင္သာသနာဝင္\n"+
                        "သမိုင္းစာအုပ္အား လူမႈထူးခ်ြန္စာၾကည့္တုိက္မွဴး\n  ဦးကိုကိုမ်ိဳးမွဖတ္ရႈ၍ facebook တြင္တင္ထားသည့္\n" +
                "အခ်က္အလက္ႏွင့္ဓာတ္ပံုမ်ားကိုကူးယူေဖာ္ျပ\nထားပါသည္။ေက်းဇူးပါဦးကိုကိုမ်ိဳး။"
                ;
        txonez= (String) sanZtoU.ZawGyiToUni(fortxone,true);
        txoneu= (String) sanZtoU.ZawGyiToUni(fortxone,false);

        txtwoz= (String) sanZtoU.ZawGyiToUni(fortxtwo,true);
        txtwou= (String) sanZtoU.ZawGyiToUni(fortxtwo,false);

        readSharepre();
        if (fontsave.contains("zawgyi")){
          txone.setText(fortxone);
           txtwo.setText(fortxtwo);
        }else {
            if (fontsave.contains("unicode")){
              txone.setText(txoneu);
                txtwo.setText(txtwou);
            }else {
                txone.setText(fortxone);
                txtwo.setText(fortxtwo);
            }
        }

        colorchangetheme();
     //   toolbar.setBackgroundColor(colorchange);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.phzaw:
               phcall("09961990824");
                break;

            case R.id.zxasms:
                if (checkzawsms==true) {
                    linearLayoutzxa.setVisibility(VISIBLE);
                    checkzawsms=false;
                }else {
                    linearLayoutzxa.setVisibility(View.GONE);
                    checkzawsms=true;
                    zawedt.setText("");
                }
                break;

            case R.id.sendzawsms:
                   if (zawedt.getText().toString().matches("")){

                   }else {
                       sendsms("09961990824", zawedt.getText().toString());
                       zawedt.setText("");
                   }
                break;

            case R.id.phk:
               phcall("09421715064");
                break;

            case R.id.ktsms:
                if (checkkosms==true) {
                    linearLayoutkt.setVisibility(VISIBLE);
                    checkkosms=false;
                }else {
                    linearLayoutkt.setVisibility(View.GONE);
                    checkkosms=true;
                    koedt.setText("");
                }
                break;

            case R.id.sendkosms:
                   if (koedt.getText().toString().matches("")){

                   }else {
                       sendsms("09421715064", koedt.getText().toString());
                       koedt.setText("");
                   }
                break;


            case R.id.ktph:
                Intent myfbkt= FbKt(this,"100005526285021");
                if (myfbkt!=null)
                    startActivity(myfbkt);
                break;
            case R.id.zxaph:
                Intent myfbz = Fbz(this,"100013798122575");
                if (myfbz!=null)
                    startActivity(myfbz);
                break;
            case R.id.fbpage:
                Intent fbp = Fbp(this,"6375167992788321221");
                if (fbp!=null)
                    startActivity(fbp);
        }
    }

    private Intent Fbp(AboutFg aboutActivity, String page) {
        try {
            //fbid();
            aboutActivity.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
            String facebookScheme = "fb://page/" + page;
            return new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Intent Fbz(AboutFg aboutActivity, String s) {
        try {
            //fbid();
            aboutActivity.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
            String facebookScheme = "fb://profile/" + s;
            return new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Intent FbKt(AboutFg c, String facebookId) {
        try {
            //fbid();
            c.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
            String facebookScheme = "fb://profile/" + facebookId;
            return new Intent(Intent.ACTION_VIEW, Uri.parse(facebookScheme));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
        themecolorshare=getContext().getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");
        fontsave=themecolorshare.getString("savefont","");
    }

    public void phcall(String ph){
        Intent in=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ph));
        startActivity(in);
    }
    public void sendsms(String ph,String sms){
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(ph,null,sms,null,null);

    }
}
