package be.ehb.mesdoigtsdefeesapp.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import be.ehb.mesdoigtsdefeesapp.R;
import be.ehb.mesdoigtsdefeesapp.models.Post;
import be.ehb.mesdoigtsdefeesapp.views.viewmodels.PostViewModel;


public class NewPostFragment extends Fragment {




    public NewPostFragment() {
    }

    public static NewPostFragment newInstance(){
        return new NewPostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PostViewModel viewModel = new ViewModelProvider(getActivity()).get(PostViewModel.class);

        EditText etTitle = view.findViewById(R.id.editTextTitle);
        EditText etContent = view.findViewById(R.id.editTextContent);
        EditText etSize = view.findViewById(R.id.editTextSize);
        Button btn = view.findViewById(R.id.buttonCreatePost);

        btn.setOnClickListener( (View v)->{
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String size = etSize.getText().toString();
            Post toSave = new Post(title, content, size);
            viewModel.insertPost(toSave);

            Navigation.findNavController(view).navigateUp();
        } );

    }
}
