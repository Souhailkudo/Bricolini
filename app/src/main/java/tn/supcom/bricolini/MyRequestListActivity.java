package tn.supcom.bricolini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyRequestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request_list);
    }



      /*  public void PopupRate(FeedItem item) {
        TextView txtclose,remplir, txtrate;
        Button btnConfirm,btnRefuse;
        myDialog.setContentView(R.layout.popupmyrequests);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("");
        remplir =(TextView) myDialog.findViewById(R.id.remplir);
        btnConfirm = (Button) myDialog.findViewById(R.id.btnConfirm);
        btnRefuse = (Button) myDialog.findViewById(R.id.btnRefuse);
        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);
        Button submitButton = (Button) findViewById(R.id.submit_button);
       // final TextView ratingDisplayTextView = (TextView) findViewById(R.id.rating_display_text_View);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ratingDisplayTextView.setText("Your rating is: " + ratingRatingBar.getRating());
                Toast.makeText(FeedActivity.this, "Your rating is: " + ratingRatingBar.getRating(), Toast.LENGTH_LONG).show() ;
            }
        });



        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
*/
}
