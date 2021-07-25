package com.furrycatxd.RocketNews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Articles> articles;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public Adapter(Context context,List<Articles> list){
        this.articles=list;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.newscard,parent,false);
        return new MyViewHolder(view,onItemClickListener);
    }

    public void shareLink(View view){

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder=holders;
        Articles model=articles.get(position);
        holder.title.setText(model.getTitle());
        holder.sourceName.setText(model.getSource().getName());
        holder.desc.setText(model.getDescription());
        Picasso.with(context).load(model.getUrlToImage()).into(holder.imageView);

//        holder.shareButtonMain.setOnClickListener(v -> {
//            Intent shareLinkIntent=new Intent();
//            shareLinkIntent.setAction(Intent.ACTION_SEND);
//            shareLinkIntent.putExtra(Intent.EXTRA_TEXT,model.getUrl());
//            shareLinkIntent.setType("text/plane");
//            context.startActivity(shareLinkIntent);
//        });

        holder.outerCard.setOnClickListener(v -> {
            Intent intent=new Intent(context.getApplicationContext(),WebActivity.class);
            intent.putExtra("NEWSURL",model.getUrl());
            intent.putExtra("NEWSSOURCE",model.getSource().getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,desc,sourceName;
        ImageView imageView;
        //Button shareButtonMain;
        CardView innerCard,outerCard;
        LinearLayout linearLayoutX;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener){
            super(itemView);
            itemView.setOnClickListener(this);
            title=itemView.findViewById(R.id.title);
            sourceName=itemView.findViewById(R.id.sourceName);
            desc=itemView.findViewById(R.id.desc);
            imageView=itemView.findViewById(R.id.image);
            innerCard=itemView.findViewById(R.id.cardInner);
            outerCard=itemView.findViewById(R.id.cardOuter);
            linearLayoutX=itemView.findViewById(R.id.linearLayoutX);
            //shareButtonMain=itemView.findViewById(R.id.shareButtonMain);
            this.onItemClickListener=onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }

}