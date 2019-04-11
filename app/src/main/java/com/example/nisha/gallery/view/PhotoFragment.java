package com.example.nisha.gallery.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nisha.gallery.R;
import com.squareup.picasso.Picasso;

import static com.google.common.base.Preconditions.checkNotNull;

public class PhotoFragment extends Fragment implements PhotoContract.View {

    private ImageView mImageView;
    private PhotoContract.Presenter mPresenter;
    private String photoUrl;
    private String photoTitle;

    public PhotoFragment() {
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_photo_detail, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(photoTitle);

        mImageView = root.findViewById(R.id.photo_detail);
        Picasso.get()
                .load(photoUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(mImageView);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
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
    public void showTitle(String title) {
        photoTitle = title;
    }

    @Override
    public void showPhoto(String url) {
        photoUrl = url;

    }

    @Override
    public void setPresenter(PhotoContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
