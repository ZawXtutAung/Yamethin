package com.zawxtutaung.yamethin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Dell on 12/7/2016.
 */

public class MainpagerView extends Fragment {
    ViewPager viewPager;
    Toolbar toolbar;
    TextView toolTitle;
    private View v;
    private TabLayout tabLayout;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    ViewFlipper viewFlipper;

    private StorageReference storageReferencefirst,storageReferencefirstaa;

    private ImageView firstimg,secondimg,thirdimg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=LayoutInflater.from(getActivity()).inflate(R.layout.pager_view,container,false);
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout= (TabLayout) v.findViewById(R.id.tlly);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("New"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager= (ViewPager) v.findViewById(R.id.pager);
        viewFlipper = (ViewFlipper)v. findViewById(R.id.view_flipper);
        firstimg= (ImageView) v.findViewById(R.id.firstimg);
        secondimg= (ImageView) v.findViewById(R.id.secondimg);
        thirdimg= (ImageView) v.findViewById(R.id.thirdimg);

        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();

        final MainpagerAdapter myPagerAdapter=new MainpagerAdapter(getChildFragmentManager(),getActivity().getApplicationContext());

        viewPager.setAdapter(myPagerAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        colorchangetheme();
        tabLayout.setBackgroundColor(colorchange);
  showImage();
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

    private void  showImage(){
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();

         storageReferencefirst = firebaseStorage.getReferenceFromUrl("gs://yamethin-6f538.appspot.com").child("directoryadsfirst.jpg");
        StorageReference storageReferencesecond=firebaseStorage.getReferenceFromUrl("gs://yamethin-6f538.appspot.com").child("directoryadssecond.jpg");
        StorageReference storageReferencethird=firebaseStorage.getReferenceFromUrl("gs://yamethin-6f538.appspot.com").child("directoryadsthird.jpg");



        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(storageReferencefirst)
                .into(firstimg);

        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(storageReferencesecond)
                .into(secondimg);

        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(storageReferencethird)
                .into(thirdimg);
    }

    public void readSharepre(){
        themecolorshare=getContext().getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");

    }


}
