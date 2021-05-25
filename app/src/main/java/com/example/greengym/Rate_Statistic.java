package com.example.greengym;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Rate_Statistic extends AppCompatActivity {

    private BarChart chart;
    Rate_DB myDB;
    SQLiteDatabase sql;
    Cursor cursor;
    double min, sec, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate__statistic);

        //액션바 제목
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("운동량 통계");

        //그래프
        chart = (BarChart)findViewById(R.id.chart);

        //x축
        ArrayList week = new ArrayList();
        week.add("일"); week.add("월"); week.add("화"); week.add("수");
        week.add("목"); week.add("금"); week.add("토");
        XAxis x = chart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setTextSize(15f);
        x.setDrawGridLines(false);

        //y축 왼쪽
        YAxis yL = chart.getAxisLeft();
        yL.setTextSize(15f);
        yL.setSpaceBottom(0);
        yL.enableGridDashedLine(10, 15, 0);
        yL.setAxisMinValue(0);
        yL.setAxisMaxValue(50);

        //y축 오른쪽 비활성화
        YAxis yR = chart.getAxisRight();
        yR.setDrawLabels(false);
        yR.setDrawGridLines(false);

        //Color
        int[] color = new int[7];
        color[0] = Color.parseColor("#7DB577");
        color[1] = Color.parseColor("#2F6535");
        color[2] = Color.parseColor("#319308");
        color[3] = Color.parseColor("#84BF32");
        color[4] = Color.parseColor("#158C08");
        color[5] = Color.parseColor("#0A9117");
        color[6] = Color.parseColor("#147809");

        //데이터
        ArrayList entry = new ArrayList();
        myDB = new Rate_DB(this);
        sql = myDB.getReadableDatabase();
        cursor = sql.rawQuery("Select * From Rate", null);
        int i = 0;
        while(cursor.moveToNext()){
            long sumTime = cursor.getLong(1);
            min = (sumTime / 1000) / 60;
            sec = (sumTime / 1000.0 % 60.0) / 100.0;
            result = (min + sec);
            entry.add(new BarEntry((float) result, i));
            i++;
        }

        BarDataSet dataSet = new BarDataSet(entry, "운동량");
        dataSet.setDrawValues(true);
        dataSet.setValueTextSize(15f);
        dataSet.setValueFormatter(new myFormatter());
        dataSet.setColors(ColorTemplate.createColors(color));
        BarData data = new BarData(week, dataSet);

        //차트 설정
        chart.animateY(2000);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setDescription("");
        chart.setData(data);
    }

    public class myFormatter implements ValueFormatter{

        private DecimalFormat decimal;

        public myFormatter(){
            decimal = new DecimalFormat("##0.##");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return decimal.format(value) + "분";
        }
    }
}