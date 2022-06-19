package com.example.movingnode.Utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CImageView extends androidx.appcompat.widget.AppCompatImageView {

    public CImageView(@NonNull Context context) {
        super(context);
    }

    public CImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
