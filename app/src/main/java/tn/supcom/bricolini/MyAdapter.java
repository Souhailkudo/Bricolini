package tn.supcom.bricolini;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<FeedItem> listItems ;
    private Context context ;
    private static ClickListener clickListener;
    public MyAdapter(List<FeedItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false) ;
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FeedItem item = listItems.get(position) ;

        holder.tvName.setText(item.getName()) ;
        holder.tvAdress.setText(item.getAdress());
        holder.tvRate.setText(item.getRate());
        holder.tvJobCount.setText(item.getJobCount());


        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference("images/"+item.getProfilePhoto()) ;

        GlideApp.with(this.context)
                .load(storageReference)
                .error(R.drawable.profilepic)
                .into(holder.ivProfilePhoto);
    }


    @Override
    public int getItemCount() {
        return listItems.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView tvName, tvAdress, tvRate, tvJobCount ;
        public ImageView ivProfilePhoto ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            tvName = (TextView) itemView.findViewById(R.id.nameText) ;
            tvAdress = (TextView) itemView.findViewById(R.id.adressText) ;
            tvRate = (TextView) itemView.findViewById(R.id.rateText) ;
            tvJobCount = (TextView) itemView.findViewById(R.id.jobCountText) ;
            ivProfilePhoto = (ImageView) itemView.findViewById(R.id.profilePic) ;
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);

        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        MyAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
