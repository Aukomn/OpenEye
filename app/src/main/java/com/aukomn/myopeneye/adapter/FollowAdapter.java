package com.aukomn.myopeneye.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.Follow;
import com.aukomn.myopeneye.ui.VideoPlayActivity;
import com.aukomn.myopeneye.view.CoverVideoPlayerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import androidx.annotation.NonNull;

public class FollowAdapter extends BaseQuickAdapter<Follow.Item, BaseViewHolder> {
    private Context context;
    public FollowAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
        this.context=activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Follow.Item item) {
        ((CoverVideoPlayerView)helper.getView(R.id.video_player_item)).getBackButton().setVisibility(View.INVISIBLE);
        helper.setText(R.id.item_follow,item.getData().getContent().getData().getTitle());
        ((CoverVideoPlayerView)helper.getView(R.id.video_player_item)).loadCoverImage(item.getData().getContent().getData().getCover().getFeed(),4);
        Glide.with(getContext()).load(item.getData().getHeader().getIcon()).into((ImageView) helper.getView(R.id.imageV));
        helper.setText(R.id.followauthor,item.getData().getContent().getData().getDescription().toString());
        GSYVideoOptionBuilder gsyVideoOptionBuilder =
                new GSYVideoOptionBuilder();
        gsyVideoOptionBuilder.setIsTouchWiget(false)
                .setUrl(item.getData().getContent().getData().getPlayUrl())
                //  .setVideoTitle(item.getData().getTitle())
                .setCacheWithPlay(false)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag("2")
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setPlayPosition(helper.getAdapterPosition())
                .setVideoAllCallBack(new GSYSampleCallBack(){

                }).build((StandardGSYVideoPlayer) helper.getView(R.id.video_player_item));
        ((CoverVideoPlayerView)helper.getView(R.id.video_player_item)).getThumbImageViewLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, VideoPlayActivity.class);
                i.putExtra("url",item.getData().getContent().getData().getPlayUrl());
                i.putExtra("cover",item.getData().getContent().getData().getCover().getFeed());
                i.putExtra("title",item.getData().getContent().getData().getTitle());
                context.startActivity(i);
            }
        });
        (helper.getView(R.id.linearLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, VideoPlayActivity.class);
                i.putExtra("url",item.getData().getContent().getData().getPlayUrl());
                i.putExtra("cover",item.getData().getContent().getData().getCover().getFeed());
                i.putExtra("title",item.getData().getContent().getData().getTitle());
                context.startActivity(i);
            }
        });
        ((CoverVideoPlayerView)helper.getView(R.id.video_player_item)).rvContent.setOnClickListener(new View.OnClickListener() {
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
