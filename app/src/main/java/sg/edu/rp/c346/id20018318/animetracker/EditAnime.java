package sg.edu.rp.c346.id20018318.animetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class EditAnime extends AppCompatActivity {

    EditText etId, etName, etEP;
    Spinner spinnerStatus;
    CheckBox cbFav;
    RatingBar rbRating;
    Button btnUpdate, btnDelete, btnCancel;
    Anime data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_anime);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etEP = findViewById(R.id.etEpisodes);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        cbFav = findViewById(R.id.checkBoxFav);
        rbRating = findViewById(R.id.ratingBar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent j = getIntent();
        data = (Anime) j.getSerializableExtra("data");

        etId.setText(String.valueOf(data.getId()));
        etName.setText(data.getName());
        etEP.setText(String.valueOf(data.getEpisodes()));
        if (data.getStatus().equalsIgnoreCase("Watched")) {
            spinnerStatus.setSelection(1);
        } else if (data.getStatus().equalsIgnoreCase("Watching")) {
            spinnerStatus.setSelection(2);
        } else {
            spinnerStatus.setSelection(3);
        }

        if (data.getFavourite().equalsIgnoreCase("favourite")) {
            cbFav.setChecked(true);
        } else {
            cbFav.setChecked(false);
        }
        rbRating.setRating(data.getRating());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditAnime.this);
                data.setName(etName.getText().toString());
                data.setEpisodes(Integer.parseInt(etEP.getText().toString()));
                data.setStatus(spinnerStatus.getSelectedItem().toString());
                if (cbFav.isChecked()) {
                    data.setFavourite("favourite");
                } else {
                    data.setFavourite("");
                }
                data.setRating((int) rbRating.getRating());

                dbh.updateAnime(data);

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditAnime.this);
                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to delete the anime: " + data.getName());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Cancel", null);
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditAnime.this);
                        dbh.deleteAnime(data.getId());

                        finish();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditAnime.this);
                myBuilder.setTitle("Warning");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Do not discard", null);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}