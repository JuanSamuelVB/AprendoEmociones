package tectijuana.aprendoemociones.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import tectijuana.aprendoemociones.model.GameImage;

/**
 * Created by js on 5/23/17.
 */

public class DatabaseHelper extends SQLiteAssetHelper {
    public static final String DB_NAME = "images.db";
    public static final int DB_VERSION = 2;

    public static final String LOG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public GameImage getImage(int image_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM images WHERE id = " + image_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        GameImage gameImage = null;

        if (c != null && c.moveToFirst()) {
            int id = c.getInt(c.getColumnIndex("id"));
            byte[] imageByte = c.getBlob(c.getColumnIndex(("image")));
            Bitmap image = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            String emotion = c.getString(c.getColumnIndex("emotion"));

            gameImage = new GameImage(id, image, emotion);

            c.close();
        }

        return gameImage;
    }

    public int getScore() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM score WHERE id = 1";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int score = 0;

        if (c != null && c.moveToFirst()) {
            int correct = c.getInt(c.getColumnIndex("correct"));
            int wrong = c.getInt(c.getColumnIndex("wrong"));

            score = correct - wrong;

            c.close();
        }

        return score;
    }

    public int getCorrectScore() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT correct FROM score WHERE id = 1";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int correct = 0;

        if (c != null && c.moveToFirst()) {
            correct = c.getInt(c.getColumnIndex("correct"));

            c.close();
        }

        return correct;
    }

    public int getWrongScore() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT wrong FROM score WHERE id = 1";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int wrong = 0;

        if (c != null && c.moveToFirst()) {
            wrong = c.getInt(c.getColumnIndex("wrong"));

            c.close();
        }

        return wrong;
    }

    public void setScore(int correct, int wrong) {
        SQLiteDatabase db = this.getReadableDatabase();

        String updateQuery = "UPDATE score SET correct = " + correct
                + ", wrong = " + wrong
                + " WHERE id = 1";

        Log.e(LOG, updateQuery);

        db.execSQL(updateQuery);
    }

    public void resetScore() {
        SQLiteDatabase db = this.getReadableDatabase();

        String updateQuery = "UPDATE score SET correct = 0, wrong = 0 WHERE id = 1";

        Log.e(LOG, updateQuery);

        db.execSQL(updateQuery);
    }
}