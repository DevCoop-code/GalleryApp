package com.dino.galleryapp.media;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.dino.galleryapp.data.MediaData;

import java.util.ArrayList;

public class GetMediaData {
    public static ArrayList<MediaData> getImageArrayData(Context context){
        ArrayList<MediaData> mediaList = null;

        Cursor imgSearchCursor = null;

        String[] imagecolumns = {
                MediaStore.Images.Media._ID,            //ID
                MediaStore.Images.Media.DATA,           //Path
                MediaStore.Images.Media.ORIENTATION,    //orientation
                MediaStore.Images.Media.DATE_ADDED      //Time about added(s)
        };

        imgSearchCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                imagecolumns,
                null
                ,null,
                null
        );

        if(imgSearchCursor != null && imgSearchCursor.moveToFirst()){
            mediaList = new ArrayList<MediaData>();

            int imageIdColumnIndex = imgSearchCursor.getColumnIndex(MediaStore.Images.Media._ID);
            int imagePathColumnIndex = imgSearchCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            int imageOrientationColumnIndex = imgSearchCursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION);
            int imageDateColumnIndex = imgSearchCursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);

            do{
                mediaList.add(new MediaData(
                        imgSearchCursor.getInt(imageIdColumnIndex),
                        imgSearchCursor.getString(imagePathColumnIndex),
                        imgSearchCursor.getInt(imageOrientationColumnIndex)
                ));
            }while(imgSearchCursor.moveToNext());
        }

        return mediaList;
    }
}
