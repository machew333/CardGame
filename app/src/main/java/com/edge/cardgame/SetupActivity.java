package com.edge.cardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SetupActivity extends Activity implements View.OnClickListener {

    Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setup);





        startGame =(Button) findViewById(R.id.startGameButton);
        startGame.setOnClickListener(this);

    }

    public void startGame() {
        Intent intent = new Intent(this,HeartsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startGameButton:
                Intent intent = new Intent(this,HeartsActivity.class);
                startActivity(intent);
        }
    }
}
