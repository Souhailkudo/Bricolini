package tn.supcom.bricolini;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {


    private FirebaseAuth mAuth ;
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter adapter ;
    private List<FeedItem> listItems ;
    public  TextView tvTitle ;
    private String StrService ;
    private FirebaseDatabase database ;
    private DatabaseReference JobRef, userRef, requestRef ;
    private List<Jobs> joblst ;
    private List<UserInformation> userlst ;
    Dialog myDialog;
    private Spinner gouvSpinner, villeSpinner ;
    private String gouvSelected, villeSelected ;
    //for popup
    EditText detailsTextField, dateTextField;
    FeedItem clickedItem ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        //pop up dialog
        myDialog = new Dialog(this);
        //get Auth
        mAuth = FirebaseAuth.getInstance();
        //set database
        database = FirebaseDatabase.getInstance();
        JobRef = database.getReference("Job");
        userRef = database.getReference("Users") ;
        requestRef = database.getReference("Requests");

        joblst = new ArrayList<>() ;
        userlst = new ArrayList<>() ;

        //Choose a title
        Intent intent = getIntent();
        StrService = intent.getStringExtra("SERVICE_NAME");
        tvTitle = (TextView) findViewById(R.id.serviceText) ;
        tvTitle.setText(StrService);
        //set recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
        recyclerView.setHasFixedSize(true) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        //set spinners
        gouvSpinner = (Spinner) findViewById(R.id.gouvernoratSpinner) ;
        villeSpinner = (Spinner) findViewById(R.id.villeSpinner) ;

        // Class Spinner implementing onItemSelectedListener


        gouvSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                gouvSelected = parent.getItemAtPosition(position).toString();
                //switch case to select city
                switch (gouvSelected)
                {
                    case "Ariana":
                        // assigning div item list defined in XML to the div Spinner
                        villeSpinner.setAdapter(new ArrayAdapter<>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Ariana_list)));
                        break;

                    case "Béja":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Beja_list)));
                        break;

                    case "Ben Arous":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Ben_Arous_list)));
                        break;

                    case "Bizerte":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Bizerte_list)));
                        break;

                    case "Gabes":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Gabes_list)));
                        break;

                    case "Gafsa":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Gafsa_list)));
                        break;

                    case "Jendouba":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Gafsa_list)));
                        break;


                    case "Kairouan":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Kairouan_list)));
                        break;


                    case "Kasserine":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Kasserine_list)));
                        break;

                    case "Kebili":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Kebili_list)));
                        break;

                    case "Manouba":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Manouba_list)));
                        break;

                    case "Le Kef":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Le_Kef_list)));
                        break;

                    case "Mahdia":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Mahdia_list)));
                        break;

                    case "Médenine":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Medenine_list)));
                        break;

                    case "Monastir":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Monastir_list)));
                        break;

                    case "Nabeul":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Nabeul_list)));
                        break;

                    case "Sfax":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Sfax_list)));
                        break;

                    case "Sidi Bouzid":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Sidi_Bouzid_list)));
                        break;

                    case "Siliana":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Siliana_list)));
                        break;

                    case "Sousse":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Sousse_list)));
                        break;

                    case "Tataouine":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Tataouine_list)));
                        break;

                    case "Tozeur":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Tozeur_list)));
                        break;

                    case "Tunis":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Tunis_list)));
                        break;

                    case "Zaghouan":
                        villeSpinner.setAdapter(new ArrayAdapter<String>(FeedActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.Zaghouan_list)));
                        break;
                }

                villeSpinner.setVisibility(View.VISIBLE);
                if((joblst!=null) && (userlst!=null) ) refreshItemList(userlst, joblst) ;
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
                if((joblst!=null) && (userlst!=null) ) refreshItemList(userlst, joblst) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });



    }


    @Override
    protected void onStart() {
        super.onStart();

/*
        Query query = JobRef
                .orderByChild("service")
                .equalTo(StrService) ;
        Toast.makeText(this, query.getClass().toString() , Toast.LENGTH_LONG).show();

  */
        JobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                joblst=new ArrayList<>() ;
                for(DataSnapshot jobSnapshot : dataSnapshot.getChildren()){
                    Jobs job = jobSnapshot.getValue(Jobs.class) ;
                    joblst.add(job) ;
                }
                if (userlst!=null)
                    refreshItemList(userlst, joblst) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        }) ;

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlst=new ArrayList<>() ;

                for(DataSnapshot jobSnapshot : dataSnapshot.getChildren()){
                    UserInformation user = jobSnapshot.getValue(UserInformation.class) ;
                    userlst.add(user) ;
                }
                if(joblst!=null) refreshItemList(userlst, joblst) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;

    }

    void  refreshItemList(List<UserInformation> userlst, List<Jobs> joblst) {
        listItems = new ArrayList<>();
        for (Jobs job : joblst) {

            //only show jobs from this service
            if(!job.getService().equals(StrService)) continue ;

            //find technician (user)
            String technicianId = job.getTechnicianId();
            UserInformation person = new UserInformation();
            for (UserInformation user : userlst) {
                if (user.getUserID().equals(technicianId)) {
                    person = user;
                    break;
                }
            }

            //choose users from same city
            if(( person.getGouvernorat().equals(gouvSelected) && person.getVille().equals(villeSelected) ) ) {
                //    continue ;
                FeedItem item = new FeedItem();

                //set job data
                item.setJobId(job.getJobId());
                item.setRate(String.valueOf(job.getRate()));
                item.setJobCount(String.valueOf(job.getJobCount()));
                item.setProfilePhoto(technicianId);
                item.setTechDescription(job.getDescription());

                //set user data
                item.setAdress(person.getGouvernorat() + ", " + person.getVille());
                item.setName(person.getNom() + " " + person.getPrenom());
                listItems.add(item);
            }

        }
            adapter = new MyAdapter(listItems, FeedActivity.this);
            ((MyAdapter) adapter).setOnItemClickListener(new MyAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    // Log.d("TAG", "onItemClick position: " + position);
                    // Toast.makeText(FeedActivity.this,"onItemClick position: " + position, Toast.LENGTH_LONG).show() ;
                    ShowPopup(listItems.get(position));
                }

                @Override
                public void onItemLongClick(int position, View v) {
                    // Log.d("TAG", "onItemLongClick pos = " + position);
                    Toast.makeText(FeedActivity.this, "onItemLongClick position: " + position, Toast.LENGTH_LONG).show();
                }
            });
            recyclerView.setAdapter(adapter);

    }


    public void addJob(android.view.View view){

        myDialog.setContentView(R.layout.add_job_pop);
        TextView txtclose ;
        Button btnConfirm;
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        btnConfirm = (Button) myDialog.findViewById(R.id.btnConfirm);
        txtclose.setText("");
        detailsTextField = (EditText) myDialog.findViewById(R.id.detailsTextField) ;

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uniqueID=JobRef.push().getKey() ;
                Jobs job = new Jobs(uniqueID, mAuth.getCurrentUser().getUid(),StrService,
                        detailsTextField.getText().toString(),0.0f,0) ;
                JobRef.child(uniqueID).setValue(job) ;
                Toast.makeText(FeedActivity.this, "service ajouté", Toast.LENGTH_LONG).show() ;
                myDialog.dismiss();
            }

        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopup(FeedItem item) {
        TextView txtclose,remplir;
       // Button btnConfirm;
        myDialog.setContentView(R.layout.custompop);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("");
        remplir =(TextView) myDialog.findViewById(R.id.remplir);
       // btnConfirm = (Button) myDialog.findViewById(R.id.btnConfirm);
        detailsTextField = (EditText) myDialog.findViewById(R.id.detailsTextField) ;
        dateTextField = (EditText) myDialog.findViewById(R.id.dateTextField) ;
        clickedItem = item ;
        TextView techDetails = (TextView) myDialog.findViewById(R.id.techDetails) ;
        techDetails.setText(item.getTechDescription());
        techDetails.setMovementMethod(new ScrollingMovementMethod());
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void Addrequest(View view){
        Request request = new Request() ;
        request.setJobId(clickedItem.getJobId());
        request.setDetails(detailsTextField.getText().toString());
        //Toast.makeText(FeedActivity.this, "Button pressed", Toast.LENGTH_LONG).show() ;
        request.setClientId(mAuth.getCurrentUser().getUid());
        request.setExpirationDate(dateTextField.getText().toString());
        request.setState("In queue");
        request.setTechID(clickedItem.getProfilePhoto());
        request.setRequestId(requestRef.push().getKey());
        requestRef.child(request.getRequestId()).setValue(request) ;
        Toast.makeText(FeedActivity.this, "Demande anvoyée avec succès", Toast.LENGTH_LONG).show() ;
        myDialog.dismiss();

    }

}
