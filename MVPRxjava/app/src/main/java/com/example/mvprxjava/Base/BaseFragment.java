package com.example.mvprxjava.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mvprxjava.R;
import com.example.mvprxjava.view.Fragment.Without_wlan_Fragment;
import com.example.mvprxjava.view.Fragment.Main_Fragment;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_, null);
        return v;
    }
    public abstract void init(View v);

    public void TurnToMain(String s1, String s2) {
        Fragment fragment = Main_Fragment.newInstance(s1,s2);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.request_fragment,fragment)
                .commit();
    }

    public void TurnToFailure(){
        Fragment fragment = Without_wlan_Fragment.newInstance();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.request_fragment,fragment)
                .commit();
    }
}
