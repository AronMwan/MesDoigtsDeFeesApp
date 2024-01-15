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

import java.util.ArrayList;
import java.util.List;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder>{
    private ArrayList<Lesson> lessonList;



    public LessonAdapter() {
        this.lessonList = new ArrayList<Lesson>();
    }




    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {

        Lesson currentLesson = lessonList.get(position);
        holder.tvName.setText(currentLesson.getName());
        holder.tvType.setText(currentLesson.getSelectedType());
        holder.tvDescription.setText(currentLesson.getDescription());
    }

    public void addItems(List<Lesson> newLessons){

        this.lessonList = new ArrayList<Lesson>();
        this.lessonList.addAll(newLessons);

    }





    public int getItemCount() {
        return lessonList.size();
    }
    public static class LessonViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvType, tvDescription;
        ImageView ivBooks;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewLessonName);
            tvType = itemView.findViewById(R.id.textViewLessonType);
            tvDescription = itemView.findViewById(R.id.textViewLessonDescription);
            ivBooks = itemView.findViewById(R.id.imageViewBooks);


        }
    }

}
