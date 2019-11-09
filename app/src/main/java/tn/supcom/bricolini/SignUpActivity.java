package tn.supcom.bricolini;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth ;
    Dialog myDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        myDialog = new Dialog(this);
    }

    public void signUp(View view){
        //get email
        EditText editTextmail = (EditText) findViewById(R.id.emailInput);
        String email = editTextmail.getText().toString();
        //get password
        EditText editTextpass = (EditText) findViewById(R.id.passwordInput);
        String password = editTextpass.getText().toString();

        if(email.isEmpty()){
            editTextmail.setError("Entrez votre email");
            editTextmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextmail.setError("Entrez un email valide");
            editTextmail.requestFocus();
            return;
        }

        if(password.isEmpty()||(password.length())<6){
            editTextpass.setError("Entrez un mot de passe min 6 caractères");
            editTextpass.requestFocus();
            return;
        }


        //terms
        CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox) ;
        if(checkbox.isChecked()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(
                                        SignUpActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
        else {
            Toast.makeText(this, "Veuillez accepter les termes", Toast.LENGTH_SHORT).show();
        }
    }
    public void showTermsPopup(){
        TextView txtclose ;
        myDialog.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        myDialog.setContentView(R.layout.terms_pop);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("");
        WebView myWebView = (WebView) myDialog.findViewById(R.id.termsWebView) ;
        Button acceptButton = (Button) myDialog.findViewById(R.id.acceptButton) ;
        myWebView.loadUrl("https://www.websitepolicies.com/policies/view/EyHUTiD8");
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        }) ;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void termsPopup(View view){
        showTermsPopup();
    }
}
