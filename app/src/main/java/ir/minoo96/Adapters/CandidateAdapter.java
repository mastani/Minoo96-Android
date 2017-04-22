package ir.minoo96.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ir.minoo96.CandidateActivity;
import ir.minoo96.Items.Candidate;
import ir.minoo96.R;
import ir.minoo96.Utility.SwipeBack.SwipeBackActivityHelper;
import ir.minoo96.Utility.Variables;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.MyViewHolder> {

    ArrayList<Candidate> candidates;
    public Activity activity;
    boolean bigSize = false;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircularImageView imgProfile;
        TextView txtName;
        TextView txtStatus;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.imgProfile = (CircularImageView) itemView.findViewById(R.id.image);
            this.txtName = (TextView) itemView.findViewById(R.id.name);
            this.txtStatus = (TextView) itemView.findViewById(R.id.txtStatusMsg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), CandidateActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("item", Variables.candidates.get(getAdapterPosition()).getId());
                    i.putExtras(mBundle);

                    SwipeBackActivityHelper.activityBuilder(activity)
                            .intent(i)
                            .needParallax(true)
                            .needBackgroundShadow(true)
                            .startActivity();
                }
            });
        }
    }

    public CandidateAdapter(Activity activity, boolean bigSize, ArrayList<Candidate> candidates) {
        Collections.shuffle(candidates);
        this.candidates = candidates;
        this.activity = activity;
        this.bigSize = bigSize;
    }

    public CandidateAdapter(Activity activity) {
        ArrayList<Candidate> candidates = Variables.candidates;
        Collections.shuffle(candidates);
        this.candidates = candidates;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candidate, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        Candidate candidate = candidates.get(listPosition);

        holder.txtName.setText(candidate.getName());
        Picasso.with(this.activity).load(candidate.getImage()).into(holder.imgProfile);

        if (bigSize) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
            holder.imgProfile.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }
}