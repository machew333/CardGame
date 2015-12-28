package com.edge.cardgame;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SetupActivity extends Activity {

    Button startButton;
    public String TAG = "hepMe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setup);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new SetPlayerCountFragment())
                .commit();

    }
    public void namePlayers(int playerCount) {
        FragmentManager fragmentManager= getFragmentManager();
        String PNF_TAG = "playerCount";

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new PlayerNameFragment(),PNF_TAG)
                .addToBackStack(null)
                .commit();



        fragmentManager = getFragmentManager();
        fragmentManager.executePendingTransactions();
        PlayerNameFragment pnf = (PlayerNameFragment) fragmentManager.findFragmentByTag(PNF_TAG);

        pnf.populateUI(playerCount);

    }

}
