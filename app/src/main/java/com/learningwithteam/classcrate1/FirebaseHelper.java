package com.learningwithteam.classcrate1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;

import java.util.HashMap;
import android.content.SharedPreferences;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private static final String TAG = "FirebaseHelper";
    private final FirebaseAuth mAuth;
    private final DatabaseReference userRef;
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public FirebaseHelper(Context context) {
        FirebaseApp.initializeApp(context);
        this.context = context;
        this.mAuth = FirebaseAuth.getInstance();
        this.userRef = FirebaseDatabase.getInstance().getReference("Users");
        this.sharedPreferences = context.getSharedPreferences("AutoLoginPrefs", Context.MODE_PRIVATE);
    }

    // 🔐 Login with Google
    public void signInWithGoogle(GoogleSignInAccount account, OnCompleteCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    saveUserData(user);
                    saveAutoLoginInfo();
                    callback.onComplete(true);
                }
            } else {
                Log.e(TAG, "Google sign in failed", task.getException());
                callback.onComplete(false);
            }
        });
    }
    // 📤 Save user info to Firebase Realtime Database
    private void saveUserData(FirebaseUser user) {
        String uid = user.getUid();
        String name = user.getDisplayName();
        String email = user.getEmail();
        String photoUrl = user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : "";

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("uid", uid);
        userMap.put("name", name);
        userMap.put("email", email);
        userMap.put("photo", photoUrl);

        userRef.child(uid).setValue(userMap)
                .addOnSuccessListener(unused -> Log.d(TAG, "User data saved"))
                .addOnFailureListener(e -> Log.e(TAG, "Failed to save user data", e));
    }
    // 🔄 Auto-login logic using SharedPreferences
    private void saveAutoLoginInfo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }
    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean("isLoggedIn", false) && mAuth.getCurrentUser() != null;
    }
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
    public void logout(Activity activity, Runnable afterLogout) {
        mAuth.signOut();
        sharedPreferences.edit().clear().apply();
        Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
        afterLogout.run();
    }
    // ⭐ Save app rating to Firebase
    public void saveAppRating(float rating) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            userRef.child(uid).child("rating").setValue(rating)
                    .addOnSuccessListener(unused -> Toast.makeText(context, "Thanks for rating!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Log.e(TAG, "Failed to save rating", e));
        }
    }
    // Callback Interface
    public interface OnCompleteCallback {
        void onComplete(boolean success);
    }
}