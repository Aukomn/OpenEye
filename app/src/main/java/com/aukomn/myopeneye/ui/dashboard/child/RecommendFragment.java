package com.aukomn.myopeneye.ui.dashboard.child;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.adapter.CrecAdapter;
import com.aukomn.myopeneye.bean.CommunityRecommend;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * A fragment representing a list of Items.
 */
public class RecommendFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    SmartRefreshLayout refreshLayout;
    private PageViewModel pageViewModel;
    private CrecAdapter adapter;
    private Boolean isfirst=true;
    private String url="http://baobab.kaiyanapp.com/api/v7/community/tab/rec?startScore=1599830283000&pageCount=2";
    private String next="http://baobab.kaiyanapp.com/api/v7/community/tab/rec?startScore=1599830283000&pageCount=2";
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecommendFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecommendFragment newInstance(int columnCount) {
        RecommendFragment fragment = new RecommendFragment();
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
        View view = inflater.inflate(R.layout.fragment_recommend_list, container, false);
        // Set the adapter
        //if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = view.findViewById(R.id.listcrec);
            refreshLayout= view.findViewById(R.id.refreshcrec);
            adapter=new CrecAdapter(R.layout.item_recommend);
            recyclerView.setHasFixedSize(true);
            StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(layoutManager);
            }
            recyclerView.setAdapter(adapter);
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    pageViewModel.getCRec(url).observe(getViewLifecycleOwner(), new Observer<CommunityRecommend>() {
                        @Override
                        public void onChanged(CommunityRecommend communityRecommend) {
                            communityRecommend.getItemList();
                            adapter.setNewData(communityRecommend.getItemList());
                            adapter.notifyDataSetChanged();
                            next=communityRecommend.getNextPageUrl();
                            refreshLayout.finishRefresh();
                        }
                    });
                }
            });
      if(isfirst){
          refreshLayout.autoRefresh();
          isfirst=false;
      }
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageViewModel.getCRec(next).observe(getViewLifecycleOwner(), new Observer<CommunityRecommend>() {
                    @Override
                    public void onChanged(CommunityRecommend communityRecommend) {
                        communityRecommend.getItemList();
                        adapter.addData(communityRecommend.getItemList());
                        adapter.notifyDataSetChanged();
                        next=communityRecommend.getNextPageUrl();
                        refreshLayout.finishLoadMore();
                    }
                });
            }
        });
        return view;
    }
}