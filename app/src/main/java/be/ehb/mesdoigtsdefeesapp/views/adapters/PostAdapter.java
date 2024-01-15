package be.ehb.mesdoigtsdefeesapp.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Post;
import be.ehb.mesdoigtsdefeesapp.views.activity.DetailsActivity;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvSize;
        FloatingActionButton buttonDetails;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvSize = itemView.findViewById(R.id.tvSize);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
        }
    }

    private ArrayList<Post> items;
    private Context context;

    public PostAdapter(Context context) {
        this.items = new ArrayList<>();
        this.context = context;
    }

    public void addItems(List<Post> newItems) {
        this.items = new ArrayList<>(newItems);
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

        holder.buttonDetails.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", currentItem.getTitle());
            intent.putExtra("content", currentItem.getContent());
            intent.putExtra("size", currentItem.getSize());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFilterList(ArrayList<Post> filterList) {
        items = new ArrayList<>(filterList);
        notifyDataSetChanged();
    }


}
