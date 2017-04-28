package ir.minoo96;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import ir.minoo96.Items.Feed;
import ir.minoo96.Utility.FeedUtils;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SwipeBack.SwipeBackActivityHelper;
import ir.minoo96.Utility.Variables;

public class FeedActivity extends AppCompatActivity {

    SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
    Feed feed;
    int feedId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(10)
                .setNeedBackgroundShadow(true)
                .init(this);

        Bundle bundle = getIntent().getExtras();
        feedId = bundle.getInt("item", 0);
        feed = Variables.feeds.get(feedId);

        FontTextView name = (FontTextView) findViewById(R.id.name);
        FontTextView statusMsg = (FontTextView) findViewById(R.id.txtStatusMsg);
        SimpleDraweeView netImageView = (SimpleDraweeView) findViewById(R.id.feedImage1);

        FeedUtils.setData(getBaseContext(), feedId, name, statusMsg, netImageView, true);
    }

    @Override
    public void onBackPressed() {
        helper.finish();
    }

}
