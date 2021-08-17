package com.aukomn.myopeneye.ui.home.ui.main;

import com.aukomn.myopeneye.bean.Daily;
import com.aukomn.myopeneye.bean.HomePageRecommend;
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
    public MutableLiveData<Daily>getDaily(String url){
        final MutableLiveData<Daily>data=new MutableLiveData<>();
        LoadUtil.getService().getDaily(url)
                .enqueue(new Callback<Daily>() {
                    @Override
                    public void onResponse(Call<Daily> call, Response<Daily> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Daily> call, Throwable t) {

                    }
                });
        return data;
    }
    public LiveData<HomePageRecommend>getHomecommand(String url){
        final MutableLiveData<HomePageRecommend>data=new MutableLiveData<>();
        LoadUtil.getService().getCommand(url)
                .enqueue(new Callback<HomePageRecommend>() {
                    @Override
                    public void onResponse(Call<HomePageRecommend> call, Response<HomePageRecommend> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<HomePageRecommend> call, Throwable t) {

                    }
                });
        return data;
    }
}