package project5.rucafeapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>{
    private ArrayList<Flavor> flavorList;
    private RecyclerViewClickListener listener;
    public RecyclerAdaptor(ArrayList<Flavor> list,RecyclerViewClickListener listener){
        this.flavorList = list;
        this.listener = listener;

    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView flavorTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flavorTextView = itemView.findViewById(R.id.flavorChoicePreview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public RecyclerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flavors_page,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaptor.ViewHolder holder, int position) {
        String flavor = flavorList.get(position).getFlavorType();
        holder.flavorTextView.setText(flavor);
    }

    @Override
    public int getItemCount() {
        return this.flavorList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);

    }

}
