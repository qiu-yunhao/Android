package com.example.mvprxjava.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvprxjava.R;
import com.example.mvprxjava.bean.USER;
import com.example.mvprxjava.view.RoundImageView;

public class ViewHolder_choose extends RecyclerView.ViewHolder implements View.OnClickListener {
    public RoundImageView imageView;
    public TextView textView_name;
    public TextView textView_introduction;
    public TextView textView_link;
    public ImageView imageView1;
    public ImageView imageView2;
    public ImageView imageView3;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public LinearLayout linearLayout;
    public ViewHolder_choose(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_view_2, parent, false));
        init();
        hide();
    }

    public void init(){
        imageView = (RoundImageView) itemView.findViewById(R.id.imageView_choose);
        textView_name = (TextView) itemView.findViewById(R.id.name_choose);
        textView_introduction = (TextView) itemView.findViewById(R.id.introduction_choose);
        textView_link = (TextView)itemView.findViewById(R.id.link_choose);
        imageView1 = (ImageView)itemView.findViewById(R.id.image1_choose);
        textView1 = (TextView)itemView.findViewById(R.id.text_choose_1);
        imageView2 = (ImageView)itemView.findViewById(R.id.image2_choose);
        textView2 = (TextView)itemView.findViewById(R.id.text_choose_2);
        imageView3 = (ImageView)itemView.findViewById(R.id.image3_choose);
        textView3 = (TextView)itemView.findViewById(R.id.text_choose_3);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout_hide);

        itemView.setOnClickListener(this);
    }
    public void hide(){
        textView_link.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
        imageView1.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
    }

    public void bind(USER.ItemsDTO userDate) {
        char[] s = userDate.getRepo().toCharArray();
        int key = 0;
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '/') {
                key = 1;
                continue;
            }
            if (key == 0) {
                stringBuilder1.append(s[i]);
            } else {
                stringBuilder2.append(s[i]);
            }
        }
        textView_name.setText(stringBuilder1);
        textView_introduction.setText(stringBuilder2);
        textView_link.setText(userDate.getRepo_link());
        textView1.setText(userDate.getLang());
        textView2.setText(userDate.getStars());
        textView3.setText(userDate.getForks());
    }


    @Override
    public void onClick(View v) {
        textView_link.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        imageView1.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
    }
}
