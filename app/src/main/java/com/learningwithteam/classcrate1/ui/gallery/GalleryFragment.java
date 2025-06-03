package com.learningwithteam.classcrate1.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.learningwithteam.classcrate1.R;
import com.learningwithteam.classcrate1.YoutubeLinkFragment;
import com.learningwithteam.classcrate1.databinding.FragmentGalleryBinding;
import com.learningwithteam.classcrate1.ui.slideshow.SlideshowFragment;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textGallery;
        binding.textGallery.setText("Wow code is running");

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        Button btn = view.findViewById(R.id.btnSlideShow);

        btn.setText("Open Youtube......");
        btn.setOnClickListener(v -> {

            textView.setText("Open Youtube Page");

//            requireActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frameLayoutGallery, new SlideshowFragment())
//                    .addToBackStack(null)
//                    .commit();
//
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutGallery, new SlideshowFragment())
                    .addToBackStack(null)
                    .commit();

        });



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}