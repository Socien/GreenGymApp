package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class Search_Map extends AppCompatActivity {

    //TMapGpsManager tMapGPS = null;
    TMapView tMapView;
    ArrayList<TMapPoint> alTMapPoint;
    ArrayList<String> Parkname;
    Bitmap bitmap;
    EditText editText;
    String km;
    InputMethodManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__map);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("공원 검색");

        //지도
        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xxffa04884ccc4425e9ac15036b9b46726");

        tMapView.setZoomLevel(15);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tMapView.setCenterPoint(127.090039, 37.538517, true);
        tMapView.setLocationPoint(127.090039, 37.538517);
        tMapView.setIconVisibility(true);

        LinearLayout Tmap = (LinearLayout) findViewById(R.id.Tmap);
        Tmap.addView(tMapView);

        //거리 설정
        km = "1";
        editText = (EditText) findViewById(R.id.editText);
        editText.setCursorVisible(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                editText.setCursorVisible(true);
            }
        });

        manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                km = editText.getText().toString();
                editText.setCursorVisible(false);
                manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //마커 초기화
                tMapView.removeAllMarkerItem();
                //통신
                sendRequest(km);
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

                //길찾기b
                tmapdata.findPathDataWithType(TMapData.TMapPathType.PEDESTRIAN_PATH, startpoint, point, new TMapData.FindPathDataListenerCallback() {
                    @Override
                    public void onFindPathData(TMapPolyLine polyLine) {
                        tMapView.addTMapPath(polyLine);
                    }
                });

            }
        });
    }
    public void sendRequest(String km){

        String url = String.format("http://15.164.250.186:8000/api/v1/park/list?x=%s&y=%s&dist=%s", "37.538517", "127.090039", km);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONObject name = response.getJSONObject("response");
                    JSONArray list = name.getJSONArray("List");

                    alTMapPoint = new ArrayList<>();
                    Parkname = new ArrayList<>();

                    for(int i=0; i<list.length();i++){
                        JSONObject park = list.getJSONObject(i);
                        String p_name = park.getString("p_name");
                        Parkname.add(p_name);

                        double x = park.getDouble("x");
                        double y = park.getDouble("y");
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
                Toast.makeText(Search_Map.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonObjectRequest.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
        AppHelper.requestQueue.add(jsonObjectRequest);

    }

}