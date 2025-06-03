package com.learningwithteam.classcrate1.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.learningwithteam.classcrate1.R;
import com.learningwithteam.classcrate1.databinding.FragmentSlideshowBinding;
import com.learningwithteam.classcrate1.ui.gallery.GalleryFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

       /* Button btn = view.findViewById(R.id.btnSlideShow);
        btn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutSlide, new GalleryFragment())
                    .addToBackStack(null)
                    .commit();
        });*/
//        Button btn1 = view.findViewById(R.id.btn);
        EditText youtubeLinkInput = view.findViewById(R.id.editText);
        Button loadThumbnailButton = view.findViewById(R.id.btn);
        ImageView youtubeThumbnail = view.findViewById(R.id.img);

        loadThumbnailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String youtubeUrl = youtubeLinkInput.getText().toString().trim();
                String videoId = extractVideoId(youtubeUrl);

                if (videoId != null) {
                    String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
                    Glide.with(getActivity())
                            .load(thumbnailUrl)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_about)
                            .into(youtubeThumbnail);
                } else {
                    Toast.makeText(getActivity(), "Invalid YouTube link", Toast.LENGTH_SHORT).show();
                }

            }
        });




        return view;
    }



    private String extractVideoId(String youtubeUrl) {
        String pattern = "(?:https?://)?(?:www\\.)?(?:youtube\\.com/watch\\?v=|youtu\\.be/|youtube\\.com/embed/|youtube\\.com/v/|youtube\\.com/shorts/|youtube\\.com/playlist\\?list=)([\\w-]{11})";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youtubeUrl);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}