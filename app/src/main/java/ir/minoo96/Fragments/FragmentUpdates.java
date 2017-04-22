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

import java.util.ArrayList;

import ir.minoo96.Adapters.UpdateAdapter;
import ir.minoo96.API.RequestCallback;
import ir.minoo96.Items.Post;
import ir.minoo96.R;
import ir.minoo96.API.Requests;
import ir.minoo96.Utility.Variables;

public class FragmentUpdates extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = FragmentNews.class.getSimpleName();

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    UpdateAdapter updateAdapter;

    public FragmentUpdates() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_updates, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        updateAdapter = new UpdateAdapter(getActivity());
        recyclerView.setAdapter(updateAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        fetchUpdates();
                    }
                }
        );
    }

    @Override
    public void onRefresh() {
        fetchUpdates();
    }

    void fetchUpdates() {
        swipeRefreshLayout.setRefreshing(true);
        Requests.fetchInit(getContext(), new RequestCallback() {
            @Override
            public void onSuccess() {
                swipeRefreshLayout.setRefreshing(false);
                updateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
