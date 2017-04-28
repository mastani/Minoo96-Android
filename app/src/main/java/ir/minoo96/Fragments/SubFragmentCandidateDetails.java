package ir.minoo96.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import ir.minoo96.Items.Candidate;
import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.Utilities;

public class SubFragmentCandidateDetails extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = SubFragmentCandidateDetails.class.getSimpleName();

    //private SwipeRefreshLayout swipeRefreshLayout;
    SimpleDraweeView imgProfile;
    FontTextView txtName;
    FontTextView txtFather;
    FontTextView txtAge;
    FontTextView txtHezb;
    FontTextView txtTahsilat;
    FontTextView txtReshteh;
    FontTextView txtDate;
    FontTextView txtBio;
    FontTextView txtSavabegh;
    FontTextView txtIdeas;
    FontTextView txtOthers;
    FontTextView txtContacts;

    public SubFragmentCandidateDetails() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_fragment_candidate_datails, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        imgProfile = (SimpleDraweeView) view.findViewById(R.id.imgProfile);
        txtName = (FontTextView) view.findViewById(R.id.txtName);
        txtFather = (FontTextView) view.findViewById(R.id.txtFather);
        txtAge = (FontTextView) view.findViewById(R.id.txtAge);
        txtHezb = (FontTextView) view.findViewById(R.id.txtHezb);
        txtTahsilat = (FontTextView) view.findViewById(R.id.txtTahsilat);
        txtReshteh = (FontTextView) view.findViewById(R.id.txtReshteh);
        txtDate = (FontTextView) view.findViewById(R.id.txtDate);
        txtBio = (FontTextView) view.findViewById(R.id.txtBio);
        txtSavabegh = (FontTextView) view.findViewById(R.id.txtSavabegh);
        txtIdeas = (FontTextView) view.findViewById(R.id.txtIdeas);
        txtOthers = (FontTextView) view.findViewById(R.id.txtOthers);
        txtContacts = (FontTextView) view.findViewById(R.id.txtContacts);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int i = getActivity().getIntent().getExtras().getInt("item");
        Candidate candidate = Utilities.findCandidateItem(i);

        imgProfile.setImageURI(candidate.getImage());
        txtName.setText(candidate.getName());
        txtFather.setText(candidate.getFatherName());
        txtAge.setText("" + candidate.getAge());
        txtHezb.setText(candidate.getHezb());
        txtTahsilat.setText(candidate.getTahsilat());
        txtReshteh.setText(candidate.getReshteh());
        txtDate.setText(candidate.getRegisterDate());

        txtBio.setText(candidate.getBio());
        txtSavabegh.setText(candidate.getSavabegh());
        txtIdeas.setText(candidate.getIdea());
        txtOthers.setText(candidate.getOthers());
        txtContacts.setText(candidate.getTChannel());

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
