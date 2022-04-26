package project5.rucafeapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 The RecyclerAdaptor is a dynamic list used for the list of available flavors for the donuts.
 Each flavor is a clickable view, of which the contents of this view will be used to ensure the
 correct flavor is being ordered in the FlavorActivity screen.
 Scrollable list.
 @author Jah C. Speed, Abe Vitangcol
 */
public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>{
    private ArrayList<Flavor> flavorList;
    private RecyclerViewClickListener listener;

    /**
     Constructs the RecyclerAdaptor by filling it with a flavor list and a click listener
     @param list The flavor list to initialize the RecyclerAdaptor.
     @param listener The action the list shall respond to.
     */
    public RecyclerAdaptor(ArrayList<Flavor> list,RecyclerViewClickListener listener){
        this.flavorList = list;
        this.listener = listener;

    }

    /**
     Creates a ViewHolder to show the list of flavors.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView flavorTextView;

        /**
         Creates the ViewHolder given the list of flavors in text.
         @param itemView The list of flavors in text.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flavorTextView = itemView.findViewById(R.id.flavorChoicePreview);
            itemView.setOnClickListener(this);
        }

        /**
         Performs an action upon clicking one of the flavors.
         @param view The flavor clicked on.
         */
        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    /**
     Creates a new view (button) for a new flavor to be in.
     @param parent The viewgroup that the new view will be added to later.
     @param viewType The view type of the new view.
     @return
     */
    @NonNull
    @Override
    public RecyclerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flavors_page,parent,false);
        return new ViewHolder(itemView);
    }

    /**
     Displays the specifics of an item at a specific position in the list.
     @param holder The view that has all the contents of the items.
            position The position of the item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaptor.ViewHolder holder, int position) {
        String flavor = flavorList.get(position).getFlavorType();
        holder.flavorTextView.setText(flavor);
    }

    /**
     Returns the number of items showing in the flavorList.
     @return The size of the flavorList.
     */
    @Override
    public int getItemCount() {
        return this.flavorList.size();
    }

    /**
     Creates an interface for the RecyclerView for click listening.
     */
    public interface RecyclerViewClickListener{
        /**
         Performs a set of actions when clicked.
         @param view The button or view clicked on.
         @param position The position on the list when clicked.
         */
        void onClick(View view, int position);

    }

}
