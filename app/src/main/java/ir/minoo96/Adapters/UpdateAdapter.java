package ir.minoo96.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import ir.minoo96.API.Callbacks.RequestCallback;
import ir.minoo96.API.Requests;
import ir.minoo96.CandidateActivity;
import ir.minoo96.CommentsActivity;
import ir.minoo96.Items.Candidate;
import ir.minoo96.Items.Post;
import ir.minoo96.R;
import ir.minoo96.Utility.FontTextView;
import ir.minoo96.Utility.SwipeBack.SwipeBackActivityHelper;
import ir.minoo96.Utility.Utilities;
import ir.minoo96.Utility.Variables;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyViewHolder> {

    private Activity activity;
    private boolean oneCandidate = false;
    private ArrayList<Post> posts;

    class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView candidatesRecyclerView;
        LinearLayout candidateHolder;
        SimpleDraweeView profile;
        FontTextView name;
        FontTextView date;
        SimpleDraweeView postImage;
        FontTextView statusMsg;
        ImageView heartAnim;
        ImageView like;
        ImageView comment;
        FontTextView likesCommentCount;

        MyViewHolder(View itemView) {
            super(itemView);

            candidatesRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            candidateHolder = (LinearLayout) itemView.findViewById(R.id.candidateHolder);
            profile = (SimpleDraweeView) itemView.findViewById(R.id.profile_image);
            name = (FontTextView) itemView.findViewById(R.id.name);
            date = (FontTextView) itemView.findViewById(R.id.date);
            postImage = (SimpleDraweeView) itemView.findViewById(R.id.post_image);
            statusMsg = (FontTextView) itemView.findViewById(R.id.txtStatusMsg);
            heartAnim = (ImageView) itemView.findViewById(R.id.heart_anim);
            like = (ImageView) itemView.findViewById(R.id.like);
            comment = (ImageView) itemView.findViewById(R.id.comment);
            likesCommentCount = (FontTextView) itemView.findViewById(R.id.likes_comment_count);

            candidateHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), CandidateActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putInt("item", Variables.posts.get(getAdapterPosition()).getCandidateId());
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

    public UpdateAdapter(Activity activity) {
        this.activity = activity;
        this.posts = Variables.posts;
    }

    public UpdateAdapter(Activity activity, ArrayList<Post> posts) {
        this.activity = activity;
        this.posts = posts;
        oneCandidate = true;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final Post post = posts.get(listPosition);
        Candidate candidate = Utilities.findCandidateItem(post.getCandidateId());

        holder.profile.setImageURI(candidate.getImage());
        holder.name.setText(candidate.getName());
        holder.postImage.setImageURI(post.getImage());
        holder.statusMsg.setText(post.getContent());
        setLikeCommentTextView(holder.likesCommentCount, post.getLikes(), post.getComments());

        if (listPosition == 0 && !oneCandidate) {
            RecyclerView recyclerView = holder.candidatesRecyclerView;
            recyclerView.setHasFixedSize(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);

            CandidateAdapter candidateAdapter = new CandidateAdapter(activity);
            recyclerView.setAdapter(candidateAdapter);
        } else {
            holder.candidatesRecyclerView.setVisibility(View.GONE);
        }

        final GestureDetector gd = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {

                Animation pulse_fade = AnimationUtils.loadAnimation(activity, R.anim.pulse_fade_in);
                pulse_fade.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        holder.heartAnim.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.heartAnim.setVisibility(View.GONE);
                        holder.like.setImageResource(R.mipmap.ic_fill_heart);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                holder.heartAnim.startAnimation(pulse_fade);
                like(holder.likesCommentCount, post, Variables.userId);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }
        });

        holder.postImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.like.setImageResource(R.mipmap.ic_fill_heart);
                like(holder.likesCommentCount, post, Variables.userId);
            }
        });


        holder.date.setText(Utilities.toPersianDate(post.getTime()));

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, CommentsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("item", post.getId());
                i.putExtras(mBundle);
                activity.startActivity(i);
            }
        });
    }

    void like(FontTextView textView, Post post, int user) {
        setLikeCommentTextView(textView, post.getLikes() + 1, post.getComments());

        Requests.fetchLike(post.getId(), user, new RequestCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {

            }
        });
    }

    void setLikeCommentTextView(FontTextView textView, int likes, int comments) {
        textView.setText(likes + " لایک و " + comments + " کامنت");
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
