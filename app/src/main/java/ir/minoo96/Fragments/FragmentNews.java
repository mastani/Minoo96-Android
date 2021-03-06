package ir.minoo96.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ir.minoo96.API.Callbacks.FeedsCallback;
import ir.minoo96.API.Callbacks.RequestCallback;
import ir.minoo96.API.Requests;
import ir.minoo96.Adapters.FeedListAdapter;
import ir.minoo96.FeedActivity;
import ir.minoo96.R;
import ir.minoo96.Utility.Variables;

public class FragmentNews extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ListView.OnScrollListener {

    private static final String TAG = FragmentNews.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private FeedListAdapter listAdapter;
    private int preLast;
    View footerView;

    public FragmentNews() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Variables.feeds = new ArrayList<>();
        listAdapter = new FeedListAdapter(getActivity(), Variables.feeds);
        listView.setAdapter(listAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            listView.setNestedScrollingEnabled(true);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        fetchFeeds(true);
                    }
                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent feed = new Intent(getActivity(), FeedActivity.class);
                feed.putExtra("item", i);
                startActivity(feed);
            }
        });

        listView.setOnScrollListener(this);

        footerView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loading, null, false);
    }

    @Override
    public void onRefresh() {
        fetchFeeds(true);
    }

    private void fetchFeeds(final boolean refresh) {
        if (refresh) {
            swipeRefreshLayout.setRefreshing(true);
            Variables.feedsOffset = 0;
        }

        Requests.fetchNews(getContext(), Variables.feedsOffset, new FeedsCallback() {
            @Override
            public void onSuccess() {
                if (refresh)
                    swipeRefreshLayout.setRefreshing(false);
                listAdapter.notifyDataSetChanged();
                listView.addFooterView(footerView);
            }

            @Override
            public void onLastSuccess() {
                listView.removeFooterView(footerView);
            }

            @Override
            public void onFailed() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onScroll(AbsListView lw, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {

        switch (lw.getId()) {
            case R.id.listView:
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {
                    if (preLast != lastItem) {
                        fetchFeeds(false);
                        preLast = lastItem;
                    }
                }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
