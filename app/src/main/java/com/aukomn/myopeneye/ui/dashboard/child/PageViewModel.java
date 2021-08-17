package com.aukomn.myopeneye.ui.dashboard.child;

import android.util.Log;

import com.aukomn.myopeneye.bean.CommunityRecommend;
import com.aukomn.myopeneye.bean.Follow;
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
    public MutableLiveData<CommunityRecommend>getCRec(String url){
        final MutableLiveData<CommunityRecommend>data=new MutableLiveData<>();
        LoadUtil.getService().getCrec(url)
                .enqueue(new Callback<CommunityRecommend>() {
                    @Override
                    public void onResponse(Call<CommunityRecommend> call, Response<CommunityRecommend> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                        else Log.e("---------",1111+"");
                    }

                    @Override
                    public void onFailure(Call<CommunityRecommend> call, Throwable t) {
                            Log.e("---------",1111+"");
                    }
                });
        return data;
    }
    public MutableLiveData<Follow>getFollow(String url){
        final MutableLiveData<Follow>data=new MutableLiveData<>();
        LoadUtil.getService().getFollow(url)
                .enqueue(new Callback<Follow>() {
                    @Override
                    public void onResponse(Call<Follow> call, Response<Follow> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Follow> call, Throwable t) {

                    }
                });
        return data;
    }
}