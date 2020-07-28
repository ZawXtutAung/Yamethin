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
 * Created by zawxtutaung on 1/2/2017.
 */
public class PagodaRecycAdapter extends RecyclerView.Adapter<PagodaRecycAdapter.RViewHolders>{
    private List<ItemObject> itemList;
    private Context pcontext;
    private LayoutInflater inflater;
    private int colorchange;
    OnCustomClick onCustomClick;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String fontsave;
    private String forcontactuni;
    CardView cardView;
    private String forcontactzawgyi;
    public PagodaRecycAdapter(Context context, List<ItemObject> itemList){
        this.itemList = itemList;
        this.pcontext = context;
        inflater=LayoutInflater.from(context);


    }

    @Override
    public PagodaRecycAdapter.RViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.pagoda_card,parent,false);
        PagodaRecycAdapter.RViewHolders rViewHolders=new PagodaRecycAdapter.RViewHolders(view);
        return rViewHolders;
    }

    @Override
    public void onBindViewHolder(PagodaRecycAdapter.RViewHolders holder, int position) {
       // holder.countryName.setText(itemList.get(position).getName());
        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());

        SanZtoU sanZtoU=new SanZtoU(pcontext, (MainActivity) pcontext);
        String forgetcontact=itemList.get(position).getName();
        forcontactuni= (String) sanZtoU.ZawGyiToUni(forgetcontact,true);
        forcontactzawgyi= (String) sanZtoU.ZawGyiToUni(forgetcontact,false);

        colorchangetheme();
        holder.cardView.setCardBackgroundColor(colorchange);

        readSharepre();
        if (fontsave.contains("zawgyi")){
            holder.countryName.setText(forgetcontact);
        }else {
            if (fontsave.contains("unicode")){
                holder.countryName.setText(forcontactuni);
            }else {
                holder.countryName.setText(forgetcontact);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setOnCustomClick(OnCustomClick customClick){
        this.onCustomClick=customClick;
    }
    public class RViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView countryName;
        ImageView countryPhoto;
        private CardView cardView;
        public RViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardView= (CardView) itemView.findViewById(R.id.pcard);
            countryName = (TextView)itemView.findViewById(R.id.pagodaName);
            countryPhoto = (ImageView)itemView.findViewById(R.id.pagodaimage);
        }

        @Override
        public void onClick(View v) {
         //Toast.makeText(v.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
            onCustomClick.onCustomItemClick(v,itemList.get(getPosition()).getName().toString(),itemList.get(getPosition()).getPhoto(),getPosition());
        }

    }
    public interface OnCustomClick{
        public void onCustomItemClick(View v, String textstring,int imagesend,int position);
    }
    public void colorchangetheme(){
        readSharepre();
        if (colorsaveString.contains("bluelow")){
            colorchange=pcontext.getResources().getColor(R.color.bluelow);
        }else {
            if (colorsaveString.contains("blue")){
                colorchange=pcontext.getResources().getColor(R.color.blue);
            }else {
                if (colorsaveString.contains("brown")){
                    colorchange=pcontext.getResources().getColor(R.color.brown);
                }else {
                    if (colorsaveString.contains("high")){
                        colorchange=pcontext.getResources().getColor(R.color.bluehigh);
                    }else {
                        if (colorsaveString.contains("greena")){
                            colorchange=pcontext.getResources().getColor(R.color.greena);
                        }else {
                            if (colorsaveString.contains("orange")){
                                colorchange=pcontext.getResources().getColor(R.color.orange);
                            }else {
                                if (colorsaveString.contains("blacka")){
                                    colorchange=pcontext.getResources().getColor(R.color.blacka);
                                }else {
                                    colorchange=pcontext.getResources().getColor(R.color.bluelow);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    public void readSharepre(){
        themecolorshare=pcontext.getSharedPreferences("savethemecolor",0);
        colorsaveString=themecolorshare.getString("colorsave","");
        fontsave=themecolorshare.getString("savefont","");

    }}
