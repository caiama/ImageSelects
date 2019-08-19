package com.dxngxhl.imageselection.t2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dxngxhl.imageselection.ImageLoader;
import com.dxngxhl.imageselection.R;

import java.util.List;

/**
 * Created by Ma
 * on 2019/7/12
 */
public class ImageSelectAdapter2 extends BaseAdapter {
    private List<Bean> images;
    private Context context;
    private ImageLoader imageLoader;
    private ImageView.ScaleType scaleType;
    private int imageAddRous,videoAddRous,closeRous;
    int width,imageWidth,numColumns;
    boolean isNoInit = true;
    Bean addBean;
    RemoveListener removeListener;
    public ImageSelectAdapter2(List<Bean> images,
                               Context context,
                               ImageLoader imageLoader,
                               ImageView.ScaleType scaleType,
                               Bean addBean,
                               int imageAddRous,
                               int videoAddRous,
                               int closeRous,
                               int width,
                               int numColumns) {
        this.images = images;
        this.context = context;
        this.imageLoader = imageLoader;
        this.scaleType = scaleType;
        this.addBean = addBean;
        this.imageAddRous = imageAddRous;
        this.videoAddRous = videoAddRous;
        this.closeRous = closeRous;
        this.width = width;
        this.numColumns = numColumns;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image_select,null);
            viewHolder.imageView = convertView.findViewById(R.id.item_iamge_select_image);
            viewHolder.imageClose = convertView.findViewById(R.id.item_iamge_select_close);
            convertView.setTag(viewHolder);
        }
        if (convertView != null && isNoInit && parent.getWidth() > 0){
            imageWidth = (parent.getWidth() - dip2px(context,10 * numColumns * 2)) / numColumns;
            isNoInit = false;
        }
        if (!isNoInit && convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.imageView.getLayoutParams().height = imageWidth;
            viewHolder.imageView.getLayoutParams().width = imageWidth;
            if (scaleType != null){
                viewHolder.imageView.setScaleType(scaleType);
            }
            viewHolder.imageView.setImageResource(imageAddRous);
            //
            viewHolder.imageClose.setImageResource(closeRous);
            if (images.get(position).getImagePath() == null){
                viewHolder.imageClose.setVisibility(View.GONE);
            }else {
                viewHolder.imageClose.setVisibility(View.VISIBLE);
            }
            viewHolder.imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bean bean = new Bean(
                            images.get(position).getImagePath(),
                            images.get(position).getVidePath(),
                            images.get(position).isVideo());
                    bean.setTag(images.get(position).getTag());
                    bean.setType(images.get(position).getType());
                    //
                    images.remove(position);

                    if (!bean.isVideo()){
                        images.remove(addBean);
                        images.add(addBean);
                    }
                    notifyDataSetChanged();

                    if (removeListener != null){
                        removeListener.remove(bean,position);
                    }
                }
            });
            if (imageLoader != null){
                if (images.get(position).getImagePath() != null){
                    imageLoader.imageLoad(context,images.get(position).getImagePath(),viewHolder.imageView);
                }else {
                    imageLoader.imageLoad(context,images.get(position).isVideo()?videoAddRous:imageAddRous,viewHolder.imageView);
                }
            }
        }


        return convertView;
    }
    class ViewHolder{
        ImageView imageView,imageClose;
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setRemoveListener(RemoveListener removeListener) {
        this.removeListener = removeListener;
    }
}
