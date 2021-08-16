package sg.edu.rp.c346.id20018318.animetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AnimeList extends AppCompatActivity {

    Button btnFilter;
    Spinner spinnerStatus;
    ListView lv;
    ArrayList<Anime> al;
    CustomAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);

        btnFilter = findViewById(R.id.btnFilter);
        spinnerStatus = findViewById(R.id.spinner);
        lv = findViewById(R.id.lvList);

        al = new ArrayList<Anime>();
        aa = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(AnimeList.this);
        al.addAll(dbh.getAllAnime());
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Anime data = al.get(position);
                Intent j = new Intent(AnimeList.this, EditAnime.class);
                j.putExtra("data", data);
                startActivity(j);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(AnimeList.this);
                al.clear();
                String keyword = "favourite";
                al.addAll(dbh.getAllFavouriteAnime(keyword));
                aa.notifyDataSetChanged();
            }
        });

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(AnimeList.this);
                String keyword = "";

                switch(position) {
                    case 0:
                        al.clear();
                        al.addAll(dbh.getAllAnime());
                        break;
                    case 1:
                        al.clear();
                        keyword = "Watched";
                        al.addAll(dbh.getAllAnimeByStatus(keyword));
                        break;
                    case 2:
                        al.clear();
                        keyword = "Watching";
                        al.addAll(dbh.getAllAnimeByStatus(keyword));
                        break;
                    case 3:
                        al.clear();
                        keyword = "Want to watch";
                        al.addAll(dbh.getAllAnimeByStatus(keyword));
                        break;
                }
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(AnimeList.this);
        al.clear();
        al.addAll(dbh.getAllAnime());
        aa.notifyDataSetChanged();

        dbh.close();
    }
}