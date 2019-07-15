package com.dxngxhl.imageselection;

import java.io.Serializable;

/**
 * Created by Ma
 * on 2019/7/14
 */
public interface ImageChoose extends Serializable {
    /**
     * 实现选择图片的逻辑
     * 选择后将图片地址通过imageSelectionView.addImagePath();设置进去
     * @param num:剩余可选择图片张数
     */
    void imageChoose(int num);
}
