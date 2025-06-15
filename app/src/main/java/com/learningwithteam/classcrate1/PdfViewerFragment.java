package com.learningwithteam.classcrate1;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

// this is my Main code file
public class PdfViewerFragment extends Fragment {

    public PdfViewerFragment () {

    }

    private SharedViewModel inputDataViewModel;
    private static String mainButtonName;
    private static String msg;
    private static String subjectName;
    WebView webView;
//    private TextView title;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf_viewer, container, false);
//        title = view.findViewById(R.id.title_pdfView);

        // Hide ActionBar and status bara
        if (requireActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().hide();
            }
            activity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }

        webView = view.findViewById(R.id.webView);
//        WebSettings settings = webView.getSettings();
//        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
//
//        settings.setJavaScriptEnabled(true);
//        settings.setAllowFileAccess(true);
//        settings.setDomStorageEnabled(true);
//
//        settings.setLoadWithOverviewMode(true);
//        settings.setUseWideViewPort(true);
//        settings.setBuiltInZoomControls(true);
//        settings.setDisplayZoomControls(true);


        webView.setHapticFeedbackEnabled(true); // for vibration feedback (optional)


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("WebView", consoleMessage.message());
                return true;
            }
//            public void onReceivedError(WebView view, int errorCode,
//                                        String description, String failingUrl) {
//                Log.e("WebViewError", "Error: " + description + " URL: " + failingUrl);
//                Toast.makeText(getContext(), "WebView Error: " + description, Toast.LENGTH_SHORT).show();
//            }
        });




        // Block clicks passing through
//        view.setOnTouchListener((v, event) -> true); // Consumes all touch events



/*

        String message = "";
        Bundle bundle = getArguments();
        if (bundle != null) {
            message = bundle.getString("key_name");

//             Use 'message' as needed, e.g., show in a TextView
            Log.d("SubjectFragment", "Received message: " + message);

            if("Syllabus".equalsIgnoreCase(message)) {
                webView.loadUrl("https://drive.google.com/file/d/1NkhWanzQT9ldio7nb5KlY_aN3LQIBfzd/view?usp=drive_link");
                Toast.makeText(getContext(), "Work Properly!!!!!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Error! In load Syllabus Pdf", Toast.LENGTH_SHORT).show();
            }
        }

*/





        inputDataViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


// for book, notes, models, exam papers, papers solution...
        inputDataViewModel.getData3().observe(getViewLifecycleOwner(), btnClickFromHomePage -> {
            mainButtonName = btnClickFromHomePage;
            msg = btnClickFromHomePage;
        });

