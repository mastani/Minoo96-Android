package ir.minoo96.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ir.minoo96.API.Callbacks.RequestCallback;
import ir.minoo96.API.Requests;
import ir.minoo96.Adapters.CandidateAdapter;
import ir.minoo96.Items.Candidate;
import ir.minoo96.R;
import ir.minoo96.Utility.FontButton;
import ir.minoo96.Utility.RtlGridLayoutManager;
import ir.minoo96.Utility.Variables;

public class FragmentCandidates extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private static final String TAG = FragmentCandidates.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    CandidateAdapter candidateAdapter;
    FontButton btnAll, btnGentleman, btnLadies, btnReCandidate;

    public FragmentCandidates() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_candidates, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL)
            gridLayoutManager = new RtlGridLayoutManager(getContext(), 3);
         else if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL)
            gridLayoutManager = new RtlGridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        btnAll = (FontButton) view.findViewById(R.id.btn_all);
        btnGentleman = (FontButton) view.findViewById(R.id.btn_gentleman);
        btnLadies = (FontButton) view.findViewById(R.id.btn_ladies);
        btnReCandidate = (FontButton) view.findViewById(R.id.btn_recandidate);

        candidateAdapter = new CandidateAdapter(getActivity(), true, Variables.candidates);
        recyclerView.setAdapter(candidateAdapter);
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

        btnAll.setOnClickListener(this);
        btnGentleman.setOnClickListener(this);
        btnLadies.setOnClickListener(this);
        btnReCandidate.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {
        fetchCandidates();
    }

    protected void fetchCandidates() {
        swipeRefreshLayout.setRefreshing(true);
        Requests.fetchCandidates(getContext(), new RequestCallback() {
            @Override
            public void onSuccess() {
                swipeRefreshLayout.setRefreshing(false);
                candidateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        ArrayList<Candidate> candidates = new ArrayList<>();

        FontButton btn = (FontButton) v;
        for (Candidate item : Variables.candidates) {
            switch (btn.getId()) {
                case R.id.btn_gentleman:
                    if (item.getGender() == 0)
                        candidates.add(item);
                    break;
                case R.id.btn_ladies:
                    if (item.getGender() == 1)
                        candidates.add(item);
                    break;
                case R.id.btn_recandidate:
                    if (item.getReCandidate() == 1)
                        candidates.add(item);
                    break;
                default:
                    candidates.add(item);
            }
        }

        recyclerView.setAdapter(new CandidateAdapter(getActivity(), true, candidates));
        candidateAdapter.notifyDataSetChanged();
    }
}
