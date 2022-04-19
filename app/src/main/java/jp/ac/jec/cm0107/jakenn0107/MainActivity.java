package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View topstart = findViewById(R.id.topStartbtn);
        topstart.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                // AnimationHelper.cancelAnimation(topstart);
                Intent intent = new Intent(MainActivity.this, ConfigurationView.class);
                startActivity(intent);
            }
        });

    }
}