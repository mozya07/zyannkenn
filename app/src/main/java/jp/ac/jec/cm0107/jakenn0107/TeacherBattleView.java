package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class TeacherBattleView extends AppCompatActivity {

    private ArrayList<GameData> ary = new ArrayList<GameData>();
    private int gamedatacount;
    private int losecount;

    private SoundPool soundPool;
    private int soundOne, soundTwo, soundnaguru, soundaiuchi, soundokinnkyuu;


    final Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_battle_view);


        Intent intent = getIntent();
        String charas = intent.getStringExtra("charadatas");
        ImageView selectmymonster = (ImageView)findViewById(R.id.mymonsterView);
        if (charas.equals("ちびドラ")) {
            selectmymonster.setImageResource(R.drawable.chibidora);
        } else if (charas.equals("リュウ")) {
            selectmymonster.setImageResource(R.drawable.ryuu);
        } else if (charas.equals("リュウ酒")) {
            selectmymonster.setImageResource(R.drawable.ryuusake);
        } else if (charas.equals("ドロイドロボ")) {
            selectmymonster.setImageResource(R.drawable.kikaidoro);
        }


////////////////////
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて
                .setMaxStreams(3)
                .build();


        soundnaguru = soundPool.load(this, R.raw.pannchi, 1);

        soundaiuchi = soundPool.load(this, R.raw.aiuchi, 1);

        soundokinnkyuu = soundPool.load(this, R.raw.kinnkyuu, 1);


        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        });
        /////////////////////////////////

        ImageView hagut = (ImageView)findViewById(R.id.hagurumabtn);
        hagut.setImageResource(R.drawable.hagutt);
        ImageView mymonstertop = (ImageView)findViewById(R.id.mymonsterView);
        mymonstertop.setImageResource(R.drawable.kikaidoro);
        ImageView guk = (ImageView)findViewById(R.id.gubtn);
        guk.setImageResource(R.drawable.gut);
        ImageView chokik = (ImageView)findViewById(R.id.chokibtn);
        chokik.setImageResource(R.drawable.chokit);
        ImageView pak = (ImageView)findViewById(R.id.pabtn);
        pak.setImageResource(R.drawable.pat);
        ImageView topenemyhand = (ImageView)findViewById(R.id.enemyhandView);
        topenemyhand.setImageResource(R.drawable.enemygut);




        //　GameDataに追加するデータ
        ary.add(new GameData("ドロイド.先生var", 100, "ドロイド先生が現れた！！", 1));
        ary.add(new GameData("ドロイド.先生var", 50, "5ダメージ与えた", 2));
        ary.add(new GameData("ドロイド.先生var", 0, "5ダメージ与えた", 3));
        ary.add(new GameData("ドロイドゴン.先生var", 100, "ドロイドゴン先生が現れた！！", 4));
        ary.add(new GameData("ドロイドゴン.先生var", 67, "15ダメージ与えた", 5));
        ary.add(new GameData("ドロイドゴン.先生var", 34, "15ダメージ与えた", 6));
        ary.add(new GameData("ドロイドゴン.先生var", 0, "20ダメージ与えた", 7));
        ary.add(new GameData("ドロイドドラゴン.先生var", 100, "ドロイドドラゴン先生が現れた！！", 8));
        ary.add(new GameData("ドロイドドラゴン.先生var", 75, "25ダメージ与えた", 9));
        ary.add(new GameData("ドロイドドラゴン.先生var", 50, "25ダメージ与えた", 10));
        ary.add(new GameData("ドロイドドラゴン.先生var", 25, "25ダメージ与えた", 11));
        ary.add(new GameData("ドロイドドラゴン.先生var", 0, "25ダメージ与えた", 12));
        ary.add(new GameData("ドロイド神.先生var", 100, "ドロイド神先生が異次元から現れた", 13));
        ary.add(new GameData("ドロイド神.先生var", 80, "20ダメージ与えた", 14));
        ary.add(new GameData("ドロイド神.先生var", 60, "20ダメージ与えた", 15));
        ary.add(new GameData("ドロイド神.先生var", 40, "20ダメージ与えた", 16));
        ary.add(new GameData("ドロイド神.先生var", 20, "20ダメージ与えた", 17));
        ary.add(new GameData("ドロイド神.先生var", 0, "20ダメージ与えた", 18));



        gamedatacount = 0; //　GameDataを順々に表示するための変数
        losecount = 0;

        GameDatainformation(); //　GameDataが最初に呼ばれる

        //　敵のHP
        ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar1);
        bar.setMax(100);
        // 自分のHP
        ProgressBar bars = (ProgressBar)findViewById(R.id.progressBar2);
        bars.setMax(100);
        bars.setProgress(100);

        //　tennmetuが適用されるView
        ImageView traget = findViewById(R.id.monsterView);
        View tragetgu = findViewById(R.id.gubtn);
        View tragetchoki = findViewById(R.id.chokibtn);
        View tragetpa = findViewById(R.id.pabtn);


        //　AnimationHelperが処理された後実行される処理（点滅を止める処理）
        Runnable showMessageTask = new Runnable() {
            @Override
            public void run() {

                AnimationHelper.cancelAnimation(traget);
                AnimationHelper.cancelAnimation(tragetgu);
                AnimationHelper.cancelAnimation(tragetchoki);
                AnimationHelper.cancelAnimation(tragetpa);
            }
        };

        // NextStageの遅延処理
        Runnable showMessageTasks = new Runnable() {
            @Override
            public void run() {
                NextStage();
            }
        };

        // 緊急事態のときにstopする処理
        Runnable emergency = new Runnable() {
            @Override
            public void run() {
                ImageView monsterView = (ImageView)findViewById(R.id.monsterView);
                AnimationHelper.cancelAnimations(monsterView);
                gamedatacount++;
                losecount = 0;
                GameDatainformation();
                monsterViewChange();
                ProgressBar bars = (ProgressBar) findViewById(R.id.progressBar2);
                bars.setMax(100);
                bars.setProgress(100);
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setImageResource(R.drawable.enemygut);
                enemyhand.setEnabled(false);
                TextView myhptxt = (TextView)findViewById(R.id.mymonsterHPtxt);
                myhptxt.setText("100／100");
                ImageView gu = (ImageView)findViewById(R.id.gubtn);
                ImageView choki = (ImageView)findViewById(R.id.chokibtn);
                ImageView pa = (ImageView)findViewById(R.id.pabtn);
                gu.setEnabled(true); // 押せなくする
                choki.setEnabled(true); // 押せなくする
                pa.setEnabled(true); // 押せなくする
            }
        };

        // muhandON遅延処理
        Runnable myhandbtnONcontrol = new Runnable() {
            @Override
            public void run() {
                ImageView gu = (ImageView) findViewById(R.id.gubtn);
                ImageView choki = (ImageView) findViewById(R.id.chokibtn);
                ImageView pa = (ImageView) findViewById(R.id.pabtn);
                gu.setEnabled(true); // 初期状態は押せるようにする
                choki.setEnabled(true); // 初期状態は押せるようにする
                pa.setEnabled(true); // 初期状態は押せるようにする
            }
        };

        // muhandOFF遅延処理
        Runnable myhandbtnOFFcontrol = new Runnable() {
            @Override
            public void run() {
                ImageView gu = (ImageView) findViewById(R.id.gubtn);
                ImageView choki = (ImageView) findViewById(R.id.chokibtn);
                ImageView pa = (ImageView) findViewById(R.id.pabtn);
                gu.setEnabled(false); // 初期状態は押せなくする
                choki.setEnabled(false); // 初期状態は押せなくする
                pa.setEnabled(false); // 初期状態は押せなくする
            }
        };

        ImageView gu = (ImageView) findViewById(R.id.gubtn);
        gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationHelper.startAlphaAnimation(tragetgu);
                handler.postDelayed(showMessageTask, 125);
                handler.postDelayed(myhandbtnOFFcontrol, 1); // myhandOFF遅延処理
                handler.postDelayed(myhandbtnONcontrol, 1000); // myhandON遅延処理


                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                Random rd = new Random();
                int cpu = rd.nextInt(3);

                // CPUの画像を設定する
                // 画像のリソースdrawable id を配列で持っておく
                int[] item = {R.drawable.enemygut, R.drawable.enemychokit, R.drawable.enemypat};
                // setImageResourceメソッドは画像のリソースdrawable idを指定する
                enemyhand.setImageResource(item[cpu]);

                if (item[cpu] == R.drawable.enemygut) {
                    TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                    situationtxt.setText("相殺された");
                    soundPool.play(soundaiuchi, 1.0f, 1.0f, 0, 0, 1);
                } else if (item[cpu] == R.drawable.enemychokit) {
                    AnimationHelper.startAlphaAnimation(traget);
                    handler.postDelayed(showMessageTask, 125);
                    gamedatacount++;
                    GameDatainformation(); //　GameDataの処理を呼ぶ
                    monsterViewChange(); //　monsterViewChangeの処理を呼ぶ
                    soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    if (gamedatacount == 2 || gamedatacount == 6 || gamedatacount == 11) {
                        handler.postDelayed(showMessageTasks, 1000);
                    }
                } else if (item[cpu] == R.drawable.enemypat) {
                    handler.postDelayed(myhandbtnOFFcontrol, 1); // myhandOFF遅延処理
                    soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    lose();
                }
            }
        });

        ImageView choki = (ImageView) findViewById(R.id.chokibtn);
        choki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationHelper.startAlphaAnimation(tragetchoki);
                handler.postDelayed(showMessageTask, 125);
                handler.postDelayed(myhandbtnOFFcontrol, 1); // myhandOFF遅延処理
                handler.postDelayed(myhandbtnONcontrol, 1000); // myhandON遅延処理

                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                Random rd = new Random();
                int cpu = rd.nextInt(3);

                // CPUの画像を設定する
                // 画像のリソースdrawable id を配列で持っておく
                int[] item = {R.drawable.enemygut, R.drawable.enemychokit, R.drawable.enemypat};
                // setImageResourceメソッドは画像のリソースdrawable idを指定する
                enemyhand.setImageResource(item[cpu]);

                if (item[cpu] == R.drawable.enemychokit) {
                    TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                    situationtxt.setText("相殺された");
                    soundPool.play(soundaiuchi, 1.0f, 1.0f, 0, 0, 1);
                } else if (item[cpu] == R.drawable.enemypat) {
                    AnimationHelper.startAlphaAnimation(traget);
                    handler.postDelayed(showMessageTask, 125);
                    gamedatacount++;
                    GameDatainformation(); //　GameDataの処理を呼ぶ
                    monsterViewChange(); //　monsterViewChangeの処理を呼ぶ
                    soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    if (gamedatacount == 2 || gamedatacount == 6 || gamedatacount == 11) {
                        handler.postDelayed(showMessageTasks, 1000);
                    }
                } else if (item[cpu] == R.drawable.enemygut) {
                    handler.postDelayed(myhandbtnOFFcontrol, 1); // myhandOFF遅延処理
                    soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    lose();
                }
            }
        });

        ImageView pa = (ImageView) findViewById(R.id.pabtn);
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimationHelper.startAlphaAnimation(tragetpa);
                handler.postDelayed(showMessageTask, 125);
                handler.postDelayed(myhandbtnOFFcontrol, 1); // myhandOFF遅延処理
                handler.postDelayed(myhandbtnONcontrol, 1000); // myhandON遅延処理

                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                Random rd = new Random();
                int cpu = rd.nextInt(3);

                // CPUの画像を設定する
                // 画像のリソースdrawable id を配列で持っておく
                int[] item = {R.drawable.enemygut, R.drawable.enemychokit, R.drawable.enemypat};
                // setImageResourceメソッドは画像のリソースdrawable idを指定する
                enemyhand.setImageResource(item[cpu]);

                if (item[cpu] == R.drawable.enemypat) {
                    TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                    situationtxt.setText("相殺された");
                    soundPool.play(soundaiuchi, 1.0f, 1.0f, 0, 0, 1);
                } else if (item[cpu] == R.drawable.enemygut) {
                    AnimationHelper.startAlphaAnimation(traget);
                    handler.postDelayed(showMessageTask, 125);
                    gamedatacount++;
                    GameDatainformation(); //　GameDataの処理を呼ぶ
                    monsterViewChange(); //　monsterViewChangeの処理を呼ぶ
                    soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    if (gamedatacount == 2 || gamedatacount == 6 || gamedatacount == 11) {
                        handler.postDelayed(showMessageTasks, 1000);
                    }
                } else if (item[cpu] == R.drawable.enemychokit) {
                    handler.postDelayed(myhandbtnOFFcontrol, 1); // myhandOFF遅延処理
                    soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    lose();
                }
            }
        });

        // タップしてNextStageへ移ったときの処理
        ImageView enemyhand = (ImageView) findViewById(R.id.enemyhandView);
        enemyhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                String gameoversituationtxt = situationtxt.getText().toString();
                TextView myhptxt = (TextView)findViewById(R.id.mymonsterHPtxt);

                if (gameoversituationtxt.equals("タップすると戻ります") || gameoversituationtxt.equals("You are a great player")) {
                    Intent intent = new Intent(TeacherBattleView.this, DungeonSelectView.class);
                    startActivity(intent);
                } else if (gameoversituationtxt.equals("タップすると終了します")) {
                    TextView monstername = (TextView) findViewById(R.id.monsternametxt);
                    monstername.setText("緊急事態！？緊急事態！？緊急事態");
                    situationtxt.setText("緊急事態！？緊急事態！？緊急事態");
                    soundPool.play(soundokinnkyuu, 1.0f, 1.0f, 0, 0, 1);
                    enemyhand.setImageResource(R.drawable.sirot);
                    ImageView monsterView = (ImageView) findViewById(R.id.monsterView);
                    monsterView.setImageResource(R.drawable.extrastage);
                    AnimationHelper.startAlphaAnimationsecond(monsterView);
                    handler.postDelayed(emergency, 4300);
                } else {
                    gamedatacount++;
                    losecount = 0;
                    GameDatainformation();
                    monsterViewChange();
                    enemyhand.setImageResource(R.drawable.enemygut);
                    ProgressBar bars = (ProgressBar) findViewById(R.id.progressBar2);
                    bars.setMax(100);
                    bars.setProgress(100);
                    enemyhand.setEnabled(false);
                    myhptxt.setText("100／100");
                    gu.setEnabled(true); // 押せるようにする
                    choki.setEnabled(true); // 押せるようにする
                    pa.setEnabled(true); // 押せるようにする
                }
            }
        });
        enemyhand.setEnabled(false); // 初期状態は押せなくする

        ImageView hagu = (ImageView)findViewById(R.id.hagurumabtn);
        hagu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherBattleView.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("確認");
                builder.setMessage("リタイアして大丈夫ですか？");
                // OKクリックイベントの設定
                builder.setPositiveButton("諦める", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TeacherBattleView.this, "撤退しました", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("バトルに戻る", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TeacherBattleView.this, "バトルを再開します", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }

        });
    }



    // GameDataを取得し表示する処理（勝ったときの処理）
    private void GameDatainformation() {
        GameData temp = ary.get(gamedatacount); // 現在位置のGameDataを取得する
        TextView monsternametxt = (TextView)findViewById(R.id.monsternametxt); //　敵の名前
        monsternametxt.setText(temp.getMonsterName());
        ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar1); //　敵のHP
        bar.setMax(100);
        bar.setProgress(temp.getMonsterHP());
        TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
        situationtxt.setText(temp.getSituationtxt());

    }

    // 負けたときの処理
    private void lose() {
        losecount++;
        ProgressBar bars = (ProgressBar) findViewById(R.id.progressBar2); //　敵のHP
        bars.setMax(100);
        TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
        TextView myhptxt = (TextView)findViewById(R.id.mymonsterHPtxt);
        ImageView gu = (ImageView)findViewById(R.id.gubtn);
        ImageView choki = (ImageView)findViewById(R.id.chokibtn);
        ImageView pa = (ImageView)findViewById(R.id.pabtn);
        if (gamedatacount <= 1) {
            if (losecount == 1) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(90);
                myhptxt.setText(" 90／100");
            } else if (losecount == 2) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(80);
                myhptxt.setText(" 80／100");
            } else if (losecount == 3) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(70);
                myhptxt.setText(" 70／100");
            } else if (losecount == 4) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(60);
                myhptxt.setText(" 60／100");
            } else if (losecount == 5) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(50);
                myhptxt.setText(" 50／100");
            } else if (losecount == 6) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(40);
                myhptxt.setText(" 40／100");
            } else if (losecount == 7) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(30);
                myhptxt.setText(" 30／100");
            } else if (losecount == 8) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(20);
                myhptxt.setText(" 20／100");
            } else if (losecount == 9) {
                situationtxt.setText("10ダーメジ受けた！");
                bars.setProgress(10);
                myhptxt.setText(" 10／100");
            } else if (losecount == 10) {
                ImageView GameOverView = (ImageView)findViewById(R.id.monsterView);
                GameOverView.setImageResource(R.drawable.gameover);
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setImageResource(R.drawable.yazit);
                situationtxt.setText("タップすると戻ります");
                bars.setProgress(0);
                myhptxt.setText("  0／100");
                enemyhand.setEnabled(true);
                gu.setEnabled(false); // 押せなくする
                choki.setEnabled(false); // 押せなくする
                pa.setEnabled(false); // 押せなくする

            }
        } else if (gamedatacount <= 5) {
            if (losecount == 1) {
                situationtxt.setText("20ダーメジ受けた！");
                bars.setProgress(80);
                myhptxt.setText(" 80／100");
            } else if (losecount == 2) {
                situationtxt.setText("20ダーメジ受けた！");
                bars.setProgress(60);
                myhptxt.setText(" 60／100");
            } else if (losecount == 3) {
                situationtxt.setText("20ダーメジ受けた！");
                bars.setProgress(40);
                myhptxt.setText(" 40／100");
            } else if (losecount == 4) {
                situationtxt.setText("20ダーメジ受けた！");
                bars.setProgress(20);
                myhptxt.setText(" 20／100");
            } else if (losecount == 5) {
                ImageView GameOverView = (ImageView)findViewById(R.id.monsterView);
                GameOverView.setImageResource(R.drawable.gameover);
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setImageResource(R.drawable.yazit);
                situationtxt.setText("タップすると戻ります");
                bars.setProgress(0);
                myhptxt.setText("  0／100");
                enemyhand.setEnabled(true);
                gu.setEnabled(false); // 押せなくする
                choki.setEnabled(false); // 押せなくする
                pa.setEnabled(false); // 押せなくする
            }
        } else if (gamedatacount <= 10) {
            if (losecount == 1) {
                situationtxt.setText("33ダーメジ受けた！");
                bars.setProgress(67);
                myhptxt.setText(" 67／100");
            } else if (losecount == 2) {
                situationtxt.setText("33ダーメジ受けた！");
                bars.setProgress(34);
                myhptxt.setText(" 34／100");
            } else if (losecount == 3) {
                ImageView GameOverView = (ImageView)findViewById(R.id.monsterView);
                GameOverView.setImageResource(R.drawable.gameover);
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setImageResource(R.drawable.yazit);
                situationtxt.setText("タップすると戻ります");
                bars.setProgress(0);
                myhptxt.setText("  0／100");
                enemyhand.setEnabled(true);
                gu.setEnabled(false); // 押せなくする
                choki.setEnabled(false); // 押せなくする
                pa.setEnabled(false); // 押せなくする
            }
        } else if (gamedatacount <= 16) {
            if (losecount == 1) {
                situationtxt.setText("50ダーメジ受けた！");
                bars.setProgress(50);
                myhptxt.setText(" 50／100");
            } else if (losecount == 2) {
                ImageView GameOverView = (ImageView)findViewById(R.id.monsterView);
                GameOverView.setImageResource(R.drawable.gameover);
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setImageResource(R.drawable.yazit);
                situationtxt.setText("タップすると戻ります");
                bars.setProgress(0);
                myhptxt.setText("  0／100");
                enemyhand.setEnabled(true);
                gu.setEnabled(false); // 押せなくする
                choki.setEnabled(false); // 押せなくする
                pa.setEnabled(false); // 押せなくする
            }
        }
    }

    //　monsterViewChangeの処理
    private void monsterViewChange() {
        // モンスターの画像データ
        int[] monsterChangeData = { R.drawable.doroidosennsei, R.drawable.doroidosennsei, R.drawable.doroidosennsei,
                R.drawable.doroidogonnsennsei, R.drawable.doroidogonnsennsei, R.drawable.doroidogonnsennsei, R.drawable.doroidogonnsennsei,
                R.drawable.doroidoragonnsennsei, R.drawable.doroidoragonnsennsei, R.drawable.doroidoragonnsennsei,
                R.drawable.doroidoragonnsennsei, R.drawable.doroidoragonnsennsei,
                R.drawable.doroidosinnsennsei, R.drawable.doroidosinnsennsei, R.drawable.doroidosinnsennsei,
                R.drawable.doroidosinnsennsei, R.drawable.doroidosinnsennsei, R.drawable.doroidosinnsennsei};

        ImageView monsterView = (ImageView)findViewById(R.id.monsterView);
        monsterView.setImageResource(monsterChangeData[gamedatacount]);
    }

    // NextStageに移る前の処理
    private void NextStage() {
        TextView monstername = (TextView)findViewById(R.id.monsternametxt);
        ImageView monsterView = (ImageView) findViewById(R.id.monsterView);
        TextView situationtxt = (TextView) findViewById(R.id.situationtxt); //　状況
        ImageView enemyhand = (ImageView) findViewById(R.id.enemyhandView);
        ImageView gu = (ImageView)findViewById(R.id.gubtn);
        ImageView choki = (ImageView)findViewById(R.id.chokibtn);
        ImageView pa = (ImageView)findViewById(R.id.pabtn);

        int[] nextstagedata = {R.drawable.nextstage, R.drawable.gameclear, R.drawable.sinngameclear};
        if (gamedatacount == 2 || gamedatacount == 6) {
            monsterView.setImageResource(nextstagedata[0]);
            situationtxt.setText("タップボタンを押してください");
            enemyhand.setImageResource(R.drawable.yazit);
            enemyhand.setEnabled(true); // 押せるようにする
            gu.setEnabled(false); // 押せなくする
            choki.setEnabled(false); // 押せなくする
            pa.setEnabled(false); // 押せなくする
        } else if (gamedatacount == 11) {
            monstername.setText("ゲームクリア！！");
            monsterView.setImageResource(nextstagedata[1]);
            situationtxt.setText("タップすると終了します");
            enemyhand.setImageResource(R.drawable.yazit);
            enemyhand.setEnabled(true); // 押せるようにする
            gu.setEnabled(false); // 押せなくする
            choki.setEnabled(false); // 押せなくする
            pa.setEnabled(false); // 押せなくする
        } else if (gamedatacount == 17) {
            monstername.setText("ゲームオールクリア！！");
            monsterView.setImageResource(nextstagedata[2]);
            situationtxt.setText("You are a great player");
            enemyhand.setImageResource(R.drawable.yazit);
            enemyhand.setEnabled(true); // 押せるようにする
            gu.setEnabled(false); // 押せなくする
            choki.setEnabled(false); // 押せなくする
            pa.setEnabled(false); // 押せなくする

        }
    }
}