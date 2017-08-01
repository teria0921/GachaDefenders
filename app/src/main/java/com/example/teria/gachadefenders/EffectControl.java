package com.example.teria.gachadefenders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by teria on 2017/07/24.
 */

public class EffectControl extends AppCompatActivity {


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
    public void playFromSoundPool() {
        // 再生
        mSoundPool.play(mSoundId, 1.0F, 1.0F, 0, 0, 1.0F);
    }



}
