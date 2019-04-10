package com.example.nisha.gallery.presenter;

import android.util.Log;

import com.example.nisha.gallery.data.Album;
import com.example.nisha.gallery.view.AlbumsContract;

import java.util.List;


public class AlbumsPresenter implements AlbumsContract.Presenter, AlbumsContract.Intractor.OnFinishedListener {

    private AlbumsContract.View view;
    private AlbumsContract.Intractor intractor;
    private static final String TAG = "AlbumsPresenter";

    public AlbumsPresenter(AlbumsContract.View mainView, AlbumsContract.Intractor intractor) {
        this.view = mainView;
        this.intractor = intractor;

        view.setPresenter(this);
    }

    @Override
    public void loadAlbums() {
        intractor.getAlbumsList(this);
    }

    @Override
    public void onFinished(List<Album> albums) {
        if (this.view != null) {
            view.showAlbums(albums);
        }
    }

    @Override
    public void navigateToAlbumDetails(Album album) {
        view.showAlbumDetailsUI(album);
    }

    @Override
    public void onFailure(Throwable t) {
        if (this.view != null) {
            //Show error dialog on UI
        }
    }

    @Override
    public void onCancel() {
        Log.d(TAG, "onCancel: Request Cancelled!");
    }

    @Override
    public void start() {
        loadAlbums();
    }

    @Override
    public void stop() {
        intractor.cancelAlbumsLoad(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
