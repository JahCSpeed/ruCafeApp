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
 The ListAdapter class is used in the storeorderscreen to hold the details of all the orders.
 Capable of showing how long it is and can remove specific items or orders from the list.
 It is a scrollable list.
 @author Jah C. Speed, Abe Vitangcol
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


    /**
     Returns how long the storeorderlist is.
     @return The size of the storeorderlist.
     */
    @Override
    public int getCount() {
        if(storeorderList != null){
            return storeorderList.size();
        }
        return orderList.size();
    }

    /**
     Gets the item and returns it as null.
     @param i The item to be obtained.
     @return null.
     */
    @Override
    public Object getItem(int i) {
        return null;
    }

    /**
     Gets the item id and returns it as 0.
     @param i The item to check the id of.
     @return 0.
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     Gives the order information on the screen given its position and contents.
     Able to scroll through the ListAdapter to see all the items there.
     @param position The position where the item is.
            convertView The view to be converted.
            parent The type or group the convertView is from.
     @return The contents of the convertview, false otherwise.
     */
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

    /**
     Removes an item from a specific order.
     Throws an error if the removable item does not exist for any reason.
     @param item The item to be removed from an order.
     */
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

    /**
     Removes a specific order, removing all items from its name.
     Throws an error if the removable item does not exist for any reason.
     @param item The order to be removed from the store order.
     */
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
