package com.aukomn.myopeneye.adapter;

import android.widget.ImageView;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.ThemesContent;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import androidx.annotation.NonNull;

public class ThemeAdapter extends BaseQuickAdapter<ThemesContent.ItemListBean, BaseViewHolder> {
    public ThemeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ThemesContent.ItemListBean item) {
        helper.setText(R.id.titletheme,item.getData().getTitle());
        Glide.with(getContext()).load(item.getData().getIcon()).into((ImageView) helper.getView(R.id.imgtheme));
        helper.setText(R.id.contentheme,item.getData().getDescription());
    }
}
