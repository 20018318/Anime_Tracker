package sg.edu.rp.c346.id20018318.animetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppIntro extends AppCompatActivity {

    Button btnCreate, btnDetails, btnWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_intro);

        btnCreate = findViewById(R.id.btnCreate);
        btnDetails = findViewById(R.id.btnDetails);
        btnWatch = findViewById(R.id.btnWatch);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppIntro.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animeInfo = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myanimelist.net/"));
                startActivity(animeInfo);
            }
        });

        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animeWatch = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ww1.gogoanimes.org/"));
                startActivity(animeWatch);
            }
        });
    }
}