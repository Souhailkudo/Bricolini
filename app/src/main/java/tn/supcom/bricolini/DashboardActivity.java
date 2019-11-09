package tn.supcom.bricolini;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String message = intent.getStringExtra("userID");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
    public void logOut(View view){
        mAuth.signOut();
        Intent intent = new Intent(this, SignInActivity.class) ;
        startActivity(intent);
    }

    public void testfeed(View view) {
        Intent intent = new Intent(this, FeedActivity.class) ;
        intent.putExtra("SERVICE_NAME", "Jardinage");
        startActivity(intent);
    }
}
