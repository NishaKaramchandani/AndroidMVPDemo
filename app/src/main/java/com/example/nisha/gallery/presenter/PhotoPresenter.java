package com.example.nisha.gallery.presenter;

import com.example.nisha.gallery.data.Photo;
import com.example.nisha.gallery.view.PhotoContract;

public class PhotoPresenter implements PhotoContract.Presenter {

    private PhotoContract.View mView;

    public PhotoPresenter(Photo photo, PhotoContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        mView.showPhoto(photo.getUrl());
        mView.showTitle(photo.getTitle());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }
}
