package com.example.nisha.gallery.view;

import com.example.nisha.gallery.BasePresenter;
import com.example.nisha.gallery.BaseView;

public interface PhotoContract {

    interface View extends BaseView<Presenter>{
        void showTitle(String title);
        void showPhoto(String url);
    }

    interface Presenter extends BasePresenter{

    }
}
