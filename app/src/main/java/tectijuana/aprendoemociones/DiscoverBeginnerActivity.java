package tectijuana.aprendoemociones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DiscoverBeginnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_beginner);
    }

    public void happy(View view){
        Intent intent = new Intent(this, DiscoverHappyActivity.class);
        startActivity(intent);
    }

    public void angry(View view){
        Intent intent = new Intent(this, DiscoverAngryActivity.class);
        startActivity(intent);
    }
}
