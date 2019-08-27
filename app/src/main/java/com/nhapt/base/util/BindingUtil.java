package com.nhapt.base.util;

import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.nhapt.base.R;

public class BindingUtil {

    @BindingAdapter({"app:rounded_image"})
    public static void setRoundedImage(ImageView imageView, Object data) {
        if (data != null) {
            Glide.with(imageView.getContext()).load(data).transform(new CenterCrop(), new RoundedCorners(imageView.getContext().getResources().getDimensionPixelSize(R.dimen.small_radius))).into(imageView);
        }
    }

    @BindingAdapter({"app:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null)
            Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({"app:text_html"})
    public static void setTextHtml(TextView textView, String text) {
        if (text != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
            } else textView.setText(Html.fromHtml(text));
        }
    }

    @BindingAdapter({"app:text_res"})
    public static void setTextRes(TextView textRes, int res) {
        if (res > 0) textRes.setText(res);
    }

    @BindingAdapter({"app:selected"})
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }
}
