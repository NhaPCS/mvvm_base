package com.nhapt.base.mvvm;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<V extends ViewDataBinding, M extends Object> extends RecyclerView.ViewHolder {
    V dataBinding;

    public BaseViewHolder(V binding) {
        super(binding.getRoot());
        dataBinding = binding;
    }

    public V getDataBinding() {
        return dataBinding;
    }

    public abstract void bind(int pos, M model);
}
