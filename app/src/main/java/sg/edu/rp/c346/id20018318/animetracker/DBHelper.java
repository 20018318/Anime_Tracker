package sg.edu.rp.c346.id20018318.animetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "animelist.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_ANIME = "anime";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "anime_name";
    private static final String COLUMN_EPISODES = "anime_epsisodes";
    private static final String COLUMN_STATUS = "anime_status";
    private static final String COLUMN_FAVOURITE = "anime_favourite";
    private static final String COLUMN_STARS = "anime_stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_ANIME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EPISODES + " INTEGER,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_FAVOURITE + " TEXT,"
                + COLUMN_STARS + " INTEGER ) ";

        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIME);
        onCreate(db);
    }

    public long insertAnime(String name, int episodes, String status, String favourite, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EPISODES, episodes);
        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_FAVOURITE, favourite);
        values.put(COLUMN_STARS, rating);
        long result = db.insert(TABLE_ANIME, null, values);
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert", "ID:" + result);
        return result;
    }

    public ArrayList<Anime> getAllAnime() {
        ArrayList<Anime> animes = new ArrayList<Anime>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + ","
                + COLUMN_EPISODES + ","
                + COLUMN_STATUS + ","
                + COLUMN_FAVOURITE + ","
                + COLUMN_STARS + " FROM " + TABLE_ANIME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int episodes = cursor.getInt(2);
                String status = cursor.getString(3);
                String favourite = cursor.getString(4);
                int stars = cursor.getInt(5);
                Anime anime = new Anime(id, name, episodes, status, favourite, stars);
                animes.add(anime);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return animes;
    }

    public ArrayList<Anime> getAllFavouriteAnime(String keyword) {
        ArrayList<Anime> animes = new ArrayList<Anime>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EPISODES, COLUMN_STATUS, COLUMN_FAVOURITE, COLUMN_STARS};
        String condition = COLUMN_FAVOURITE + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_ANIME, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int episodes = cursor.getInt(2);
                String status = cursor.getString(3);
                String favourite = cursor.getString(4);
                int stars = cursor.getInt(5);
                Anime anime = new Anime(id, name, episodes, status, favourite, stars);
                animes.add(anime);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return animes;
    }

    public ArrayList<Anime> getAllAnimeByStatus(String keyword) {
        ArrayList<Anime> animes = new ArrayList<Anime>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EPISODES, COLUMN_STATUS, COLUMN_FAVOURITE, COLUMN_STARS};
        String condition = COLUMN_STATUS + " Like ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_ANIME, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int episodes = cursor.getInt(2);
                String status = cursor.getString(3);
                String favourite = cursor.getString(4);
                int stars = cursor.getInt(5);
                Anime anime = new Anime(id, name, episodes, status, favourite, stars);
                animes.add(anime);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return animes;
    }

    public int updateAnime(Anime data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_EPISODES, data.getEpisodes());
        values.put(COLUMN_STATUS, data.getStatus());
        values.put(COLUMN_FAVOURITE, data.getFavourite());
        values.put(COLUMN_STARS, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_ANIME, values, condition, args);
        db.close();
        return result;
    }

    public int deleteAnime(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_ANIME, condition, args);
        db.close();
        return result;
    }
}
