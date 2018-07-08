package com.surajgoyal.store2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;


public class DetailActivity extends AppCompatActivity {

    Button closeButton, buyButton, cardButton;
    CardView cardView;
    boolean visible = false;
    LinearLayout walletBalanceLayout, appPriceLayout;
    TextView walletBalanceLayout1, walletBalanceLayout2, appPriceLayout1, appPriceLayout2;
    ImageView paymentConfirmed;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        closeButton = (Button)findViewById(R.id.close);
        buyButton = (Button)findViewById(R.id.buyButton);
        cardButton = (Button)findViewById(R.id.card_button);
        cardView = (CardView)findViewById(R.id.cardView);
        walletBalanceLayout = (LinearLayout)findViewById(R.id.wallet_balance_layout);
        appPriceLayout = (LinearLayout) findViewById(R.id.app_price_layout);

        walletBalanceLayout1 = (TextView)findViewById(R.id.wallet_balance_layout1);
        walletBalanceLayout2 = (TextView)findViewById(R.id.wallet_balance_layout2);

        appPriceLayout1 = (TextView)findViewById(R.id.app_price_layout1);
        appPriceLayout2 = (TextView)findViewById(R.id.app_price_layout2);



        paymentConfirmed = (ImageView)findViewById(R.id.payment_confirmed);

        closeButton.animate().rotationBy(90f).setDuration(500);




    }

    public void closeActivity(View v){

        closeButton.animate().rotationBy(90f).setDuration(500);

      supportFinishAfterTransition();
    }

    public void openCardView(View view){

        cardView.setVisibility(View.VISIBLE);
        cardView.animate().translationY(-100f).setDuration(200);

    }

    public void downloadAndPlay(View view){

        if(visible==false) {

            android.transition.Slide slide = new android.transition.Slide();
            slide.setSlideEdge(android.view.Gravity.LEFT);
            TransitionManager.beginDelayedTransition(walletBalanceLayout,slide);
            TransitionManager.beginDelayedTransition(appPriceLayout,slide);
            walletBalanceLayout.setVisibility(View.INVISIBLE);
            appPriceLayout.setVisibility(View.INVISIBLE);

          //  walletBalanceLayout.animate().translationX(-1000f).setDuration(500);

          //  appPriceLayout.animate().translationX(-1000f).setDuration(500);

            paymentConfirmed.setVisibility(View.VISIBLE);
            paymentConfirmed.animate().scaleX(2f).scaleY(2f).setDuration(300);
            cardButton.setText("DOWNLOAD");
            visible = true;
        }
        else{

            cardButton.setText("PLAY");
            cardButton.setBackground(getResources().getDrawable(R.drawable.button));
            cardButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            doBounceAnimation(cardButton);
            visible = false;


        }



    }


    private void doBounceAnimation(View targetView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationY", 50, 0, 40);
        animator.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
        animator.setStartDelay(200);
        animator.setDuration(1000);
        animator.start();
    }
}
