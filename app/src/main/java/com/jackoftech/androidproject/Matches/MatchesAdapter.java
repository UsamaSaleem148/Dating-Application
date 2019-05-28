package com.jackoftech.androidproject.Matches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jackoftech.androidproject.R;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<com.jackoftech.androidproject.Matches.MatchesViewHolders> {
    private List<com.jackoftech.androidproject.Matches.MatchesObject> matchesList;
    private Context context;


    public MatchesAdapter(List<com.jackoftech.androidproject.Matches.MatchesObject> matchesList, Context context){
        this.matchesList = matchesList;
        this.context = context;
    }

    @Override
    public com.jackoftech.androidproject.Matches.MatchesViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        com.jackoftech.androidproject.Matches.MatchesViewHolders rcv = new com.jackoftech.androidproject.Matches.MatchesViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(com.jackoftech.androidproject.Matches.MatchesViewHolders holder, int position) {
        holder.mMatchId.setText(matchesList.get(position).getUserId());
        holder.mMatchName.setText(matchesList.get(position).getName());
        if(!matchesList.get(position).getProfileImageUrl().equals("default")){
            Glide.with(context).load(matchesList.get(position).getProfileImageUrl()).into(holder.mMatchImage);
        }
    }

    @Override
    public int getItemCount() {
        return this.matchesList.size();
    }
}
