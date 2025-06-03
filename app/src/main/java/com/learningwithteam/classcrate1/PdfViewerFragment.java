package com.learningwithteam.classcrate1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


public class PdfViewerFragment extends Fragment {

    public PdfViewerFragment () {

    }

//    private SharedViewModel viewModel;
    private SharedViewModel inputDataViewModel;
    private static String mainButtonName;
    private String subjectName;

    WebView webView;
    TextView title;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf_viewer, container, false);
        title = view.findViewById(R.id.title_pdfView);

        // Hide the ActionBar
        if (requireActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();
        }

        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setBuiltInZoomControls(true);   // Enable zoom controls
        webView.getSettings().setDisplayZoomControls(false);  // Hide on-screen zoom buttons
        webView.getSettings().setSupportZoom(true);           // Enable zoom support
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null); // or LAYER_TYPE_SOFTWARE





        // Block clicks passing through
//        view.setOnTouchListener((v, event) -> true); // Consumes all touch events




        String message = "";
        Bundle bundle = getArguments();
        if (bundle != null) {
            message = bundle.getString("key_name");


            // Use 'message' as needed, e.g., show in a TextView
//            Log.d("SecondFragment", "Received message: " + message);

            if(message.equalsIgnoreCase("Syllabus")) {
                webView.loadUrl("https://drive.google.com/file/d/1NkhWanzQT9ldio7nb5KlY_aN3LQIBfzd/view?usp=drive_link");
                Toast.makeText(getContext(), "Work Properly!!!!!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Error! In load Syllabus Pdf", Toast.LENGTH_SHORT).show();
            }
        }






// for Syllabus , ....
        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);





 /*       inputDataViewModel.getData2().observe(getViewLifecycleOwner(), data -> {
            if(data.equalsIgnoreCase("Syllabus")) {
                webView.loadUrl("https://drive.google.com/file/d/1NkhWanzQT9ldio7nb5KlY_aN3LQIBfzd/view?usp=drive_link");
                Toast.makeText(getContext(), "Work Properly!!!!!!!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(getContext(), "Syllabu pdf not runnig***********",
                    Toast.LENGTH_SHORT).show();
        });*/


