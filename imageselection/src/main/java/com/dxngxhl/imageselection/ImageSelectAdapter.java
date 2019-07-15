package com.dxngxhl.imageselection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Ma
 * on 2019/7/12
 */
public class ImageSelectAdapter extends BaseAdapter {
    private List<String> images;
    private Context context;
    private ImageLoader imageLoader;
    private ImageView.ScaleType scaleType;
    private int imageAddRous,closeRous;
    int width ;

    public ImageSelectAdapter(List<String> images,
                              Context context,
                              ImageLoader imageLoader,
                              ImageView.ScaleType scaleType,
                              int imageAddRous,
                              int closeRous,
                              int width) {
        this.images = images;
        this.context = context;
        this.imageLoader = imageLoader;
        this.scaleType = scaleType;
        this.imageAddRous = imageAddRous;
        this.closeRous = closeRous;
        this.width = width;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_select,null);
        ImageView imageView = view.findViewById(R.id.item_iamge_select_image);
        imageView.getLayoutParams().height = width;
        imageView.getLayoutParams().width = width;
        if (scaleType != null){
            imageView.setScaleType(scaleType);
        }
        imageView.setImageResource(imageAddRous);
        ImageView close = view.findViewById(R.id.item_iamge_select_close);
        close.setImageResource(closeRous);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images.remove(position);
                images.remove(ImageSelectionView.SELECTION_TAG);
                images.add(ImageSelectionView.SELECTION_TAG);
                notifyDataSetChanged();
            }
        });
        close.setVisibility(View.VISIBLE);
        if (ImageSelectionView.SELECTION_TAG.equals(images.get(position))){
            close.setVisibility(View.GONE);
        }
        if (imageLoader != null){
            imageLoader.imageLoad(context,ImageSelectionView.SELECTION_TAG.equals(images.get(position))?R.drawable.ic_image_select_add:images.get(position),imageView);
        }
        return view;
    }
}
