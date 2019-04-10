package com.example.nisha.gallery.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nisha.gallery.R;
import com.example.nisha.gallery.data.Album;
import com.example.nisha.gallery.data.Photo;
import com.example.nisha.gallery.data.source.AlbumDetailsIntractor;
import com.example.nisha.gallery.data.source.AlbumsIntractor;
import com.example.nisha.gallery.presenter.AlbumDetailPresenter;
import com.example.nisha.gallery.presenter.AlbumsPresenter;
import com.example.nisha.gallery.presenter.PhotoPresenter;
import com.example.nisha.gallery.util.ActivityUtils;

public class MainActivity extends AppCompatActivity implements AlbumsFragment.OnAlbumClickedListener, AlbumDetailFragment.OnThumbnailClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
        if (fragment == null) {
            fragment = AlbumsFragment.newInstance();
            new AlbumsPresenter((AlbumsFragment) fragment, new AlbumsIntractor());
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.content);
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof AlbumsFragment) {
            ((AlbumsFragment) fragment).setOnAlbumClickedListener(this);
        } else if (fragment instanceof AlbumDetailFragment) {
            ((AlbumDetailFragment) fragment).setOnThumbnailClickedListener(this);
        }
    }

    @Override
    public void onAlbumSelected(Album album) {
        AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.newInstance();
        new AlbumDetailPresenter(album, albumDetailFragment, new AlbumDetailsIntractor());
        ActivityUtils.replaceFragment(getSupportFragmentManager(), albumDetailFragment, R.id.content);
    }

    @Override
    public void onThumbnailClicked(Photo photo) {
        PhotoFragment photoFragment = PhotoFragment.newInstance();
        new PhotoPresenter(photo, photoFragment);
        ActivityUtils.replaceFragment(getSupportFragmentManager(), photoFragment, R.id.content);
    }
}
