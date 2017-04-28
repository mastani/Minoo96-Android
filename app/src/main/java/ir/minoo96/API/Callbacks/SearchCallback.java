package ir.minoo96.API.Callbacks;

import java.util.ArrayList;

import ir.minoo96.Items.Candidate;
import ir.minoo96.Items.Comment;
import ir.minoo96.Items.Post;

public interface SearchCallback {
    void onSuccess(ArrayList<Candidate> candidates, ArrayList<Post> posts);
    void onFailed();
}