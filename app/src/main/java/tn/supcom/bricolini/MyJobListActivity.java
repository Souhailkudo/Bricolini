package tn.supcom.bricolini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.common.api.Api;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyJobListActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter adapter ;
    private List<ClientItem> itemlist ;

    private FirebaseAuth mAuth ;
    private FirebaseDatabase database ;
    private DatabaseReference userRef, requestRef ;
    private String userID ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_job_list);

        //set recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
        recyclerView.setHasFixedSize(true) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemlist = new ArrayList<>();

        //set database
        mAuth = FirebaseAuth.getInstance() ;
        userID = mAuth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Users") ;


    }

    @Override
    protected void onStart() {
        super.onStart();

        //search database
        /*
        Query query = requestRef
                .orderByChild()
                .equalTo(StrService) ;
        Toast.makeText(this, query.getClass().toString() , Toast.LENGTH_LONG).show();

  */


        //set itemlist
        itemlist.add(new ClientItem("person1","2yM2WXNpXGRhpVKq4iPPSHJXudJ2","adresse1",
                "21452125","03-04-1995","Accepted","Electricité","gfkld,gfkld,lg", "gfdgfd") ) ;
        itemlist.add(new ClientItem("person1","2yM2WXNpXGRhpVKq4iPPSHJXudJ2","adresse1",
                "21452125","03-04-1995","In queue","Plomberie","gfkld,gfkld,lg", "fdsfdsf") ) ;
        itemlist.add(new ClientItem("person1","2yM2WXNpXGRhpVKq4iPPSHJXudJ2","adresse1",
                "21452125","03-04-1995","Completed","Revêtement","gfkld,gfkld,lg","fdsgsf") ) ;

        //start recyclerView
        adapter = new ClientsAdapter(itemlist, MyJobListActivity.this) ;
        recyclerView.setAdapter(adapter);

    }
}
