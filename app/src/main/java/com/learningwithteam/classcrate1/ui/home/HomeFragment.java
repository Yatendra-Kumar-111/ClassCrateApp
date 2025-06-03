package com.learningwithteam.classcrate1.ui.home;

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
//    private TextView title;
    private LinearLayout btnBook, btnNotes, btnSyllabus, btnModel, btnPaper,
        btnPaperSolution, btnVideo, btnNotice;
    String msgFromHomeToSem;
    private SharedViewModel inputDataViewModel;

    public HomeFragment () {

    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
///*
//        title = view.findViewById(R.id.title);
        btnBook = view.findViewById(R.id.btnBook);
        btnNotes = view.findViewById(R.id.btnNotes);
        btnSyllabus = view.findViewById(R.id.btnSyllabus);
        btnModel = view.findViewById(R.id.btnModel);
        btnPaper = view.findViewById(R.id.btnPapers);
        btnVideo = view.findViewById(R.id.btnVideo);
//        btnPaperSolution = view.findViewById(R.id.btnPapersSolution);
//        btnNotice = view.findViewById(R.id.btnNotice);
//    */


        TextView title = view.findViewById(R.id.title);
        TextView tvBook = view.findViewById(R.id.tvBook);
        TextView tvNotes = view.findViewById(R.id.tvNotes);
        TextView tvSyllabus = view.findViewById(R.id.tvSyllabus);
        TextView tvModel = view.findViewById(R.id.tvModel);
        TextView tvPaper = view.findViewById(R.id.tvPaper);
        TextView tvVideo = view.findViewById(R.id.tvVideo);
//        TextView tvPaperSolution = view.findViewById(R.id.tvPaperSolution);
//        TextView tvNotice = view.findViewById(R.id.tvNotice);


        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

// 2:28am 03 june 25



        btnBook.setOnClickListener(v -> {

            msgFromHomeToSem = tvBook.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

//            NavHostFragment.findNavController(this).navigate(R.id.action_firstFragment_to_fourthFragment);


            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemesterFragment())
                    .addToBackStack(null)
                    .commit();
        });

        btnNotes.setOnClickListener(v -> {

            msgFromHomeToSem = tvNotes.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemesterFragment())
                    .addToBackStack(null)
                    .commit();
        });
        btnSyllabus.setOnClickListener(v -> {

            msgFromHomeToSem = tvSyllabus.getText().toString();
//            inputDataViewModel.setData2(msgFromHomeToSem);
//            inputDataViewModel.setData(msgFromHomeToSem);


            PdfViewerFragment secondFragment = new PdfViewerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key_name", msgFromHomeToSem);
            secondFragment.setArguments(bundle); // Must be called BEFORE committing the transaction

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutHome, secondFragment)
                    .addToBackStack(null)
                    .commit();



          /*  requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new PdfViewerFragment())
                    .addToBackStack(null)
                    .commit();*/
        });

        btnModel.setOnClickListener(v -> {

            msgFromHomeToSem = tvModel.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemesterFragment())
                    .addToBackStack(null)
                    .commit();
        });

        btnPaper.setOnClickListener(v -> {
            msgFromHomeToSem = tvPaper.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemesterFragment())
                    .addToBackStack(null)
                    .commit();
        });
/*
        btnPaperSolution.setOnClickListener(v -> {
            msgFromHomeToSem = tvPaperSolution.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemesterFragment())
                    .addToBackStack(null)
                    .commit();
        });
*/
        btnVideo.setOnClickListener(v -> {
//            msgFromHomeToSem = tvVideo.getText().toString();
//            inputDataViewModel.setData(msgFromHomeToSem);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemsterYtFragment())
                    .addToBackStack(null)
                    .commit();
        });

/*
        btnNotice.setOnClickListener(v -> {
            msgFromHomeToSem = tvNotice.getText().toString();
            inputDataViewModel.setData(msgFromHomeToSem);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutHome, new SemesterFragment())
                    .addToBackStack(null)
                    .commit();
        });

        */



// pata nahi eska use hai ki nahi abhi to
///*
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.title;
        binding.title.setText("-----BCA CRATE-----");
//*/
        return view;
    }

/*    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Restore the ActionBar
        if (requireActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
        }
    }




}


