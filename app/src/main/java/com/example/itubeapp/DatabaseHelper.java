package com.example.itubeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iTubeDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users(fullname TEXT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE Playlist(videoUrl TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Playlist");
        onCreate(db);
    }

    public void insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullname", user.fullName);
        values.put("username", user.username);
        values.put("password", user.password);
        db.insert("Users", null, values);
        db.close();
    }

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public void insertPlaylistItem(String url) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("videoUrl", url);
        db.insert("Playlist", null, values);
        db.close();
    }

    public List<PlaylistItem> getAllPlaylistItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Playlist", null);
        List<PlaylistItem> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            list.add(new PlaylistItem(cursor.getString(0)));
        }
        cursor.close();
        db.close();
        return list;
    }
}