package com.example.andrearodriguez.androidchat.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;


/**
 * Created by andrearodriguez on 6/17/16.
 */
public class GladeImageLoader implements ImageLoader {

    private RequestManager requestManager;

    public GladeImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imgAvatar, String url) {
        requestManager.load(url).into(imgAvatar);

    }
}
