package project5.rucafeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        convertView = inflater.inflate(R.layout.listview_item,null);
        TextView menuItem = convertView.findViewById(R.id.menuItemType);
        TextView menuDetails = convertView.findViewById(R.id.menuItemDetails);
        MenuItem orderItem = donutArrayList.get(position);
        menuItem.setText(orderItem.getType());
        menuDetails.setText("Order details: \n\tDonut Type: " + orderItem.getItemName() + "\n\tQuantity: " + orderItem.getAmount()
                + "\n\tPrice: " + orderItem.getPrice());
        return convertView;
    }
}
