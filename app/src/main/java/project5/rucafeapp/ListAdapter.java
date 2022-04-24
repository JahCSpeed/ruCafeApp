package project5.rucafeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import donut.Donut;
import mainMenu.MenuItem;
import orders.Order;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<MenuItem> donutArrayList;
    LayoutInflater inflater;
    private final String optionRemove = "Remove";
    private final String optionEdit = "Edit";
    private final String keyForFlavor = "Flavors";
    public ListAdapter(Context context, ArrayList<MenuItem> arrayList) {
        this.context = context;
        this.donutArrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return donutArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MenuItem orderItem = donutArrayList.get(position);
        convertView = inflater.inflate(R.layout.listview_item,null);
        TextView menuItem = convertView.findViewById(R.id.menuItemType);
        TextView menuDetails = convertView.findViewById(R.id.menuItemDetails);
        Button removeBtn = convertView.findViewById(R.id.removeOrder);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(orderItem);
            }
        });
        if(!(orderItem instanceof Donut)){
            return convertView;
        }
        menuItem.setText(orderItem.getType());
        menuDetails.setText("Order details: \n\tDonut Type: " + orderItem.getItemName() + "\n\tQuantity: " + orderItem.getAmount()
                + "\n\tPrice: $" + orderItem.itemPrice());
        return convertView;
    }


    public void removeItem(MenuItem item) {
       if(MainActivity.currentOrder.remove(item)){
           Toast.makeText(context.getApplicationContext(), "Item Removed From Cart", Toast.LENGTH_LONG).show();
           notifyDataSetChanged();
       }else{
           Toast.makeText(context.getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
       }

    }


}
