package com.example.vuphi.bill.BillOrder;

/**
 * Created by vuphi on 6/20/2017.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
