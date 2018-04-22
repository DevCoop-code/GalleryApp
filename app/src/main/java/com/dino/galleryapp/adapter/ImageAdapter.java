package com.dino.galleryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dino.galleryapp.R;
import com.dino.galleryapp.data.MediaData;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter{
    private LayoutInflater mInflater = null;
    private Context mContext = null;
    private ImageLoader mImageLoader = null;

    private ArrayList<MediaData> mMediaList = null;

    public ImageAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mImageLoader = new ImageLoader(mContext.getApplicationContext());
    }

    public void initData(ArrayList<MediaData> mediaList){
        mMediaList = mediaList;
    }

    public int getCount(){
        if(mMediaList == null){
            return 0;
        }
        return mMediaList.size();
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        MediaData mediaData = null;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.thumbnail, null);
            mediaData = new MediaData();
            mediaData.setImgview((ImageView)convertView.findViewById(R.id.thumbnail));

            //Identify the view
            convertView.setTag(mediaData);
        }else{
            mediaData = (MediaData)convertView.getTag();
        }

        if(mMediaList == null || mMediaList.size() == 0){
            return convertView;
        }

        mediaData.setMediaid(mMediaList.get(position).getMediaid());
        mediaData.setMediapath(mMediaList.get(position).getMediapath());
        mediaData.setOrientation(mMediaList.get(position).getOrientation());

        mImageLoader.displayImage(mediaData);

        return convertView;
    }
}
