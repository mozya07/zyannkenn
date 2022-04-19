package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class CharacterselectionView extends AppCompatActivity {

    public static final int CODE_SETTINGS = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterselection_view);

        ImageView chibidorabtn = (ImageView) findViewById(R.id.chibidorabtn);
        chibidorabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                // 次の画面はこれだよー
                intent.putExtra("cnamedata", "ちびドラ");
                // dataにedtsの内容が入っている (ここでデータを格納している)
                setResult(RESULT_OK, intent);
                // どこの画面に行くの？となり、MainActivityで書いたあいつの効果が発動してMainActivityに移動する
                finish();
//                Intent intent = new Intent(CharacterselectionView.this, ConfigurationView.class);
//                startActivity(intent);
            }
        });

        ImageView ryuubtn = (ImageView) findViewById(R.id.ryuubtn);
        ryuubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                // 次の画面はこれだよー
                intent.putExtra("cnamedata", "リュウ");
                // dataにedtsの内容が入っている (ここでデータを格納している)
                setResult(RESULT_OK, intent);
                // どこの画面に行くの？となり、MainActivityで書いたあいつの効果が発動してMainActivityに移動する
                finish();
//                Intent intent = new Intent(CharacterselectionView.this, ConfigurationView.class);
//                startActivity(intent);
            }
        });

        ImageView ryuusakebtn = (ImageView) findViewById(R.id.ryuusakebtn);
        ryuusakebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                // 次の画面はこれだよー
                intent.putExtra("cnamedata", "リュウ酒");
                // dataにedtsの内容が入っている (ここでデータを格納している)
                setResult(RESULT_OK, intent);
                // どこの画面に行くの？となり、MainActivityで書いたあいつの効果が発動してMainActivityに移動する
                finish();
//                Intent intent = new Intent(CharacterselectionView.this, ConfigurationView.class);
//                startActivity(intent);
            }
        });

        ImageView doroidorobobtn = (ImageView) findViewById(R.id.doroidorobobtn);
        doroidorobobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                // 次の画面はこれだよー
                intent.putExtra("cnamedata", "ドロイドロボ");
                // dataにedtsの内容が入っている (ここでデータを格納している)
                setResult(RESULT_OK, intent);
                // どこの画面に行くの？となり、MainActivityで書いたあいつの効果が発動してMainActivityに移動する
                finish();
//                Intent intent = new Intent(CharacterselectionView.this, ConfigurationView.class);
//                startActivity(intent);
            }
        });







    }
}