package com.example.mvprxjava.view;

import com.example.mvprxjava.bean.USER;

import java.util.List;

public interface HandleView {
    void ShowRepoInfo(List<USER.ItemsDTO> dtoList);
    void ShowError();
}
