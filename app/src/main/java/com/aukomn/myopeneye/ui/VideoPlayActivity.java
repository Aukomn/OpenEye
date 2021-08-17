package com.aukomn.myopeneye.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.view.CoverVideoPlayerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class VideoPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getIntent().getStringExtra("title"));
        CoverVideoPlayerView cover=((CoverVideoPlayerView)findViewById(R.id.video_player));
        cover.getBackButton().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });
        cover.loadCoverImage(getIntent().getStringExtra("cover"),0);
        GSYVideoOptionBuilder gsyVideoOptionBuilder =
                new GSYVideoOptionBuilder();

        try {
            gsyVideoOptionBuilder
                    .setUrl(getIntent().getStringExtra("url"))
                    .setIsTouchWiget(true)
                    .setRotateViewAuto(false)
                    .setLockLand(false)
                    .setAutoFullWithSize(false)
                    .setShowFullAnimation(false)
                    .setNeedLockFull(true)
                    .setCacheWithPlay(false)
                    .setStartAfterPrepared(true)
                    .setVideoAllCallBack(new GSYSampleCallBack(){
                        @Override
                        public void onPrepared(String url, Object... objects) {
                            super.onPrepared(url, objects);

                        }
                    }).build((StandardGSYVideoPlayer) findViewById(R.id.video_player));
        }catch (NullPointerException e) {
            Log.d("1111111", getIntent().getStringExtra("url"));
        }
        ((CoverVideoPlayerView) findViewById(R.id.video_player)).startAfterPrepared();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }
}