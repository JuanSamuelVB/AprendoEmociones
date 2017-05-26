package tectijuana.aprendoemociones;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import tectijuana.aprendoemociones.helper.DatabaseHelper;

public class StatsActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        db = new DatabaseHelper(this);

        displayScore();
    }

    public void restart(View view) {
        db.resetScore();
        displayScore();
    }

    public void displayScore() {
        TextView correctText = (TextView)findViewById(R.id.correctScore);
        TextView wrongText = (TextView)findViewById(R.id.wrongScore);
        TextView scoreText = (TextView)findViewById(R.id.totalScore);

        correctText.setText(String.valueOf(db.getCorrectScore()));
        wrongText.setText(String.valueOf(db.getWrongScore()));
        scoreText.setText(String.valueOf(db.getScore()));
    }
}
