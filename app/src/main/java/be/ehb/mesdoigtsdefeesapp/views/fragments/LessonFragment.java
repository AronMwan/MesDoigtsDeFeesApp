package be.ehb.mesdoigtsdefeesapp.views.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;
import be.ehb.mesdoigtsdefeesapp.views.adapters.LessonAdapter;
import be.ehb.mesdoigtsdefeesapp.views.viewmodels.LessonViewModel;

public class LessonFragment extends Fragment {

    private LessonViewModel mViewModel;

    private FloatingActionButton button;

    private RecyclerView recyclerView;
    private ArrayList<Lesson> lessonList;

    private Context context;
    public LessonFragment() {
    }


    public static LessonFragment newInstance() {
        return new LessonFragment();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessonList = new ArrayList<>();  // Initialisatie toevoegen

        mViewModel = new ViewModelProvider(getActivity()).get(LessonViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerViewLessons);
        LessonAdapter lessonAdapter = new LessonAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recyclerView.setAdapter(lessonAdapter);
        recyclerView.setLayoutManager(layoutManager);





        mViewModel.getAllLessons().observe(getViewLifecycleOwner(), (List<Lesson> lessons)-> {
            lessonAdapter.addItems(lessons);
            lessonAdapter.notifyDataSetChanged();
        });

        button = view.findViewById(R.id.btn_new_lesson);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_lessonFragment_to_newLessonFragment);

            }
        });


    }





}