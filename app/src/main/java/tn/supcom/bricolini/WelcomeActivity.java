package tn.supcom.bricolini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    LinearLayout l1,l2;
    Button btnsub;
    Animation uptodown,downtoup;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        l1 = (LinearLayout) findViewById(R.id.l1);
       // l2 = (LinearLayout) findViewById(R.id.l2);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        //downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        //l2.setAnimation(downtoup);
        timer=new Timer();
        timer.schedule((new TimerTask() {
            @Override
            public void run() {



                Intent mainIntent = new Intent(WelcomeActivity.this,SignInActivity.class);
                startActivity(mainIntent);
                finish();



            }
        }),2000);
    }
}