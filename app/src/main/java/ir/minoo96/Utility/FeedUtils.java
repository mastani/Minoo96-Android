package ir.minoo96.Utility;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ir.minoo96.Items.Feed;

public class FeedUtils {

    public static void setData(Context context, int feedId, FontTextView name, FontTextView statusMsg, ImageView netImageView, boolean single) {
        Feed feed = Variables.feeds.get(feedId);
        name.setText(feed.getName());

        // Chcek for empty status message
        if (!TextUtils.isEmpty(feed.getStatus())) {
            if (single) {
                statusMsg.setText(Utilities.getTextFromHtml(feed.getStatus()));
            } else {
                String text = Utilities.getTextFromHtml(feed.getStatus());
                String[] segments = text.split(" ");
                int wordsCount = segments.length;
                if (wordsCount > 25) {
                    text = "";
                    for (int i = 0; i < 25; i += 1) {
                        text += segments[i] + " ";
                    }
                }

                statusMsg.setText(Html.fromHtml(text));
            }

            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // Feed image
        if (feed.getImge() != null) {
            Picasso.with(context).load(feed.getImge()).into(netImageView);
            netImageView.setVisibility(View.VISIBLE);
        } else {
            netImageView.setVisibility(View.GONE);
        }
    }
}
