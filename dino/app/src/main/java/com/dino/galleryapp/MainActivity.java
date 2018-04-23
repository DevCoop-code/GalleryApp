package com.dino.galleryapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.dino.galleryapp.adapter.ImageAdapter;
import com.dino.galleryapp.data.MediaData;
import com.dino.galleryapp.media.GetMediaData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView mGridview;
    private ArrayList<MediaData> mMediaList = null;
    private Context mContext;

    private final int REQUEST_CODE_STORAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        mContext = this;

        checkPermissionCheck();
    }

    private void setupUI(){
        mGridview = findViewById(R.id.gridview);
    }

    private void init(){
        mMediaList = GetMediaData.getImageArrayData(mContext);

        ImageAdapter imageAdapter = new ImageAdapter(mContext);
        imageAdapter.initData(mMediaList);
        mGridview.setAdapter(imageAdapter);
        //mGridview.setOnItemClickListener(mContext);

    }

    private void checkPermissionCheck(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                        ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                }else{
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                }
            }else{
                init();
            }
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case REQUEST_CODE_STORAGE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    init();
                }else{
                    finish();
                }
                break;
        }
    }
}
