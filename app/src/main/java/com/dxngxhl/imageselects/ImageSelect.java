package com.dxngxhl.imageselects;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.dxngxhl.imageselection.ImageChoose;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import io.reactivex.functions.Consumer;

/**
 * 图片选择实现
 * Created by Ma
 * on 2019/7/14
 */
public class ImageSelect implements ImageChoose {
    FragmentActivity activity;
    Fragment fragment;
    public int maxSelectable;
    public static final int REQUEST_CODE_CHOOSE = 0x111;

    public ImageSelect(FragmentActivity activity) {
        this.activity = activity;
    }

    public ImageSelect(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void imageChoose(int num) {
        if (activity != null){
            imageChoose(activity,num);
        }else {
            imageChoose(fragment,num);
        }
    }

    public void imageChoose(FragmentActivity act,int num) {
        maxSelectable = num;
        activity = act;
        new RxPermissions(activity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Matisse.from(activity)
                                    .choose(MimeType.ofImage())
                                    .showSingleMediaType(true)//是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                                    .countable(true)
                                    .maxSelectable(maxSelectable)
                                    .capture(true)
                                    .captureStrategy(new CaptureStrategy(true, "com.dxngxhl.imageselects.fileProvider"))
                                    //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                    .thumbnailScale(0.85f)
                                    .imageEngine(new GlideEngine4())
                                    .forResult(REQUEST_CODE_CHOOSE);
                            //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                        } else {
                            Toast.makeText(activity, "请给与相应权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void imageChoose(Fragment fmt,int num) {
        fragment = fmt;
        maxSelectable = num;
        new RxPermissions(fragment)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Matisse.from(fragment)
                                    .choose(MimeType.ofImage())
                                    .showSingleMediaType(true)//是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                                    .countable(true)
                                    .maxSelectable(maxSelectable)
                                    .capture(true)
                                    .captureStrategy(new CaptureStrategy(true, "com.dxngxhl.imageselects.fileProvider"))
                                    //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                    .thumbnailScale(0.85f)
                                    .imageEngine(new GlideEngine4())
                                    .forResult(REQUEST_CODE_CHOOSE);
                            //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                        } else {
                            Toast.makeText(fragment.getActivity(), "请给与相应权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
