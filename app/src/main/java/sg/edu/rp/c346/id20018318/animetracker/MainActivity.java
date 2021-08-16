package sg.edu.rp.c346.id20018318.animetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEP;
    Spinner spinnerStatus;
    CheckBox cbFav;
    RatingBar rbRating;
    Button btnInsert, btnList, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEP = findViewById(R.id.etEpisodes);
        spinnerStatus =  findViewById(R.id.spinnerStatus);
        cbFav = findViewById(R.id.checkBoxFav);
        rbRating = findViewById(R.id.ratingBar);
        btnInsert = findViewById(R.id.btnUpdate);
        btnList = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().trim().isEmpty() || etEP.getText().toString().trim().isEmpty() || rbRating.getRating() == 0.0) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String name = etName.getText().toString();
                    int ep = Integer.parseInt(etEP.getText().toString());
                    String status = spinnerStatus.getSelectedItem().toString();
                    String fav = "";
                    if (cbFav.isChecked()) {
                        fav = "favourite";
                    } else {
                        fav = "";
                    }
                    int stars = (int) rbRating.getRating();

                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertAnime(name, ep, status, fav, stars);
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();

                    etName.setText("");
                    etEP.setText("");
                    spinnerStatus.setSelection(0);
                    cbFav.setChecked(false);
                    rbRating.setRating(0);
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnimeList.class);
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}