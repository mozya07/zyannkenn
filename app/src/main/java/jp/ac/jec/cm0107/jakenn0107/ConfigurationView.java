package jp.ac.jec.cm0107.jakenn0107;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigurationView extends AppCompatActivity {

    public String username = "名無し";
    public String cname = "ちびドラ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_view);

        TextView usernametxt = (TextView) findViewById(R.id.usernametxt);
        SharedPreferences sp = getSharedPreferences("jankenn0107", Context.MODE_PRIVATE);
        String memo = sp.getString("MEMO", username);
        usernametxt.setText(memo);

ImageView kinamet = (ImageView)findViewById(R.id.renamebtn);
kinamet.setImageResource(R.drawable.kinamet);


        View dungeonbtn = findViewById(R.id.Dungeonbtn);
        dungeonbtn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationView.this, DungeonSelectView.class);
                intent.putExtra("charadata", cname);
                startActivity(intent);
            }
        });

        View topstart = findViewById(R.id.Charactersettingbtn);
        topstart.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
//                Intent intent = new Intent(ConfigurationView.this, CharacterselectionView.class);
//                startActivity(intent);
                Intent intent = new Intent(ConfigurationView.this, CharacterselectionView.class);
                startActivityForResult(intent, CharacterselectionView.CODE_SETTINGS);
            }
        });

        View rename = findViewById(R.id.renamebtn);
        rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ConfigurationView.this, RenameActivity.class);
                intent.putExtra("username", username);
                startActivityForResult(intent, RenameActivity.CODE_SETTING);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 第二引数のint resultCodeが（今回は）OKが押されたのかCancelが押されたのかを持っている
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RenameActivity.CODE_SETTING) {
            if (resultCode == RESULT_OK) {
                username = data.getStringExtra("userrename");
                TextView usernametxt = (TextView) findViewById(R.id.usernametxt);
                usernametxt.setText("・ユーザー名：" + username);

                String memoo = usernametxt.getText().toString();

                // MODE_PRIVATE 自アプリからのみ、読み書きかできる
                // MODE_WORLD_READABLE 他アプリからでも、読むことができる
                // MODE_WORLD_WRITABLE 他アプリからでも、読み書きができる
                SharedPreferences sp = getSharedPreferences("jankenn0107", Context.MODE_PRIVATE);
                SharedPreferences.Editor edtr = sp.edit();
                edtr.putString("MEMO", memoo);
                edtr.commit();



                return;

            }
        }
        else if (requestCode == CharacterselectionView.CODE_SETTINGS) {
            if (resultCode == RESULT_OK) {
                ImageView monsterview = (ImageView)findViewById(R.id.mymonsterViewf);
                cname = data.getStringExtra("cnamedata");
                if (cname.equals("ちびドラ")) {
                    monsterview.setImageResource(R.drawable.chibidora);
                } else if (cname.equals("リュウ")) {
                    monsterview.setImageResource(R.drawable.ryuu);
                } else if (cname.equals("リュウ酒")) {
                    monsterview.setImageResource(R.drawable.ryuusake);
                } else if (cname.equals("ドロイドロボ")) {
                    monsterview.setImageResource(R.drawable.kikaidoro);
                }

            }

        }
    }

}