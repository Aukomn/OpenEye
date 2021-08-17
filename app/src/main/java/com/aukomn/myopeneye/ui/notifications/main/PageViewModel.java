package com.aukomn.myopeneye.ui.notifications.main;

import android.util.Log;

import com.aukomn.myopeneye.bean.Message;
import com.aukomn.myopeneye.bean.TabInfo;
import com.aukomn.myopeneye.bean.TopicBean;
import com.aukomn.myopeneye.http.LoadUtil;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ViewModel {
    private String url="http://baobab.kaiyanapp.com/api/v7/tag/tabList";
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<TopicBean>getTop(String url){
        final MutableLiveData<TopicBean>data=new MutableLiveData<>();
        LoadUtil.getService().getTop(url)
                .enqueue(new Callback<TopicBean>() {
                    @Override
                    public void onResponse(Call<TopicBean> call, Response<TopicBean> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<TopicBean> call, Throwable t) {

                    }
                });
        return data;
    }
    public LiveData<Message>getMes(String url){
        final MutableLiveData<Message>data=new MutableLiveData<>();
        LoadUtil.getService().getMes(url)
                .enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                        else Log.e("---------",1111+"");
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Log.e("---------",t.getMessage());
                    }
                });
        return data;
    }
    public LiveData<TabInfo>getTab(){
        final MutableLiveData<TabInfo>data=new MutableLiveData<>();
        LoadUtil.getService().getTab(url)
                .enqueue(new Callback<TabInfo>() {
                    @Override
                    public void onResponse(Call<TabInfo> call, Response<TabInfo> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                        else Log.e("---------",1111+"");
                    }

                    @Override
                    public void onFailure(Call<TabInfo> call, Throwable t) {
                        Log.e("---------",t.getMessage());
                    }
                });
        return data;
    }
}