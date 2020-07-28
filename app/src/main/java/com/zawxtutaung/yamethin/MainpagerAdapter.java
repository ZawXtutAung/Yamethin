package com.zawxtutaung.yamethin;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Dell on 12/7/2016.
 */

public class MainpagerAdapter extends FragmentStatePagerAdapter {
    public static int int_items = 3 ;
    private Context context;

    public MainpagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new Second();
            case 1 :
                return new Home();
            case 2:
                return new Pagoda();
                        //Pagoda();


        }

        return   new Second();
    }

    @Override
    public int getCount() {
        return int_items;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Home";
            case 1 :
                return "Emergency";
            case 2:
                return "Pagoda";

        }
        return null;
    }

}
