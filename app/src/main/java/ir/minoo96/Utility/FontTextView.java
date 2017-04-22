package ir.minoo96.Utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontTextView extends android.support.v7.widget.AppCompatTextView {


    public FontTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void applyCustomFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        this.setTypeface(face);
    }
}