package com.aukomn.myopeneye.ui.home.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.adapter.daily.DailyAdapter;
import com.aukomn.myopeneye.bean.Daily;
import com.aukomn.myopeneye.constants.Constants;
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
public class DailyFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String url=Constants.VideoUrl.base_url+Constants.VideoUrl.feed;
    private PageViewModel pageViewModel;
    private DailyAdapter adapter;
    SmartRefreshLayout refreshLayout;
    private String next;
    private Boolean isfirst=true;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DailyFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DailyFragment newInstance(int columnCount) {
        DailyFragment fragment = new DailyFragment();
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
        View view = inflater.inflate(R.layout.fragment_daily_list, container, false);
        // Set the adapter
     //   if (view instanceof RecyclerView) {
            Context context = view.getContext();
            refreshLayout=view.findViewById(R.id.refreshday);
            adapter=new DailyAdapter(getActivity());
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listdaily);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);

            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    pageViewModel.getDaily(url).observe(getViewLifecycleOwner(), new Observer<Daily>() {
                        @Override
                        public void onChanged(Daily daily) {
                            adapter.setNewData(daily.getItemList());
                            adapter.notifyDataSetChanged();
                            next=daily.getNextPageUrl();
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
                    if(next==null)refreshLayout.finishLoadMoreWithNoMoreData();
                    pageViewModel.getDaily(next).observe(getViewLifecycleOwner(), new Observer<Daily>() {
                        @Override
                        public void onChanged(Daily daily) {
                            adapter.addData(daily.getItemList());
                            adapter.notifyDataSetChanged();
                            next=daily.getNextPageUrl();
                            refreshLayout.finishLoadMore();
                        }
                    });
                }
            });
        return view;
    }
}