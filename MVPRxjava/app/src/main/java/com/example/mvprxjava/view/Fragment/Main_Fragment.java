package com.example.mvprxjava.view.Fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvprxjava.Base.BaseFragment;
import com.example.mvprxjava.R;
import com.example.mvprxjava.presenter.User_Adapter;
import com.example.mvprxjava.bean.Developer;
import com.example.mvprxjava.bean.USER;
import com.example.mvprxjava.model.Content;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Main_Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private final  static String KEY = "GET_key";
    private final static String S1 = "GET_MAIN_FRAGMENT_S1";
    private final static String S2 = "GET_MAIN_FRAGMENT_S2";
    private int num;
    private USER use;
    private List<USER.ItemsDTO> list;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private User_Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View v;
    private String lang;
    private String since;
    public Main_Fragment() {
        // Required empty public constructor
    }

    public static Main_Fragment newInstance(String s1,String s2) {
        Main_Fragment fragment = new Main_Fragment();
        Bundle args = new Bundle();
        args.putString(S1,s1);
        args.putString(S2,s2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        num = getArguments().getInt(KEY);
        lang = getArguments().getString(S1);
        since = getArguments().getString(S2);
        // Inflate the layout for this fragment
        v  = inflater.inflate(R.layout.fragment_main_, container, false);
        GetRepo(lang,since);
        return v;
    }

    public void init(View v){
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeFreshLayout);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        initSWipe();
        Log.d("MainActivity","1");
        adapter = new User_Adapter(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        Log.d("MainActivity","2");
        recyclerView.setAdapter(adapter);
        Log.d("MainActivity","3");
    }

    public void initSWipe(){
        //swipeRefreshLayout.setColorSchemeResources();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void GetRepo(String s1,String s2){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Content content = retrofit.create(Content.class);
        /*
        content.getRepo(s1,s2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<USER>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(USER user) {
                        list = user.getItems();
                        init(v);

                    }
                });*/
        Call<USER> u = content.getRepo(s1, s2);
        u.enqueue(new Callback<USER>(){

            @Override
            public void onResponse(Call<USER> call, Response<USER> response) {
                if(response.code()==201){
                    Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();
                    list =  response.body().getItems();
                    init(v);
                }

            }

            @Override
            public void onFailure(Call<USER> call, Throwable t) {
                TurnToFailure();
                Toast.makeText(getContext(),"失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void GetDevelopers(String s1,String s2){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Content content = retrofit.create(Content.class);
        Call<Developer> u = content.getDevelopers(s1, s2);
        u.enqueue(new Callback<Developer>(){

            @Override
            public void onResponse(Call<Developer> call, Response<Developer> response) {
                Toast.makeText(getContext(),"成功",Toast.LENGTH_SHORT).show();
                init(v);
            }

            @Override
            public void onFailure(Call<Developer> call, Throwable t) {
               TurnToFailure();
            }
        });
    }


    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        Handler handler = new Handler(){
            @Override
            public void handleMessage( Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x996){
                    GetRepo(lang,since);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };

        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0x996);
            }
        }).start();
    }
}