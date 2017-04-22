package ir.minoo96.Utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class FontButton extends android.support.v7.widget.AppCompatButton {

    public FontButton(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public FontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public FontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansWeb.ttf");
        this.setTypeface(face);
    }
}
