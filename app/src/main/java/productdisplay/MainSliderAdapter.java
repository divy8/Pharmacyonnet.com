package productdisplay;


import android.content.Context;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    ArrayList<Images_url> images_urls;
    Context context;

    public MainSliderAdapter(ArrayList<Images_url> images_urls,Context context) {
        this.images_urls = images_urls;
        this.context=context;
    }

    @Override
        public int getItemCount() {
            return images_urls.size();
        }

        @Override
        public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {


        Images_url imagesUrl=images_urls.get(position);
            viewHolder.bindImageSlide(imagesUrl.getUrl());

        }
    }
