package com.zawxtutaung.yamethin.noti;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zawxtutaung.yamethin.R;

import java.util.ArrayList;

import san.zgyi.uni.SanZtoU;

/**
 * Created by zawxtutaung on 1/12/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>  {
    Context c;
    private String fontsave;
   private OnCustomClick onCustomClick;
    ArrayList<Movier> movies;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String forcontactuni;
    private String forcontactzawgyi;

    private String titleuni;
    private String titlezaegyi;

    public MyAdapter(Context c, ArrayList<Movier> movies) {
        this.c = c;
        this.movies = movies;
    }

    @Override
    public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_card,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyHolder holder, int position) {


        SanZtoU sanZtoU=new SanZtoU(c, (Activity) c);

        String titlestring=movies.get(position).getName().trim();
        titleuni= (String) sanZtoU.ZawGyiToUni(titlestring,true);
        titlezaegyi= (String) sanZtoU.ZawGyiToUni(titlestring,false);




        holder.datenew.setText(movies.get(position).getDate());

        readSharepre();
        if (fontsave.contains("zawgyi")){

            holder.titlenew.setText(titlestring);
        }else {
            if (fontsave.contains("unicode")){

                holder.titlenew.setText(titleuni);
            }else {
                holder.titlenew.setText(titlestring);
            }
        }


        colorchangetheme();
      //  PicassoClient.downloadImage(c,movies.get(position).getUrl(),holder.img);
        holder.cardView.setCardBackgroundColor(colorchange);
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnCustomClick(OnCustomClick customClicks) {
        this.onCustomClick = customClicks;
    }

    public interface OnCustomClick {
        public void onCustomItemClick(View v, int position,String date,String title,String body,String url);
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView datenew,titlenew;
        ImageView img;
        CardView cardView;
        public MyHolder(final View itemView) {
            super(itemView);

            datenew= (TextView) itemView.findViewById(R.id.datenew);
            titlenew= (TextView) itemView.findViewById(R.id.titlenew);
           // img= (ImageView) itemView.findViewById(R.id.movieImage);
            itemView.setOnClickListener(this);
           cardView=(CardView)itemView.findViewById(R.id.cv);
           // cardView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onCustomClick.onCustomItemClick(v,getPosition(),movies.get(getPosition()).getDate(),movies.get(getPosition()).getName(),movies.get(getPosition()).getBody(),movies.get(getPosition()).getUrl());
        }
    }
    public void colorchangetheme(){
        readSharepre();
        if (colorsaveString.contains("bluelow")){
            colorchange=c.getResources().getColor(R.color.bluelow);
        }else {
            if (colorsaveString.contains("blue")){
                colorchange=c.getResources().getColor(R.color.blue);
            }else {
                if (colorsaveString.contains("brown")){
                    colorchange=c.getResources().getColor(R.color.brown);
                }else {
                    if (colorsaveString.contains("high")){
                        colorchange=c.getResources().getColor(R.color.bluehigh);
                    }else {
                        if (colorsaveString.contains("greena")){
                            colorchange=c.getResources().getColor(R.color.greena);
                        }else {
                            if (colorsaveString.contains("orange")){
                                colorchange=c.getResources().getColor(R.color.orange);
                            }else {
                                if (colorsaveString.contains("blacka")){
                                    colorchange=c.getResources().getColor(R.color.blacka);
                                }else {
                                    colorchange=c.getResources().getColor(R.color.bluelow);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    public void readSharepre(){
        themecolorshare=c.getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");
        fontsave=themecolorshare.getString("savefont","");

    }
}
