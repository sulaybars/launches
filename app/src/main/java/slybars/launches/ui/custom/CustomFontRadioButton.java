package slybars.launches.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import slybars.launches.R;

/**
 * Created by slybars on 04/03/2018.
 */

public class CustomFontRadioButton extends AppCompatRadioButton {

    public CustomFontRadioButton(Context context) {
        super(context);
    }

    public CustomFontRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        int[] attribute = new int[]{android.R.attr.fontFamily};
        TypedArray ta = context.obtainStyledAttributes(attrs, attribute);
        int fontFamily = ta.getResourceId(0, R.font.roboto_regular);
        ta.recycle();
        setTypeface(ResourcesCompat.getFont(context, fontFamily));
    }
}