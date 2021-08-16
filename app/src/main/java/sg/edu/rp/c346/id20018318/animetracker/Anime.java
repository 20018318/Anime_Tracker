package sg.edu.rp.c346.id20018318.animetracker;

import java.io.Serializable;

public class Anime implements Serializable {
    private int id;
    private String name;
    private int episodes;
    private String status;
    private String favourite;
    private int rating;

        public Anime(int id, String name, int episodes, String status, String favourite, int rating) {
        this.id = id;
        this.name = name;
        this.episodes = episodes;
        this.status = status;
        this.favourite = favourite;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
