package project5.rucafeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import coffee.Coffee;
import donut.Donut;
import mainMenu.MenuItem;
import orders.Order;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<MenuItem> orderList;
    ArrayList<Order> storeorderList;
    LayoutInflater inflater;
    private final String optionRemove = "Remove";
    private final String keyForFlavor = "Flavors";
    private int orderType;
    public ListAdapter(Context context, ArrayList<MenuItem> arrayList, int orderType) {
        this.context = context;
        this.orderList = arrayList;
        this.inflater = LayoutInflater.from(context);
        this.orderType = orderType;
    }
    public ListAdapter(Context context, ArrayList<Order> arrayList) {
        this.context = context;
        this.storeorderList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        if(storeorderList != null){
            return storeorderList.size();
        }
        return orderList.size();
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
        if(orderList != null) {
            MenuItem orderItem = orderList.get(position);
            convertView = inflater.inflate(R.layout.listview_item, null);
            TextView menuItem = convertView.findViewById(R.id.menuItemType);
            TextView menuDetails = convertView.findViewById(R.id.menuItemDetails);
            Button removeBtn = convertView.findViewById(R.id.removeOrder);
            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(orderItem);

                }
            });

            menuItem.setText(orderItem.getType());
            menuDetails.setText("Order Details:" + orderItem.toString());

            return convertView;
        }else{
            Order orderItem = storeorderList.get(position);
            convertView = inflater.inflate(R.layout.listview_item, null);
            TextView menuItem = convertView.findViewById(R.id.menuItemType);
            TextView menuDetails = convertView.findViewById(R.id.menuItemDetails);
            Button removeBtn = convertView.findViewById(R.id.removeOrder);
            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(orderItem);

                }
            });

            menuItem.setText("Order Number: " + orderItem.orderNumber);
            menuDetails.setText("Order Details:\n" + orderItem.toString());
            return convertView;
        }
    }
    public void removeItem(MenuItem item) {
        if(MainActivity.currentOrder.remove(item)){
            Toast.makeText(context.getApplicationContext(), "Item Removed From Cart", Toast.LENGTH_SHORT).show();
            this.orderList = MainActivity.currentOrder.getItemList(orderType);
            notifyDataSetChanged();
            OrderScreen.updateTotals();
        }else{
            Toast.makeText(context.getApplicationContext(), "ERROR Removing Item", Toast.LENGTH_SHORT).show();
        }
    }
    public void removeItem(Order item) {
        if(MainActivity.storeOrders.remove(item)){
            Toast.makeText(context.getApplicationContext(), "Order Removed From Store Orders", Toast.LENGTH_SHORT).show();
            this.storeorderList = MainActivity.storeOrders.storeOrders;
            notifyDataSetChanged();
        }else{
            Toast.makeText(context.getApplicationContext(), "ERROR Removing Order", Toast.LENGTH_SHORT).show();
        }
    }


}
