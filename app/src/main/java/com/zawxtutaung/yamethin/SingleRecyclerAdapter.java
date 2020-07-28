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
 * Created by Dell on 12/10/2016.
 */

public class SingleRecyclerAdapter extends RecyclerView.Adapter<SingleRecyclerAdapter.ViewHolder> {

    private List<Contact> contactList;
    private Context mContext;
    private LayoutInflater inflater;
    SingleOnCustomClick onCustomClick;
    private int colorchange;
    private SharedPreferences themecolorshare;
    private SharedPreferences.Editor editor;
    private String colorsaveString;
    private String fontsave;
    private String forcontactuni;
    private String forcontactzawgyi;
    private String foradressuni;
    private String foradresszawgyi;
    private SanZtoU sanZtoU;
    private int sendimg;


    public SingleRecyclerAdapter(Context context, List<Contact> contactList,SanZtoU sanZtoU,int sendimg){
        this.contactList=contactList;
        this.mContext = context;
        this.sanZtoU=sanZtoU;
        this.sendimg=sendimg;
        inflater=LayoutInflater.from(context);


    }

    @Override
    public SingleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.single_cardview,parent,false);
        SingleRecyclerAdapter.ViewHolder viewHolder=new SingleRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SingleRecyclerAdapter.ViewHolder holder, int position) {

        //SanZtoU sanZtoU=new SanZtoU(mContext, (Activity) mContext);
        String forgetcontact=contactList.get(position).getName();
        String forgetadress=contactList.get(position).getAdress();
        forcontactuni= (String) sanZtoU.ZawGyiToUni(forgetcontact,true);
        forcontactzawgyi= (String) sanZtoU.ZawGyiToUni(forgetcontact,false);
        foradressuni= (String) sanZtoU.ZawGyiToUni(forgetadress,true);
        foradresszawgyi= (String) sanZtoU.ZawGyiToUni(forgetadress,false);

      //holder.singlename.setText(contactList.get(position).getName());
        holder.singlephoneone.setText(contactList.get(position).getPhone());
        holder.singlephonetwo.setText(contactList.get(position).getPhonetwo());
     //   holder.singleadress.setText(contactList.get(position).getAdress());
        holder.ivMovieIcon.setImageResource(sendimg);
        colorchangetheme();
        holder.cardView.setCardBackgroundColor(colorchange);

        readSharepre();
        if (fontsave.contains("zawgyi")){
            holder.singlename.setText(forgetcontact);
            holder.singleadress.setText(forgetadress);
        }else {
            if (fontsave.contains("unicode")){
                holder.singlename.setText(forcontactuni);
                holder.singleadress.setText(foradressuni);

            }else {
                holder.singlename.setText(forgetcontact);
                holder.singleadress.setText(forgetadress);
            }
        }
    }

    public void setSingleOnCustomClick(SingleOnCustomClick customClick){
        this.onCustomClick=customClick;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView singlename;
        private TextView singlephoneone,singlephonetwo;
        private TextView singleadress;
        private ImageView ivMovieIcon;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            singlename = (TextView) itemView.findViewById(R.id.singlename);
            singlephoneone = (TextView) itemView.findViewById(R.id.fistphone);
            singlephonetwo= (TextView) itemView.findViewById(R.id.secondphone);
            singleadress = (TextView) itemView.findViewById(R.id.singleadress);
            ivMovieIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            cardView= (CardView) itemView.findViewById(R.id.singlecard);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCustomClick.singleonCustomItemClick(v,singlename.getText().toString(),singlephoneone.getText().toString(),singlephonetwo.getText().toString());
        }
    }

    public interface SingleOnCustomClick{
        public void singleonCustomItemClick(View v,String bussinesname,String phoneone,String phonetwo);
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
