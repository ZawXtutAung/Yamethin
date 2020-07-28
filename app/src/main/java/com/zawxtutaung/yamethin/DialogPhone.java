package com.zawxtutaung.yamethin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Dell on 12/14/2016.
 */

public class DialogPhone extends DialogFragment implements View.OnClickListener {
    View v;
     String bussinesname;
     String phoneone;
     String phonetwo;
    private TextView busname;
    private TextView phone;
    private  TextView phtwo;
    private CheckBox checkBoxone;
    private CheckBox checkBoxtwo;
    private Button callbtn;
    private Button dimissbtn;

//    public DialogPhone(){
//     this.bussinesname=bussinesname;
//     this.phoneone=phoneone;
//      this.phonetwo=phonetwo;
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.dialogfor_phonecall, container, false);
       getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_title);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         busname= (TextView) v.findViewById(R.id.bussinesname);
         phone= (TextView) v.findViewById(R.id.phoneone);
         phtwo= (TextView) v.findViewById(R.id.phonetwo);
         checkBoxone= (CheckBox) v.findViewById(R.id.checkboxone);
         checkBoxtwo= (CheckBox) v.findViewById(R.id.checkboxtwo);
         callbtn= (Button) v.findViewById(R.id.call);
         dimissbtn= (Button) v.findViewById(R.id.dimiss);
        Bundle bundle=new Bundle();

         callbtn.setOnClickListener(this);
         dimissbtn.setOnClickListener(this);
         checkBoxone.setOnClickListener(this);
         checkBoxtwo.setOnClickListener(this);

        this.bussinesname=getArguments().getString("name");
        this.phoneone=getArguments().getString("phone");
        this.phonetwo=getArguments().getString("phtwo");



        checkBoxone.setChecked(true);
         busname.setText(bussinesname);
         phone.setText(phoneone);
         phtwo.setText(phonetwo);
    }

    @Override
    public void onClick(View v) {
       btnClick(v);
        checkClick(v);
    }

    public void btnClick(View v){
        switch (v.getId()){
            case R.id.call:
               checkStatecheckBox();
                getDialog().dismiss();
                break;
            case R.id.dimiss:
                getDialog().dismiss();
                break;
        }
    }
    public void checkClick(View v){
        switch (v.getId()){
            case R.id.checkboxone:
                checkBoxone.setChecked(true);
                checkBoxtwo.setChecked(false);
                break;
            case R.id.checkboxtwo:
                checkBoxone.setChecked(false);
                checkBoxtwo.setChecked(true);
                break;
        }
    }

    public void checkStatecheckBox(){
                if (checkBoxone.isChecked()){
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+phone.getText().toString()));
                    startActivity(intent);
                }else {
                    if (checkBoxtwo.isChecked()){
                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+phtwo.getText().toString()));
                        startActivity(intent);
                    }
                }
            }


}
