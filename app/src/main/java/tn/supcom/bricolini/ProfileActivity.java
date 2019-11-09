package tn.supcom.bricolini;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth ;
    private FirebaseDatabase database ;
    private DatabaseReference userRef ;
    private EditText nomd, prenomd, teld,  postd ,gouvd,villed;
    private Spinner gouvSpinner, villeSpinner;
    public String gouvSelected, villeSelected;
    private StorageReference mStorageRef;
    private ImageView ivProfilePic ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //storage reference for profile pic
        mStorageRef = FirebaseStorage.getInstance().getReference();
        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);


        //get Auth
        mAuth = FirebaseAuth.getInstance();
        //set database
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users");

        nomd = (EditText)findViewById(R.id.nomTextField) ;
        prenomd = (EditText) findViewById(R.id.prenomTextField) ;
        teld = (EditText) findViewById(R.id.telephoneTextField) ;
        gouvSpinner = (Spinner) findViewById(R.id.gouvernoratSpinner);
        villeSpinner = (Spinner) findViewById(R.id.villeSpinner);
        postd = (EditText) findViewById(R.id.codePostalTextField) ;



        // Class Spinner implementing onItemSelectedListener
        gouvSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                gouvSelected = parent.getItemAtPosition(position).toString();
                switch (gouvSelected)
                {
                    case "Ariana":
                        // assigning div item list defined in XML to the div Spinner
                        villeSpinner.setAdapter(new ArrayAdapter<>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Ariana_list)));
                        break;

                    case "Béja":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Beja_list)));
                        break;

                    case "Ben Arous":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Ben_Arous_list)));
                        break;

                    case "Bizerte":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Bizerte_list)));
                        break;

                    case "Gabes":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Gabes_list)));
                        break;

                    case "Gafsa":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Gafsa_list)));
                        break;

                    case "Jendouba":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Gafsa_list)));
                        break;


                    case "Kairouan":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Kairouan_list)));
                        break;


                    case "Kasserine":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Kasserine_list)));
                        break;

                    case "Kebili":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Kebili_list)));
                        break;

                    case "Manouba":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Manouba_list)));
                        break;

                    case "Le Kef":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Le_Kef_list)));
                        break;

                    case "Mahdia":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Mahdia_list)));
                        break;

                    case "Médenine":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Medenine_list)));
                        break;

                    case "Monastir":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Monastir_list)));
                        break;

                    case "Nabeul":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Nabeul_list)));
                        break;

                    case "Sfax":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Sfax_list)));
                        break;

                    case "Sidi Bouzid":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Sidi_Bouzid_list)));
                        break;

                    case "Siliana":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Siliana_list)));
                        break;

                    case "Sousse":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Sousse_list)));
                        break;

                    case "Tataouine":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Tataouine_list)));
                        break;

                    case "Tozeur":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Tozeur_list)));
                        break;

                    case "Tunis":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Tunis_list)));
                        break;

                    case "Zaghouan":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(ProfileActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Zaghouan_list)));
                        break;





                }


                villeSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        villeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                villeSelected = parent.getItemAtPosition(position).toString();
                /*

                    show the values on screen
                */
                Toast.makeText(ProfileActivity.this, "\n Gouv: \t " + gouvSelected +
                        "\n Ville: \t" + villeSelected, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });


    }



    public void signUpProfile(android.view.View view) {
        String uniqueID = mAuth.getCurrentUser().getUid();

        UserInformation info = new UserInformation(
                mAuth.getCurrentUser().getUid(),
                nomd.getText().toString(),
                prenomd.getText().toString(),
                teld.getText().toString(),
                gouvSelected,
                villeSelected,
                postd.getText().toString()

        );

        if((nomd.getText().toString()).isEmpty()){
            nomd.setError("Entrer votre nom ");
            nomd.requestFocus();
            return;
        }

        if((prenomd.getText().toString()).isEmpty()){
            prenomd.setError("Entrer votre prénom ");
            prenomd.requestFocus();
            return;
        }


        if((teld.getText().toString()).isEmpty()||((teld.getText().toString()).length())!=8){
            teld.setError("Entrer votre téléphone ");
            teld.requestFocus();
            return;
        }


        if((postd.getText().toString()).isEmpty()||((postd.getText().toString()).length())!=4){
            postd.setError("Entrer votre code postal ");
            postd.requestFocus();
            return;
        }


        userRef.child(uniqueID).setValue(info);

        Toast.makeText(this, "utilisateur ajouté", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class) ;
        startActivity(intent);
    }

    public void addProfilePicture(View view)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK) ;
        File pictureDirectory=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) ;
        String pictureDirectoryPath = pictureDirectory.getPath() ;

        Uri data = Uri.parse(pictureDirectoryPath) ;
        photoPickerIntent.setDataAndType(data, "image/*") ;
        startActivityForResult(photoPickerIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode==2){
                final Uri file = data.getData() ;
                mStorageRef = mStorageRef.child("images/"+mAuth.getCurrentUser().getUid());
                mStorageRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ProfileActivity.this,"ajouté avec succès", Toast.LENGTH_LONG).show() ;
                                InputStream inputStream ;
                                try {
                                    inputStream = getContentResolver().openInputStream(file) ;
                                    Bitmap image = BitmapFactory.decodeStream(inputStream) ;
                                    ivProfilePic.setImageBitmap(image);

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                    Toast.makeText(ProfileActivity.this,"imageview error", Toast.LENGTH_LONG).show() ;
                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(ProfileActivity.this,"erreur d'upload", Toast.LENGTH_LONG).show() ;
                            }
                        });
            }
        }
    }
}
