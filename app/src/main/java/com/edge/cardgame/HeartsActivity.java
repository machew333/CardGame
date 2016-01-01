package com.edge.cardgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeartsActivity extends Activity {

    protected String TAG = "hepMe";

    public GameState currentGameState = new GameState();

    TableView tableView;
    public Map<String,Bitmap> bitmapMap = new HashMap<String,Bitmap>();


    Deck heartsDeck;
    LinearLayout linLay;
    Player john;
    Player elroy;
    Player jeff;
    Player matt;

    ArrayList<Player> players = new ArrayList<Player>();
    PlayerQueue playerQueue = new PlayerQueue(players);

    int playerCount = 0;

    int screenHeight, screenWidth, cardWidth, cardHeight;
    int middleX, middleY;
    int halfCardWidth,halfCardHeight;
    int textSize;
    double cardOverlapFraction;
    public Rect placeCardRect;
    public Region placeCardRegion = new Region();

    int touchedX,touchedY;

    Bitmap D2Bitmap, D3Bitmap, D4Bitmap, D5Bitmap, D6Bitmap, D7Bitmap, D8Bitmap, D9Bitmap, D10Bitmap, DJBitmap, DQBitmap, DKBitmap, DABitmap;
    Bitmap C2Bitmap, C3Bitmap, C4Bitmap, C5Bitmap, C6Bitmap, C7Bitmap, C8Bitmap, C9Bitmap, C10Bitmap, CJBitmap, CQBitmap, CKBitmap, CABitmap;
    Bitmap S2Bitmap, S3Bitmap, S4Bitmap, S5Bitmap, S6Bitmap, S7Bitmap, S8Bitmap, S9Bitmap, S10Bitmap, SJBitmap, SQBitmap, SKBitmap, SABitmap;
    Bitmap H2Bitmap, H3Bitmap, H4Bitmap, H5Bitmap, H6Bitmap, H7Bitmap, H8Bitmap, H9Bitmap, H10Bitmap, HJBitmap, HQBitmap, HKBitmap, HABitmap;
    Bitmap cardBackBitmap;

    double fractionOfScreenWithCards = .95;
    double startingPlaceOfCards = (1-fractionOfScreenWithCards)/2;

    Player currentPlayer;

    HeartsTrick currentTrick;
    boolean heartsIsBroken = false;
    boolean cardSelected = false;
    boolean deckIsFinished = false;
    Card currentCard;

    public Rect otPlayer1Rect,otPlayer2Rect,otPlayer3Rect, otPlayer4Rect, otPlayer5Rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        pickUpPlayers();
        //alternatePickUpPlayers();


        initializeNewDeck();

        configureDisplay();
        tableView = new TableView(this);
        setContentView(tableView);
    }

    public void createDeck() {
        heartsDeck = new Deck();
        heartsDeck.setPlayers(players);
    }

    public void initializeNewDeck() {
        createDeck();
        heartsDeck.shuffle();
        heartsDeck.dealOutAll();
        ownCards();
        currentPlayer = heartsDeck.getStartPlayer();
        deckIsFinished =false;
        HeartsTrick.resetTrickCount();
        startNewHeartsTrick();

    }

    public void pickUpPlayers() {
        //Sent from setup activity
        String jsonPlayers;

        jsonPlayers = getIntent().getStringExtra("jsonPlayers");

        Gson gson = new Gson();
        ArrayList<Player> tempPlayers = gson.fromJson(jsonPlayers, new TypeToken<ArrayList<Player>>() {
        }.getType());

        //I had to instantiate it this way for some reason.

        for (Player player: tempPlayers) {
            players.add(player);
        }
        playerCount = players.size();
    }

    public void alternatePickUpPlayers() {
        matt = new Player("matt",1);
        john = new Player("john",2);
        jeff = new Player("jeff",3);
        elroy = new Player("elroy",4);

        players.add(matt);
        players.add(john);
        players.add(jeff);
        players.add(elroy);

        playerCount = players.size();
    }

    public void startNewHeartsTrick() {
        currentTrick = new HeartsTrick(players);
        if (HeartsTrick.trickCount>1) {
            currentPlayer =currentTrick.whoGoesFirst();
        }
    }

    public void ownCards() {
        for (Player player: players) {
            //player.ownHand();
        }
    }

    public void displayDialog() {


    }



    class TableView extends View {
        public Paint cardPaint, tintPaint,placeCardPaint,highlighPaint, textPaint;

        public int trickIsOver = 0;



        public TableView(Context context) {
            super(context);


            cardPaint = new Paint(Paint.DITHER_FLAG);
            tintPaint = new Paint();
            tintPaint.setColor(0x33000000);
            placeCardPaint = new Paint();
            placeCardPaint.setColor(0x992196F3);
            highlighPaint = new Paint();
            highlighPaint.setColor(0x33EEFF41);
            textPaint = new Paint();
            textPaint.setColor(0xFF000000);
            textPaint.setTextSize(textSize);


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(0xFF4CAF50); //Green

            if (deckIsFinished) {
                drawDeckIsFinished(canvas);
            }

            else if (trickIsOver>0) {
                drawTrickIsOver(canvas);
            }
            else {
                drawNormalGameplay(canvas);
            }
        }

        public void drawDeckIsFinished(Canvas canvas)  {
            canvas.drawColor(0xFF4CAF50); //Green
            drawPlayerScores(canvas);
            displayDialog();
            delayDisplay(1000);
        }

        public void drawTrickIsOver(Canvas canvas) {
            drawPlayerHand(canvas);
            drawCurrentTrick(canvas);
            displayTrickWinner(canvas);
            if (trickIsOver ==1) {

                startNewHeartsTrick();

                delayDisplay(1000);
                trickIsOver--;
            }
            trickIsOver--;
            invalidate();
        }

        public void drawNormalGameplay(Canvas canvas) {
            canvas.drawColor(0xFF4CAF50); //Green
            drawCurrentTrick(canvas);
            drawPlayerHand(canvas);
            displayCurrentPlayerName(canvas);
            drawOtherPlayerHands(canvas);


            if (cardSelected) {
                canvas.drawRect(placeCardRect, placeCardPaint);
            }

        }






        public boolean onTouchEvent(MotionEvent event) {
            touchedX = (int) event.getRawX();
            touchedY = (int) event.getRawY();

            findCurrentCardClicked();

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN: {
                    findCurrentCardClicked();
                    if (deckIsFinished) {
                        initializeNewDeck();
                        Log.d(TAG, "Name = " + currentPlayer.name + " hand = " + currentPlayer.hand);
                    }

                    break;
                }
            }
            return true;
        }

        public void findCurrentCardClicked() {

            if (cardSelected==true && placeCardRegion.contains(touchedX,touchedY)) {
                placeCard();
            }
            else {
                cardSelected=false;
            }
            for (Card card: currentPlayer.hand) {
                if (card.region.contains(touchedX,touchedY) && card.isClickable) {
                    currentCard = card;
                    cardSelected = true;
                }
            }

            invalidate();
        }

        public void placeCard() {
            int index = currentPlayer.hand.indexOf(currentCard);

            //Sometimes I was getting invalid index because the user clicks for longer than a few milliseconds.
            if (index !=-1){

                Card cardWeWant;
                cardWeWant = currentPlayer.hand.remove(index);
                cardWeWant.setOwner(currentPlayer.name);
                if (cardWeWant.suitValue ==2) {
                    heartsIsBroken = true;
                }

                currentTrick.playCard(cardWeWant);
                cardSelected = false;
                if (currentTrick.playedCards.size() >playerCount-1) {
                    trickIsOver=2;
                }
                else {
                    selectNextPlayer();
                }

            }

        }

        public void selectNextPlayer() {
            currentPlayer = playerQueue.getNextPlayer(currentPlayer);
        }


        public void drawCurrentTrick(Canvas canvas) {

            int xCoordinate = (int) (middleX - ((playerCount)*cardOverlapFraction));
            int yCoordinate = middleY- halfCardHeight;

            for (Card card : currentTrick.playedCards) {

                xCoordinate = (int) (xCoordinate + cardOverlapFraction);

                Bitmap cardBitmap = card.findBitmap(bitmapMap);
                canvas.drawBitmap(cardBitmap, xCoordinate, yCoordinate, cardPaint);

            }
        }

        public void displayCurrentPlayerName(Canvas canvas) {
            String name= currentPlayer.name;

            canvas.drawText(name, 10, screenHeight/4, textPaint);

        }

        public void displayTrickWinner(Canvas canvas) {
            String winner = HeartsTrick.playerThatWonLastTrick.name;
            canvas.drawText(winner + " wins the trick",10,middleY-cardHeight, textPaint);

        }

        public void delayDisplay(int delayTime) {
            try {
                Thread.sleep(delayTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        public void drawPlayerHand(Canvas canvas) {
            ArrayList<Card> possibleMoves = getPossibleMoves();


            int xCoordinate = (int) (startingPlaceOfCards*screenWidth - cardOverlapFraction);

            for (Card card: currentPlayer.hand) {

                boolean tint = true;
                int yCoordinate = (int) (screenHeight-(cardHeight));

                if (possibleMoves.contains(card)) {
                    yCoordinate = (int) (yCoordinate-(cardHeight/4.0));
                    tint =false;

                }

                xCoordinate = (int) (xCoordinate + cardOverlapFraction);

                Rect tempRect = new Rect(xCoordinate,yCoordinate,xCoordinate+cardWidth,yCoordinate+cardHeight);



                Bitmap cardBitmap = card.findBitmap(bitmapMap);
                canvas.drawBitmap(cardBitmap, xCoordinate, yCoordinate, cardPaint);

                if (tint) {
                    card.setIsClickable(false);
                    canvas.drawRect(tempRect,tintPaint);
                }
                else {
                    card.setIsClickable(true);
                    card.setPostion(tempRect);

                    if (card.equals(currentCard) && cardSelected) {
                        canvas.drawRect(tempRect,highlighPaint);
                    }
                }





            }



        }

        public void drawOtherPlayerHands(Canvas canvas) {

            findWhereOtherPlayersAreSitting();

            canvas.drawBitmap(cardBackBitmap, otPlayer1Rect.left, otPlayer1Rect.top, cardPaint);

            if (playerCount>2) {
                canvas.drawBitmap(cardBackBitmap,otPlayer2Rect.left,otPlayer2Rect.top,cardPaint);
            }
            if (playerCount>3) {
                canvas.drawBitmap(cardBackBitmap,otPlayer3Rect.left,otPlayer3Rect.top,cardPaint);
            }
            if (playerCount>4) {
                canvas.drawBitmap(cardBackBitmap,otPlayer4Rect.left,otPlayer4Rect.top,cardPaint);
            }
            if (playerCount>5) {
                canvas.drawBitmap(cardBackBitmap, otPlayer5Rect.left, otPlayer5Rect.top, cardPaint);
            }


        }

        public void findWhereOtherPlayersAreSitting() {


            ArrayList<Player> tempPlayers = playerQueue.reorderQueue(currentPlayer);

            players = (ArrayList<Player>) tempPlayers.clone();

        }

        public ArrayList<Card> getPossibleMoves() {
            ArrayList<Card> possibleMoves = new ArrayList<Card>();


            if (currentTrick.playedCards.isEmpty()) {

                if (HeartsTrick.trickCount ==1) {

                    for (Card card: currentPlayer.hand) {

                        if (card.title.equals("twoOfClubs")) {
                            possibleMoves.clear();
                            possibleMoves.add(card);
                            break;
                        }
                        //Can't play value cards
                        if (card.getScore() ==0) {
                            possibleMoves.add(card);
                        }
                    }

                }
                else {
                    for (Card c: currentPlayer.hand) {
                        if (c.suitValue ==2) {
                            if (heartsIsBroken) {
                                possibleMoves.add(c);
                            }
                        }
                        else {
                            possibleMoves.add(c);
                        }
                    }
                }
            }
            else {

                for (Card card: currentPlayer.hand) {
                    if (card.suitValue == currentTrick.mainSuitValue ) {
                        possibleMoves.add(card);
                    }
                }
            }

            if (possibleMoves.isEmpty()) {
                possibleMoves = currentPlayer.hand;
            }
            return possibleMoves;
        }


        public void drawPlayerScores(Canvas canvas) {
            int xCoordinate = 10;
            int yCoordinate =10;
            int lowerYCoordinate = middleY;
            for (Player player: players) {
                player.getRoundScore();


                yCoordinate+=textSize;
                lowerYCoordinate +=textSize;

                canvas.drawText(player.name+ " score == "+player.roundScore,xCoordinate,yCoordinate, textPaint);
                player.addRoundToTotalScore();
                canvas.drawText(player.name + " total score = " + player.totalScore, xCoordinate,lowerYCoordinate, textPaint);

                if (player.totalScore >=100) {
                    //endGame();
                }
            }
            canvas.drawText("Tap to continue",xCoordinate,screenHeight-cardHeight, textPaint);



        }

        public void checkDebugInfo() {
            Log.d(TAG,"current Player = "+currentPlayer.name);
            for (Card card: currentPlayer.cardsWon) {
                Log.d(TAG,"Cards Won = "+card.title);
            }

            Log.d(TAG, "Current Trick = " + HeartsTrick.trickCount);
            Log.d(TAG,"Trick Player Cards = "+currentTrick.playedCards);
            Log.d(TAG, "Number of players = " + playerCount);


        }

        public void startNewHeartsTrick() {
            //If the trick is over
            if (currentTrick.playedCards.size() >playerCount-1) {

                if (isLastTrick()) {
                    deckIsFinished = true;
                    createDeck();
                    invalidate();
                }

                if (!deckIsFinished) {
                    drainPlayerCards();
                }

                currentTrick = new HeartsTrick(players);
                currentPlayer = HeartsTrick.playerThatWonLastTrick;
                return;
            }
        }

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Card Game")
                .setMessage("End this game?")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //do more to end this game safely
                        finish();


                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }



    public void configureDisplay() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        textSize = screenWidth/10;

        middleX = screenWidth/2;
        middleY= screenHeight/2;


        double cardDisplayRatio = 726 / 500.0;



        double sectionOfScreenWithCards = screenWidth*fractionOfScreenWithCards;


        //x = (6L/(n+7))    Equation for width of a card based on number of cards and size of screen being used.  w/ a 1/6 card overlap

        cardWidth = (int) (3*sectionOfScreenWithCards)/(15);
        //The amount the cards overlap as a fraction of the cardWidth
        cardOverlapFraction = ((1/3.0) * cardWidth);

        cardHeight = (int) (cardWidth * cardDisplayRatio);
        halfCardWidth = cardWidth/2;
        halfCardHeight = cardHeight/2;


        placeCardRect = new Rect(middleX-(cardWidth*2),
                middleY-(cardHeight*2),
                middleX+(cardWidth*2),
                middleY+(int)(cardHeight*1.5));


        placeCardRegion.set(placeCardRect);

        placeCardRect = new Rect(middleX-halfCardWidth,
                middleY-halfCardHeight,
                middleX+halfCardWidth,
                middleY+halfCardHeight);



        //Generate Rects to display the other players cards

        int leftEdge = 10;
        int rightEdge = screenWidth-10;
        int topEdge = 10;

        //Left middle
        otPlayer1Rect = new Rect(leftEdge,middleY-halfCardHeight,leftEdge+cardWidth,middleY+halfCardHeight);

        if (playerCount>2) {
            //Middle Top
            otPlayer2Rect = new Rect(middleX-halfCardWidth,topEdge,middleX+halfCardWidth,topEdge+cardHeight);
        }
        if ( playerCount>3) {
            //Right middle
            otPlayer3Rect = new Rect(rightEdge-cardWidth,middleY-halfCardHeight,rightEdge,middleY+halfCardHeight);
        }
        if (playerCount>4) {
            //Alternate Left (north west)
            otPlayer4Rect = new Rect(leftEdge,topEdge,leftEdge+cardWidth,topEdge+cardHeight);
        }
        if (playerCount>5) {
            otPlayer5Rect = new Rect(rightEdge-cardWidth,topEdge,rightEdge,topEdge+cardHeight);
        }


        //Card Bitmaps
        //C = clubs, D = diamonds, S =spades, H=hearts

        C2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c2);
        C2Bitmap = Bitmap.createScaledBitmap(C2Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C2",C2Bitmap);
        C3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c3);
        C3Bitmap = Bitmap.createScaledBitmap(C3Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C3",C3Bitmap);
        C4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c4);
        C4Bitmap = Bitmap.createScaledBitmap(C4Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C4",C4Bitmap);
        C5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c5);
        C5Bitmap = Bitmap.createScaledBitmap(C5Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C5",C5Bitmap);
        C6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c6);
        C6Bitmap = Bitmap.createScaledBitmap(C6Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C6",C6Bitmap);
        C7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c7);
        C7Bitmap = Bitmap.createScaledBitmap(C7Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C7",C7Bitmap);
        C8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c8);
        C8Bitmap = Bitmap.createScaledBitmap(C8Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C8",C8Bitmap);
        C9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c9);
        C9Bitmap = Bitmap.createScaledBitmap(C9Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C9",C9Bitmap);
        C10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c10);
        C10Bitmap = Bitmap.createScaledBitmap(C10Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("C10",C10Bitmap);
        CJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cj);
        CJBitmap = Bitmap.createScaledBitmap(CJBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("CJ",CJBitmap);
        CQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cq);
        CQBitmap = Bitmap.createScaledBitmap(CQBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("CQ",CQBitmap);
        CKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ck);
        CKBitmap = Bitmap.createScaledBitmap(CKBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("CK",CKBitmap);
        CABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ca);
        CABitmap = Bitmap.createScaledBitmap(CABitmap, cardWidth, cardHeight, false);
        bitmapMap.put("CA",CABitmap);


        D2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d2);
        D2Bitmap = Bitmap.createScaledBitmap(D2Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D2",D2Bitmap);
        D3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d3);
        D3Bitmap = Bitmap.createScaledBitmap(D3Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D3",D3Bitmap);
        D4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d4);
        D4Bitmap = Bitmap.createScaledBitmap(D4Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D4",D4Bitmap);
        D5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d5);
        D5Bitmap = Bitmap.createScaledBitmap(D5Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D5",D5Bitmap);
        D6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d6);
        D6Bitmap = Bitmap.createScaledBitmap(D6Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D6",D6Bitmap);
        D7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d7);
        D7Bitmap = Bitmap.createScaledBitmap(D7Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D7",D7Bitmap);
        D8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d8);
        D8Bitmap = Bitmap.createScaledBitmap(D8Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D8",D8Bitmap);
        D9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d9);
        D9Bitmap = Bitmap.createScaledBitmap(D9Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D9",D9Bitmap);
        D10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d10);
        D10Bitmap = Bitmap.createScaledBitmap(D10Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("D10",D10Bitmap);
        DJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dj);
        DJBitmap = Bitmap.createScaledBitmap(DJBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("DJ",DJBitmap);
        DQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dq);
        DQBitmap = Bitmap.createScaledBitmap(DQBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("DQ",DQBitmap);
        DKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dk);
        DKBitmap = Bitmap.createScaledBitmap(DKBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("DK",DKBitmap);
        DABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.da);
        DABitmap = Bitmap.createScaledBitmap(DABitmap, cardWidth, cardHeight, false);
        bitmapMap.put("DA",DABitmap);


        S2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s2);
        S2Bitmap = Bitmap.createScaledBitmap(S2Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S2",S2Bitmap);
        S3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s3);
        S3Bitmap = Bitmap.createScaledBitmap(S3Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S3",S3Bitmap);
        S4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s4);
        S4Bitmap = Bitmap.createScaledBitmap(S4Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S4",S4Bitmap);
        S5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s5);
        S5Bitmap = Bitmap.createScaledBitmap(S5Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S5",S5Bitmap);
        S6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s6);
        S6Bitmap = Bitmap.createScaledBitmap(S6Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S6",S6Bitmap);
        S7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s7);
        S7Bitmap = Bitmap.createScaledBitmap(S7Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S7",S7Bitmap);
        S8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s8);
        S8Bitmap = Bitmap.createScaledBitmap(S8Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S8",S8Bitmap);
        S9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s9);
        S9Bitmap = Bitmap.createScaledBitmap(S9Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S9",S9Bitmap);
        S10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s10);
        S10Bitmap = Bitmap.createScaledBitmap(S10Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("S10",S10Bitmap);
        SJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sj);
        SJBitmap = Bitmap.createScaledBitmap(SJBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("SJ",SJBitmap);
        SQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sq);
        SQBitmap = Bitmap.createScaledBitmap(SQBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("SQ",SQBitmap);
        SKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sk);
        SKBitmap = Bitmap.createScaledBitmap(SKBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("SK",SKBitmap);
        SABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sa);
        SABitmap = Bitmap.createScaledBitmap(SABitmap, cardWidth, cardHeight, false);
        bitmapMap.put("SA",SABitmap);

        H2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h2);
        H2Bitmap = Bitmap.createScaledBitmap(H2Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H2",H2Bitmap);
        H3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h3);
        H3Bitmap = Bitmap.createScaledBitmap(H3Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H3",H3Bitmap);
        H4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h4);
        H4Bitmap = Bitmap.createScaledBitmap(H4Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H4",H4Bitmap);
        H5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h5);
        H5Bitmap = Bitmap.createScaledBitmap(H5Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H5",H5Bitmap);
        H6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h6);
        H6Bitmap = Bitmap.createScaledBitmap(H6Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H6",H6Bitmap);
        H7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h7);
        H7Bitmap = Bitmap.createScaledBitmap(H7Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H7",H7Bitmap);
        H8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h8);
        H8Bitmap = Bitmap.createScaledBitmap(H8Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H8",H8Bitmap);
        H9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h9);
        H9Bitmap = Bitmap.createScaledBitmap(H9Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H9",H9Bitmap);
        H10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.h10);
        H10Bitmap = Bitmap.createScaledBitmap(H10Bitmap, cardWidth, cardHeight, false);
        bitmapMap.put("H10",H10Bitmap);
        HJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hj);
        HJBitmap = Bitmap.createScaledBitmap(HJBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("HJ",HJBitmap);
        HQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hq);
        HQBitmap = Bitmap.createScaledBitmap(HQBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("HQ",HQBitmap);
        HKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hk);
        HKBitmap = Bitmap.createScaledBitmap(HKBitmap, cardWidth, cardHeight, false);
        bitmapMap.put("HK",HKBitmap);
        HABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ha);
        HABitmap = Bitmap.createScaledBitmap(HABitmap, cardWidth, cardHeight, false);
        bitmapMap.put("HA",HABitmap);

        cardBackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cardback);
        cardBackBitmap = Bitmap.createScaledBitmap(cardBackBitmap,cardWidth,cardHeight,false);
        bitmapMap.put("cardback",cardBackBitmap);



    }


    public void drainPlayerCards() {
        for (Player player: players) {
            Card oneCard = player.hand.get(0);
            player.cardsWon = (ArrayList<Card>) player.hand.clone();
            player.cardsWon.remove(0);
            player.hand.clear();
            player.hand.add(oneCard);
        }
    }

    public boolean isLastTrick() {
        if (currentPlayer.hand.size() ==0) {
            return true;
        }
        else {
            return false;
        }
    }

}