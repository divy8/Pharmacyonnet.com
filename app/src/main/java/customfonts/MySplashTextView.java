package customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by one on 3/12/15.
 */
public class MySplashTextView extends android.support.v7.widget.AppCompatTextView {

    public MySplashTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MySplashTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySplashTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/5thgradecursive-2-italic.ttf");
            setTypeface(tf);
        }
    }

}