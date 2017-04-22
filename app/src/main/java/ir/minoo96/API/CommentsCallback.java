package ir.minoo96.API;

import java.util.ArrayList;

import ir.minoo96.Items.Comment;

public interface CommentsCallback {
    void onSuccess(ArrayList<Comment> comments);
    void onFailed();
}