package com.aukomn.myopeneye.adapter;

import android.widget.ImageView;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.CommunityRecommend;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import androidx.annotation.NonNull;

public class CrecAdapter extends BaseQuickAdapter<CommunityRecommend.Item, BaseViewHolder> {
    public CrecAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommunityRecommend.Item item) {
        if(item.getData().getContent()==null)return;
        Glide.with(getContext()).load(item.getData().getContent().getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.imgcrec));
    }
}