// for book, notes, models, exam papers, papers solution...
        inputDataViewModel.getData3().observe(getViewLifecycleOwner(), data -> {
            mainButtonName = data;

        });


        inputDataViewModel.getData().observe(getViewLifecycleOwner(), subName -> {
            
            try {
                if (subName == null || subName.isEmpty()){
//            if(subName.isEmpty() || subName==null) {
                    Toast.makeText(getContext(), "Error! Run App Again", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
            }
            
            
            
            if (subName == null || subName.isEmpty()){
//            if(subName.isEmpty() || subName==null) {
                Toast.makeText(getContext(), "Error! Run App Again", Toast.LENGTH_SHORT).show();
            } else {
                subjectName = subName;
                title.setText(subjectName);

                String notAvailablePdf = "https://drive.google.com/file/d/1HMbN4GHl5uRbOhchPWdcFFBpjZaNQiX-/view?usp=drive_link";
                String pdfNull = "https://drive.google" +
                        ".com/file/d/1tN1MMFMrqcwm9sMee3oeZJWq5n56Khzz/view?usp=drive_link";
                String noChoice = "https://drive.google" +
                        ".com/file/d/1LgVdGPzr4dUB5p0dfe9NFXIxgJta2bnl" +
                        "/view?usp=drive_link";

//----------------------------------------------------------------------------------------------------------------------------
//                                                        START LINK WORK
//----------------------------------------------------------------------------------------------------------------------------


        //      Start PDF open by links
                // Sub -> FOC
                if("Computer Fundamentals and MS-Office".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName)) {
//                    if(mainButtonName.equalsIgnoreCase("book")) {
                        webView.loadUrl("https://drive.google.com/file/d/1ZL0CBYn_OW3TIo1elZtefTlLo0aQJUir/view?usp=drive_link");

                    }
                    else if("notes".equalsIgnoreCase(mainButtonName))
//                    else if(mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1mL9W7GyB1vbYoBnU8NUNInKM8h0xA7QW/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1_rm_m9QP_04BXhEk5XzccR33BH5PRZNY/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> C
                else if (subjectName.equalsIgnoreCase("Introduction to Programming using C")){
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1UbRfKKKwzgqdZOt6_JjxCMQtYLgFnPl3/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1qa6lg4fTZFlS-QSWIZvvOfnZSxuwNuaE/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1-rrhabn-gRF_JTQcr7A6x3LFOiD3fPP4/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }

                }
                // Sub -> BC
                else if (subjectName.equalsIgnoreCase("Business Communication and Soft Skill")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/19J-CZIg-PsFGxdFNLHKU6M_ca6IL7_eY/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1GxgwuwVm6FfiTk-VOAyE-Fqo21vVsGMZ/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1V3jGqbC5E8Mr-_oXASy2eAn_4CbwIAwJ/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> HTML
                else if (subjectName.equalsIgnoreCase("Introduction to HTML-CSS-XML")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1Z31If7LIoUNCNWP-ssUtnHxrYa-lmrY-/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1aFTD_nItHbpDhJcixPiSt--6Ng3dr8Sh/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/17pNEGUCib3iURX3UNyvm60qXj1-WHoFO/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Mathematics
                else if (subjectName.equalsIgnoreCase("Mathematics- I")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
//                        webView.loadUrl("https://drive.google.com/file/d/14La1oER2AeGth8JnJiXL8Oytr2G5mko-/view?usp=drive_link");
                        webView.loadUrl("https://drive.google.com/file/d/1sue6RfikzJCuSUor8YJra9OPmi3yKrNI/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1voWdzLuTlUldMQl6dAY0rRZvLOLlj28x/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }

                // Sem 2
                // Sub -> Object Oriented Programming using C++
                else if ("Object Oriented Programming using C++".equalsIgnoreCase(subjectName)) {

//                    if(mainButtonName.equalsIgnoreCase("book")) {
                    if("book".equalsIgnoreCase(mainButtonName)) {
                        webView.loadUrl("https://drive.google.com/file/d/10mSdqeqpHJ0dAHYBctDE538HhK3edrq2/view?usp=drive_link");
                    }
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1swEnAtri03LZ3YzSPUvMjamBG-7NZlju/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1d-UcILH8exx4pUCZWU-3TuMyhCSqEhgZ/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Digital Electronics
                else if (subjectName.equalsIgnoreCase("Digital Electronics")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1JGwxNa5X2t4CRuwTuYGsLJq5zuLLgIZL/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1bRcFxwNMkM7PQ0FKLLPrdgZlBAHixxY1/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/129iFL2bYar0PNDCMVuh3Dfc801vZAAMa/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Data Structure using ‘C’/’C++’
                else if (subjectName.equalsIgnoreCase("Data Structure using C/C++")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1WZ0p5uz-2gU_T7EXD-PYXdp26c6BJ-Zo/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1XqxO9i_wp04oOq-1c84j_pS1A2gqwBHn/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1TFC6_u8wR62lSPt_ZZ9ldz-FiVkc8zwb/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1jABndh0QaizLn7whWjU6hoz_9ZnuAehl/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Principles of Management
                else if (subjectName.equalsIgnoreCase("Principles of Management")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/17YSWelwdQjVFh00r5iOhhIWrWVYFrEnC/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1Uvw_hM_lpdyknfFN03MssxZ5cgWtEK84/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1Rw99oVS_q6JxUpJ3ZMVMZvegBb0wsxo4/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Numerical Methods
                else if (subjectName.equalsIgnoreCase("Numerical Methods")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1jP3kUdv8wY0ZZhjB96Zwe1cLioVfmAyW/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/19M_SrbYMIOa5HAx_fPWTmgTFgBbb63gu/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1yKdPDgCxhHpIPraUa9rEOXcmig9pu4UB/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }

                // Sem 3
                // Sub -> Data Base Management System
                else if (subjectName.equalsIgnoreCase("Data Base Management System")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1KSHP4bIQ_R2I0CgOyxrVnZoQThzHSbUR/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/17MgkMMqWoiebUxtF44qvOPBJZmH1-354/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1s5HFFEIdxT2lCZRXBzNsG8iNZ6T9KicK/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1-aiD6qcHjzjVKGfQhmw5d-K4w5T_DjOO/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> E-Commerce and ERP
                else if (subjectName.equalsIgnoreCase("E-Commerce and ERP")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl(notAvailablePdf);
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/18DUhcW9BKrpHUugDwDSJfAzAWl0IQvTT/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1Bwjb1V3ilnHFnwdECB6NCyQ2Ru0P8G4t/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Computer Organization and Architecture
                else if (subjectName.equalsIgnoreCase("Computer Organization and Architecture")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl(notAvailablePdf);
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1A9IMq9G2U5LfrBQIef_QPmqsqiveqYqB/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1lklouFBBNIvSn-vuryjVt2xyG_PeNO5w/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1fn7p-iGkyM0IIAggVE7adYWBESV8iL8g/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Operating System with the case study of UNIX & Windows
                else if (subjectName.equalsIgnoreCase("Operating System with the case study of UNIX & Windows")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/15fy3CO4sx9-vLvPK1-IHxT2_LZjRYP2I/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1eW8abjknaDvwSp_nQkGDSa5Dc3PSArEJ/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/10IF6AaFxjLOLnFSwq1KM7-xRRhMfCjcG/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1nFk6KL0C4AZ8I2eJolV2GHSSk_iKxVwv/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Statistical Method and Application
                else if (subjectName.equalsIgnoreCase("Statistical Method and Application")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1f63EThFWl0SNU3MlP54tT3eO-sB5wTLZ/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1cHuVvDbhAzRthAsfXwQ9SoU9CNQUYEDj/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1qTZvxlXT7RsTqelINBj1UNf8vUW1JR16/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }

                // Sem 4
                // Sub -> JAVA Programming
                else if (subjectName.equalsIgnoreCase("JAVA Programming")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1RP2YVkaUbnJqdOM8Pzw8LwiskrIPHvw8/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/16IRPT30Ys-flkd7BXNtGUIRwoWKr7z4a/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1RZ_X5WF_F8oXZDM2lwcEo3hJtWuKokSF/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1KLl09LsUg_F1ISHax5iLCIAyMg_97RRu/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Web Technology with PHP & MySQL
                else if (subjectName.equalsIgnoreCase("Web Technology with PHP & MySQL")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1HfKlgvbYTRvpZlhLXNdFeEv6aCX8oeo1/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1-uRO2qS11hE72D_HqGM9FxU6aUrSpB6s/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1L2Rk1qznQQD6kGBNo124xV-MXpOMixkG/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Artificial Intelligence
                else if (subjectName.equalsIgnoreCase("Artificial Intelligence")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/134-XGzXS7cW6-fCde5twWGRVAjKC3GWS/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1R0iYEusKogVrljeA93x7xAG670Rt80g_/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1RwFAE4fFxelYX9fD5M5KCgvM4m1fKI-Z/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Computer Network
                else if (subjectName.equalsIgnoreCase("Computer Network")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1m17z6SkW9qOsWu9OnFGHuKT_zX_9Njx_/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1A9IMq9G2U5LfrBQIef_QPmqsqiveqYqB/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/15gtoJcZvN-1KLTR_Bq4SOQGBOnVr9j4A/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1Zas2byIeICn7xPMka6Y_oEr2HUCIb88L/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Optimization Techniques
                else if (subjectName.equalsIgnoreCase("Optimization Techniques")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl(notAvailablePdf);
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl(notAvailablePdf);
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1SIijvjyR8q7OzUYsyL0B1PBcdpxnb1JP/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1nY2sSX_BygCTPWimC4W4jtqbr5Cq0Cm2/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Network Security
                else if (subjectName.equalsIgnoreCase("Network Security")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/11m_RD-cRKhFHagR1mvIR7sn2MD-61Y1S/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1d_rHaCXHeYzGLBzj2qc9pbtKOyIU-b-p/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1KR0V-bIl4CUZ1bxf8y8vIZhDbDBF-LqF/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1yZ4bF0JUPhpd-Uag1iY_3rbLUvnVdyd1/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Visual Basic .NET
                else if (subjectName.equalsIgnoreCase("Visual Basic .NET")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1PRohgnVxm7cn1vDYFmP_maG6i-xbWNm0/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1BAxEgZaw2B0kmUyMKIIs06YxBSuQw_hF/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1MBW8AsgFpkOWtYsS30XmjpYuFK5KcE9s/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1qUDQrerHMNTjo3PJ81XI8ThB9CrOqdKx/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Computer Graphics
                else if (subjectName.equalsIgnoreCase("Computer Graphics")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1VUSeLcbq6Mzb7e6HBNQ2v8nBkHuxf2ay/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1-pX8El01PRrzByrpU3PYlcPDdn5Z9pOB/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1ugtaaxvEi0m7D-rEU_JZOoWMkDR8KmvC/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/160P7_SeI_OD3pX8YVoqap6XCQVbMjGLK/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> System Analysis & Design
                else if (subjectName.equalsIgnoreCase("System Analysis & Design")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl("https://drive.google.com/file/d/1e5ZNcb0Xbd0sSgXDXsuU4Z2eaIBfL_MO/view?usp=drive_link");
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1RwS-58dO246C-d12ig73wEHVIO-qRJ5F/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1UIx8ZZks_BTCZmTMP0uc9q2u20mtw41b/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1Ly9ck2rGiR1NThJYqgxZXP_-NCBFUZFy/view?usp=drive_link");

                    else {
                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Design & Analysis of Algorithms
                else if (subjectName.equalsIgnoreCase("Design & Analysis of Algorithms")) {
                    if(mainButtonName.equalsIgnoreCase("book"))
                        webView.loadUrl(notAvailablePdf);
                    else if (mainButtonName.equalsIgnoreCase("notes"))
                        webView.loadUrl("https://drive.google.com/file/d/1yOyb7hpmqQDOmjhEQnaUDR4RxL6Djqv4/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("models"))
                        webView.loadUrl("https://drive.google.com/file/d/1DyROSQ3C6wjL8dThvTKiVVhD8yz1XjBS/view?usp=drive_link");
                    else if(mainButtonName.equalsIgnoreCase("exam papers"))
                        webView.loadUrl("https://drive.google.com/file/d/1l6iW2a5BmqZtZ7EEf69k_WWegLwdcz9h/view?usp=drive_link");
                    else {
                        webView.loadUrl(pdfNull);
                    }
                }


                else  {
                    Toast.makeText(getContext(), "Error! in Load Pdf", Toast.LENGTH_SHORT).show();
                    webView.loadUrl(noChoice);
                }

            }
        });

        return view;
    }


/*   @Override
    public void onDestroyView() {
        super.onDestroyView();

        // ViewModel se list clear karo
//        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        inputDataViewModel.clearList();
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