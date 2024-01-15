package be.ehb.mesdoigtsdefeesapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;
import be.ehb.mesdoigtsdefeesapp.models.Post;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder>{


    public class LessonViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvDescription, tvType;


        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewLessonName);
            tvDescription = itemView.findViewById(R.id.textViewLessonDescription);
            tvType = itemView.findViewById(R.id.textViewLessonType);

        }
    }




    private ArrayList<Lesson> items;

    public LessonAdapter() {
        this.items = new ArrayList<Lesson>();
    }

    public void addItems(List<Lesson> newItems){
        this.items = new ArrayList<Lesson>();
        this.items.addAll(newItems);
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson currentItem = items.get(position);
        holder.tvName.setText(currentItem.getName());
        holder.tvDescription.setText(currentItem.getDescription());
        holder.tvType.setText(currentItem.getSelectedType());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFilterList(ArrayList<Lesson> filterList){
        items = filterList;
        notifyDataSetChanged();
    }
}