// for Syllabus , ....
        inputDataViewModel.getData2().observe(getViewLifecycleOwner(), data -> {
            msg = data;

            if("Syllabus".equalsIgnoreCase(msg)) {
                webView.loadUrl("https://drive.google.com/file/d/1NkhWanzQT9ldio7nb5KlY_aN3LQIBfzd/view?usp=drive_link");
//                Toast.makeText(getContext(), "Work Properly!!!!!!!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(getContext(), "Syllabu pdf not runnig***********",
                    Toast.LENGTH_SHORT).show();
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
//                title.setText(subjectName);

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

                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1ZL0CBYn_OW3TIo1elZtefTlLo0aQJUir/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1BRlyGFPDBm9gFO5MeyAq0VbrdoDRkaOK/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1elzx8JKQimCiwJBF9dRyVJpK2c_1Jxbk/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1V3jGqbC5E8Mr-_oXASy2eAn_4CbwIAwJ/view?usp=drive_link");
                    else {
                        nullPdfCall();
                    }
                }
                // Sub -> C
                else if ("Introduction to Programming using C".equalsIgnoreCase(subjectName)){

                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1UbRfKKKwzgqdZOt6_JjxCMQtYLgFnPl3/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
//                        webView.loadUrl("https://drive.google.com/file/d/1B4woa6HlDWrbcv2DbPNj3goikXPVNrG9/view?usp=drive_link");
                        webView.loadUrl("https://drive.google.com/file/d/1vJ7IhJrErsXYFJIPo3L5S_RfYh6-8mGS/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1q2qFhIiCZmmI7sptH-rVeXQPxTaA0SOU/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1-rrhabn-gRF_JTQcr7A6x3LFOiD3fPP4/view?usp=drive_link");
                    else {
                        nullPdfCall();
                    }
                }
                // Sub -> BC
                else if ("Business Communication and Soft Skill".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                            webView.loadUrl("https://drive.google.com/file/d/19J-CZIg-PsFGxdFNLHKU6M_ca6IL7_eY/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                            webView.loadUrl("https://drive.google.com/file/d/1GxgwuwVm6FfiTk-VOAyE-Fqo21vVsGMZ/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                            webView.loadUrl("https://drive.google.com/file/d/10HfoDuf174_CfDQcqh-0TyxN0NJihQdt/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                            webView.loadUrl("https://drive.google.com/file/d/1V3jGqbC5E8Mr-_oXASy2eAn_4CbwIAwJ/view?usp=drive_link");
                    else {
                        nullPdfCall();
                    }
                }
                // Sub -> HTML
                else if ("Introduction to HTML-CSS-XML".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Z31If7LIoUNCNWP-ssUtnHxrYa-lmrY-/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1aFTD_nItHbpDhJcixPiSt--6Ng3dr8Sh/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1KSfZnXORY5eKtUbZU-_Y6zFMJ237vf3x/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/17pNEGUCib3iURX3UNyvm60qXj1-WHoFO/view?usp=drive_link");
                    else {
                        nullPdfCall();
                    }
                }
                // Sub -> Mathematics
                else if ("Mathematics- I".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/14La1oER2AeGth8JnJiXL8Oytr2G5mko-/view?usp=drive_link");
//                        webView.loadUrl("https://drive.google.com/file/d/1sue6RfikzJCuSUor8YJra9OPmi3yKrNI/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName)) {
                        webView.loadUrl("https://drive.google.com/file/d/14YXdGWa6SE1hIxFt2gx1YuOELpISySy2/view?usp=drive_link");
//                        Toast.makeText(getContext(), "Sorry! We have no notes for this subject", Toast.LENGTH_SHORT).show();
                    }
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/11UT3BEGJFIGrkhI04VXI2Adaw9S-PQkQ/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1voWdzLuTlUldMQl6dAY0rRZvLOLlj28x/view?usp=drive_link");
                    else {
                        nullPdfCall();
                    }
                }

                // Sem 2
                // Sub -> Object Oriented Programming using C++
                else if ("Object Oriented Programming using C++".equalsIgnoreCase(subjectName)) {

                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/10mSdqeqpHJ0dAHYBctDE538HhK3edrq2/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/162vFac_Y4J9Q5TfcwodxQS9rwZ1xX5xl/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1swEnAtri03LZ3YzSPUvMjamBG-7NZlju/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1d-UcILH8exx4pUCZWU-3TuMyhCSqEhgZ/view?usp=drive_link");

                    else {
                        nullPdfCall();
                    }
                }
                // Sub -> Digital Electronics
                else if ("Digital Electronics".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1JGwxNa5X2t4CRuwTuYGsLJq5zuLLgIZL/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1GcU869OPEm_NYZSFeCbRZBoAWqLu2Chs/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1bRcFxwNMkM7PQ0FKLLPrdgZlBAHixxY1/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/129iFL2bYar0PNDCMVuh3Dfc801vZAAMa/view?usp=drive_link");

                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
//                    Toast.makeText(getContext(), "Catch Error", Toast.LENGTH_SHORT).show();

                    }
                }
                // Sub -> Data Structure using ‘C’/’C++’
                else if ("Data Structure using C/C++".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1WZ0p5uz-2gU_T7EXD-PYXdp26c6BJ-Zo/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Np9KM93p0qJY-SXeRA7DR1eLtZ5YqcLw/view?usp=drive_link");
//                        webView.loadUrl("https://drive.google.com/file/d/1XqxO9i_wp04oOq-1c84j_pS1A2gqwBHn/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1TFC6_u8wR62lSPt_ZZ9ldz-FiVkc8zwb/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1jABndh0QaizLn7whWjU6hoz_9ZnuAehl/view?usp=drive_link");

                    else nullPdfCall();
                }
                // Sub -> Principles of Management
                else if ("Principles of Management".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/17YSWelwdQjVFh00r5iOhhIWrWVYFrEnC/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1vmQF0KN48bxC4QFlWxfqlJY7Y13t0jDI/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Uvw_hM_lpdyknfFN03MssxZ5cgWtEK84/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Rw99oVS_q6JxUpJ3ZMVMZvegBb0wsxo4/view?usp=drive_link");

                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
//                    Toast.makeText(getContext(), "Catch Error", Toast.LENGTH_SHORT).show();

                    }
                }
                // Sub -> Numerical Methods
                else if ("Numerical Methods".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1jP3kUdv8wY0ZZhjB96Zwe1cLioVfmAyW/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1-0SbTzHyM_ufzxV6Kh9-CvwrB2ZhDJvj/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/19M_SrbYMIOa5HAx_fPWTmgTFgBbb63gu/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1yKdPDgCxhHpIPraUa9rEOXcmig9pu4UB/view?usp=drive_link");

                    else nullPdfCall();

                }

                // Sem 3
                // Sub -> Data Base Management System
                else if ("Data Base Management System".equalsIgnoreCase(subjectName)) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1KSHP4bIQ_R2I0CgOyxrVnZoQThzHSbUR/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/17MgkMMqWoiebUxtF44qvOPBJZmH1-354/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1s5HFFEIdxT2lCZRXBzNsG8iNZ6T9KicK/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1-aiD6qcHjzjVKGfQhmw5d-K4w5T_DjOO/view?usp=drive_link");
                    else nullPdfCall();

                }
                // Sub -> E-Commerce and ERP
                else if ("E-Commerce and ERP".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1oMMHp4vVid1zOGx4cWeQ0iTPRdPz4mBI/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1PzdGHIg_XAb2VSzNyAgTCOW55SZw-XtR/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/18DUhcW9BKrpHUugDwDSJfAzAWl0IQvTT/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Bwjb1V3ilnHFnwdECB6NCyQ2Ru0P8G4t/view?usp=drive_link");

                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Computer Organization and Architecture
                else if ("Computer Organization and Architecture".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1TdxQQ6iK5ar-038WUE7hBRO7pSpMef3M/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1A9IMq9G2U5LfrBQIef_QPmqsqiveqYqB/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1lklouFBBNIvSn-vuryjVt2xyG_PeNO5w/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1fn7p-iGkyM0IIAggVE7adYWBESV8iL8g/view?usp=drive_link");

                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Operating System with the case study of UNIX & Windows
                else if ("Operating System with the case study of UNIX & Windows".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/15fy3CO4sx9-vLvPK1-IHxT2_LZjRYP2I/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1OPg5VIPIwW-SHVwzChSogfRXScyCAtQG/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/10IF6AaFxjLOLnFSwq1KM7-xRRhMfCjcG/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1nFk6KL0C4AZ8I2eJolV2GHSSk_iKxVwv/view?usp=drive_link");

                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Statistical Method and Application
                else if ("Statistical Method and Application".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1f63EThFWl0SNU3MlP54tT3eO-sB5wTLZ/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/16ZNf9LL7Mg1gxGbBd2aiW77Izmahud-S/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1cHuVvDbhAzRthAsfXwQ9SoU9CNQUYEDj/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1qTZvxlXT7RsTqelINBj1UNf8vUW1JR16/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }

                // Sem 4
                // Sub -> JAVA Programming
                else if ("JAVA Programming".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1RP2YVkaUbnJqdOM8Pzw8LwiskrIPHvw8/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/16IRPT30Ys-flkd7BXNtGUIRwoWKr7z4a/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1RZ_X5WF_F8oXZDM2lwcEo3hJtWuKokSF/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1KLl09LsUg_F1ISHax5iLCIAyMg_97RRu/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Web Technology with PHP & MySQL
                else if ("Web Technology with PHP & MySQL".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1HfKlgvbYTRvpZlhLXNdFeEv6aCX8oeo1/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Iy2vuevNx2CUgEh05GXa2mgRx44xT--R/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1-uRO2qS11hE72D_HqGM9FxU6aUrSpB6s/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1L2Rk1qznQQD6kGBNo124xV-MXpOMixkG/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Artificial Intelligence
                else if ("Artificial Intelligence".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/134-XGzXS7cW6-fCde5twWGRVAjKC3GWS/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1L_aDb_Xy5wn8mO_RDxyy8zVdCvek9SvT/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1R0iYEusKogVrljeA93x7xAG670Rt80g_/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1RwFAE4fFxelYX9fD5M5KCgvM4m1fKI-Z/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Computer Network
                else if ("Computer Network".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1m17z6SkW9qOsWu9OnFGHuKT_zX_9Njx_/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1A9IMq9G2U5LfrBQIef_QPmqsqiveqYqB/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/15gtoJcZvN-1KLTR_Bq4SOQGBOnVr9j4A/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Zas2byIeICn7xPMka6Y_oEr2HUCIb88L/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Optimization Techniques
                else if ("Optimization Techniques".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1OB_dTCr9PaLSCbTvvPaV4tWVdkeXT_D1/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Vp5ds0jvJxDSDLj57spa3Pht__He86l-/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1SIijvjyR8q7OzUYsyL0B1PBcdpxnb1JP/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1nY2sSX_BygCTPWimC4W4jtqbr5Cq0Cm2/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Network Security
                else if ("Network Security".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/11m_RD-cRKhFHagR1mvIR7sn2MD-61Y1S/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1d_rHaCXHeYzGLBzj2qc9pbtKOyIU-b-p/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1KR0V-bIl4CUZ1bxf8y8vIZhDbDBF-LqF/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1yZ4bF0JUPhpd-Uag1iY_3rbLUvnVdyd1/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Visual Basic .NET
                else if ("Visual Basic .NET".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1PRohgnVxm7cn1vDYFmP_maG6i-xbWNm0/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1BAxEgZaw2B0kmUyMKIIs06YxBSuQw_hF/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1MBW8AsgFpkOWtYsS30XmjpYuFK5KcE9s/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1qUDQrerHMNTjo3PJ81XI8ThB9CrOqdKx/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Computer Graphics
                else if ("Computer Graphics".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1VUSeLcbq6Mzb7e6HBNQ2v8nBkHuxf2ay/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1YU7QW8xR6uwia-D5UTNYKZryGRSJDErL/view?usp=sharing");
//                        webView.loadUrl("https://drive.google.com/file/d/1-pX8El01PRrzByrpU3PYlcPDdn5Z9pOB/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1ugtaaxvEi0m7D-rEU_JZOoWMkDR8KmvC/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/160P7_SeI_OD3pX8YVoqap6XCQVbMjGLK/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> System Analysis & Design
                else if ("System Analysis & Design".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1e5ZNcb0Xbd0sSgXDXsuU4Z2eaIBfL_MO/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1RwS-58dO246C-d12ig73wEHVIO-qRJ5F/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1UIx8ZZks_BTCZmTMP0uc9q2u20mtw41b/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1Ly9ck2rGiR1NThJYqgxZXP_-NCBFUZFy/view?usp=drive_link");
                    else {
                        nullPdfCall();
//                        webView.loadUrl(pdfNull);
                    }
                }
                // Sub -> Design & Analysis of Algorithms
                else if ("Design & Analysis of Algorithms".equalsIgnoreCase(subjectName )) {
                    if("book".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1wqpPngYBijF-UiQlA1r-w1Qe_Y9IcASX/view?usp=drive_link");
                    else if ("notes".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1yOyb7hpmqQDOmjhEQnaUDR4RxL6Djqv4/view?usp=drive_link");
                    else if("models".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1DyROSQ3C6wjL8dThvTKiVVhD8yz1XjBS/view?usp=drive_link");
                    else if("exam papers".equalsIgnoreCase(mainButtonName))
                        webView.loadUrl("https://drive.google.com/file/d/1l6iW2a5BmqZtZ7EEf69k_WWegLwdcz9h/view?usp=drive_link");
                    else {
//                        webView.loadUrl(pdfNull);
                        nullPdfCall();
                    }
                }
                else if(("syllabus".equalsIgnoreCase(mainButtonName)) || ("Syllabus".equalsIgnoreCase(msg)) ) {
                    webView.loadUrl("https://drive.google.com/file/d/1NkhWanzQT9ldio7nb5KlY_aN3LQIBfzd/view?usp=drive_link");
//                        Toast.makeText(getContext(), "Work Properly!!!!!!!", Toast.LENGTH_SHORT).show();
                }



                else  {
                    Toast.makeText(getContext(), "Error! in Load Pdf", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "No choice found when loading pdf from google drive",
                            Toast.LENGTH_SHORT).show();

                    webView.loadUrl(noChoice);
                }

            }
        });

        requireActivity()
                .getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        // Manually pop and destroy the fragment
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
                });



        return view;
    }

    private void nullPdfCall () {
        String pdfNull = "https://drive.google" +
                ".com/file/d/1tN1MMFMrqcwm9sMee3oeZJWq5n56Khzz/view?usp=drive_link";
        webView.loadUrl(pdfNull);
        Toast.makeText(getContext(), "Error in loading the pdf",
                Toast.LENGTH_SHORT).show();
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

        // Show ActionBar and exit fullscreen
        if (requireActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().show();
            }
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }




}