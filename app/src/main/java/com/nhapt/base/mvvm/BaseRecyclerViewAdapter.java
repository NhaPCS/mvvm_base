package com.nhapt.base.mvvm;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhapt.base.BR;

public abstract class BaseRecyclerViewAdapter<O extends Object> extends RecyclerView.Adapter<BaseViewHolder> {

    protected OnItemClickListener mItemClickListener;

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public abstract O getItem(int index);

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(holder.itemView, position);
            }
        });
        holder.dataBinding.executePendingBindings();
        holder.dataBinding.setVariable(BR.viewModel, getItem(position));
        holder.bind(position, getItem(position));
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }
}
