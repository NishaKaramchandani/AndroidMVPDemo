package com.example.nisha.gallery.presenter;

import android.util.Log;

import com.example.nisha.gallery.data.Album;
import com.example.nisha.gallery.data.Photo;
import com.example.nisha.gallery.view.AlbumDetailContract;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailPresenter implements AlbumDetailContract.Presenter, AlbumDetailContract.Intractor.OnFinishedListener {

    public AlbumDetailContract.Intractor mIntractor;
    public AlbumDetailContract.View mView;
    private Album mAlbum;

    private static final String TAG = "AlbumDetailPresenter";

    public AlbumDetailPresenter(Album album, AlbumDetailContract.View view, AlbumDetailContract.Intractor intractor) {
        this.mView = view;
        this.mIntractor = intractor;
        this.mAlbum = album;
        mView.setPresenter(this);
        mView.showAlbumTitle(album.getTitle());
    }

    @Override
    public void navigateToPhotoDetails(Photo photo) {
        if (mView != null) {
            mView.showPhotoDetails(photo);
        }
    }

    @Override
    public void start() {
        mIntractor.getAlbumDetails(mAlbum.getId(), this);
    }

    @Override
    public void stop() {
        mIntractor.cancelAlbumLoad(this);
    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }

    @Override
    public void onFinished(List<Photo> photos) {
        if (mView != null) {
            mView.showAlbumDetails(photos);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (mView != null) {
            //Show error dialog on UI
        }
    }

    @Override
    public void onCancel() {
        Log.d(TAG, "onCancel: Album Load Cancelled!!");
    }
}
