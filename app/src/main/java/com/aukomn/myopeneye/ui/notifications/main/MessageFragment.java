package com.aukomn.myopeneye.ui.notifications.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.adapter.MesAdapter;
import com.aukomn.myopeneye.bean.Message;
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
public class MessageFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private PageViewModel viewModel;
    private String url="http://baobab.kaiyanapp.com/api/v3/messages";
    private MesAdapter adapter;
    SmartRefreshLayout refreshLayout;
    private Boolean isfirst=true;
    private String next;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MessageFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MessageFragment newInstance(int columnCount) {
        MessageFragment fragment = new MessageFragment();
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        adapter=new MesAdapter(R.layout.item_message);
        // Set the adapter
       // if (view instanceof RecyclerView) {
            Context context = view.getContext();
            refreshLayout=view.findViewById(R.id.refreshms);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listms);
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
               viewModel.getMes(url).observe(getViewLifecycleOwner(), new Observer<Message>() {
                   @Override
                   public void onChanged(Message messageListBeans) {
                       if(messageListBeans!=null){
                           adapter.setNewData(messageListBeans.getMessageList());
                           adapter.notifyDataSetChanged();
                           next=messageListBeans.getNextPageUrl();
                           refreshLayout.finishRefresh();
                       }
                   }
               });
           }
       });
      refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
          @Override
          public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
              viewModel.getMes(next).observe(getViewLifecycleOwner(), new Observer<Message>() {
                  @Override
                  public void onChanged(Message messageListBeans) {
                      if(messageListBeans!=null){
                          adapter.addData(messageListBeans.getMessageList());
                          adapter.notifyDataSetChanged();
                          next=messageListBeans.getNextPageUrl();

                      }
                  }
              });
              refreshLayout.finishLoadMore();
          }
      });
        return view;
    }
}