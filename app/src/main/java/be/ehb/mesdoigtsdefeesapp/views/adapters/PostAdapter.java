package be.ehb.mesdoigtsdefeesapp.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvContent, tvSize;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvSize = itemView.findViewById(R.id.tvSize);
        }
    }

    private ArrayList<Post> items;

    public PostAdapter() {
        this.items = new ArrayList<Post>();
    }

    public void addItems(List<Post> newItems){
        this.items = new ArrayList<Post>();
        this.items.addAll(newItems);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post currentItem = items.get(position);
        holder.tvTitle.setText(currentItem.getTitle());
        holder.tvContent.setText(currentItem.getContent());
        holder.tvSize.setText(currentItem.getSize());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFilterList(ArrayList<Post> filterList){
        items = filterList;
        notifyDataSetChanged();
    }
}
