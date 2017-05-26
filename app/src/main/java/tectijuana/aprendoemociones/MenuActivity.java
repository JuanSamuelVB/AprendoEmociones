package tectijuana.aprendoemociones;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class MenuActivity extends AppCompatActivity {
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sb = (SeekBar)findViewById(R.id.config_seekbar);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 8) {
                    openSettings();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.config_menu:
                toggleToolbar();
        }

        return super.onOptionsItemSelected(item);
    }

    void toggleToolbar() {
        Toolbar t = (Toolbar)findViewById(R.id.actionbarT);
        if (t.getVisibility() == View.INVISIBLE) {
            t.setVisibility(View.VISIBLE);
        }
        else {
            t.setVisibility(View.INVISIBLE);
        }
    }

    public void discover(View view){
        Intent intent = new Intent(this, DiscoverBeginnerActivity.class);
        startActivity(intent);
    }

    public void guess(View view){
        Intent intent = new Intent(this, GuessBeginnerActivity.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
