package com.example.mvprxjava.model;

import com.example.mvprxjava.bean.USER;

import java.util.List;

public interface RepoListener {
    void RespondSuccess(List<USER.ItemsDTO> list);
    void RespondOver();
    void RespondFailure();
}
