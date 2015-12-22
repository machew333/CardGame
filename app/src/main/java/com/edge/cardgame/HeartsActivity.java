package com.edge.cardgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class HeartsActivity extends AppCompatActivity {

    protected String TAG = "hepMe";

    TableView tableView;

    Deck heartsDeck;
    LinearLayout linLay;
    ScrollView scrollView;
    Player machew;
    Player tooMuchDog;
    Player chewcifer;
    Player matt;

    ArrayList<Player> players = new ArrayList<Player>();

    int playerCount;

    int screenHeight, screenWidth, cardWidth, cardHeight;

    Bitmap D2Bitmap, D3Bitmap, D4Bitmap, D5Bitmap, D6Bitmap, D7Bitmap, D8Bitmap, D9Bitmap, D10Bitmap, DJBitmap, DQBitmap, DKBitmap, DABitmap;
    Bitmap C2Bitmap, C3Bitmap, C4Bitmap, C5Bitmap, C6Bitmap, C7Bitmap, C8Bitmap, C9Bitmap, C10Bitmap, CJBitmap, CQBitmap, CKBitmap, CABitmap;
    Bitmap S2Bitmap, S3Bitmap, S4Bitmap, S5Bitmap, S6Bitmap, S7Bitmap, S8Bitmap, S9Bitmap, S10Bitmap, SJBitmap, SQBitmap, SKBitmap, SABitmap;
    Bitmap H2Bitmap, H3Bitmap, H4Bitmap, H5Bitmap, H6Bitmap, H7Bitmap, H8Bitmap, H9Bitmap, H10Bitmap, HJBitmap, HQBitmap, HKBitmap, HABitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        configureDisplay();
        tableView = new TableView(this);
        setContentView(tableView);

        createDeck();
        createPlayers();
        heartsDeck.shuffle();
        heartsDeck.dealOutAll();

    }

    public void createDeck() {
        heartsDeck = new Deck();
        heartsDeck.setPlayers(players);
    }

    public void createPlayers() {
        matt = new Player("matt");
        machew = new Player("machew");
        chewcifer = new Player("chewcifer");
        tooMuchDog = new Player("tooMuchDog");

        players.add(matt);
        players.add(machew);
        players.add(chewcifer);
        players.add(tooMuchDog);

        playerCount = players.size();
    }





    class TableView extends View {
        //Clubs
        public ImageView C2IV, C3IV, C4IV, C5IV, C6IV, C7IV, C8IV, C9IV, C10IV, CJIV, CQIV, CKIV, CAIV;
        //Diamonds
        public ImageView D2IV, D3IV, D4IV, D5IV, D6IV, D7IV, D8IV, D9IV, D10IV, DJIV, DQIV, DKIV, DAIV;
        //Hearts
        public ImageView H2IV, H3IV, H4IV, H5IV, H6IV, H7IV, H8IV, H9IV, H10IV, HJIV, HQIV, HKIV, HAIV;
        //Spades
        public ImageView S2IV, S3IV, S4IV, S5IV, S6IV, S7IV, S8IV, S9IV, S10IV, SJIV, SQIV, SKIV, SAIV;


        public TableView(Context context) {
            super(context);
            initializeImageViews();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(0xFF00FFFF);
            drawPlayerHand(canvas, machew);
        }

        public void drawPlayerHand(Canvas canvas, Player currentPlayer) {

        }

        //All this method does is initialize the imageviews
        public void initializeImageViews() {

            C2IV = new ImageView(getApplicationContext());
            C2IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C2IV.setScaleType(ImageView.ScaleType.CENTER);
            C2IV.setImageBitmap(C2Bitmap);
            C3IV = new ImageView(getApplicationContext());
            C3IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C3IV.setScaleType(ImageView.ScaleType.CENTER);
            C3IV.setImageBitmap(C3Bitmap);
            C4IV = new ImageView(getApplicationContext());
            C4IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C4IV.setScaleType(ImageView.ScaleType.CENTER);
            C4IV.setImageBitmap(C4Bitmap);
            C5IV = new ImageView(getApplicationContext());
            C5IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C5IV.setScaleType(ImageView.ScaleType.CENTER);
            C5IV.setImageBitmap(C5Bitmap);
            C6IV = new ImageView(getApplicationContext());
            C6IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C6IV.setScaleType(ImageView.ScaleType.CENTER);
            C6IV.setImageBitmap(C6Bitmap);
            C7IV = new ImageView(getApplicationContext());
            C7IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C7IV.setScaleType(ImageView.ScaleType.CENTER);
            C7IV.setImageBitmap(C7Bitmap);
            C8IV = new ImageView(getApplicationContext());
            C8IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C8IV.setScaleType(ImageView.ScaleType.CENTER);
            C8IV.setImageBitmap(C8Bitmap);
            C9IV = new ImageView(getApplicationContext());
            C9IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C9IV.setScaleType(ImageView.ScaleType.CENTER);
            C9IV.setImageBitmap(C9Bitmap);
            C10IV = new ImageView(getApplicationContext());
            C10IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            C10IV.setScaleType(ImageView.ScaleType.CENTER);
            C10IV.setImageBitmap(C10Bitmap);
            CJIV = new ImageView(getApplicationContext());
            CJIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            CJIV.setScaleType(ImageView.ScaleType.CENTER);
            CJIV.setImageBitmap(CJBitmap);
            CQIV = new ImageView(getApplicationContext());
            CQIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            CQIV.setScaleType(ImageView.ScaleType.CENTER);
            CQIV.setImageBitmap(CQBitmap);
            CKIV = new ImageView(getApplicationContext());
            CKIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            CKIV.setScaleType(ImageView.ScaleType.CENTER);
            CKIV.setImageBitmap(CKBitmap);
            CAIV = new ImageView(getApplicationContext());
            CAIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            CAIV.setScaleType(ImageView.ScaleType.CENTER);
            CAIV.setImageBitmap(CABitmap);


            D2IV = new ImageView(getApplicationContext());
            D2IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D2IV.setScaleType(ImageView.ScaleType.CENTER);
            D2IV.setImageBitmap(C2Bitmap);
            D3IV = new ImageView(getApplicationContext());
            D3IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D3IV.setScaleType(ImageView.ScaleType.CENTER);
            D3IV.setImageBitmap(C3Bitmap);
            D4IV = new ImageView(getApplicationContext());
            D4IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D4IV.setScaleType(ImageView.ScaleType.CENTER);
            D4IV.setImageBitmap(C4Bitmap);
            D5IV = new ImageView(getApplicationContext());
            D5IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D5IV.setScaleType(ImageView.ScaleType.CENTER);
            D5IV.setImageBitmap(C5Bitmap);
            D6IV = new ImageView(getApplicationContext());
            D6IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D6IV.setScaleType(ImageView.ScaleType.CENTER);
            D6IV.setImageBitmap(C6Bitmap);
            D7IV = new ImageView(getApplicationContext());
            D7IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D7IV.setScaleType(ImageView.ScaleType.CENTER);
            D7IV.setImageBitmap(C7Bitmap);
            D8IV = new ImageView(getApplicationContext());
            D8IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D8IV.setScaleType(ImageView.ScaleType.CENTER);
            D8IV.setImageBitmap(C8Bitmap);
            D9IV = new ImageView(getApplicationContext());
            D9IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D9IV.setScaleType(ImageView.ScaleType.CENTER);
            D9IV.setImageBitmap(C9Bitmap);
            D10IV = new ImageView(getApplicationContext());
            D10IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            D10IV.setScaleType(ImageView.ScaleType.CENTER);
            D10IV.setImageBitmap(C10Bitmap);
            DJIV = new ImageView(getApplicationContext());
            DJIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            DJIV.setScaleType(ImageView.ScaleType.CENTER);
            DJIV.setImageBitmap(CJBitmap);
            DQIV = new ImageView(getApplicationContext());
            DQIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            DQIV.setScaleType(ImageView.ScaleType.CENTER);
            DQIV.setImageBitmap(CQBitmap);
            DKIV = new ImageView(getApplicationContext());
            DKIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            DKIV.setScaleType(ImageView.ScaleType.CENTER);
            DKIV.setImageBitmap(CKBitmap);
            DAIV = new ImageView(getApplicationContext());
            DAIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            DAIV.setScaleType(ImageView.ScaleType.CENTER);
            DAIV.setImageBitmap(CABitmap);


            S2IV = new ImageView(getApplicationContext());
            S2IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S2IV.setScaleType(ImageView.ScaleType.CENTER);
            S2IV.setImageBitmap(C2Bitmap);
            S3IV = new ImageView(getApplicationContext());
            S3IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S3IV.setScaleType(ImageView.ScaleType.CENTER);
            S3IV.setImageBitmap(C3Bitmap);
            S4IV = new ImageView(getApplicationContext());
            S4IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S4IV.setScaleType(ImageView.ScaleType.CENTER);
            S4IV.setImageBitmap(C4Bitmap);
            S5IV = new ImageView(getApplicationContext());
            S5IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S5IV.setScaleType(ImageView.ScaleType.CENTER);
            S5IV.setImageBitmap(C5Bitmap);
            S6IV = new ImageView(getApplicationContext());
            S6IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S6IV.setScaleType(ImageView.ScaleType.CENTER);
            S6IV.setImageBitmap(C6Bitmap);
            S7IV = new ImageView(getApplicationContext());
            S7IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S7IV.setScaleType(ImageView.ScaleType.CENTER);
            S7IV.setImageBitmap(C7Bitmap);
            S8IV = new ImageView(getApplicationContext());
            S8IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S8IV.setScaleType(ImageView.ScaleType.CENTER);
            S8IV.setImageBitmap(C8Bitmap);
            S9IV = new ImageView(getApplicationContext());
            S9IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S9IV.setScaleType(ImageView.ScaleType.CENTER);
            S9IV.setImageBitmap(C9Bitmap);
            S10IV = new ImageView(getApplicationContext());
            S10IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            S10IV.setScaleType(ImageView.ScaleType.CENTER);
            S10IV.setImageBitmap(C10Bitmap);
            SJIV = new ImageView(getApplicationContext());
            SJIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            SJIV.setScaleType(ImageView.ScaleType.CENTER);
            SJIV.setImageBitmap(CJBitmap);
            SQIV = new ImageView(getApplicationContext());
            SQIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            SQIV.setScaleType(ImageView.ScaleType.CENTER);
            SQIV.setImageBitmap(CQBitmap);
            SKIV = new ImageView(getApplicationContext());
            SKIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            SKIV.setScaleType(ImageView.ScaleType.CENTER);
            SKIV.setImageBitmap(CKBitmap);
            SAIV = new ImageView(getApplicationContext());
            SAIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            SAIV.setScaleType(ImageView.ScaleType.CENTER);
            SAIV.setImageBitmap(CABitmap);


            H2IV = new ImageView(getApplicationContext());
            H2IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H2IV.setScaleType(ImageView.ScaleType.CENTER);
            H2IV.setImageBitmap(C2Bitmap);
            H3IV = new ImageView(getApplicationContext());
            H3IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H3IV.setScaleType(ImageView.ScaleType.CENTER);
            H3IV.setImageBitmap(C3Bitmap);
            H4IV = new ImageView(getApplicationContext());
            H4IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H4IV.setScaleType(ImageView.ScaleType.CENTER);
            H4IV.setImageBitmap(C4Bitmap);
            H5IV = new ImageView(getApplicationContext());
            H5IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H5IV.setScaleType(ImageView.ScaleType.CENTER);
            H5IV.setImageBitmap(C5Bitmap);
            H6IV = new ImageView(getApplicationContext());
            H6IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H6IV.setScaleType(ImageView.ScaleType.CENTER);
            H6IV.setImageBitmap(C6Bitmap);
            H7IV = new ImageView(getApplicationContext());
            H7IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H7IV.setScaleType(ImageView.ScaleType.CENTER);
            H7IV.setImageBitmap(C7Bitmap);
            H8IV = new ImageView(getApplicationContext());
            H8IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H8IV.setScaleType(ImageView.ScaleType.CENTER);
            H8IV.setImageBitmap(C8Bitmap);
            H9IV = new ImageView(getApplicationContext());
            H9IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H9IV.setScaleType(ImageView.ScaleType.CENTER);
            H9IV.setImageBitmap(C9Bitmap);
            H10IV = new ImageView(getApplicationContext());
            H10IV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            H10IV.setScaleType(ImageView.ScaleType.CENTER);
            H10IV.setImageBitmap(C10Bitmap);
            HJIV = new ImageView(getApplicationContext());
            HJIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            HJIV.setScaleType(ImageView.ScaleType.CENTER);
            HJIV.setImageBitmap(CJBitmap);
            HQIV = new ImageView(getApplicationContext());
            HQIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            HQIV.setScaleType(ImageView.ScaleType.CENTER);
            HQIV.setImageBitmap(CQBitmap);
            HKIV = new ImageView(getApplicationContext());
            HKIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            HKIV.setScaleType(ImageView.ScaleType.CENTER);
            HKIV.setImageBitmap(CKBitmap);
            HAIV = new ImageView(getApplicationContext());
            HAIV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            HAIV.setScaleType(ImageView.ScaleType.CENTER);
            HAIV.setImageBitmap(CABitmap);


        }

    }
    public void configureDisplay() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        double cardRatio = 726 / 500;

        cardWidth = screenWidth / 13;

        cardHeight = (int) (cardWidth * cardRatio);

        //Card Bitmaps

        C2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c2);
        C2Bitmap = Bitmap.createScaledBitmap(C2Bitmap, cardWidth, cardHeight, false);
        C3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c3);
        C3Bitmap = Bitmap.createScaledBitmap(C3Bitmap, cardWidth, cardHeight, false);
        C4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c4);
        C4Bitmap = Bitmap.createScaledBitmap(C4Bitmap, cardWidth, cardHeight, false);
        C5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c5);
        C5Bitmap = Bitmap.createScaledBitmap(C5Bitmap, cardWidth, cardHeight, false);
        C6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c6);
        C6Bitmap = Bitmap.createScaledBitmap(C6Bitmap, cardWidth, cardHeight, false);
        C7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c7);
        C7Bitmap = Bitmap.createScaledBitmap(C7Bitmap, cardWidth, cardHeight, false);
        C8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c8);
        C8Bitmap = Bitmap.createScaledBitmap(C8Bitmap, cardWidth, cardHeight, false);
        C9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c9);
        C9Bitmap = Bitmap.createScaledBitmap(C9Bitmap, cardWidth, cardHeight, false);
        C10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.c10);
        C10Bitmap = Bitmap.createScaledBitmap(C10Bitmap, cardWidth, cardHeight, false);
        CJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cj);
        CJBitmap = Bitmap.createScaledBitmap(CJBitmap, cardWidth, cardHeight, false);
        CQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cq);
        CQBitmap = Bitmap.createScaledBitmap(CQBitmap, cardWidth, cardHeight, false);
        CKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ck);
        CKBitmap = Bitmap.createScaledBitmap(CKBitmap, cardWidth, cardHeight, false);
        CABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ca);
        CABitmap = Bitmap.createScaledBitmap(CABitmap, cardWidth, cardHeight, false);


        D2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d2);
        D2Bitmap = Bitmap.createScaledBitmap(D2Bitmap, cardWidth, cardHeight, false);
        D3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d3);
        D3Bitmap = Bitmap.createScaledBitmap(D3Bitmap, cardWidth, cardHeight, false);
        D4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d4);
        D4Bitmap = Bitmap.createScaledBitmap(D4Bitmap, cardWidth, cardHeight, false);
        D5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d5);
        D5Bitmap = Bitmap.createScaledBitmap(D5Bitmap, cardWidth, cardHeight, false);
        D6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d6);
        D6Bitmap = Bitmap.createScaledBitmap(D6Bitmap, cardWidth, cardHeight, false);
        D7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d7);
        D7Bitmap = Bitmap.createScaledBitmap(D7Bitmap, cardWidth, cardHeight, false);
        D8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d8);
        D8Bitmap = Bitmap.createScaledBitmap(D8Bitmap, cardWidth, cardHeight, false);
        D9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d9);
        D9Bitmap = Bitmap.createScaledBitmap(D9Bitmap, cardWidth, cardHeight, false);
        D10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d10);
        D10Bitmap = Bitmap.createScaledBitmap(D10Bitmap, cardWidth, cardHeight, false);
        DJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dj);
        DJBitmap = Bitmap.createScaledBitmap(DJBitmap, cardWidth, cardHeight, false);
        DQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dq);
        DQBitmap = Bitmap.createScaledBitmap(DQBitmap, cardWidth, cardHeight, false);
        DKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dk);
        DKBitmap = Bitmap.createScaledBitmap(DKBitmap, cardWidth, cardHeight, false);
        DABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.da);
        DABitmap = Bitmap.createScaledBitmap(DABitmap, cardWidth, cardHeight, false);


        S2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s2);
        S2Bitmap = Bitmap.createScaledBitmap(S2Bitmap, cardWidth, cardHeight, false);
        S3Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s3);
        S3Bitmap = Bitmap.createScaledBitmap(S3Bitmap, cardWidth, cardHeight, false);
        S4Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s4);
        S4Bitmap = Bitmap.createScaledBitmap(S4Bitmap, cardWidth, cardHeight, false);
        S5Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s5);
        S5Bitmap = Bitmap.createScaledBitmap(S5Bitmap, cardWidth, cardHeight, false);
        S6Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s6);
        S6Bitmap = Bitmap.createScaledBitmap(S6Bitmap, cardWidth, cardHeight, false);
        S7Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s7);
        S7Bitmap = Bitmap.createScaledBitmap(S7Bitmap, cardWidth, cardHeight, false);
        S8Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s8);
        S8Bitmap = Bitmap.createScaledBitmap(S8Bitmap, cardWidth, cardHeight, false);
        S9Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s9);
        S9Bitmap = Bitmap.createScaledBitmap(S9Bitmap, cardWidth, cardHeight, false);
        S10Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s10);
        S10Bitmap = Bitmap.createScaledBitmap(S10Bitmap, cardWidth, cardHeight, false);
        SJBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sj);
        SJBitmap = Bitmap.createScaledBitmap(SJBitmap, cardWidth, cardHeight, false);
        SQBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sq);
        SQBitmap = Bitmap.createScaledBitmap(SQBitmap, cardWidth, cardHeight, false);
        SKBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sk);
        SKBitmap = Bitmap.createScaledBitmap(SKBitmap, cardWidth, cardHeight, false);
        SABitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sa);
        SABitmap = Bitmap.createScaledBitmap(SABitmap, cardWidth, cardHeight, false);

        H2Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h2);
        H2Bitmap = Bitmap.createScaledBitmap(H2Bitmap,cardWidth,cardHeight,false);
        H3Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h3);
        H3Bitmap = Bitmap.createScaledBitmap(H3Bitmap,cardWidth,cardHeight,false);
        H4Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h4);
        H4Bitmap = Bitmap.createScaledBitmap(H4Bitmap,cardWidth,cardHeight,false);
        H5Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h5);
        H5Bitmap = Bitmap.createScaledBitmap(H5Bitmap,cardWidth,cardHeight,false);
        H6Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h6);
        H6Bitmap = Bitmap.createScaledBitmap(H6Bitmap,cardWidth,cardHeight,false);
        H7Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h7);
        H7Bitmap = Bitmap.createScaledBitmap(H7Bitmap,cardWidth,cardHeight,false);
        H8Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h8);
        H8Bitmap = Bitmap.createScaledBitmap(H8Bitmap,cardWidth,cardHeight,false);
        H9Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h9);
        H9Bitmap = Bitmap.createScaledBitmap(H9Bitmap,cardWidth,cardHeight,false);
        H10Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.h10);
        H10Bitmap = Bitmap.createScaledBitmap(H10Bitmap,cardWidth,cardHeight,false);
        HJBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.hj);
        HJBitmap = Bitmap.createScaledBitmap(HJBitmap,cardWidth,cardHeight,false);
        HQBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.hq);
        HQBitmap = Bitmap.createScaledBitmap(HQBitmap,cardWidth,cardHeight,false);
        HKBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.hk);
        HKBitmap = Bitmap.createScaledBitmap(HKBitmap,cardWidth,cardHeight,false);
        HABitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ha);
        HABitmap = Bitmap.createScaledBitmap(HABitmap,cardWidth,cardHeight,false);




    }
}