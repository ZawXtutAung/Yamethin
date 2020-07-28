package com.zawxtutaung.yamethin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zawxtutaung on 1/2/2017.
 */
public class Pagoda extends Fragment {
    View pv;
    RecyclerView pRecyclerView;
    private GridLayoutManager glManager;
    LinearLayoutManager llyManager;
    private Context mContext;

    private String[] pname={"ရမည္းသင္းသမိုင္း",
            "မင္းနန္သူဘုရား",
            "မင္းသမီးဘုရား",
            "ရန္ေအာင္ျမင္ဘုရား",
            "ရွင္မာလဲဘုရား",
            "အင္ပင္ေရႊကူဘုရား",
            "ေရႊစည္းခံုဘုရား",
            "ေရႊမင္းဝန္ဘုရား",
            "ေရႊမုေ႒ာဘုရား",
            "ေရႊျပည္သာဘုရား",
            "ေရႊျမင္တင္ဘုရား",
            "ေလးမ်က္ႏွာဘုရား",
            "ျမိဳ့တြင္းေရႊကူဘုရား"};

    private int[] imgsend={R.drawable.ymtmain,R.drawable.minnanthu,R.drawable.minthamee,R.drawable.yanaung,R.drawable.shinmarlel,
                          R.drawable.inpin,R.drawable.shweseegon,R.drawable.shweminwon,R.drawable.shwemotal,R.drawable.shwepyithar,
                          R.drawable.shwemyintin,R.drawable.laymyatnar,R.drawable.myottwinshwegu};

PagodaRecycAdapter pagodaadapter;
    public Pagoda() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pv = inflater.inflate(R.layout.pagoda_layout, container, false);

        return pv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pRecyclerView = (RecyclerView) pv.findViewById(R.id.precycler);
        llyManager = new LinearLayoutManager(mContext);
        pRecyclerView.setLayoutManager(llyManager);
        List<ItemObject> rowListItem = getAllItemList();
        PagodaRecycAdapter pagodaadapter=new PagodaRecycAdapter(getContext(),rowListItem);
        pRecyclerView.setAdapter(pagodaadapter);
pagodaadapter.setOnCustomClick(new PagodaView());

    }

    public List<ItemObject> getAllItemList() {
        List<ItemObject> allItems = new ArrayList<ItemObject>();

        allItems.add(new ItemObject(pname[0], R.drawable.yamethinimg));
        allItems.add(new ItemObject(pname[1], R.drawable.minnanthu));
        allItems.add(new ItemObject(pname[2], R.drawable.minthamee));
        allItems.add(new ItemObject(pname[3], R.drawable.yanaung));
        allItems.add(new ItemObject(pname[4], R.drawable.shinmarlel));
        allItems.add(new ItemObject(pname[5], R.drawable.inpin));
        allItems.add(new ItemObject(pname[6], R.drawable.shweseegon));
        allItems.add(new ItemObject(pname[7], R.drawable.shweminwon));
        allItems.add(new ItemObject(pname[8], R.drawable.shwemotal));
        allItems.add(new ItemObject(pname[9], R.drawable.shwepyithar));
        allItems.add(new ItemObject(pname[10], R.drawable.shwemyintin));
        allItems.add(new ItemObject(pname[11], R.drawable.laymyatnar));
        allItems.add(new ItemObject(pname[12], R.drawable.myottwinshwegu));

        return allItems;
    }


    private class PagodaView implements PagodaRecycAdapter.OnCustomClick {
        @Override
        public void onCustomItemClick(View v, String textstring, int imagesend,int position) {
               switch (position){
                   case 0:
                       clickposition("yamethinhistory.txt",textstring,imagesend);
                       break;
                   case 1:
                       clickposition("minnanthu.txt",textstring,imagesend);
                       break;
                   case 2:
                       clickposition("minthame.txt",textstring,imagesend);
                       break;
                   case 3:
                       clickposition("yanaungmyin.txt",textstring,imagesend);
                       break;
                   case 4:
                       clickposition("chinmarlel.txt",textstring,imagesend);
                       break;
                   case 5:
                       clickposition("innpin.txt",textstring,imagesend);
                       break;
                   case 6:
                       clickposition("shweseegon.txt",textstring,imagesend);
                       break;
                   case 7:
                       clickposition("shweminwon.txt",textstring,imagesend);
                       break;
                   case 8:
                       clickposition("shwemawtal.txt",textstring,imagesend);
                       break;
                   case 9:
                       clickposition("shwepyithar.txt",textstring,imagesend);
                       break;
                   case 10:
                       clickposition("shwemyinttin.txt",textstring,imagesend);
                       break;
                   case 11:
                       clickposition("laymyatnar.txt",textstring,imagesend);
                       break;
                   case 12:
                       clickposition("mywottwinshweku.txt",textstring,imagesend);
                       break;

               }
        }
    }
    public void clickposition(String filename,String textstring, int imagesend){
        Intent intent=new Intent(getActivity(),PagodaDataView.class);
        intent.putExtra("title",textstring);
        intent.putExtra("imageget",imagesend);
        intent.putExtra("body",filename);
        startActivity(intent);
    }
}