package sg.edu.rp.c346.id20018318.animetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Anime> animeList;

    public CustomAdapter(Context context, int resource, ArrayList<Anime> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        animeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        ImageView ivFav = rowView.findViewById(R.id.ivFav);
        TextView tvEP = rowView.findViewById(R.id.tvEpisodes);
        TextView tvStatus = rowView.findViewById(R.id.tvStatus);
        RatingBar rbRating = rowView.findViewById(R.id.ratingBar);

        Anime currentAnime = animeList.get(position);

        tvName.setText(currentAnime.getName());
        tvEP.setText(String.valueOf("Episodes: " + currentAnime.getEpisodes()));
        tvStatus.setText("|   " + currentAnime.getStatus());
        rbRating.setRating(currentAnime.getRating());

        if (currentAnime.getFavourite().equalsIgnoreCase("favourite")) {
            ivFav.setVisibility(View.VISIBLE);
        }

        return rowView;
    }
}
