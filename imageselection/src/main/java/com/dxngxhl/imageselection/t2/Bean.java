package com.dxngxhl.imageselection.t2;

import com.dxngxhl.imageselection.R;

/**
 * Created by Ma
 * on 2019/8/18
 */
public class Bean {
    private String imagePath = "";
    private String videPath;
    private boolean isVideo = false;
    private int imageRus = R.drawable.ic_image_select_add;
    private int type;
    private String tag;

    /**
     *
     * @param imagePath
     * @param videPath
     * @param isVideo
     * @param imageRus
     * @param type
     * @param tag
     */
    public Bean(String imagePath, String videPath, boolean isVideo, int imageRus, int type, String tag) {
        this.imagePath = imagePath;
        this.videPath = videPath;
        this.isVideo = isVideo;
        this.imageRus = imageRus;
        this.type = type;
        this.tag = tag;
    }

    /**
     *
     * @param imagePath
     * @param videPath
     * @param isVideo
     * @param imageRus
     */
    public Bean(String imagePath, String videPath, boolean isVideo, int imageRus) {
        this.imagePath = imagePath;
        this.videPath = videPath;
        this.isVideo = isVideo;
        this.imageRus = imageRus;
    }

    /**
     *
     * @param imagePath
     * @param videPath
     * @param isVideo
     */
    public Bean(String imagePath, String videPath, boolean isVideo) {
        this.imagePath = imagePath;
        this.videPath = videPath;
        this.isVideo = isVideo;
    }

    /**
     *
     * @param imagePath:为空--添加 ---null
     * @param isVideo
     */
    public Bean(String imagePath, boolean isVideo) {
        this.imagePath = imagePath;
        this.isVideo = isVideo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageRus() {
        return imageRus;
    }

    public void setImageRus(int imageRus) {
        this.imageRus = imageRus;
    }

    public String getVidePath() {
        return videPath;
    }

    public void setVidePath(String videPath) {
        this.videPath = videPath;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "imagePath='" + imagePath + '\'' +
                ", videPath='" + videPath + '\'' +
                ", isVideo=" + isVideo +
                ", imageRus=" + imageRus +
                ", type=" + type +
                ", tag='" + tag + '\'' +
                '}';
    }
}
