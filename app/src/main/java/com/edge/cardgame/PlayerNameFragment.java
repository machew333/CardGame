package com.edge.cardgame;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;

public class PlayerNameFragment extends Fragment implements View.OnClickListener{
    public String TAG = "hepMe";
    public PlayerNameFragment() {};

    public ArrayList<EditText> editTexts = new ArrayList<EditText>();
    public LinearLayout linearLayout;
    public int playerCount;

    public ArrayList<Player> players= new ArrayList<Player>();

    Button startButton;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_player_name, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.playerNameFragmentLinLay);
        startButton = (Button) view.findViewById(R.id.startGameButton);
        startButton.setOnClickListener(this);

        return view;
    }

    public void populateUI(int playerCount) {
        this.playerCount = playerCount;

        for (int i = 1;i<playerCount+1;i++) {
            EditText editText = new EditText(getActivity());
            editText.setId(300+i);
            editText.setHint("Player" + i);
            linearLayout.addView(editText);
            editTexts.add(editText);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startGameButton:
                startGame();
                break;
        }
    }

    public void startGame() {
        nameAndCreatePlayers();
        /*
        Alright, I don't know if this the best method. But I had done something similar before so I am trying it.
        What is going on below is that the ArrayList<Players> is condensed and put in JSON or gson or whatever as
        a string and then sent to the other activity. Because it is easy to call getIntent.getExtraInt() or whatever.
        It might be better to use Parcelable or Serializable though
         */


        Gson gson = new Gson();

        String jsonPlayers = gson.toJson(players);

        Intent intent = new Intent(getActivity(),HeartsActivity.class);
        intent.putExtra("jsonPlayers",jsonPlayers);
        startActivity(intent);



    }

    private void nameAndCreatePlayers() {
        int ct=0;

        players.clear();
        for (EditText et : editTexts)  {
            ct++;
            String playerName;
            if (et.getText().length() ==0) {
                playerName = (et.getHint().toString());

            }
            else {
                playerName =(et.getText().toString());
            }
            players.add(new Player(playerName,ct));
        }


    }

}




