package ir.minoo96.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import ir.minoo96.API.RequestCallback;
import ir.minoo96.API.Requests;
import ir.minoo96.Adapters.CandidateAdapter;
import ir.minoo96.Adapters.UpdateAdapter;
import ir.minoo96.Items.Candidate;
import ir.minoo96.Items.Post;
import ir.minoo96.R;
import ir.minoo96.Utility.FontButton;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.RtlGridLayoutManager;
import ir.minoo96.Utility.Variables;

public class SubFragmentCandidatePosts extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = SubFragmentCandidatePosts.class.getSimpleName();

    SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout subLayout;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    UpdateAdapter updateAdapter;

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

        int i = getActivity().getIntent().getExtras().getInt("item");
        ArrayList<Post> posts = findCandidatePosts(i);

        if (posts.size() > 0) {
            updateAdapter = new UpdateAdapter(getActivity(), posts);
            recyclerView.setAdapter(updateAdapter);

            FontTextView txtNoPosts = (FontTextView) view.findViewById(R.id.txtNoPost);
            txtNoPosts.setVisibility(View.GONE);
            subLayout.setBackgroundColor(getResources().getColor(R.color.feed_bg));
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    private ArrayList<Post> findCandidatePosts(int candidateId) {
        ArrayList<Post> posts = new ArrayList<>();
        for (Post item : Variables.posts) {
            if (item.getCandidateId() == candidateId) {
                posts.add(item);
            }
        }
        return posts;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.post(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        fetchCandidates();
//                    }
//                }
//        );

    }

    @Override
    public void onRefresh() {
        fetchCandidates();
    }

    protected void fetchCandidates() {
//        swipeRefreshLayout.setRefreshing(true);
//        Requests.fetchCandidates(getContext(), new RequestCallback() {
//            @Override
//            public void onSuccess() {
//                swipeRefreshLayout.setRefreshing(false);
//                //candidateAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailed() {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }
}
