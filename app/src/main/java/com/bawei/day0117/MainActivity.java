package com.bawei.day0117;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_but)
    RadioButton listBut;
    @BindView(R.id.pay_but)
    RadioButton payBut;
    @BindView(R.id.receive_but)
    RadioButton receiveBut;
    @BindView(R.id.finish_but)
    RadioButton finishBut;
    @BindView(R.id.comment_but)
    RadioButton commentBut;
    @BindView(R.id.rv)
    RecyclerView rv;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        rv.setLayoutManager(new LinearLayoutManager(this));
        getData(0);

    }

    @OnClick({R.id.list_but, R.id.pay_but, R.id.receive_but, R.id.finish_but, R.id.comment_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.list_but:
                getData(0);
                break;
            case R.id.pay_but:
                getData(1);
                break;
            case R.id.receive_but:
                getData(2);
                break;
            case R.id.finish_but:
                getData(3);
                break;
            case R.id.comment_but:
                getData(9);
                break;
        }
    }

    public void getData(int status) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(FindOrderService.class)
                .getData("27875", "157922114140727875", status, 1, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindOrderEntity>() {
                    @Override
                    public void accept(FindOrderEntity findOrderEntity) throws Exception {
                        if (findOrderEntity != null) {

                            List<FindOrderEntity.OrderListBean> orderList = findOrderEntity.getOrderList();
                            MyRvAdapter myRvAdapter = new MyRvAdapter(MainActivity.this, orderList);
                            rv.setAdapter(myRvAdapter);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

}
