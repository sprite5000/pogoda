package com.example.pogoda;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainWeather extends AppCompatActivity {
    TextView next;
    ImageView image;
    String val;
    TableLayout tablica;
    RelativeLayout napis1;
    View divider;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        divider = findViewById(R.id.divider);
        divider.setVisibility(View.GONE);

        tablica = findViewById(R.id.tablica);
        tablica.setVisibility(View.GONE);

        napis1 = findViewById(R.id.napis1);
        napis1.setVisibility(View.GONE);

        Intent intent1 = getIntent();
        val = intent1.getStringExtra("city");
        Api.nazwa=val;

        next = findViewById(R.id.nextBtn);
        image = findViewById(R.id.image);


        next.setOnClickListener(view -> {
            Intent intent = new Intent(MainWeather.this, MainWeatherNext.class);
            intent.putExtra("city", Api.nazwa);
            startActivity(intent);
        });
        new ApiTask().execute();

    }

    class ApiTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Api.api();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            displayData();
        }
    }

    void displayData() {

        if(Api.responseCode !=200){
            Toast.makeText(this, "Wrong data!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        }else {
            progressBar.setVisibility(View.GONE);
            napis1.setVisibility(View.VISIBLE);
            tablica.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            String seaGrndLevel = "S: "+Api.tab1[9][0] + " hPa | G: "+Api.tab1[10][0]+" hPa";
            MainPhoto mainPhoto = new MainPhoto(this,Api.tab1[0][0]);
            mainPhoto.wyswietl(Api.tab1[2][0],Api.tab1[5][0],Api.tab1[4][0],Api.tab1[8][0],Api.tab1[7][0],Api.tab1[6][0],val, seaGrndLevel);
            mainPhoto.wywolaj(image);

            WeatherTime[] weatherTimes = new WeatherTime[10];
            for (int i = 1 ; i<weatherTimes.length ; i++){
                weatherTimes[i] = new WeatherTime(this,Api.tab1[0][i] ,Api.tab1[4][i], Api.tab1[11][i]);
                weatherTimes[i].wyswietl();
            }
        }

    }
}
