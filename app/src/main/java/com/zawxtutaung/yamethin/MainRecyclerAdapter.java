package com.zawxtutaung.yamethin;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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
 * Created by Dell on 12/6/2016.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<Contact> contactList;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Contact> filterlist;
    OnCustomClick onCustomClick;
    Contact contact;
    int myposition;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String fontsave;
    private String colorsaveString;
    private Typeface typefacezawgyi,typefaceuni;

    private String forcontactuni;
    private String forcontactzawgyi;
    private Integer[] imgforarray;


    public MainRecyclerAdapter(Context context, List<Contact> contactList,Integer[] imgarray){
        this.contactList=contactList;
        this.mContext = context;
        this.imgforarray=imgarray;
        inflater=LayoutInflater.from(context);

    }
public MainRecyclerAdapter(){

}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        myposition=position;
        SanZtoU sanZtoU=new SanZtoU(mContext, (MainActivity) mContext);
        String forgetcontact=contactList.get(position).getName();
        forcontactuni= (String) sanZtoU.ZawGyiToUni(forgetcontact,true);
        forcontactzawgyi= (String) sanZtoU.ZawGyiToUni(forgetcontact,false);


       // typefacezawgyi=Typeface.createFromAsset(mContext.getAssets(),"fonts/zawgyione.ttf");
      //  typefaceuni=Typeface.createFromAsset(mContext.getAssets(),"fonts/mm3.ttf");




        holder.getidtext.setText(contactList.get(position).getTitle());

            holder.ivMovieIcon.setImageResource(imgforarray[position]);

        colorchangetheme();
        holder.cardView.setCardBackgroundColor(colorchange);


        readSharepre();
        if (fontsave.contains("zawgyi")){
            holder.contact_name.setText(forgetcontact);
        }else {
            if (fontsave.contains("unicode")){
                holder.contact_name.setText(forcontactuni);
//                holder.contact_name.setTypeface(typefaceuni);
            }else {
                 holder.contact_name.setText(forgetcontact);
            }
        }
    }

    public void setOnCustomClick(OnCustomClick customClick){
        this.onCustomClick=customClick;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
public void filter(final  List<Contact> list){
            contactList=list;
            notifyDataSetChanged();
}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView contact_name;
        private TextView getidtext;
        private ImageView ivMovieIcon;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            contact_name = (TextView) itemView.findViewById(R.id.contact_name);
            getidtext= (TextView) itemView.findViewById(R.id.getidtext);
            ivMovieIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            cardView= (CardView) itemView.findViewById(R.id.cardcartegory);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onCustomClick.onCustomItemClick(v,getidtext.getText().toString(),contact_name.getText().toString(),getPosition());
        }
    }

    public interface OnCustomClick{
        public void onCustomItemClick(View v, String textstring, String titleString,int position);
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
