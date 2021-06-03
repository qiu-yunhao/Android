package com.example.mvprxjava.view.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvprxjava.Base.BaseFragment;
import com.example.mvprxjava.R;
import com.example.mvprxjava.bean.USER;

import java.util.List;

public class Without_wlan_Fragment extends BaseFragment implements View.OnClickListener {

    private TextView textView;
    private ImageView imageView;
    private Button button;
    private Main_Fragment fragment1;
    private List<USER> list;

    public Without_wlan_Fragment() {
        // Required empty public constructor
    }

    public static Without_wlan_Fragment newInstance() {
        Without_wlan_Fragment fragment = new Without_wlan_Fragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_without_wlan_, container, false);
        init(v);
        return v;
    }

    public void init(View view){
        textView = (TextView)view.findViewById(R.id.without_wlan_txt);
        imageView = (ImageView)view.findViewById(R.id.without_wlan_image);
        button = (Button)view.findViewById(R.id.without_wlan_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //再次网络请求
        //getInternetContent();
    }
/*
    public void getInternetContent() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Content content = retrofit.create(Content.class);
        content.getRepo("java","weekly")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<USER>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getContext(), "获取信息成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(USER user) {

                    }


                });
    }

    public void TurnToMainFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (fragment1 != null) {
            fragmentTransaction.show(fragment1);
        } else {
            fragment1 = Main_Fragment.newInstance();
            fragmentTransaction.add(R.id.first_fragment, fragment1);
        }
        fragmentTransaction.commit();
    }*/
}