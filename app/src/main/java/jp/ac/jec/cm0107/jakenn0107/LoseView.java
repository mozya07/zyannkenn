package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class LoseView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_view);

        // 自分のHP
        ProgressBar bars = (ProgressBar)findViewById(R.id.progressBar2);
        bars.setMax(100);
        bars.setProgress(0);

        ImageView pa = (ImageView) findViewById(R.id.pabtn);
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoseView.this, DungeonSelectView.class);
                startActivity(intent);

            }
        });
    }
}