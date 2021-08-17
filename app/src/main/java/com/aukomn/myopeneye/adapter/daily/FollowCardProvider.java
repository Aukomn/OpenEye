package com.aukomn.myopeneye.adapter.daily;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.Daily;
import com.aukomn.myopeneye.ui.VideoPlayActivity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class FollowCardProvider extends BaseItemProvider<Daily.Item> {
    private Context context;
    public FollowCardProvider(Activity activity){
        this.context=activity;
    }

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_daily;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, Daily.Item item) {
        try {
            Glide.with(getContext()).load(item.getData().getContent().getData().getCover().getFeed())
                    .into((ImageView) baseViewHolder.getView(R.id.imgdaily));
            baseViewHolder.setText(R.id.item_daily, item.getData().getContent().getData().getTitle());
        }catch (NullPointerException e){

        }
        baseViewHolder.getView(R.id.imgdaily).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, VideoPlayActivity.class);
                i.putExtra("url",item.getData().getContent().getData().getPlayUrl());
                i.putExtra("cover",item.getData().getContent().getData().getCover().getFeed());
                i.putExtra("title",item.getData().getContent().getData().getTitle());
                context.startActivity(i);
            }
        });

    }
}
