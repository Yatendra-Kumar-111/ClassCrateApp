package com.learningwithteam.classcrate1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.learningwithteam.classcrate1.databinding.ActivityNavigationDrawerViewsBinding;
import com.learningwithteam.classcrate1.ui.home.HomeFragment;

import java.io.File;
import java.util.HashMap;

public class NavigationDrawerViewsActivity extends AppCompatActivity {



    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerViewsBinding binding;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationDrawerViewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigationDrawerViews.toolbar);


        FirebaseHelper firebaseHelper = new FirebaseHelper(this);
        FirebaseUser user = firebaseHelper.getCurrentUser();

/*

        // ✅ Initialize Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // ✅ Log a custom event (optional)
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, "app_opened");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
*/

        drawer = binding.drawerLayout;
        navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();

//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_views);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //  Custom menu click handling
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_dark_mode) {
                // Dark Mode toggle
                int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            } else if (id == R.id.nav_about) {
                btnAboutUs();
            } else if (id == R.id.nav_rate) {
                showRatingDialog();
            } else if (id == R.id.nav_share) {
                btnShare();
            } else if (id == R.id.nav_exit) {
                showExitDialog();
            }
            drawer.closeDrawers(); // Close drawer
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer_views, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_views);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void finish() {
        showExitDialog();
//        super.finish();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom); // or reverse it if needed
    }

    public void onBackPressedDispatcher() {
        super.getOnBackPressedDispatcher();

        showExitDialog();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutHome);

            // Replace "HomeFragment" with your first or main fragment
            if (currentFragment instanceof HomeFragment) {
                // If we are on the first fragment, ask before exit
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                showExitDialog();
            } else {
                // Otherwise, pop the back stack (go to previous fragment)
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                getSupportFragmentManager().popBackStack();
            }
        }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            return btnAboutUs();
        } else if (id == R.id.action_rate) {
            showRatingDialog();
            return true;
        } else if (id == R.id.action_share) {
            btnShare();
        } else if (id == R.id.action_exit) {
            showExitDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
//                .setPositiveButton("Yes", (dialog, which) -> finishAffinity())
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity(); // or finish() if you just want to close current activity
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private boolean btnAboutUs() {
        Intent intent = new Intent(NavigationDrawerViewsActivity.this, AboutUsLayout.class);
        startActivity(intent);
        return true;
    }

    private boolean btnShare() {


        String folderLink = "https://drive.google.com/drive/folders/1V64WVO-vUBa2b6AaPPWcDxjJBmZhOId0?usp=drive_link";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Download ClassCrate App Files");
        intent.putExtra(Intent.EXTRA_TEXT,
                "📂 *Access app file here*:\n" + folderLink +
                        "\n\n💡 *Tip:* Open in browser or Google Drive app for best experience! 🌐📁"
        );
        startActivity(Intent.createChooser(intent, "Share Folder Link via"));



//        String driveLink = "https://drive.google.com/file/d/1V64WVO-vUBa2b6AaPPWcDxjJBmZhOId0/view?usp=sharing"; // ClassCrate1

//        String driveLink = "https://drive.google.com/file/d/1Yf0zV71MZ5HBc1auCQqq5iqhWjSApHoc/view?usp=sharing"; // ClassCrate1
//        String driveLink = "https://drive.google.com/file/d/1589eZ39CMP6PZRJv3fBn7hICnbvvoVbn" +
//                "/view?usp=sharing"; // ClassCrateApp link

/*
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");  // We're sharing text (the link)

        intent.putExtra(Intent.EXTRA_SUBJECT, "Download ClassCrate App");
//        intent.putExtra(Intent.EXTRA_TEXT, "Download our app using this link:\n" + driveLink);

        intent.putExtra(Intent.EXTRA_TEXT,
                "📲 *Download our app using this link (open in browser for best results)*:\n" +
                        driveLink +
                        "\n\n💡 *Tip:* If it doesn't open correctly, 👉 *long-press the link* and choose *'Open in browser'* 🌐."
        );
        startActivity(Intent.createChooser(intent, "Share App Link via"));
*/


//  1----------------First Method for sharing----------------
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app: https://play.google.com/store/apps/details?id=" + getPackageName());
//        startActivity(Intent.createChooser(shareIntent, "Share via"));

//  2----------------Second Method for sharing----------------
/*
        try {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String apkPath = app.sourceDir;
            File apkFile = new File(apkPath);

            Uri apkUri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".provider",
                    apkFile
            );

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, apkUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Share App via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
//  3----------------Third Method for sharing----------------
/*
        try {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String apkPath = app.sourceDir;
            File originalApk = new File(apkPath);

            // Copy to cache dir with custom name
            File newApk = new File(getExternalCacheDir(), "ClassCrate.apk");
            try (java.io.InputStream in = new java.io.FileInputStream(originalApk);
                 java.io.OutputStream out = new java.io.FileOutputStream(newApk)) {

                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }

            Uri apkUri = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".provider",
                    newApk
            );

            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("application/vnd.android.package-archive");
            intent.setType("application/octet-stream");
            intent.putExtra(Intent.EXTRA_STREAM, apkUri);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Share App via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    */
        return true;
    }

    private void showRatingDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rating); // Custom layout for the dialog
        dialog.setTitle("Rate this app");

        DatabaseReference ratingRef = FirebaseDatabase.getInstance().getReference("app_ratings");

        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        Button submitBtn = (Button) dialog.findViewById(R.id.submitButton);
        RatingBar ratingBar = (RatingBar) dialog. findViewById(R.id.ratingBar);
        EditText feedbackEditText = (EditText) dialog. findViewById(R.id.feedback);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ratingValue = ratingBar.getRating();
                String feedback = feedbackEditText.getText().toString();

                String key = ratingRef.push().getKey();

                HashMap<String, Object> ratingData = new HashMap<>();
                ratingData.put("rating", ratingValue);
                ratingData.put("feedback", feedback);

                assert key != null;
                ratingRef.child(key).setValue(ratingData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(getApplicationContext(), "Thanks for your feedback!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        })
                        .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to submit!", Toast.LENGTH_SHORT).show());
            }
        });
       dialog.show();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
