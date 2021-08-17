package com.aukomn.myopeneye.ui.notifications.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.adapter.TopicAdapter;
import com.aukomn.myopeneye.bean.TopicBean;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

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
public class TopicFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private PageViewModel viewModel;
    private String url="https://baobab.kaiyanapp.com/api/v7/topic/list";
    private TopicAdapter adapter;
    SmartRefreshLayout refreshLayout;
    private Boolean isfirst=true;
    private String next;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TopicFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TopicFragment newInstance(int columnCount) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= ViewModelProviders.of(this).get(PageViewModel.class);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        adapter=new TopicAdapter(R.layout.item_topic);
        // Set the adapter
        //if (view instanceof RecyclerView) {
        refreshLayout=view.findViewById(R.id.refreshtop);
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listtop);
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
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                viewModel.getTop(url).observe(getViewLifecycleOwner(), new Observer<TopicBean>() {
                    @Override
                    public void onChanged(TopicBean topicBean) {
                        if(topicBean!=null){
                            adapter.setNewData(topicBean.getItemList());
                            adapter.notifyDataSetChanged();
                            next=topicBean.getNextPageUrl();
                            refreshLayout.finishRefresh();
                        }
                    }
                });
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                if(next==null) refreshLayout.finishLoadMoreWithNoMoreData();
                viewModel.getTop(next).observe(getViewLifecycleOwner(), new Observer<TopicBean>() {
                    @Override
                    public void onChanged(TopicBean topicBean) {
                        if(topicBean!=null){
                            adapter.addData(topicBean.getItemList());
                            adapter.notifyDataSetChanged();
                            next=topicBean.getNextPageUrl();
                            refreshLayout.finishLoadMore();
                        }
                    }
                });

            }
        });
        return view;
    }
}