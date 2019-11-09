package tn.supcom.bricolini;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {

    private List<ClientItem> listItems ;
    private Context context ;
    private static ClickListener clickListener;
    public ClientsAdapter(List<ClientItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_client, parent, false) ;
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ClientItem item = listItems.get(position) ;

        holder.tvName.setText(item.getName()) ;
        holder.tvAdress.setText(item.getAdress());
        holder.tvDate.setText(item.getDate());
        holder.tvPhone.setText(item.getPhone());

        //state holder
        String state = item.getState() ;
        holder.tvState.setText(" "+state+" ") ;

        if (state.equals("In queue"))
            holder.tvState.setBackgroundResource(R.drawable.in_queue_background) ;
        else if(state.equals("Accepted"))
            holder.tvState.setBackgroundResource(R.drawable.accepted_background);
        else holder.tvState.setBackgroundResource(R.drawable.completed_background);



        //photo holder
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference("images/"+item.getProfilePic()) ;
        GlideApp.with(this.context)
                .load(storageReference)
                .error(R.drawable.profilepic)
                .into(holder.ivProfilePhoto);
        //service holder
        // "Menuiserie","Jardinage","Electricité", "Plomberie","Revêtement","Petits Travaux"
        String service = item.getService() ;
        if (service.equals("Menuiserie"))
            holder.ivService.setImageResource(R.drawable.menuiserie);
        else if (service.equals("Jardinage"))
            holder.ivService.setImageResource(R.drawable.jardinage);
        else if (service.equals("Electricité"))
            holder.ivService.setImageResource(R.drawable.electricite);
        else if (service.equals("Plomberie"))
            holder.ivService.setImageResource(R.drawable.plomberie);
        else if (service.equals("Revêtement"))
            holder.ivService.setImageResource(R.drawable.revetement);
        else if (service.equals("Petits Travaux"))
            holder.ivService.setImageResource(R.drawable.bricolage);
    }


    @Override
    public int getItemCount() {
        return listItems.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView tvName, tvAdress, tvDate, tvPhone, tvState ;
        public ImageView ivProfilePhoto, ivService ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            tvName = (TextView) itemView.findViewById(R.id.nameText) ;
            tvAdress = (TextView) itemView.findViewById(R.id.adressText) ;
            tvDate = (TextView) itemView.findViewById(R.id.dateText) ;
            tvPhone = (TextView) itemView.findViewById(R.id.numberText) ;
            tvState = (TextView) itemView.findViewById(R.id.stateText) ;
            ivProfilePhoto = (ImageView) itemView.findViewById(R.id.profilePic) ;
            ivService = (ImageView) itemView.findViewById(R.id.serviceView) ;
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
        ClientsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
