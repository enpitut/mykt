package com.enpit.mykt.Global;

//import com.enpit.mykt.R;

/**
 * Created by kenichiro on 2015/08/28.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.enpit.mykt.Fragment.ScheduleToolsFragment;
import com.enpit.mykt.R;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder> {
    private ScheduleToolsFragment.datasets[] mDataset;
    private Context mContext;

    public RecyclerAdapter(ScheduleToolsFragment.datasets[] dataset, Context context) {
        mDataset = dataset;
        mContext = context;
    }

    // Not use static
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            mContext,
                            "onItemClick - " + getPosition() + " - "
                                    + mTextView.getText().toString() + " - "
                                    + mDataset[getPosition()], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setBackgroundColor(mDataset[position].color);
        holder.mTextView.setText(mDataset[position].item_name);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}