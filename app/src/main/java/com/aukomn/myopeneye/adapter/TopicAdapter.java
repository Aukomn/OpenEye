package com.aukomn.myopeneye.adapter;

import android.widget.ImageView;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.TopicBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import androidx.annotation.NonNull;

public class TopicAdapter extends BaseQuickAdapter<TopicBean.ItemListBean, BaseViewHolder> {
    public TopicAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TopicBean.ItemListBean item) {
        helper.setText(R.id.item_number,item.getData().getTitle());
        helper.setText(R.id.content,item.getData().getViewCount()+"浏览/"+item.getData().getJoinCount()+"参与");
        Glide.with(getContext()).load(item.getData().getImageUrl()).into((ImageView) helper.getView(R.id.imgtop));
    }
}
