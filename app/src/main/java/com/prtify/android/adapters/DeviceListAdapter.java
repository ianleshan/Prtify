package com.prtify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prtify.android.R;
import com.prtify.android.response.Device;

import java.util.List;

/**
 * Created by leshan on 11/4/17.
 */

public class DeviceListAdapter  extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {

    private List<Device> mDataset;
    OnItemClickListener onItemClickListener;

    @Override
    public DeviceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.temp_devices_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DeviceListAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position).getName());
        Log.d("DEVICE", mDataset.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position, mDataset.get(position).getId());
            }
        });
    }

    public DeviceListAdapter(List<Device> mDataset, OnItemClickListener onItemClickListener) {
        this.mDataset = mDataset;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.device_name);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int position, String id);
    }
}
