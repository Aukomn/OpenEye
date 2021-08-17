package com.aukomn.myopeneye.ui.dashboard.child;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.adapter.FollowAdapter;
import com.aukomn.myopeneye.bean.Follow;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 */
public class FollowFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private PageViewModel pageViewModel;
    private FollowAdapter adapter;
    private String url="http://baobab.kaiyanapp.com/api/v6/community/tab/follow";
    private SmartRefreshLayout refreshLayout;
    private String next;
    private Boolean isfirst=true;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FollowFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FollowFragment newInstance(int columnCount) {
        FollowFragment fragment = new FollowFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_list, container, false);
        // Set the adapter
        // if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listfollow);
            refreshLayout=view.findViewById(R.id.refreshfollow);
            adapter=new FollowAdapter(R.layout.item_follow,getActivity());
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);

        if(isfirst){
            refreshLayout.autoRefresh();
            isfirst=false;
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageViewModel.getFollow(url).observe(getViewLifecycleOwner(), new Observer<Follow>() {
                    @Override
                    public void onChanged(Follow follow) {
                        if(follow!=null) {
                            adapter.setNewData(follow.getItemList());
                            adapter.notifyDataSetChanged();
                            next=follow.getNextPageUrl();
                            refreshLayout.finishRefresh();
                        }
                    }
                });
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(next==null)return;
                pageViewModel.getFollow(next).observe(getViewLifecycleOwner(), new Observer<Follow>() {
                    @Override
                    public void onChanged(Follow follow) {
                        if(follow!=null) {
                            adapter.addData(follow.getItemList());
                            adapter.notifyDataSetChanged();
                            next=follow.getNextPageUrl();
                            refreshLayout.finishLoadMore();
                        }
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}