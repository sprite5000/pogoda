package com.example.pogoda;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
public class MainWeatherNext extends AppCompatActivity {
    LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pogoda2);


        WeatherTableBack[] weathers = new WeatherTableBack[5];
        WeatherTommorow weatherTommorow = new WeatherTommorow(this,Api.tablica[5][0],Api.tablica[0][0],Api.tablica[4][0]);
        weatherTommorow.wyswietl(Api.tablica[8][0],Api.tablica[7][0],Api.tablica[6][0],Api.tablica[9][0],Api.tablica[2][0]);

        for(int i = 1 ; i < 4 ; i++){
            weathers[i] = new WeatherTableBack(this,this,Api.tablica[5][i],Api.tablica[0][i],Api.tablica[4][i],Api.tab1[3][i+3]);
            weathers[i].wyswietl();
        }

        back = findViewById(R.id.back);
        back.setOnClickListener(view ->  onBackPressed());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}