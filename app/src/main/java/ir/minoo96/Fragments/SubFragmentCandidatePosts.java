package ir.minoo96.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ir.minoo96.API.Callbacks.CandidatePostCallback;
import ir.minoo96.API.Callbacks.PostsCallback;
import ir.minoo96.API.Requests;
import ir.minoo96.Adapters.UpdateAdapter;
import ir.minoo96.Items.Post;
import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.Variables;

public class SubFragmentCandidatePosts extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = SubFragmentCandidatePosts.class.getSimpleName();

    SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout subLayout;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    UpdateAdapter updateAdapter;
    FontTextView txtNoPosts;
    int candidateId = 0;

    public SubFragmentCandidatePosts() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_fragment_candidate_posts, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        subLayout = (RelativeLayout) view.findViewById(R.id.sub_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        txtNoPosts = (FontTextView) view.findViewById(R.id.txtNoPost);

        candidateId = getActivity().getIntent().getExtras().getInt("item");
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        fetchCandidates();
                    }
                }
        );

    }

    @Override
    public void onRefresh() {
        fetchCandidates();
    }

    protected void fetchCandidates() {
        swipeRefreshLayout.setRefreshing(true);

        Requests.fetchPosts(getActivity(), candidateId, new CandidatePostCallback() {
            @Override
            public void onResult(ArrayList<Post> posts) {
                swipeRefreshLayout.setRefreshing(false);
                updateAdapter = new UpdateAdapter(getActivity(), posts);
                recyclerView.setAdapter(updateAdapter);

                try {
                    if (posts.size() > 0) {
                        subLayout.setBackgroundColor(getResources().getColor(R.color.feed_bg));
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        txtNoPosts.setVisibility(View.VISIBLE);
                    }
                } catch (Exception ex) {

                }
            }

            @Override
            public void onFailed() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
