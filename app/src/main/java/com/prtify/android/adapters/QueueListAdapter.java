package com.prtify.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prtify.android.R;
import com.prtify.android.objects.SongItem;
import com.prtify.android.response.Device;

import java.util.List;

/**
 * Created by leshan on 11/4/17.
 */

public class QueueListAdapter extends RecyclerView.Adapter<QueueListAdapter.ViewHolder> {

    private List<SongItem> mDataset;
    OnItemClickListener onItemClickListener;

    @Override
    public QueueListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.temp_devices_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(QueueListAdapter.ViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.type.setText(mDataset.get(position).getArtist());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onItemClickListener.onItemClick(position, mDataset.get(position).getId());
            }
        });
    }

    public QueueListAdapter(List<SongItem> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView type;
        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.device_name);
            type = v.findViewById(R.id.device_type);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int position, String id);
    }
}
