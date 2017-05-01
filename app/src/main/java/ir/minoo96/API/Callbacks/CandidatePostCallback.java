package ir.minoo96.API.Callbacks;

import java.util.ArrayList;

import ir.minoo96.Items.Post;

public interface CandidatePostCallback {
    void onResult(ArrayList<Post> posts);
    void onFailed();
}