package com.prtify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by leshan on 11/4/17.
 */

public class DeviceListAdapter  extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {

    private String[] mDataset;
    OnItemClickListener onItemClickListener;

    @Override
    public DeviceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DeviceListAdapter.ViewHolder holder, int position) {

    }

    public DeviceListAdapter(String[] mDataset, OnItemClickListener onItemClickListener) {
        this.mDataset = mDataset;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int position, String id);
    }
}
