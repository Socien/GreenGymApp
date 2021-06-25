package com.example.greengym;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search_EquipList extends AppCompatActivity {

    //TMapGpsManager tMapGPS = null;
    TMapView tMapView;
    ArrayList<TMapPoint> alTMapPoint = new ArrayList<>();
    ArrayList<String> Parkname = new ArrayList<>();
    Bitmap bitmap;
    String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_equip_list);

        //선택 운동기구 데이터 받음
        Intent intent = getIntent();
        select = intent.getExtras().getString("select");

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(select);


        //지도
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xx6be41da6c7754455a30d0fa9438f6896");

        tMapView.setZoomLevel(13);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tMapView.setCenterPoint(127.090039, 37.538517, true);
        tMapView.setLocationPoint(127.090039, 37.538517);
        tMapView.setIconVisibility(true);

        LinearLayout Tmap = (LinearLayout)findViewById(R.id.Tmap1);
        Tmap.addView(tMapView);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //통신
                sendRequest(select);

            }
        });
        //롱클릭 이벤트 - 도보 경로
        tMapView.setOnLongClickListenerCallback(new TMapView.OnLongClickListenerCallback() {

            @Override
            public void onLongPressEvent(ArrayList markerlist, ArrayList poilist, TMapPoint point) {
                TMapData tmapdata = new TMapData();

                //광진구 임의 현재 위치
                TMapPoint startpoint = new TMapPoint(37.538517, 127.090039);
                //TMapPoint endpoint = tmp;

                //길찾기
                tmapdata.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, startpoint, point, new TMapData.FindPathDataListenerCallback() {
                    @Override
                    public void onFindPathData(TMapPolyLine polyLine) {
                        tMapView.addTMapPath(polyLine);
                    }
                });

            }
        });
    }
    public void sendRequest(String select){

        String url = String.format("http://15.164.250.186:8000/api/v1/equip/list?e_name=%s", select);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray equip = response.getJSONArray("response");

                    for(int i=0; i<equip.length();i++){
                        JSONObject detail = equip.getJSONObject(i);
                        String p_name = detail.getString("p_name");
                        Parkname.add(p_name);

                        double x = detail.getDouble("x");
                        double y = detail.getDouble("y");
                        alTMapPoint.add(new TMapPoint(x,y));
                    }

                    bitmap = BitmapFactory.decodeResource(getResources(), R.id.pin);
                    for(int i=0; i<alTMapPoint.size(); i++){
                        TMapMarkerItem markerItem1 = new TMapMarkerItem();
                        // 마커 아이콘 지정
                        markerItem1.setIcon(bitmap);
                        // 마커의 좌표 지정
                        markerItem1.setTMapPoint(alTMapPoint.get(i));

                        // 풍선뷰
                        markerItem1.setCanShowCallout(true);
                        markerItem1.setCalloutTitle(Parkname.get(i));
                        //markerItem1.getCalloutSubTitle();

                        //지도에 마커 추가
                        tMapView.addMarkerItem("markerItem"+i, markerItem1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Search_EquipList.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        jsonObjectRequest.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
        AppHelper.requestQueue.add(jsonObjectRequest);

    }

}