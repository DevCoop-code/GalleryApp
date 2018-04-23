package com.dino.galleryapp.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.dino.galleryapp.R;
import com.dino.galleryapp.data.MediaData;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private final int D_RES_ID = R.drawable.defaultimage;
    private ExecutorService mExecutorService = null;
    private HashMap<ImageView, String> mImageViewMap = null;

    public ImageLoader(Context context){
        mImageViewMap = new HashMap<ImageView, String>();
        mExecutorService = Executors.newFixedThreadPool(50);
    }

    public void displayImage(MediaData mediaData){
        if(mImageViewMap == null){
            return;
        }
        if(mediaData == null){
            return;
        }

        mImageViewMap.put(mediaData.getImgview(), mediaData.getMediapath());
        mExecutorService.submit(new ImageRunnable(mediaData, mImageViewMap));
        mediaData.getImgview().setImageResource(D_RES_ID);
    }
}
