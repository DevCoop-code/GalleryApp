package com.dino.galleryapp.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.dino.galleryapp.data.MediaData;
import com.dino.galleryapp.utils.ImageUtils;

import java.util.HashMap;

public class ImageRunnable implements Runnable {
    private MediaData mMediaData = null;
    private HashMap<ImageView, String> mImageHashMap = null;

    public ImageRunnable(MediaData mediaData, HashMap<ImageView, String> imageViewMap){
        mMediaData = mediaData;
        mImageHashMap = imageViewMap;
    }

    @Override
    public void run(){
        Bitmap bmp = null;

        bmp = ImageUtils.getImageThumbnail(mMediaData);

        ImageViewRunnable imageViewRunnable = new ImageViewRunnable(bmp, mMediaData, mImageHashMap);

        Activity activity = (Activity)mMediaData.getImgview().getContext();
        activity.runOnUiThread(imageViewRunnable);
    }

    private boolean isImageViewValid(MediaData mediaData){
        String mediaPath = mImageHashMap.get(mediaData.getImgview());

        if(mediaPath == null || mediaPath.equals(mediaData.getMediapath())){
            return false;
        }
        return true;
    }
}
