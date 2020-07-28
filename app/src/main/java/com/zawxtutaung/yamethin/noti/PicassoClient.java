package com.zawxtutaung.yamethin.noti;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zawxtutaung.yamethin.R;

/**
 * Created by zawxtutaung on 1/12/2017.
 */
public class PicassoClient {

    public static void downloadImage(Context c, String url, ImageView img)
    {

//
//        Picasso picasso=new Picasso.Builder(c.getApplicationContext()).listener(new Picasso.Listener(){
//
//        });




//        if(url != null && url.length()>0)
//        {

            Picasso
                    .with(c)
                    .load(url)
                   .placeholder(R.drawable.newp)
                    .into(img);
//        }else {
//            Picasso.with(c)
//                    .load(url)
//                    .into(img);
//        }
    }
}
