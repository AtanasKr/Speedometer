package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class TestAc extends AppCompatActivity implements LocationListener {
    private Chronometer firstTime;
    private Chronometer secoundTime;
    private boolean runinng;
    private Button btnStart;
    private Button btnReset;
    private static String holder1;
    private static String holder2;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        firstTime=findViewById(R.id.firstTime);
        secoundTime=findViewById(R.id.secoundTime);

       if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }

       lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);
    }

    @Override
    public void onLocationChanged(Location location) {
        MainActivity in = new MainActivity();
            textView = findViewById(R.id.textView);
           btnStart = findViewById(R.id.btnStart);
           btnReset = findViewById(R.id.btnReset);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firstTime.setBase(SystemClock.elapsedRealtime());
                    secoundTime.setBase(SystemClock.elapsedRealtime());
                    firstTime.start();
                    secoundTime.start();


                }
            });
           btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firstTime.setBase(SystemClock.elapsedRealtime());
                    secoundTime.setBase(SystemClock.elapsedRealtime());
                    firstTime.stop();
                    secoundTime.stop();
                }
            });
            if(in.getPassSpeed()>59){
                firstTime.stop();

            }
            if(in.getPassSpeed()>99){
                secoundTime.stop();
            }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
