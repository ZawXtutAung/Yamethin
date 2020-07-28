package com.zawxtutaung.yamethin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Dell on 1/24/2017.
 */

public class SettingFg extends Fragment implements View.OnClickListener {
    private View v;
    private CheckBox zawgyichange,unicodechange,changebluelow,changeblue,changebrown,changebluehigh,changegreena,changeorange,changeblacka;
    private SharedPreferences themecolorshare;
    private SharedPreferences savefrag,openbtn;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    String colorsaveString;
    String fontsave;
    String openbtnread;
    private int colorchange;
    private TextView title;
    private Button applybtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.setting_fg, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zawgyichange= (CheckBox) v.findViewById(R.id.zawgyichange);
        unicodechange= (CheckBox) v. findViewById(R.id.unichange);

        changebluelow= (CheckBox) v.findViewById(R.id.changebluelow);
        changeblue= (CheckBox) v.findViewById(R.id.changeblue);
        changebrown= (CheckBox) v.findViewById(R.id.changebrown);
        changebluehigh= (CheckBox) v.findViewById(R.id.changebluehigh);
        changegreena = (CheckBox) v.findViewById(R.id.changegreena);
        changeorange= (CheckBox)  v.findViewById(R.id.changeorange);
        changeblacka= (CheckBox) v.findViewById(R.id.changeblacka);

        applybtn= (Button) v.findViewById(R.id.apply);

        checkthemecolor();
        colorchangetheme();
        changefont();
    }
    public void  checkthemecolor(){
        zawgyichange.setOnClickListener(this);
        unicodechange.setOnClickListener(this);

        changebluelow.setOnClickListener(this);
        changeblue.setOnClickListener(this);
        changebrown.setOnClickListener(this);
        changebluehigh.setOnClickListener(this);
        changegreena.setOnClickListener(this);
        changeorange.setOnClickListener(this);
        changeblacka.setOnClickListener(this);

        applybtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clicktheme(v);
        clickfont(v);
        clickapply(v);
    }
    public void clickapply(View v){
        switch (v.getId()){
            case R.id.apply:
                readopenbtn();
                if (openbtnread.contains("openbtn")){
                    applyfont();
                    applytheme();
                    fragmentsave();
                    refleshmain();
                    clearbopenbtn();
                }else {

                }

                break;
        }

    }
