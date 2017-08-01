package com.example.teria.gachadefenders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button goMainButton = (Button) findViewById(R.id.GoMainButton);
        goMainButton.setOnClickListener(this);
    }


    public void onClick(View v){
        switch(v.getId()){
            case R.id.GoMainButton:
                onClickGoMainButton();
                break;
        }
    }

    private void onClickGoMainButton(){
        Intent intent = new Intent(getApplication(), GameActivity.class);
        startActivity(intent);
    }
}
