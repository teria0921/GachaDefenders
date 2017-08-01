package com.example.teria.gachadefenders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by teria on 2017/07/23.
 */

public class GameActivity extends Activity implements View.OnClickListener {

    Handler testHandler = new Handler();

    public TextView timerText;

    //投げつけたクリスタルの数TextView格納用グローバル変数 見やすい使い方してる
    public TextView attackNumberText;

    public int attackNumber;
    public boolean noFirstAttack = true;

    public final int ATTACK_RIGHT = 0;
    public final int ATTACK_LEFT = 1;
    public int attack_direction = ATTACK_RIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        Button countDownBtn = (Button) findViewById(R.id.countDownButton);
        countDownBtn.setOnClickListener(this);

        Button attackBtn = (Button) findViewById(R.id.attackButton);
        attackBtn.setOnClickListener(this);
        attackBtn.setEnabled(false);

        timerText = (TextView)findViewById(R.id.timer);
        timerText.setText("0:00.000");

        // オブジェクトを変数にセット
        attackNumberText = (TextView)findViewById(R.id.attackNumber);

        //ImageView imageView1 = (ImageView)findViewById(R.id.sedoricView);
        //imageView1.setImageResource(R.drawable.sedoric);

        ImageView imageView1 = (ImageView)findViewById(R.id.sedoricView);
        imageView1.setImageResource(R.drawable.sedoric);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.countDownButton:
                Button countDownBtn = (Button) findViewById(R.id.countDownButton);
                countDownBtn.setEnabled(false);
                onClickCountDownButton();

                Button attackBtn = (Button) findViewById(R.id.attackButton);
                attackBtn.setEnabled(true);

                break;

            case R.id.attackButton:
                onClickAttackButton();
                break;
        }
    }

    // 投げつけ開始ボタンの動作
    public void onClickCountDownButton(){

        //リトライ処理
        retryProccess();

        // インスタンス生成
        // CountDownTimer(long millisInFuture, long countDownInterval)
        // 3分= 3x60x1000 = 180000 msec
        final CountDown countDown = new CountDown(30000, 100);
        // カウントダウン開始
        countDown.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onClickAttackButton(){
        attackNumber+=5;
        attackNumberText.setText("投げつけたクリスタルの数：" + attackNumber);

        playFromSoundPool();



        if(attack_direction == ATTACK_RIGHT){
            ImageView imageView1 = (ImageView)findViewById(R.id.naguru_rightView);
            imageView1.setImageResource(R.drawable.naguru_dummy);

            ImageView imageView2 = (ImageView)findViewById(R.id.naguru_leftView);
            imageView2.setImageResource(R.drawable.naguru_left);

            attack_direction = ATTACK_LEFT;
        }else{
            ImageView imageView2 = (ImageView)findViewById(R.id.naguru_leftView);
            imageView2.setImageResource(R.drawable.naguru_dummy);

            ImageView imageView1 = (ImageView)findViewById(R.id.naguru_rightView);
            imageView1.setImageResource(R.drawable.naguru_right);

            attack_direction = ATTACK_RIGHT;
        }
    }

    // リトライ処理
    public void retryProccess(){
        attackNumber = 0;
        attackNumberText.setText("投げつけたクリスタルの数：" + attackNumber);
    }

    class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 完了
            timerText.setText("0:00.000");
            Button countDownBtn = (Button) findViewById(R.id.countDownButton);
            countDownBtn.setEnabled(true);

            Button attackBtn = (Button) findViewById(R.id.attackButton);
            attackBtn.setEnabled(false);



            //Toast.makeText(getApplicationContext(), "タイマー満了", Toast.LENGTH_SHORT).show();
        }

        // インターバルで呼ばれる
        @Override
        public void onTick(long millisUntilFinished) {
            // 残り時間を分、秒、ミリ秒に分割
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
            long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

            timerText.setText(String.format("%1$02d:%2$02d.%3$03d", mm, ss, ms));
        }
    }


    //打撃音再生ここから
    private SoundPool mSoundPool;
    private int mSoundId;
    @Override
    protected void onResume() {
        super.onResume();
        // 予め音声データを読み込む
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSoundId = mSoundPool.load(getApplicationContext(), R.raw.hit, 0);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // リリース
        mSoundPool.release();
    }
    private void playFromSoundPool() {
        // 再生
        mSoundPool.play(mSoundId, 1.0F, 1.0F, 0, 0, 1.0F);
    }
    //打撃音再生ここまで



}
