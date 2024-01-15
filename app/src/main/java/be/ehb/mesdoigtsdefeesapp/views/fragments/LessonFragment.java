package be.ehb.mesdoigtsdefeesapp.views.fragments;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Lesson;
import be.ehb.mesdoigtsdefeesapp.models.Post;
import be.ehb.mesdoigtsdefeesapp.views.adapters.LessonAdapter;
import be.ehb.mesdoigtsdefeesapp.views.adapters.PostAdapter;
import be.ehb.mesdoigtsdefeesapp.views.viewmodels.LessonViewModel;
import be.ehb.mesdoigtsdefeesapp.views.viewmodels.PostViewModel;

public class LessonFragment extends Fragment {



    private LessonAdapter adapter;


    private ArrayList<Lesson> lessonList;

    public LessonFragment() {
    }


    public static LessonFragment newInstance() {
        return new LessonFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        RecyclerView rvLessons = view.findViewById(R.id.rv_lessons);
        SearchView searchView = view.findViewById(R.id.searchView);

        searchView.setOnFocusChangeListener((v, hasFocus) -> {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rvLessons.getLayoutParams();

            layoutParams.topMargin = hasFocus ? 60 : 24;

            rvLessons.setLayoutParams(layoutParams);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessonList = new ArrayList<>();

        RecyclerView rvPosts = view.findViewById(R.id.rv_lessons);

        adapter = new LessonAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(layoutManager);

        LessonViewModel viewModel = new ViewModelProvider(getActivity()).get(LessonViewModel.class);

        viewModel.getAllLessons().observe(getViewLifecycleOwner(), items -> {
            lessonList.clear();
            lessonList.addAll(items);
            adapter.addItems(items);
            adapter.notifyDataSetChanged();
        });

        FloatingActionButton fab = view.findViewById(R.id.btn_new_lesson);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_newLessonFragment);
        });


        SearchView sv = view.findViewById(R.id.searchView);
        sv.clearFocus();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Lesson> filteredList = new ArrayList<>();
        for (Lesson item : lessonList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No posts found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilterList(filteredList);
        }
    }
}

