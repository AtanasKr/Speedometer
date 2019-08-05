package com.example.speedometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements LocationListener{
    private Button btnTest;
    private double passSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);
        btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestAc.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        TextView showSpeed =(TextView) this.findViewById(R.id.showSpeed);
        if(location==null){
            showSpeed.setText("-,- km/h");
        }
        else {
            double curr_speed= location.getSpeed();
            double transformSpeed = curr_speed*3.6;
            String.format("%.1f",transformSpeed);
            passSpeed = transformSpeed;
            String holder = String.format("%.1f",transformSpeed);
            showSpeed.setText(holder+" km/h");
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

    public double getPassSpeed() {
        return passSpeed;
    }
}
