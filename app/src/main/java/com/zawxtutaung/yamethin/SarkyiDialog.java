package com.zawxtutaung.yamethin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import san.zgyi.uni.SanZtoU;

/**
 * Created by Dell on 2/11/2017.
 */

public class SarkyiDialog extends DialogFragment implements View.OnClickListener {
    View v;
    private String bussinesname;
    private String phoneone;
    private String phonetwo;
    private String fstring;
    private String sstring;

    private TextView busname;
    private TextView phone;
    private  TextView phtwo;
    private CheckBox checkBoxone;
    private CheckBox checkBoxtwo;
    private Button callbtn;
    private Button dimissbtn;
    private TextView fname;
    private TextView sname;
    private TextView mytitle;
    private TextView choose;
    private String ludan;
    private String toname;

    private String namezawgyi;
    private String nameuni;

    private String mytitlezaw;
    private String mytitleuni;

    private String choosezaw;
    private String chooseuni;

    private String fstringzaw;
    private String fstringuni;

    private String sstringzaw;
    private String sstringuni;

    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String fontsave;

//    public SarkyiDialog(String bussinesname,String fname,String phoneone,String sname,String phonetwo){
//        this.bussinesname=bussinesname;
//        this.phoneone=phoneone;
//        this.phonetwo=phonetwo;
//        this.fstring=fname;
//        this.sstring=sname;
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.librayr_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_title);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        busname= (TextView) v.findViewById(R.id.bussinesname);
        phone= (TextView) v.findViewById(R.id.phoneone);
        phtwo= (TextView) v.findViewById(R.id.phonetwo);

        fname= (TextView) v.findViewById(R.id.fname);
        sname= (TextView) v.findViewById(R.id.sname);
        mytitle= (TextView) v.findViewById(R.id.mytitle);
        choose= (TextView) v.findViewById(R.id.choose);

        checkBoxone= (CheckBox) v.findViewById(R.id.checkboxone);
        checkBoxtwo= (CheckBox) v.findViewById(R.id.checkboxtwo);
        callbtn= (Button) v.findViewById(R.id.call);
        dimissbtn= (Button) v.findViewById(R.id.dimiss);
        Bundle bundle=new Bundle();
        callbtn.setOnClickListener(this);
        dimissbtn.setOnClickListener(this);
        checkBoxone.setOnClickListener(this);
        checkBoxtwo.setOnClickListener(this);
        checkBoxone.setChecked(true);

        SanZtoU sanZtoU=new SanZtoU(getContext(), (Activity) getContext());

        ludan="ေအာက္ပါပုဂၢိဳလ္မ်ားထံသုိ့  ဆက္သြယ္လွဴဒါန္းႏိုင္ပါသည္။";
        this.bussinesname=getArguments().getString("name");
        this.fstring=getArguments().getString("fname");
        this.phoneone=getArguments().getString("phone");
        this.sstring=getArguments().getString("sname");
        this.phonetwo=getArguments().getString("phtwo");
        this.toname=getArguments().getString("toname");

        nameuni= (String) sanZtoU.ZawGyiToUni(bussinesname,true);
        namezawgyi= (String) sanZtoU.ZawGyiToUni(bussinesname,false);

        mytitleuni= (String) sanZtoU.ZawGyiToUni(toname,true);
        mytitlezaw= (String) sanZtoU.ZawGyiToUni(toname,false);

        chooseuni= (String) sanZtoU.ZawGyiToUni(ludan,true);
        choosezaw= (String) sanZtoU.ZawGyiToUni(ludan,false);

        fstringuni= (String) sanZtoU.ZawGyiToUni(fstring,true);
        fstringzaw= (String) sanZtoU.ZawGyiToUni(fstring,false);

        sstringuni= (String) sanZtoU.ZawGyiToUni(sstring,true);
        sstringzaw= (String) sanZtoU.ZawGyiToUni(sstring,false);

        readSharepre();
        if (fontsave.contains("zawgyi")){
            busname.setText(bussinesname);
            fname.setText(fstring);
            sname.setText(sstring);
            mytitle.setText(toname);
            choose.setText(ludan);
        }else {
            if (fontsave.contains("unicode")){
                busname.setText(nameuni);
                fname.setText(fstringuni);
                sname.setText(sstringuni);
                mytitle.setText(mytitleuni);
                choose.setText(chooseuni);
            }else {
                busname.setText(bussinesname);
                fname.setText(fstring);
                sname.setText(sstring);
                mytitle.setText(toname);
                choose.setText(ludan);
            }
        }


        phone.setText(phoneone);
        phtwo.setText(phonetwo);

    }

    @Override
    public void onClick(View v) {
        btnClick(v);
        checkClick(v);
    }

    public void btnClick(View v){
        switch (v.getId()){
            case R.id.call:
                checkStatecheckBox();
                getDialog().dismiss();
                break;
            case R.id.dimiss:
                getDialog().dismiss();
                break;
        }
    }
    public void checkClick(View v){
        switch (v.getId()){
            case R.id.checkboxone:
                checkBoxone.setChecked(true);
                checkBoxtwo.setChecked(false);
                break;
            case R.id.checkboxtwo:
                checkBoxone.setChecked(false);
                checkBoxtwo.setChecked(true);
                break;
        }
    }

    public void checkStatecheckBox(){
        if (checkBoxone.isChecked()){
            Intent intent=new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phone.getText().toString()));
            startActivity(intent);
        }else {
            if (checkBoxtwo.isChecked()){
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phtwo.getText().toString()));
                startActivity(intent);
            }
        }
    }

    public void readSharepre(){
        themecolorshare=getContext().getSharedPreferences("savethemecolor",0);
        fontsave=themecolorshare.getString("savefont","");
    }
}
