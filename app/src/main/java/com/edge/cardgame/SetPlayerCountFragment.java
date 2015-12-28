package com.edge.cardgame;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SetPlayerCountFragment extends Fragment implements View.OnClickListener {
    public String TAG = "hepMe";
    View view;
    Button player3,player4,player5,player6;


    public SetPlayerCountFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_set_player, container, false);

        player3 = (Button) view.findViewById(R.id.button3Players);
        player4 = (Button) view.findViewById(R.id.button4Players);
        player5 = (Button) view.findViewById(R.id.button5Players);
        player6 = (Button) view.findViewById(R.id.button6Players);
        player3.setOnClickListener(this);
        player4.setOnClickListener(this);
        player5.setOnClickListener(this);
        player6.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        int playerCount =0;
        switch (v.getId()) {
            case R.id.button3Players:
                playerCount=3;
                break;
            case R.id.button4Players:
                playerCount=4;
                break;
            case R.id.button5Players:
                playerCount=5;
                break;
            case R.id.button6Players:
                playerCount=6;
                break;
        }
        sendOn(playerCount);
    }

    public void sendOn(int playerCount) {
        ((SetupActivity)getActivity()).namePlayers(playerCount);

    }
}



