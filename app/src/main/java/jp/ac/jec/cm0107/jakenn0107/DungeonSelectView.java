package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DungeonSelectView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon_select_view);


        Intent intent = getIntent();
        String charafirst = intent.getStringExtra("charadata");


        View doroidonoma = findViewById(R.id.doroidonomabtn);
        doroidonoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AnimationHelper.cancelAnimation(topstart);
                Intent intent = new Intent(DungeonSelectView.this, BattleView.class);
                intent.putExtra("charadataf", charafirst);
                startActivity(intent);
            }
        });

        View doroidonomasennsei = findViewById(R.id.doroidonomasennseibtn);
        doroidonomasennsei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AnimationHelper.cancelAnimation(topstart);
                Intent intent = new Intent(DungeonSelectView.this, TeacherBattleView.class);
                intent.putExtra("charadatas", charafirst);
                startActivity(intent);
            }
        });

        View devilbtn = findViewById(R.id.devilbtn);
        devilbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AnimationHelper.cancelAnimation(topstart);
                Intent intent = new Intent(DungeonSelectView.this, DevilBattleView.class);
                startActivity(intent);
            }
        });

        View returnbtn = findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DungeonSelectView.this, ConfigurationView.class);
                startActivity(intent);
            }
        });
    }
}