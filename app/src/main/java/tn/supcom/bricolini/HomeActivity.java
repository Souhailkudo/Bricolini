package tn.supcom.bricolini;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    GridLayout mainGrid;
    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        mAuth = FirebaseAuth.getInstance();

        //Set Event
        setSingleEvent(mainGrid);
    }

    public void logOut(View view){
        mAuth.signOut();
        Intent intent = new Intent(this, SignInActivity.class) ;
        startActivity(intent);

    }


    public void myjobs(View view){
        //mAuth.signOut();
        //Intent intent = new Intent(this, SignInActivity.class) ;
        Intent intent = new Intent(this, MyJobListActivity.class) ;
        startActivity(intent);

    }

    public void myRequests(View view){
        //mAuth.signOut();
        //Intent intent = new Intent(this, SignInActivity.class) ;
        Intent intent = new Intent(this, MyRequestListActivity.class) ;
        startActivity(intent);

    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(HomeActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(HomeActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(HomeActivity.this,FeedActivity.class);
                    String Services[] = {"Menuiserie","Jardinage","Electricité", "Plomberie","Revêtement","Petits Travaux"} ;
                    intent.putExtra("SERVICE_NAME",Services[finalI]);
                    startActivity(intent);

                }
            });
        }
    }
}
