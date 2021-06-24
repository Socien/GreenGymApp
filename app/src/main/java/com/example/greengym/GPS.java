package com.example.greengym;


import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

//사용자 현재 위치 지도에 표시 - 앱에서는 광진구 데이터만 사용하기 때문에 사용하지 않는 클래스
public class GPS extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback{
    TMapView tMapView = null;
    TMapGpsManager tMapGPS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        //지도 생성
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xx6be41da6c7754455a30d0fa9438f6896");
        //지도 설정
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        //tMapView.setIconVisibility(true); //위치표시 파란 점

        // T Map View Using Linear Layout
        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        linearLayoutTmap.addView(tMapView);


        // Request For GPS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // GPS using T Map
        tMapGPS = new TMapGpsManager(this);

        // Initial Setting
        tMapGPS.setMinTime(1000);
        tMapGPS.setMinDistance(10);
        tMapGPS.setProvider(TMapGpsManager.NETWORK_PROVIDER);
        //tMapGPS.setProvider(tMapGPS.GPS_PROVIDER);

        tMapGPS.OpenGps();

    }

    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
        //위도, 경도 값 받아서 사용
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        TMapPoint point = tMapGPS.getLocation();


        TMapMarkerItem markerItem1 = new TMapMarkerItem();

        Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.id.pin);

        markerItem1.setIcon(bitmap); // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.setTMapPoint(point); // 마커의 좌표 지정
        markerItem1.setName("현재위치"); // 마커의 타이틀 지정
        tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가

    }


}