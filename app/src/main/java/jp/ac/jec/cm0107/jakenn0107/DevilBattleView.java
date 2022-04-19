package jp.ac.jec.cm0107.jakenn0107;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Image;
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

public class DevilBattleView extends AppCompatActivity {

    private ArrayList<GameData> ary = new ArrayList<GameData>();
    private int gamedatacount;
    private int losecount;

    private SoundPool soundPool;
    private int soundOne, soundTwo, soundnaguru, soundaiuchi;

    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_view);

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
                .setMaxStreams(4)
                .build();

        // one.wav をロードしておく
        soundOne = soundPool.load(this, R.raw.one, 1);

        // two.wav をロードしておく
        soundTwo = soundPool.load(this, R.raw.two, 1);


        soundnaguru = soundPool.load(this, R.raw.kikainaguru, 1);

        soundaiuchi = soundPool.load(this, R.raw.aiuchi, 1);


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
        ImageView monstertop = (ImageView)findViewById(R.id.monsterView);
        monstertop.setImageResource(R.drawable.mdevil);
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
        ary.add(new GameData("[機械魔神]アポカリプス", 100, "お前らに終焉を与えよう・・・", 1));
        ary.add(new GameData("[機械魔神]アポカリプス", 100, "[機械魔神]アポカリプスが降臨した", 2));
        ary.add(new GameData("[機械魔神]アポカリプス", 90, "10ダメージ与えた", 3));
        ary.add(new GameData("[機械魔神]アポカリプス", 80, "10ダメージ与えた", 4));
        ary.add(new GameData("[機械魔神]アポカリプス", 70, "10ダメージ与えた", 5));
        ary.add(new GameData("[機械魔神]アポカリプス", 60, "10ダメージ与えた", 6));
        ary.add(new GameData("[機械魔神]アポカリプス", 50, "10ダメージ与えた", 7));
        ary.add(new GameData("[機械魔神]アポカリプス", 40, "10ダメージ与えた", 8));
        ary.add(new GameData("[機械魔神]アポカリプス", 30, "10ダメージ与えた", 9));
        ary.add(new GameData("[機械魔神]アポカリプス", 20, "10ダメージ与えた", 10));
        ary.add(new GameData("[機械魔神]アポカリプス", 10, "10ダメージ与えた", 11));
        ary.add(new GameData("[機械魔神]アポカリプス", 0, "100ダメージ与えた", 12));

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
        View tragetmymonster = findViewById(R.id.mymonsterView);


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

        Runnable apokourinn = new Runnable() {
            @Override
            public void run() {
                ImageView monster = (ImageView)findViewById(R.id.monsterView);
                monster.setImageResource(R.drawable.kdevil);
                gamedatacount++;
                GameDatainformation(); //　GameDataの処理を呼ぶ
            }
        };
        handler.postDelayed(apokourinn, 3500); // クリエイトされてから3秒後に呼ぶ

        Runnable enemydes = new Runnable() {
            @Override
            public void run() {
                ImageView monster = (ImageView)findViewById(R.id.monsterView);
                monster.setImageResource(R.drawable.kdevildes);
                gamedatacount++;
                GameDatainformation(); //　GameDataの処理を呼ぶ
            }
        };

        Runnable mazyodes = new Runnable() {
            @Override
            public void run() {
                ImageView monster = (ImageView)findViewById(R.id.monsterView);
                monster.setImageResource(R.drawable.devildes);
                TextView mazyosay = (TextView)findViewById(R.id.situationtxt);
                mazyosay.setText("来世でまた会いましょ");
            }
        };


        Runnable win = new Runnable() {
            @Override
            public void run() {
                ImageView monster = (ImageView)findViewById(R.id.monsterView);
                monster.setImageResource(R.drawable.sinngameclear);
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setEnabled(true);
                enemyhand.setImageResource(R.drawable.yazit);
                TextView wintxt = (TextView)findViewById(R.id.situationtxt);
                wintxt.setText("You are a great player");
                AnimationHelper.cancelAnimation(tragetmymonster);
            }
        };

        Runnable enemyhandon = new Runnable() {
            @Override
            public void run() {
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                enemyhand.setEnabled(true);
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




        ImageView gu = (ImageView)findViewById(R.id.gubtn);
        gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                Random rd = new Random();
                int cpu = rd.nextInt(3);

                // CPUの画像を設定する
                // 画像のリソースdrawable id を配列で持っておく
                int[] item = {R.drawable.enemygut, R.drawable.enemychokit, R.drawable.enemypat};
                // setImageResourceメソッドは画像のリソースdrawable idを指定する
                enemyhand.setImageResource(item[cpu]);
                handler.postDelayed(myhandbtnOFFcontrol, 1);

                if (item[cpu] == R.drawable.enemygut) {
                    TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                    situationtxt.setText("相殺された");
                    soundPool.play(soundaiuchi, 1.0f, 1.0f, 0, 0, 1);
                    handler.postDelayed(myhandbtnONcontrol, 500);
                } else if (item[cpu] == R.drawable.enemychokit) {
                    AnimationHelper.startAlphaAnimation(traget);
                    handler.postDelayed(showMessageTask, 125);
                    if (gamedatacount == 10) {
                        TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                        situationtxt.setText("最後の一撃だ！！");
                        enemyhand.setEnabled(true);
                        enemyhand.setImageResource(R.drawable.obadorabtnt);
                        handler.postDelayed(myhandbtnOFFcontrol, 1);
                    } else {
                        gamedatacount++;
                        GameDatainformation(); //　GameDataの処理を呼ぶ
                        handler.postDelayed(myhandbtnONcontrol, 500);
                        soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    }
                } else if (item[cpu] == R.drawable.enemypat) {
                    losecount++;
                    lose();
                    handler.postDelayed(myhandbtnONcontrol, 500);
                    if (losecount == 10) {
                        ImageView gu = (ImageView)findViewById(R.id.gubtn);
                        ImageView choki = (ImageView)findViewById(R.id.chokibtn);
                        ImageView pa = (ImageView)findViewById(R.id.pabtn);
                        gu.setEnabled(false); // 初期状態は押せなくする
                        choki.setEnabled(false); // 初期状態は押せなくする
                        pa.setEnabled(false); // 初期状態は押せなくする
                    }
                }
            }
        });

        ImageView choki = (ImageView)findViewById(R.id.chokibtn);
        choki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                Random rd = new Random();
                int cpu = rd.nextInt(3);

                // CPUの画像を設定する
                // 画像のリソースdrawable id を配列で持っておく
                int[] item = {R.drawable.enemygut, R.drawable.enemychokit, R.drawable.enemypat};
                // setImageResourceメソッドは画像のリソースdrawable idを指定する
                enemyhand.setImageResource(item[cpu]);
                handler.postDelayed(myhandbtnOFFcontrol, 1);

                if (item[cpu] == R.drawable.enemychokit) {
                    TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                    situationtxt.setText("相殺された");
                    soundPool.play(soundaiuchi, 1.0f, 1.0f, 0, 0, 1);
                    handler.postDelayed(myhandbtnONcontrol, 500);
                } else if (item[cpu] == R.drawable.enemypat) {
                    AnimationHelper.startAlphaAnimation(traget);
                    handler.postDelayed(showMessageTask, 125);
                    if (gamedatacount == 10) {
                        TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                        situationtxt.setText("最後の一撃だ！！");
                        enemyhand.setEnabled(true);
                        enemyhand.setImageResource(R.drawable.obadorabtnt);
                        handler.postDelayed(myhandbtnOFFcontrol, 1);
                    } else {
                        gamedatacount++;
                        GameDatainformation(); //　GameDataの処理を呼ぶ
                        handler.postDelayed(myhandbtnONcontrol, 500);
                        soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    }
                } else if (item[cpu] == R.drawable.enemygut) {
                    losecount++;
                    lose();
                    handler.postDelayed(myhandbtnONcontrol, 500);
                    if (losecount == 10) {
                        ImageView gu = (ImageView)findViewById(R.id.gubtn);
                        ImageView choki = (ImageView)findViewById(R.id.chokibtn);
                        ImageView pa = (ImageView)findViewById(R.id.pabtn);
                        gu.setEnabled(false); // 初期状態は押せなくする
                        choki.setEnabled(false); // 初期状態は押せなくする
                        pa.setEnabled(false); // 初期状態は押せなくする
                    }
                }
            }
        });

        ImageView pa = (ImageView)findViewById(R.id.pabtn);
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
                Random rd = new Random();
                int cpu = rd.nextInt(3);

                // CPUの画像を設定する
                // 画像のリソースdrawable id を配列で持っておく
                int[] item = {R.drawable.enemygut, R.drawable.enemychokit, R.drawable.enemypat};
                // setImageResourceメソッドは画像のリソースdrawable idを指定する
                enemyhand.setImageResource(item[cpu]);
                handler.postDelayed(myhandbtnOFFcontrol, 1);

                if (item[cpu] == R.drawable.enemypat) {
                    TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                    situationtxt.setText("相殺された");
                    soundPool.play(soundaiuchi, 1.0f, 1.0f, 0, 0, 1);
                    handler.postDelayed(myhandbtnONcontrol, 500);
                } else if (item[cpu] == R.drawable.enemygut) {
                    AnimationHelper.startAlphaAnimation(traget);
                    handler.postDelayed(showMessageTask, 125);
                    if (gamedatacount == 10) {
                        TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                        situationtxt.setText("最後の一撃だ！！");
                        enemyhand.setEnabled(true);
                        enemyhand.setImageResource(R.drawable.obadorabtnt);
                        handler.postDelayed(myhandbtnOFFcontrol, 1);
                    } else {
                        gamedatacount++;
                        GameDatainformation(); //　GameDataの処理を呼ぶ
                        handler.postDelayed(myhandbtnONcontrol, 500);
                        soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
                    }
                } else if (item[cpu] == R.drawable.enemychokit) {
                    losecount++;
                    lose();
                    handler.postDelayed(myhandbtnONcontrol, 500);
                    if (losecount == 10) {
                        ImageView gu = (ImageView)findViewById(R.id.gubtn);
                        ImageView choki = (ImageView)findViewById(R.id.chokibtn);
                        ImageView pa = (ImageView)findViewById(R.id.pabtn);
                        gu.setEnabled(false); // 初期状態は押せなくする
                        choki.setEnabled(false); // 初期状態は押せなくする
                        pa.setEnabled(false); // 初期状態は押せなくする
                    }
                }

            }
        });

        ImageView enemyhand = (ImageView) findViewById(R.id.enemyhandView);
        enemyhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
                String gameoversituationtxt = situationtxt.getText().toString();

                if (gameoversituationtxt.equals("タップすると戻ります") || gameoversituationtxt.equals("You are a great player")) {
                    Intent intent = new Intent(DevilBattleView.this, DungeonSelectView.class);
                    startActivity(intent);
                } else if (gamedatacount == 10 && gameoversituationtxt.equals("最後の一撃だ！！")) {
                    ImageView mymonster = (ImageView)findViewById(R.id.mymonsterView);
                    mymonster.setImageResource(R.drawable.kikaidoroobadoraibu);
                    AnimationHelper.startAlphaAnimationthired(tragetmymonster);
                    situationtxt.setText("会心の一撃を決めろ！！");
                    soundPool.play(soundTwo, 1.0f, 1.0f, 1, 0, 1);
                    enemyhand.setEnabled(false);
                    handler.postDelayed(enemyhandon, 3000);
                    enemyhand.setImageResource(R.drawable.kaisinnt);
                } else if (gamedatacount == 10 && gameoversituationtxt.equals("会心の一撃を決めろ！！")){
                    ImageView mymonster = (ImageView)findViewById(R.id.monsterView);
                    mymonster.setImageResource(R.drawable.mikatarenngeki);
                    soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
                    enemyhand.setEnabled(false);
                    handler.postDelayed(enemydes, 3000);
                    handler.postDelayed(mazyodes, 6000);
                    handler.postDelayed(win,9000);

                }
            }
        });
        enemyhand.setEnabled(false); // 初期状態は押せなくする
        handler.postDelayed(myhandbtnOFFcontrol, 1);
        handler.postDelayed(myhandbtnONcontrol, 4000);


        ImageView hagu = (ImageView)findViewById(R.id.hagurumabtn);
        hagu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DevilBattleView.this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("確認");
                builder.setMessage("リタイアして大丈夫ですか？");
                // OKクリックイベントの設定
                builder.setPositiveButton("諦める", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DevilBattleView.this, "撤退しました", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("バトルに戻る", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DevilBattleView.this, "バトルを再開します", Toast.LENGTH_SHORT).show();
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

    public Runnable lose = new Runnable() {
        @Override
        public void run() {
            ImageView monster = (ImageView)findViewById(R.id.monsterView);
            monster.setImageResource(R.drawable.devilkakusei);
            TextView losetxt = (TextView)findViewById(R.id.situationtxt);
            losetxt.setText("地球ごと滅ぼさせてもらうわね");
        }
    };

    public Runnable losesecond = new Runnable() {
        @Override
        public void run() {
            ImageView monster = (ImageView)findViewById(R.id.monsterView);
            monster.setImageResource(R.drawable.tekirenngeki);
            soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
            TextView losetxt = (TextView)findViewById(R.id.situationtxt);
            losetxt.setText("100ダメージ受けた！");
            ProgressBar bars = (ProgressBar) findViewById(R.id.progressBar2); //　敵のHP
            bars.setMax(100);
            bars.setProgress(0);
            TextView myhptxt = (TextView)findViewById(R.id.mymonsterHPtxt);
            myhptxt.setText("  0／100");
        }
    };

    public Runnable losethird = new Runnable() {
        @Override
        public void run() {
            ImageView monster = (ImageView)findViewById(R.id.monsterView);
            monster.setImageResource(R.drawable.gameover);
            TextView losetxt = (TextView)findViewById(R.id.situationtxt);
            losetxt.setText("タップすると戻ります");
            AnimationHelper.cancelAnimation(monster);
            ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
            enemyhand.setImageResource(R.drawable.yazit);
            enemyhand.setEnabled(true);
        }
    };

    private void lose() {
        ProgressBar bars = (ProgressBar) findViewById(R.id.progressBar2); //　敵のHP
        bars.setMax(100);
        TextView situationtxt = (TextView)findViewById(R.id.situationtxt); //　状況
        TextView myhptxt = (TextView)findViewById(R.id.mymonsterHPtxt);

        if (losecount == 1) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(90);
            myhptxt.setText(" 90／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 2) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(80);
            myhptxt.setText(" 80／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 3) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(70);
            myhptxt.setText(" 70／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 4) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(60);
            myhptxt.setText(" 60／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 5) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(50);
            myhptxt.setText(" 50／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 6) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(40);
            myhptxt.setText(" 40／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 7) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(30);
            myhptxt.setText(" 30／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 8) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(20);
            myhptxt.setText(" 20／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 9) {
            situationtxt.setText("10ダーメジ受けた！");
            bars.setProgress(10);
            myhptxt.setText(" 10／100");
            soundPool.play(soundnaguru, 1.0f, 1.0f, 0, 0, 1);
        } else if (losecount == 10) {
//            ImageView GameOverView = (ImageView)findViewById(R.id.monsterView);
//            GameOverView.setImageResource(R.drawable.gameover);
//            ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
//            enemyhand.setImageResource(R.drawable.yazi);
//            situationtxt.setText("タップすると戻ります");
//            bars.setProgress(0);
//            myhptxt.setText("  0／100");
//            enemyhand.setEnabled(true);
//            ImageView gu = (ImageView)findViewById(R.id.gubtn);
//            ImageView choki = (ImageView)findViewById(R.id.chokibtn);
//            ImageView pa = (ImageView)findViewById(R.id.pabtn);
//            gu.setEnabled(false); // 初期状態は押せなくする
//            choki.setEnabled(false); // 初期状態は押せなくする
//            pa.setEnabled(false); // 初期状態は押せなくする
            ImageView GameOverView = (ImageView)findViewById(R.id.monsterView);
            GameOverView.setImageResource(R.drawable.kdevilkakuseijpeg);
            situationtxt.setText("敵がオーバードライブした");
            soundPool.play(soundTwo, 1.0f, 1.0f, 1, 0, 1);
            ImageView enemyhand = (ImageView)findViewById(R.id.enemyhandView);
            enemyhand.setImageResource(R.drawable.sirot);
            AnimationHelper.startAlphaAnimationthired(GameOverView);
            enemyhand.setEnabled(false); // いらないかも
            ImageView gu = (ImageView)findViewById(R.id.gubtn);
            ImageView choki = (ImageView)findViewById(R.id.chokibtn);
            ImageView pa = (ImageView)findViewById(R.id.pabtn);
            gu.setEnabled(false); // 初期状態は押せなくする
            choki.setEnabled(false); // 初期状態は押せなくする
            pa.setEnabled(false); // 初期状態は押せなくする
            handler.postDelayed(lose, 3000);
            handler.postDelayed(losesecond, 6000);
            handler.postDelayed(losethird, 9000);



        }
    }

}