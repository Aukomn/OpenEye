package com.aukomn.myopeneye.ui.home.ui.main;

import com.aukomn.myopeneye.bean.AllRec;
import com.aukomn.myopeneye.http.LoadUtil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRecViewModel extends ViewModel {
    public MutableLiveData<AllRec> getAllRec(String url){
        final MutableLiveData<AllRec>data=new MutableLiveData<>();
        LoadUtil.getService().getAll(url)
                .enqueue(new Callback<AllRec>() {
                    @Override
                    public void onResponse(Call<AllRec> call, Response<AllRec> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<AllRec> call, Throwable t) {

                    }
                });
        return data;
    }
}
