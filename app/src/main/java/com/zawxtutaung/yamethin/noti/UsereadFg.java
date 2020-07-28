package com.zawxtutaung.yamethin.noti;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zawxtutaung.yamethin.R;

import java.util.ArrayList;

/**
 * Created by Dell on 1/26/2017.
 */

public class UsereadFg extends Fragment implements View.OnClickListener {
    private View v;

    final static String DB_URL = "https://yamethin-6f538.firebaseio.com/";

    Toolbar tob;
    EditText nameEditText, urlEditText,dateEditText,bodyEditText;
    RecyclerView rv;
    SharedPreferences sharedPreferences, savethemeshare;
    SharedPreferences.Editor editor;
    Button saveBtn;
    String saveString, colorsaveString;
    TextView toobartext;
    LinearLayoutManager llayoutManager;
    private int colorchange;
    ConnectivityManager connectivityManager;
    FireBaseClient fireBaseClient;
    FloatingActionButton floatingActionButton;
    MyAdapter adapter;
    ArrayList<Movier> movies = new ArrayList<>();
    private ProgressBar progressBar;
    private Button reload;
    boolean is3g;
    boolean isWifi;
    private boolean isConnected = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.new_fg, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rv = (RecyclerView)v. findViewById(R.id.rcv);
        llayoutManager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llayoutManager);
//        adapter = new MyAdapter(this.getContext(), movies);
//        rv.setAdapter(adapter);
//        adapter.setOnCustomClick(new REader());

        progressBar = (ProgressBar) v.findViewById(R.id.mainProgressBar);


        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.firebase_new_fbtn);
        reload= (Button) v.findViewById(R.id.reload);
        reload.setOnClickListener(this);

        floatingActionButton.setOnClickListener(this);



//        connectivityManager = (ConnectivityManager) getContext().getSystemService(this.getContext().CONNECTIVITY_SERVICE);
//         is3g = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
//                .isConnectedOrConnecting();
//         isWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
////                .isConnectedOrConnecting();
//        if (!is3g && !isWifi) {
//            NetDai();
//            reload.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.INVISIBLE);
//            // Toast.makeText(getApplicationContext(),"Network Connection is OFF", Toast.LENGTH_LONG).show();
//        } else {
//            progressBar.setVisibility(View.VISIBLE);
//
//        }

        if (CheckNetwork.isInternetAvailable(getContext()))
        {
            progressBar.setVisibility(View.VISIBLE);
            fireBaseClient = new FireBaseClient(this.getContext(), DB_URL, rv,progressBar);
            fireBaseClient.refreshData();
        }else {
            NetDai();
            reload.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firebase_new_fbtn:
                NewTextDailog();
                break;
            case R.id.reload:

                if (CheckNetwork.isInternetAvailable(getContext()))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    reload.setVisibility(View.INVISIBLE);
                    fireBaseClient = new FireBaseClient(this.getContext(), DB_URL, rv,progressBar);
                    fireBaseClient.refreshData();
                }else {
                    NetDai();
                    reload.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }


//                if (!is3g && !isWifi) {
//                    NetDai();
//                    reload.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.INVISIBLE);
//                    // Toast.makeText(getApplicationContext(),"Network Connection is OFF", Toast.LENGTH_LONG).show();
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                    reload.setVisibility(View.INVISIBLE);
//                    fireBaseClient.refreshData();
//                }





                break;
        }
    }
    private void NetDai() {
        final Dialog netdai = new Dialog(this.getContext());
        netdai.setCancelable(false);
        netdai.requestWindowFeature(Window.FEATURE_NO_TITLE);
        netdai.setContentView(R.layout.dailog_internet);
        Button bok = (Button) netdai.findViewById(R.id.netno);
        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netdai.dismiss();
            }
        });
        netdai.show();

    }
    public void readSharepre() {
        sharedPreferences = getContext().getSharedPreferences("savefragplace", 0);
        savethemeshare = getContext().getSharedPreferences("savethemecolor", 0);
        saveString = sharedPreferences.getString("savefarg", "");
        colorsaveString = savethemeshare.getString("colorsave", "");

    }
    public void colorchangetheme() {
        readSharepre();
        if (colorsaveString.contains("bluelow")) {
            colorchange = getResources().getColor(R.color.bluelow);
        } else {
            if (colorsaveString.contains("blue")) {
                colorchange = getResources().getColor(R.color.blue);
            } else {
                if (colorsaveString.contains("brown")) {
                    colorchange = getResources().getColor(R.color.brown);
                } else {
                    if (colorsaveString.contains("high")) {
                        colorchange = getResources().getColor(R.color.bluehigh);
                    } else {
                        if (colorsaveString.contains("greena")) {
                            colorchange = getResources().getColor(R.color.greena);
                        } else {
                            if (colorsaveString.contains("orange")) {
                                colorchange = getResources().getColor(R.color.orange);
                            } else {
                                if (colorsaveString.contains("blacka")) {
                                    colorchange = getResources().getColor(R.color.blacka);
                                } else {
                                    colorchange = getResources().getColor(R.color.bluelow);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    private void NewTextDailog() {
        Dialog d = new Dialog(this.getContext());
        d.setTitle("Save Online");
        d.setContentView(R.layout.firebase_new_dailog);
        nameEditText = (EditText) d.findViewById(R.id.nameEditText);
        urlEditText = (EditText) d.findViewById(R.id.urlEditText);
        dateEditText= (EditText) d.findViewById(R.id.dateEditText);
        bodyEditText= (EditText) d.findViewById(R.id.bodyEditText);

        saveBtn = (Button) d.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditText.getText().toString().matches("")||urlEditText.getText().toString().matches("")||bodyEditText.getText().toString().matches("")||dateEditText.getText().toString().matches(""))
                {
                    Toast.makeText(getContext(),"NO Data is Empty",Toast.LENGTH_LONG).show();
                }else {
                    fireBaseClient.saveOnline(nameEditText.getText().toString(), urlEditText.getText().toString(), bodyEditText.getText().toString(), dateEditText.getText().toString());
                    nameEditText.setText("");
                    urlEditText.setText("");
                    dateEditText.setText("");
                    bodyEditText.setText("");
                }
            }
        });
        //SHOW
        d.show();
    }

//    private class REader implements MyAdapter.OnCustomClick {
//        public void onCustomItemClick(View v, int position) {
//            Intent intent=new Intent(getActivity(),NewView.class);
//            startActivity(intent);
//            //  Toast.makeText(getApplicationContext(), "This position is" + position, Toast.LENGTH_LONG).show();
//        }
//    }
static class CheckNetwork {


    private static final String TAG = CheckNetwork.class.getSimpleName();


    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            Log.d(TAG, "no internet connection");
            return false;
        } else {
            if (info.isConnected()) {
                Log.d(TAG, " internet connection available...");
                return true;
            } else {
                Log.d(TAG, " internet connection");
                return true;
            }

        }
    }
}

}
