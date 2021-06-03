package com.example.mvprxjava.view.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mvprxjava.Base.BaseFragment;
import com.example.mvprxjava.R;

import static com.example.mvprxjava.R.id.Start_button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String lang;
    private String since;
    private int num;

    private Button button;

    public StartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(String param1, String param2,int num) {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt("GetNUM",num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lang = getArguments().getString(ARG_PARAM1);
            since = getArguments().getString(ARG_PARAM2);
            num = getArguments().getInt("GetNUM");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        init(v);
        return v;
    }

    public void init(View v){
        button = (Button)v.findViewById(Start_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case Start_button:
                TurnToMain(lang,since);
                break;
        }
    }

}