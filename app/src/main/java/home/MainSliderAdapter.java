package home;


import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    ArrayList<collection1> images_urls;


    public MainSliderAdapter(ArrayList<collection1> images_urls) {
        this.images_urls = images_urls;
    }

    @Override
        public int getItemCount() {
            return images_urls.size();
        }

        @Override
        public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {


        collection1 imagesUrl=images_urls.get(position);
            viewHolder.bindImageSlide(imagesUrl.getUrl());

        }
    }
