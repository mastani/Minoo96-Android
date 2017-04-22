package ir.minoo96.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import ir.minoo96.FeedActivity;
import ir.minoo96.Items.Comment;
import ir.minoo96.Items.Feed;
import ir.minoo96.R;
import ir.minoo96.Utility.FeedUtils;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SwipeBack.SwipeBackActivityHelper;
import ir.minoo96.Utility.Utilities;

public class CommentListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Comment> comments;

    public CommentListAdapter(Activity activity, List<Comment> comments) {
        this.activity = activity;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int location) {
        return comments.get(location);
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
            convertView = inflater.inflate(R.layout.item_comment, null);

        FontTextView name = (FontTextView) convertView.findViewById(R.id.name);
        FontTextView comment = (FontTextView) convertView.findViewById(R.id.comment);
        FontTextView date = (FontTextView) convertView.findViewById(R.id.date);

        Comment cm = comments.get(position);

        name.setText("نظر " + cm.getUserName());
        comment.setText(cm.getContent());
        date.setText(Utilities.toPersianDate(cm.getDate()));

        return convertView;
    }

    public void addItem(Comment comment) {
        comments.add(comment);
    }

}