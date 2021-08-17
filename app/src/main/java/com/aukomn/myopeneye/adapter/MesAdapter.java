package com.aukomn.myopeneye.adapter;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.Message;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import androidx.annotation.NonNull;

public class MesAdapter extends BaseQuickAdapter<Message.MessageListBean, BaseViewHolder> {
    public MesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Message.MessageListBean item) {
        helper.setText(R.id.mstitle,item.getTitle());
        helper.setText(R.id.contentms,item.getContent());
    }
}
