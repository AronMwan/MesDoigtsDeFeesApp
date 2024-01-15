package be.ehb.mesdoigtsdefeesapp.views.fragments;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Post;
import be.ehb.mesdoigtsdefeesapp.views.adapters.PostAdapter;
import be.ehb.mesdoigtsdefeesapp.views.viewmodels.PostViewModel;

public class PostFragment extends Fragment {

    private List<Post> postList;
    private PostAdapter adapter;

    public PostFragment() {
    }

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        // Set windowSoftInputMode to adjustPan
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        RecyclerView rvPosts = view.findViewById(R.id.rv_posts);
        SearchView searchView = view.findViewById(R.id.searchView);

        // Set an OnFocusChangeListener to the SearchView
        searchView.setOnFocusChangeListener((v, hasFocus) -> {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rvPosts.getLayoutParams();

            // Adjust the top margin when the SearchView gains or loses focus
            layoutParams.topMargin = hasFocus ? 60/* Set your desired top margin */ : 24
            /* Set your default top margin */;

            rvPosts.setLayoutParams(layoutParams);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postList = new ArrayList<>();

        RecyclerView rvPosts = view.findViewById(R.id.rv_posts);

        adapter = new PostAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(layoutManager);

        PostViewModel viewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);

        viewModel.getAllPosts().observe(getViewLifecycleOwner(), items -> {
            postList.clear(); // Clear the previous data
            postList.addAll(items); // Add the new data
            adapter.addItems(items);
            adapter.notifyDataSetChanged();
        });

        FloatingActionButton fab = view.findViewById(R.id.btn_new_post);
        fab.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_postFragment_to_newPostFragment);
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
        ArrayList<Post> filteredList = new ArrayList<>();
        for (Post item : postList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
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
