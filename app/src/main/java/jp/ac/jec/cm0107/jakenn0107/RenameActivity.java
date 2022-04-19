package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RenameActivity extends AppCompatActivity {

    public static final int CODE_SETTING = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename);

        Intent intent = getIntent();
        String text = intent.getStringExtra("username");
        EditText namechendata = findViewById(R.id.namechenedt);
        namechendata.setText(text);


        Button yesrenamebtn = (Button) findViewById(R.id.OKbtn);
        yesrenamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userdata = namechendata.getText().toString();


                Intent intent = getIntent();
                // 次の画面はこれだよー
                intent.putExtra("userrename", userdata);
                // dataにedtsの内容が入っている (ここでデータを格納している)
                setResult(RESULT_OK, intent);
                // どこの画面に行くの？となり、MainActivityで書いたあいつの効果が発動してMainActivityに移動する
                Toast.makeText(RenameActivity.this, "名前を保存しました", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        Button norenamebtn = (Button) findViewById(R.id.Canselbtn);
        norenamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}