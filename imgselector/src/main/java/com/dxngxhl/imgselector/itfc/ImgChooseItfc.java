package com.dxngxhl.imgselector.itfc;

import java.io.Serializable;
/**
 * Created by Ma
 */
public interface ImgChooseItfc extends Serializable {
    /**
     * 实现选择图片的逻辑
     * 选择后将图片地址通过imageSelectionView.addImagePath() 设置进去
     * @param count:剩余可选择数量
     */
    void imageChoose(int count);
    void videoChoose();
}
