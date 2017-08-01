package com.example.teria.gachadefenders;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by teria on 2017/07/23.
 */


/*
public class CountDown extends CountDownTimer {

    private TextView timerText;


    public CountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        // 完了
        timerText.setText("0:00.000");
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
*/