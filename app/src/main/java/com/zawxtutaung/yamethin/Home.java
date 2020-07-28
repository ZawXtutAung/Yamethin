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
import android.widget.RelativeLayout;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    View v;
    private Context mContext;
    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;
    DbBackend mydatabaseHelper;
    Contact contact;
    List<Contact> contactList;
    HomeAdapter animalsAdapter;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.home, container, false);


        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        mydatabaseHelper=new DbBackend(this.getActivity());

        contactList=mydatabaseHelper.getListemergency("SELECT * FROM emergency");
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        animalsAdapter = new HomeAdapter(getContext(),contactList);
        mRecyclerView.setAdapter(animalsAdapter);
        animalsAdapter.setOnCustomClick(new Emergncy());

    }

    private class Emergncy implements HomeAdapter.OnCustomClick {

        private String[] phone={"06440029","06440003","06440469","06440009","06440016"};
        @Override
        public void onCustomItemClick(View v, int position,String titleString,int imgsent) {
          switch (position){
              case 0:
                   showdialog(titleString,phone[0],"");
                  break;
              case 1:
                  gointent(titleString,imgsent,"SELECT * FROM yesakan");
                  break;
              case 2:
                  gointent(titleString,imgsent,"SELECT * FROM lumukunyiyay");
                  break;
              case 3:
                  showdialog(titleString,phone[2],"");
                  break;
              case 4:
                  gointent(titleString,imgsent,"SELECT * FROM thwayhlushin");
                  break;
              case 5:
                  showdialog(titleString,phone[3],"");
                  break;
              case 6:
                  showdialog(titleString,phone[4],"");
                  break;
              case 7:
                  showsarkyitite("လူမႈထူးခၽြန္စာၾကည့္တိုက္","ဦးဝင္းေရႊ   ဥကၠဌ","09402681724","ဦးေအး   ဒုဥကၠဌ","0943075865","စာၾကည့္တိုက္သို့စာအုပ္ႏွင့္အလွဴေငြမ်ားေပးပို့လွဴဒါန္းႏိုင္ပါသည္။");
                  break;
              case 8:
                  showsarkyitite("လူမႈထူးခၽြန္ပညာေရးေဖာင္းေဒးရွင္း","ဦးတင္ေအာင္   ဥကၠဌ","092173003","ဦးေဇာ္မင္းစိုး   အတြင္းေရးမွဴး","0943076578","ပညာေရးေဖာင္းေဒးရွင္းသုိ့အလွဴေငြထည့္ဝင္ႏိုင္ပါသည္။ ");
                  break;
          }
        }
    }

    public void gointent(String title,int sendimg,String tablename){
        Intent intent = new Intent(getActivity(), ShowsingleView.class);
        intent.putExtra("title", title);
        intent.putExtra("sendimg",sendimg);
        intent.putExtra("tablename",tablename);
        startActivity(intent);
    }
    public void showdialog(String bussinesname,String phoneone,String phonetwo){
        DialogPhone fragment=new DialogPhone();
        Bundle bundle=new Bundle();
        bundle.putString("name",bussinesname);
        bundle.putString("phone",phoneone);
        bundle.putString("phtwo",phonetwo);
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(),"dialog");
        fragment.setCancelable(false);
    }

    public void showsarkyitite(String bussinesname,String fname,String phoneone,String sname,String phonetwo,String toname){
          SarkyiDialog sarkyiDialog=new SarkyiDialog();
        Bundle bundlea=new Bundle();
        bundlea.putString("name",bussinesname);
        bundlea.putString("fname",fname);
        bundlea.putString("phone",phoneone);
        bundlea.putString("sname",sname);
        bundlea.putString("phtwo",phonetwo);
        bundlea.putString("toname",toname);
        sarkyiDialog.setArguments(bundlea);
        sarkyiDialog.show(getFragmentManager(),"sardialog");
        sarkyiDialog.setCancelable(false);
    }
}
