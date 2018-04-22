package com.dino.galleryapp.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.dino.galleryapp.R;
import com.dino.galleryapp.data.MediaData;

import java.util.HashMap;

public class ImageViewRunnable implements Runnable {
    private final int D_RES_ID = R.drawable.defaultimage;
    private Bitmap mBitmap = null;
    private MediaData mMediaData = null;
    private HashMap<ImageView, String> mImageViewMap = null;

    public ImageViewRunnable(Bitmap bitmap, MediaData mediaData, HashMap<ImageView, String> imageViewMap){
        mBitmap = bitmap;
        mMediaData = mediaData;
        mImageViewMap = imageViewMap;
    }

    public void run(){
        if(isImageViewValid() == false){
            return;
        }

        if(mBitmap != null){
            mMediaData.getImgview().setImageBitmap(mBitmap);
        }else{
            mMediaData.getImgview().setImageResource(D_RES_ID);
        }
    }

    private boolean isImageViewValid(){
        String mediaPath = mImageViewMap.get(mMediaData.getImgview());
        if(mediaPath == null || mediaPath.equals(mMediaData.getMediapath()) == false){
            return false;
        }
        return true;
    }
}
