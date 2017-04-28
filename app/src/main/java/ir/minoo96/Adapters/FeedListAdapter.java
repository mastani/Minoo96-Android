package ir.minoo96.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ir.minoo96.FeedActivity;
import ir.minoo96.Utility.FeedUtils;
import ir.minoo96.Items.Feed;
import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SwipeBack.SwipeBackActivityHelper;

public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Feed> feeds;

    public FeedListAdapter(Activity activity, List<Feed> feeds) {
        this.activity = activity;
        this.feeds = feeds;
    }

    @Override
    public int getCount() {
        return feeds.size();
    }

    @Override
    public Object getItem(int location) {
        return feeds.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_feed, null);

        FontTextView name = (FontTextView) convertView.findViewById(R.id.name);
        FontTextView statusMsg = (FontTextView) convertView.findViewById(R.id.txtStatusMsg);
        SimpleDraweeView netImageView = (SimpleDraweeView) convertView.findViewById(R.id.feedImage1);
        Button continueReading = (Button) convertView.findViewById(R.id.continueReading);

        FeedUtils.setData(convertView.getContext(), position, name, statusMsg, netImageView, false);

        continueReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, FeedActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("item", position);
                i.putExtras(mBundle);

                SwipeBackActivityHelper.activityBuilder(activity)
                        .intent(i)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
            }
        });

        return convertView;
    }

}