public void applyfont(){
    if (zawgyichange.isChecked()){
        fontsave("zawgyi");

    }else {
        if (unicodechange.isChecked()){
            fontsave("unicode");
        }
    }
}

    public void applytheme(){
        if (changebluelow.isChecked()){
            themesavecolor("bluelow");
        }else {
            if (changeblue.isChecked()){
                themesavecolor("blue");
            }else {
                if (changebrown.isChecked()){
                    themesavecolor("brown");
                }else {
                    if (changebluehigh.isChecked()){
                        themesavecolor("high");
                    }else {
                        if (changegreena.isChecked()){
                            themesavecolor("greena");
                        }else {
                            if (changeorange.isChecked()){
                                themesavecolor("orange");
                            }else {
                                if (changeblacka.isChecked()){
                                    themesavecolor("blacka");
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void clickfont(View v){
        switch (v.getId()){
            case R.id.zawgyichange:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                zawgyichange.setChecked(true);
                unicodechange.setChecked(false);

                break;
            case R.id.unichange:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                zawgyichange.setChecked(false);
                unicodechange.setChecked(true);

                break;
        }
    }
    public void clicktheme(View v){
        switch (v.getId()){
            case R.id.changebluelow:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(true);
                changeblue.setChecked(false);
                changebrown.setChecked(false);
                changebluehigh.setChecked(false);
                changegreena.setChecked(false);
                changeorange.setChecked(false);
                changeblacka.setChecked(false);
                break;
            case R.id.changeblue:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(false);
                changeblue.setChecked(true);
                changebrown.setChecked(false);
                changebluehigh.setChecked(false);
                changegreena.setChecked(false);
                changeorange.setChecked(false);
                changeblacka.setChecked(false);
                break;
            case R.id.changebrown:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(false);
                changeblue.setChecked(false);
                changebrown.setChecked(true);
                changebluehigh.setChecked(false);
                changegreena.setChecked(false);
                changeorange.setChecked(false);
                changeblacka.setChecked(false);
                break;
            case R.id.changebluehigh:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(false);
                changeblue.setChecked(false);
                changebrown.setChecked(false);
                changebluehigh.setChecked(true);
                changegreena.setChecked(false);
                changeorange.setChecked(false);
                changeblacka.setChecked(false);
                break;
            case R.id.changegreena:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(false);
                changeblue.setChecked(false);
                changebrown.setChecked(false);
                changebluehigh.setChecked(false);
                changegreena.setChecked(true);
                changeorange.setChecked(false);
                changeblacka.setChecked(false);
                break;
            case R.id.changeorange:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(false);
                changeblue.setChecked(false);
                changebrown.setChecked(false);
                changebluehigh.setChecked(false);
                changegreena.setChecked(false);
                changeorange.setChecked(true);
                changeblacka.setChecked(false);
                break;
            case R.id.changeblacka:
                applybtn.setTextColor(getResources().getColor(R.color.white));
                openbtnapply();
                changebluelow.setChecked(false);
                changeblue.setChecked(false);
                changebrown.setChecked(false);
                changebluehigh.setChecked(false);
                changegreena.setChecked(false);
                changeorange.setChecked(false);
                changeblacka.setChecked(true);
                break;
        }
    }
    public void fontsave(String savefont){
        themecolorshare=getContext().getSharedPreferences("savethemecolor",0);
        editor=themecolorshare.edit();
        editor.putString("savefont",savefont);
        editor.commit();
    }
    public void themesavecolor(String savecolor){
        themecolorshare=getContext().getSharedPreferences("savethemecolor",0);
        editor=themecolorshare.edit();
        editor.putString("colorsave",savecolor);
        editor.commit();
    }
    public void fragmentsave(){
        savefrag=getContext().getSharedPreferences("savefrag",0);
        editor=savefrag.edit();
        editor.putString("savefragmentsetting","savefragmentsetting");
        editor.commit();
    }
    public void  openbtnapply(){
        openbtn=getContext().getSharedPreferences("openbtn",0);
        editor=openbtn.edit();
        editor.putString("openbtn","openbtn");
        editor.commit();
    }
    public void clearbopenbtn(){
        openbtn=getContext().getSharedPreferences("openbtn",0);
        openbtn.edit().clear().commit();
    }

    public void readSharepre(){
        themecolorshare=getContext().getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");
        fontsave=themecolorshare.getString("savefont","");
    }
    public void readopenbtn(){
        openbtn=getContext().getSharedPreferences("openbtn",0);
        openbtnread=openbtn.getString("openbtn","");
    }

    public void changefont(){
        readSharepre();
        if (fontsave.contains("zaegyi")){
            zawgyichange.setChecked(true);
            unicodechange.setChecked(false);
        }else {
            if (fontsave.contains("unicode")){
                zawgyichange.setChecked(false);
                unicodechange.setChecked(true);
            }else {
                zawgyichange.setChecked(true);
                unicodechange.setChecked(false);
            }
        }
    }
    public void colorchangetheme(){
        readSharepre();
        if (colorsaveString.contains("bluelow")){
            colorchange=getResources().getColor(R.color.bluelow);
            changebluelow.setChecked(true);
            changeblue.setChecked(false);
            changebrown.setChecked(false);
            changebluehigh.setChecked(false);
            changegreena.setChecked(false);
            changeorange.setChecked(false);
            changeblacka.setChecked(false);
        }else {
            if (colorsaveString.contains("blue")){
                colorchange=getResources().getColor(R.color.blue);
                changebluelow.setChecked(false);
                changeblue.setChecked(true);
                changebrown.setChecked(false);
                changebluehigh.setChecked(false);
                changegreena.setChecked(false);
                changeorange.setChecked(false);
                changeblacka.setChecked(false);
            }else {
                if (colorsaveString.contains("brown")){
                    colorchange=getResources().getColor(R.color.brown);
                    changebluelow.setChecked(false);
                    changeblue.setChecked(false);
                    changebrown.setChecked(true);
                    changebluehigh.setChecked(false);
                    changegreena.setChecked(false);
                    changeorange.setChecked(false);
                    changeblacka.setChecked(false);
                }else {
                    if (colorsaveString.contains("high")){
                        colorchange=getResources().getColor(R.color.bluehigh);
                        changebluelow.setChecked(false);
                        changeblue.setChecked(false);
                        changebrown.setChecked(false);
                        changebluehigh.setChecked(true);
                        changegreena.setChecked(false);
                        changeorange.setChecked(false);
                        changeblacka.setChecked(false);
                    }else {
                        if (colorsaveString.contains("greena")){
                            colorchange=getResources().getColor(R.color.greena);
                            changebluelow.setChecked(false);
                            changeblue.setChecked(false);
                            changebrown.setChecked(false);
                            changebluehigh.setChecked(false);
                            changegreena.setChecked(true);
                            changeorange.setChecked(false);
                            changeblacka.setChecked(false);
                        }else {
                            if (colorsaveString.contains("orange")){
                                colorchange=getResources().getColor(R.color.orange);
                                changebluelow.setChecked(false);
                                changeblue.setChecked(false);
                                changebrown.setChecked(false);
                                changebluehigh.setChecked(false);
                                changegreena.setChecked(false);
                                changeorange.setChecked(true);
                                changeblacka.setChecked(false);
                            }else {
                                if (colorsaveString.contains("blacka")){
                                    colorchange=getResources().getColor(R.color.blacka);
                                    changebluelow.setChecked(false);
                                    changeblue.setChecked(false);
                                    changebrown.setChecked(false);
                                    changebluehigh.setChecked(false);
                                    changegreena.setChecked(false);
                                    changeorange.setChecked(false);
                                    changeblacka.setChecked(true);
                                }else {
                                    colorchange=getResources().getColor(R.color.bluelow);
                                    changebluelow.setChecked(true);
                                    changeblue.setChecked(false);
                                    changebrown.setChecked(false);
                                    changebluehigh.setChecked(false);
                                    changegreena.setChecked(false);
                                    changeorange.setChecked(false);
                                    changeblacka.setChecked(false);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void refleshmain(){
        Intent i=new Intent(getContext(),MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().finish();
        startActivity(i);


    }
}
