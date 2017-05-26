package tectijuana.aprendoemociones.model;

import android.graphics.Bitmap;

/**
 * Created by js on 5/23/17.
 */

public class GameImage {
    private int id;
    private Bitmap image;
    private String emotion;

    public GameImage(int id, Bitmap image, String emotion) {
        this.id = id;
        this.image = image;
        this.emotion = emotion;
    }
    // getters
    public int getId(){
        return this.id;
    }

    public Bitmap getImage(){
        return this.image;
    }

    public String getEmotion(){
        return this.emotion;
    }
}
