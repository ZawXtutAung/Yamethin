package com.zawxtutaung.yamethin.map;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zawxtutaung.yamethin.R;

/**
 * Created by zawxtutaung on 12/28/2016.
 */

public class MapAtivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener, View.OnClickListener {
    GoogleMap mMap;
    Toolbar mtoolbar;
     FloatingActionButton flbtn;
    LocationManager locationManager;
    SharedPreferences sharedPreferences,savethemeshare;
    SharedPreferences.Editor editor;
    Button gb1,gb2;
    Dialog fdi;
    String saveString,colorsaveString;
    private int colorchange;
    private Dialog gpd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        mtoolbar=(Toolbar)findViewById(R.id.mapbar);
        flbtn=(FloatingActionButton) findViewById(R.id.flbtn);
        flbtn.setOnClickListener(this);
//        setSupportActionBar(mtoolbar);
//       //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//       // getSupportActionBar().setHomeActionContentDescription(t);
//         getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SupportMapFragment myMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        myMapFragment.getMapAsync(this);
        colorchangetheme();
        mtoolbar.setBackgroundColor(colorchange);
locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
            //Toast.makeText(this, "GPS ပိတ္ထားပါသည္", Toast.LENGTH_LONG).show();
       gpsdai();
       // floatDailog();
        else {
            Toast.makeText(this, "GPS ဖြင့္ထားပါသည္", Toast.LENGTH_LONG).show();


        }
    }

    private void gpsdai() {
          gpd=new Dialog(MapAtivity.this);
        gpd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gpd.setContentView(R.layout.gps_activity);
        gpd.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window=gpd.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();
        param.gravity = Gravity.CENTER| Gravity.CENTER;
        gpd.setCanceledOnTouchOutside(true);
     gb1=(Button)gpd.findViewById(R.id.gpsyes);
       gb2=(Button)gpd.findViewById(R.id.gpsno);
        gb1.setOnClickListener(this);
        gb2.setOnClickListener(this);
        gb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 Intent gintent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(gintent);
                gpd.dismiss();


//                boolean gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                if (!gpsStatus) {
//                    Settings.Secure.putString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED, "network,gps");
//                }
//                String provider =Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//                if(!provider.contains("gps")){ //if gps is disabled
//                    final Intent poke = new Intent();
//                    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//                    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//                    poke.setData(Uri.parse("3"));
//                    sendBroadcast(poke);
//                }
            }
        });
        gb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
gpd.dismiss();
            }
        });
        gpd.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(20.429062, 96.138975);
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true).title("Yamethin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
       // floatDailog();
        switch (v.getId()){
            case R.id.flbtn:
                floatDailog();
                flbtn.setVisibility(View.GONE);
                break;
            case R.id.fbtnmain:

                break;
         //   case R.id.fbtn1:
            //   mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            //    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//                fdi.dismiss();
//                flbtn.setVisibility(View.VISIBLE);
//
//                break;
            case R.id.fbtn2:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                fdi.dismiss();
                flbtn.setVisibility(View.VISIBLE);

                break;
//            case R.id.fbtn3:
//                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//                break;
            case R.id.fbtn3:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                fdi.dismiss();
                flbtn.setVisibility(View.VISIBLE);
                break;
//            case R.id.gpsno:
//
//                break;
//            case R.id.gpsyes:
//
//                break;

        }
    }
  FloatingActionButton fb1,fb2,fb3,fb4,fbmain;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void floatDailog() {

       fdi=new Dialog(MapAtivity.this);
        fdi.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fdi.setContentView(R.layout.floting_dailog);
        fdi.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window=fdi.getWindow();
        WindowManager.LayoutParams param = window.getAttributes();
        param.gravity = Gravity.BOTTOM | Gravity.LEFT;
        fdi.setCanceledOnTouchOutside(true);
        fbmain= (FloatingActionButton) fdi.findViewById(R.id.fbtnmain);
       // fb1= (FloatingActionButton) fdi.findViewById(R.id.fbtn1);
        fb2= (FloatingActionButton) fdi.findViewById(R.id.fbtn2);
        fb3=(FloatingActionButton)fdi.findViewById(R.id.fbtn3);
       // fb4= (FloatingActionButton) fdi.findViewById(R.id.fbtn4);
        fdi.setCancelable(false);
        fbmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdi.dismiss();
                flbtn.setVisibility(View.VISIBLE);
            }
        });
      //  fb1.setOnClickListener(this);
        fb2.setOnClickListener(this);
        fb3.setOnClickListener(this);
        //fb4.setOnClickListener(this);

        fdi.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void readSharepre(){
        sharedPreferences=getSharedPreferences("savefragplace",0);
        savethemeshare=getSharedPreferences("savethemecolor",0);
        saveString=sharedPreferences.getString("savefarg","");
        colorsaveString=savethemeshare.getString("colorsave","");

    }

    public void colorchangetheme(){
        readSharepre();
        if (colorsaveString.contains("bluelow")){
            colorchange=getResources().getColor(R.color.bluelow);
        }else {
            if (colorsaveString.contains("blue")){
                colorchange=getResources().getColor(R.color.blue);
            }else {
                if (colorsaveString.contains("brown")){
                    colorchange=getResources().getColor(R.color.brown);
                }else {
                    if (colorsaveString.contains("high")){
                        colorchange=getResources().getColor(R.color.bluehigh);
                    }else {
                        if (colorsaveString.contains("greena")){
                            colorchange=getResources().getColor(R.color.greena);
                        }else {
                            if (colorsaveString.contains("orange")){
                                colorchange=getResources().getColor(R.color.orange);
                            }else {
                                if (colorsaveString.contains("blacka")){
                                    colorchange=getResources().getColor(R.color.blacka);
                                }else {
                                    colorchange=getResources().getColor(R.color.bluelow);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

