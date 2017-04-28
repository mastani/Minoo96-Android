package ir.minoo96.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import ir.minoo96.CandidateActivity;
import ir.minoo96.Items.Candidate;
import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SwipeBack.SwipeBackActivityHelper;
import ir.minoo96.Utility.Variables;

public class SearchCandidateAdapter extends RecyclerView.Adapter<SearchCandidateAdapter.MyViewHolder> {

    Activity activity;

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView imgProfile;
        FontTextView txtName;
        FontTextView txtTahsilat;

        RecyclerView recyclerViewPosts;

        MyViewHolder(View itemView) {
            super(itemView);

            this.imgProfile = (SimpleDraweeView) itemView.findViewById(R.id.image);
            this.txtName = (FontTextView) itemView.findViewById(R.id.name);
            this.txtTahsilat = (FontTextView) itemView.findViewById(R.id.tahsilat);

            this.recyclerViewPosts = (RecyclerView) itemView.findViewById(R.id.recyclerViewPosts);
        }
    }

    public SearchCandidateAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public SearchCandidateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_candidate, parent, false);

        return new SearchCandidateAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchCandidateAdapter.MyViewHolder holder, final int listPosition) {
        final Candidate candidate = Variables.searchCandidates.get(listPosition);

        holder.txtName.setText(candidate.getName());
        holder.imgProfile.setImageURI(candidate.getImage());
        holder.txtTahsilat.setText(candidate.getTahsilat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CandidateActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("item", candidate.getId());
                i.putExtras(mBundle);

                SwipeBackActivityHelper.activityBuilder(activity)
                        .intent(i)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
            }
        });

        if (listPosition == Variables.searchCandidates.size() - 1) {
            UpdateAdapter searchPostAdapter = new UpdateAdapter(activity, Variables.searchPosts);
            holder.recyclerViewPosts.setAdapter(searchPostAdapter);
        } else {
            holder.recyclerViewPosts.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return Variables.searchCandidates.size();
    }
}
