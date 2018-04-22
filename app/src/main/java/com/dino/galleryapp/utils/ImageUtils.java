package com.dino.galleryapp.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.provider.MediaStore;

import com.dino.galleryapp.data.MediaData;

public class ImageUtils {
    public static Bitmap getImageThumbnail(MediaData mediaData){
        ContentResolver thumbnailCR = mediaData.getImgview().getContext().getContentResolver();
        Bitmap thumbnailBM = MediaStore.Images.Thumbnails.getThumbnail(
                thumbnailCR,
                mediaData.getMediaid(),
                MediaStore.Images.Thumbnails.MINI_KIND,
                null
        );
        return getCenterBitmap(thumbnailBM, mediaData.getOrientation());
    }

    //Rotate Image
    private static Bitmap getRotateBitmap(Bitmap bitmap, int orientation){
        if(bitmap == null){
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();

        if(orientation != 0){
            matrix.setRotate(orientation);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }

    //Get Thumbnail Image
    private static Bitmap getCenterBitmap(Bitmap bitmap, int orientation){
        if(bitmap == null){
            return null;
        }

        Bitmap thumb_bitmap = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();

        if(orientation != 0){
            matrix.setRotate(orientation);
        }

        if(width >= height){
            thumb_bitmap = Bitmap.createBitmap(bitmap, width/2 - height/2, 0, height, height, matrix, true);
        }else{
            thumb_bitmap = Bitmap.createBitmap(bitmap, 0, height/2 - width/2, width, width, matrix, true);
        }
        return thumb_bitmap;
    }

    //Get Origin Image
    public static Bitmap getImageBitmap(String imagePath, int orientation){
        try{
            Bitmap displayImg = null;

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            displayImg = BitmapFactory.decodeFile(imagePath, options);

            return getRotateBitmap(displayImg, orientation);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}