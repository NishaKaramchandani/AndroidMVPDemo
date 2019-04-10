package com.example.nisha.gallery.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nisha.gallery.R;
import com.example.nisha.gallery.adapter.PhotosListAdapter;
import com.example.nisha.gallery.data.Photo;
import com.example.nisha.gallery.presenter.PhotoPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class AlbumDetailFragment extends Fragment implements AlbumDetailContract.View {

    private AlbumDetailContract.Presenter mPresenter;
    private RecyclerView mPhotosRecyclerView;
    private PhotosListAdapter mPhotosListAdapter;
    private PhotosListAdapter.PhotoItemListener mPhotoItemListener;
    private List<Photo> mPhotoList = new ArrayList<>();
    private PhotoPresenter mPhotoPresenter;
    private String albumTitle;
    private OnThumbnailClickedListener onThumbnailClickedListener;

    public interface OnThumbnailClickedListener {
        void onThumbnailClicked(Photo photo);
    }

    public void setOnThumbnailClickedListener(OnThumbnailClickedListener onThumbnailClickedListener) {
        this.onThumbnailClickedListener = onThumbnailClickedListener;
    }


    public AlbumDetailFragment() {
    }

    public static AlbumDetailFragment newInstance() {
        return new AlbumDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoItemListener = new PhotosListAdapter.PhotoItemListener() {
            @Override
            public void onItemClick(Photo photo) {
                mPresenter.navigateToPhotoDetails(photo);
            }
        };
        mPhotosListAdapter = new PhotosListAdapter(mPhotoList, mPhotoItemListener);
        mPresenter.start();
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_album_detail, container, false);
        mPhotosRecyclerView = (RecyclerView) root.findViewById(R.id.photo_list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mPhotosRecyclerView.setLayoutManager(layoutManager);
        mPhotosRecyclerView.setAdapter(mPhotosListAdapter);
        ((MainActivity) getActivity()).setActionBarTitle(albumTitle);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showAlbumDetails(List<Photo> photos) {
        this.mPhotoList = photos;
        mPhotosListAdapter.replaceData(mPhotoList);
    }

    @Override
    public void showPhotoDetails(Photo photo) {
        onThumbnailClickedListener.onThumbnailClicked(photo);
    }

    @Override
    public void showAlbumTitle(String title) {
        albumTitle = title;
    }

    @Override
    public void setPresenter(AlbumDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
