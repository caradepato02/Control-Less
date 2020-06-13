package com.example.prueba.componentes;

import android.content.Context;
import android.util.AttributeSet;

public class ImageViewPro extends androidx.appcompat.widget.AppCompatImageView {
    //Id del recurso drawable
    private int imageResource;

    public ImageViewPro(Context context) {
        super(context);
    }

    public ImageViewPro(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewPro(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        imageResource = resId;
    }

    public int getImageResource() {
        return imageResource;
    }
}
