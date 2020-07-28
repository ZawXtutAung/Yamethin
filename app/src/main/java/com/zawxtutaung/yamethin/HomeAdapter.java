package com.zawxtutaung.yamethin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import san.zgyi.uni.SanZtoU;

/**
 * Created by Dell on 11/27/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Contact> contactList;
    private Context mContext;
    private LayoutInflater inflater;
    OnCustomClick onCustomClick;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String fontsave;
    private String forcontactuni;
    private String forcontactzawgyi;



    public HomeAdapter(Context context, List<Contact> contactList){
        this.contactList=contactList;
        this.mContext = context;
        inflater=LayoutInflater.from(context);


    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.main_cardview,parent,false);
        HomeAdapter.ViewHolder viewHolder=new HomeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        SanZtoU sanZtoU=new SanZtoU(mContext, (MainActivity) mContext);
        String forgetcontact=contactList.get(position).getName();
        forcontactuni= (String) sanZtoU.ZawGyiToUni(forgetcontact,true);
        forcontactzawgyi= (String) sanZtoU.ZawGyiToUni(forgetcontact,false);

      //  holder.contact_name.setText(contactList.get(position).getName());
        holder.ivMovieIcon.setImageResource(contactList.get(position).getIntimage());
        colorchangetheme();
        holder.cardView.setCardBackgroundColor(colorchange);

        readSharepre();
        if (fontsave.contains("zawgyi")){
            holder.contact_name.setText(forgetcontact);
        }else {
            if (fontsave.contains("unicode")){
                holder.contact_name.setText(forcontactuni);
            }else {
                holder.contact_name.setText(forgetcontact);
            }
        }

    }

    public void setOnCustomClick(HomeAdapter.OnCustomClick customClick){
        this.onCustomClick=customClick;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView contact_name;
        private ImageView ivMovieIcon;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            contact_name = (TextView) itemView.findViewById(R.id.contact_name);
            ivMovieIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            cardView= (CardView) itemView.findViewById(R.id.homecard);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCustomClick.onCustomItemClick(v,getPosition(),contact_name.getText().toString(),contactList.get(getPosition()).getIntimage());
        }
    }

    public interface OnCustomClick{
        public void onCustomItemClick(View v, int position,String title,int imgsend);
    }
    public void colorchangetheme(){
        readSharepre();
        if (colorsaveString.contains("bluelow")){
            colorchange=mContext.getResources().getColor(R.color.bluelow);
        }else {
            if (colorsaveString.contains("blue")){
                colorchange=mContext.getResources().getColor(R.color.blue);
            }else {
                if (colorsaveString.contains("brown")){
                    colorchange=mContext.getResources().getColor(R.color.brown);
                }else {
                    if (colorsaveString.contains("high")){
                        colorchange=mContext.getResources().getColor(R.color.bluehigh);
                    }else {
                        if (colorsaveString.contains("greena")){
                            colorchange=mContext.getResources().getColor(R.color.greena);
                        }else {
                            if (colorsaveString.contains("orange")){
                                colorchange=mContext.getResources().getColor(R.color.orange);
                            }else {
                                if (colorsaveString.contains("blacka")){
                                    colorchange=mContext.getResources().getColor(R.color.blacka);
                                }else {
                                    colorchange=mContext.getResources().getColor(R.color.bluelow);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    public void readSharepre(){
        themecolorshare=mContext.getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");
        fontsave=themecolorshare.getString("savefont","");

    }
}
