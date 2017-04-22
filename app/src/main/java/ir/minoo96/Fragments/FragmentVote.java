package ir.minoo96.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;

import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SharedPreferenceHelper;

public class FragmentVote extends Fragment {

    private static final String TAG = FragmentVote.class.getSimpleName();

    WebView webView;

    public FragmentVote() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vote, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = (WebView) view.findViewById(R.id.webview);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView.loadUrl("http://test.minoo96.ir/vote/");
    }
}
