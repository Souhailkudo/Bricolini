package tn.supcom.bricolini;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null) goHome() ;
    }

    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class) ;
        startActivity(intent);
    }

    public void signIn(View view)
    {
        //get email
        EditText editText = (EditText) findViewById(R.id.emailInput);
        String email = editText.getText().toString();
        //get password
        editText = (EditText) findViewById(R.id.passwordInput);
        String password = editText.getText().toString();

        //Start Sign in
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user!=null)
                                goHome();
                          //  updateUI(user);
                        } else
                            // If sign in fails, display a message to the user
                            Toast.makeText(SignInActivity.this,
                                    "Authentication échouée", Toast.LENGTH_SHORT).show();
                    }
                });
        // End Sign in
    }
    public void goToSignUp(View view){

        Intent intent = new Intent(this, SignUpActivity.class) ;
        startActivity(intent);
    }

    public void signIngfb(View view){

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build() );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo)
                        .build(), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode==1){
                Intent intent = new Intent(
                        SignInActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        }
    }
}

