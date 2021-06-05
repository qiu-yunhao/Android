package com.example.mvprxjava.presenter;

import com.example.mvprxjava.bean.USER;
import com.example.mvprxjava.model.HandleRepo;
import com.example.mvprxjava.model.RepoListener;
import com.example.mvprxjava.view.HandleView;
import java.util.List;

public class Presenter {
    private HandleRepo handleRepo;
    private HandleView handleView;

    public Presenter(HandleView handleView){
        this.handleRepo = new HandleRepo().GetNewRepo();
        this.handleView = handleView;
    }

    public void GetTheRepo(String lang,String since){
        handleRepo.GetRope(new RepoListener() {
            @Override
            public void RespondSuccess(List<USER.ItemsDTO> list) {
                if(list.size()==0){
                    handleView.ShowError();
                }
                else{
                    handleView.ShowRepoInfo(list);
                }
            }

            @Override
            public void RespondOver() {

            }

            @Override
            public void RespondFailure() {
                handleView.ShowError();

            }

            @Override
            public void GetRope(RepoListener repoListener, String lang, String since) {

            }
        }, lang, since);
    }
}
