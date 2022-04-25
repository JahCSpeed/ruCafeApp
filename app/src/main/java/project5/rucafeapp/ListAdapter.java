package project5.rucafeapp;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import mainMenu.MenuItem;
import orders.Order;

/**
 The ListAdapter class
 */
public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<MenuItem> orderList;
    ArrayList<Order> storeorderList;
    LayoutInflater inflater;
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
        convertView = inflater.inflate(R.layout.listview_item, null);
        TextView menuItem = convertView.findViewById(R.id.menuItemType);
        TextView menuDetails = convertView.findViewById(R.id.menuItemDetails);
        if(orderList != null) {
            MenuItem orderItem = orderList.get(position);
            Button removeBtn = convertView.findViewById(R.id.removeOrder);
            removeBtn.setOnClickListener(v -> removeItem(orderItem));
            menuItem.setText(orderItem.getType());
            menuDetails.setText("Order Details:" + orderItem.toString());
        }else{
            Order orderItem = storeorderList.get(position);
            Button removeBtn = convertView.findViewById(R.id.removeOrder);
            removeBtn.setOnClickListener(v -> removeItem(orderItem));
            menuItem.setText("Order Number: " + orderItem.orderNumber);
            menuDetails.setText("Order Details:\n" + orderItem.toString());
        }
        menuDetails.setMaxLines(1000);
        menuDetails.setMovementMethod(new ScrollingMovementMethod());
        View.OnTouchListener listener = (v, event) -> {
            boolean isLarger;
            isLarger = ((TextView) v).getLineCount() * ((TextView) v).getLineHeight() > v.getHeight();
            if (event.getAction() == MotionEvent.ACTION_MOVE && isLarger) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        };
        menuDetails.setOnTouchListener(listener);
        return convertView;
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
