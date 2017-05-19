package tectijuana.aprendoemociones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LevelOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_options);
    }

    public void beginner(View view){
        Intent intent = new Intent(this, DiscoverBeginnerActivity.class);
        startActivity(intent);
    }

    public void advanced(View view){
        Intent intent = new Intent(this, DiscoveryAdvancedActivity.class);
        startActivity(intent);
    }
}
