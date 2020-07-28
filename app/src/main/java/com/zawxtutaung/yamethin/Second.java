package com.zawxtutaung.yamethin;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment{
View v;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    DbBackend mydatabaseHelper;
    Contact contact;
    List<Contact> contactList;
    MainRecyclerAdapter animalsAdapter;
    String findviewtext;

    private Integer[]  imgforarray={R.drawable.car,R.drawable.carservic,R.drawable.carservic,R.drawable.schoo,R.drawable.shops,R.drawable.oo,R.drawable.worka,R.drawable.shops,R.drawable.shops,R.drawable.shops,
            R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.acyc,R.drawable.cycb,R.drawable.cycb,R.drawable.buty,R.drawable.carservic,R.drawable.videocamera,R.drawable.shops,
            R.drawable.phshop,R.drawable.offiic,R.drawable.shops,R.drawable.hoa,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.shops,
            R.drawable.buty,R.drawable.shops,R.drawable.shops,R.drawable.shops,R.drawable.hoa,R.drawable.shops,R.drawable.shops

    };
    public Second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.second, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
     mydatabaseHelper=new DbBackend(this.getActivity());


        contactList=mydatabaseHelper.getListcartegory("SELECT * FROM category");
        mLayoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        animalsAdapter = new MainRecyclerAdapter(getContext(),contactList,imgforarray);
        mRecyclerView.setAdapter(animalsAdapter);
        animalsAdapter.setOnCustomClick(new MainClick());

      //  Toast.makeText(getContext(),String.valueOf(imgforarray.length),Toast.LENGTH_LONG).show();

    }


    private class MainClick implements MainRecyclerAdapter.OnCustomClick {

        @Override
        public void onCustomItemClick(View v, String textstring, String titleString,int position) {
            switch (position){
                case 0:
                    gointent(titleString,imgforarray[position],"SELECT * FROM cargate");
                    break;
                case 1:
                    gointent(titleString,imgforarray[position],"SELECT * FROM carstore");
                    break;
                case 2:
                    gointent(titleString,imgforarray[position],"SELECT * FROM carworkshop");
                    break;
                case 3:
                    gointent(titleString,imgforarray[position],"SELECT * FROM bawdar");
                    break;
                case 4:
                    gointent(titleString,imgforarray[position],"SELECT * FROM konmarsai");
                    break;
                case 5:
                    gointent(titleString,imgforarray[position],"SELECT * FROM seepan");
                    break;
                case 6:
                    gointent(titleString,imgforarray[position],"SELECT * FROM satee");
                    break;
                case 7:
                    gointent(titleString,imgforarray[position],"SELECT * FROM store");
                    break;
                case 8:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saroatsai");
                    break;
                case 9:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saryaykayiyar");
                    break;
                case 10:
                    gointent(titleString,imgforarray[position],"SELECT * FROM sarthauksai");
                    break;
                case 11:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saitpyoyaysay");
                    break;
                case 12:
                    gointent(titleString,imgforarray[position],"SELECT * FROM sansai");
                    break;
                case 13:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saikaiayawysai");
                    break;
                case 14:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saikaiaposai");
                    break;
                case 15:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saikaipyinsai");
                    break;
                case 16:
                    gointent(titleString,imgforarray[position],"SELECT * FROM sanpinhnyatsai");
                    break;
                case 17:
                    gointent(titleString,imgforarray[position],"SELECT * FROM twinkone");
                    break;
                case 18:
                    gointent(titleString,imgforarray[position],"SELECT * FROM datpontvideo");
                    break;
                case 19:
                    gointent(titleString,imgforarray[position],"SELECT * FROM pohtaisai");
                    break;
                case 20:
                    gointent(titleString,imgforarray[position],"SELECT * FROM phoneyaungwaisai");
                    break;
                case 21:
                    gointent(titleString,imgforarray[position],"SELECT * FROM bank");
                    break;
                case 22:
                    gointent(titleString,imgforarray[position],"SELECT * FROM matetusai");
                    break;
                case 23:
                    gointent(titleString,imgforarray[position],"SELECT * FROM talkhokan");
                    break;
                case 24:
                    gointent(titleString,imgforarray[position],"SELECT * FROM mounkphatsat");
                    break;
                case 25:
                    gointent(titleString,imgforarray[position],"SELECT * FROM hlyatsispyitsesai");
                    break;
                case 26:
                    gointent(titleString,imgforarray[position],"SELECT * FROM hlyatsispyintsai");
                    break;
                case 27:
                    gointent(titleString,imgforarray[position],"SELECT * FROM thinkansai");
                    break;
                case 28:
                    gointent(titleString,imgforarray[position],"SELECT * FROM ahlapyinsai");
                    break;
                case 29:
                    gointent(titleString,imgforarray[position],"SELECT * FROM aeinsaukpyitsesai");
                    break;
                case 30:
                    gointent(titleString,imgforarray[position],"SELECT * FROM aphyawyamakarsai");
                    break;
                case 31:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saysai");
                    break;
                case 32:
                    gointent(titleString,imgforarray[position],"SELECT * FROM saykan");
                    break;
                case 33:
                    gointent(titleString,imgforarray[position],"SELECT * FROM shwesai");
                    break;
                case 34:
                    gointent(titleString,imgforarray[position],"SELECT * FROM thaukyaythank");
                    break;










            }
//if (position==position) {
//    Intent intent = new Intent(getActivity(), ShowsingleView.class);
//    intent.putExtra("title", titleString);
//    intent.putExtra("sendimg",imgforarray[position]);
//    startActivity(intent);
//}
        }
    }
    public void gointent(String title,int sendimg,String tablename){
        Intent intent = new Intent(getActivity(), ShowsingleView.class);
        intent.putExtra("title", title);
        intent.putExtra("sendimg",sendimg);
        intent.putExtra("tablename",tablename);
        startActivity(intent);
    }
    public void showtoast(){
        Toast.makeText(getContext(),"No Data This Title",Toast.LENGTH_SHORT).show();
    }
}
