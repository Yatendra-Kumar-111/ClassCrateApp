package com.learningwithteam.classcrate1.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.learningwithteam.classcrate1.PdfViewerFragment;
import com.learningwithteam.classcrate1.R;
import com.learningwithteam.classcrate1.SemesterFragment;
import com.learningwithteam.classcrate1.SemsterYtFragment;
import com.learningwithteam.classcrate1.SharedViewModel;
import com.learningwithteam.classcrate1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private LinearLayout btnBook, btnNotes, btnSyllabus, btnModel, btnPaper,
            btnPaperSolution, btnVideo, btnNotice;
    private static String msgFromHomeToSem, msg;
    private SharedViewModel inputDataViewModel;
    private TextView title, tvBook, tvNotes, tvSyllabus, tvModel, tvPaper, tvVideo;

    public HomeFragment () {

    }

    @SuppressLint("CommitTransaction")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);

        btnBook = view.findViewById(R.id.btnBook);
        btnNotes = view.findViewById(R.id.btnNotes);
        btnSyllabus = view.findViewById(R.id.btnSyllabus);
        btnModel = view.findViewById(R.id.btnModel);
        btnPaper = view.findViewById(R.id.btnPapers);
        btnVideo = view.findViewById(R.id.btnVideo);

         title = view.findViewById(R.id.title);
         tvBook = view.findViewById(R.id.tvBook);
         tvNotes = view.findViewById(R.id.tvNotes);
         tvSyllabus = view.findViewById(R.id.tvSyllabus);
         tvModel = view.findViewById(R.id.tvModel);
         tvPaper = view.findViewById(R.id.tvPaper);
         tvVideo = view.findViewById(R.id.tvVideo);


        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        btnBook.setOnClickListener(v -> {
            msgFromHomeToSem = tvBook.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);
            openSubjectsFragment();
        });

        btnNotes.setOnClickListener(v -> {
            msgFromHomeToSem = tvNotes.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);
            openSubjectsFragment();
        });
        btnSyllabus.setOnClickListener(v -> {
            msgFromHomeToSem = tvSyllabus.getText().toString();
            inputDataViewModel.setData2(msgFromHomeToSem);
            inputDataViewModel.setData(msgFromHomeToSem);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right,  // enter
                            R.anim.slide_out_left,  // exit
                            R.anim.slide_in_left,   // popEnter
                            R.anim.slide_out_right  // popExit
                    )
                    .replace(R.id.frameLayoutHome, new PdfViewerFragment())
                    .addToBackStack(null)
                    .commit();
        });

        btnModel.setOnClickListener(v -> {

            msgFromHomeToSem = tvModel.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            openSubjectsFragment();
        });

        btnPaper.setOnClickListener(v -> {
            msgFromHomeToSem = tvPaper.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            openSubjectsFragment();
        });

        btnVideo.setOnClickListener(v -> {

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right,  // enter
                            R.anim.slide_out_left,  // exit
                            R.anim.slide_in_left,   // popEnter
                            R.anim.slide_out_right  // popExit
                    )
                    .replace(R.id.frameLayoutHome, new SemsterYtFragment())
                    .addToBackStack(null)
                    .commit();
        });
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.title;
        binding.title.setText("-----BCA CRATE-----");
        return view;
    }

    private void openSubjectsFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,  // enter
                        R.anim.slide_out_left,  // exit
                        R.anim.slide_in_left,   // popEnter
                        R.anim.slide_out_right  // popExit
                )
                .replace(R.id.frameLayoutHome, new SemesterFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Restore the ActionBar
        if (requireActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
        }
    }

}