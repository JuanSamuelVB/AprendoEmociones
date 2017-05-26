package tectijuana.aprendoemociones;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tectijuana.aprendoemociones.helper.DatabaseHelper;
import tectijuana.aprendoemociones.model.GameImage;

public class GuessBeginnerActivity extends AppCompatActivity {
    ImageView imgView;
    DatabaseHelper db;
    GameImage gameImage;
    int scoreCorrect;
    int scoreWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_beginner);

        imgView = (ImageView)findViewById(R.id.photo);
        db = new DatabaseHelper(this);

        gameImage = db.getImage(1);
        imgView.setImageBitmap(gameImage.getImage());

        scoreCorrect = db.getCorrectScore();
        scoreWrong = db.getWrongScore();

        Log.e("GuessBeginnerActivity", "Score correct: " + scoreCorrect);
    }

    public void happy(View view) {
        evaluateAnswer("Feliz");
    }

    public void angry(View view) {
        evaluateAnswer("Enojado");
    }

    void changePhoto() {
        Random r = new Random();
        int id = r.nextInt(10) + 1;

        gameImage = db.getImage(id);
        Bitmap image = gameImage.getImage();

        imgView.setImageBitmap(image);
    }

    void evaluateAnswer(String answer) {
        if (isCorrect(answer))
            correctAnswer();
        else
            wrongAnswer(answer);
    }

    boolean isCorrect(String answer) {
        return answer.equals(gameImage.getEmotion());
    }

    void correctAnswer() {
        showCongrats();
        playCongrats();
        changePhoto();
        resetButtons();
        addCorrect();
    }

    void wrongAnswer(String answer) {
        final ImageButton btn;

        if (answer.equals("Feliz"))
            btn = (ImageButton) findViewById(R.id.happyButton);
        else
            btn = (ImageButton) findViewById(R.id.angryButton);

        btn.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.ADD);

        addWrong();
    }

    void resetButtons() {
        final ImageButton happyButton = (ImageButton) findViewById(R.id.happyButton);
        final ImageButton angryButton = (ImageButton) findViewById(R.id.angryButton);

        angryButton.getBackground().clearColorFilter();
        happyButton.getBackground().clearColorFilter();
    }

    void addCorrect() {
        scoreCorrect += 1;
        db.setScore(scoreCorrect, scoreWrong);
    }

    void addWrong() {
        scoreWrong += 1;
        db.setScore(scoreCorrect, scoreWrong);
    }

    void showCongrats() {
        final TextView congrats = (TextView)findViewById(R.id.correctText);
        final ImageView image = (ImageView)findViewById(R.id.photo);

        congrats.setVisibility(View.VISIBLE);
        image.setVisibility(View.INVISIBLE);

/*        CountDownTimer timer = new CountDownTimer(2000, 0) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                congrats.setVisibility(View.INVISIBLE);
                image.setVisibility(View.VISIBLE);
            }
        }.start();*/

        Timer t = new Timer(false);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        congrats.setVisibility(View.INVISIBLE);
                        image.setVisibility(View.VISIBLE);
                    }
                });
            }
        }, 1000);
    }

    void playCongrats() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bien_hecho);
        mp.start();
    }
}
