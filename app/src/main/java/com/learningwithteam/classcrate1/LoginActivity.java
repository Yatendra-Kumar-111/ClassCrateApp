package com.learningwithteam.classcrate1;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import  com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class LoginActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1001;
    private GoogleSignInClient googleSignInClient;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseHelper = new FirebaseHelper(this);

        // 🔄 Auto-login check
        if (firebaseHelper.isUserLoggedIn()) {
            goToMain();
            return;
        }

        // 🔐 Google Sign-In options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // from google-services.json
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        Button googleLoginBtn = findViewById(R.id.btnGoogleLogin);
        googleLoginBtn.setOnClickListener(v -> startGoogleSignIn());
    }

    private void startGoogleSignIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseHelper.signInWithGoogle(account, success -> {
                    if (success) {
                        goToMain();
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (ApiException e) {
                Log.e("LoginActivity", "Google Sign-In Failed", e);
                Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, NavigationDrawerViewsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
//    SHA1:
//            63:7C:29:F0:BE:ED:86:EF:A0:A5:D1:F4:24:3F:70:81:3D:8C:2F:A5
//    SHA-256: 06:C5:7F:CD:A1:81:05:34:F4:21:45:4B:BE:F3:33:F1:BD:97:25:07:C6:C8:06:20:88:2D:0C:EC:1E:C8:D1:CA


}



/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Create this layout

        firebaseHelper.signInWithGoogle(account, success -> {
            if (success) {
                startActivity(new Intent(this, NavigationDrawerViewsActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}*/
/*


        private static final int RC_SIGN_IN = 1001;

        private FirebaseAuth mAuth;
        private GoogleSignInClient mGoogleSignInClient;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login); // Create this layout

            mAuth = FirebaseAuth.getInstance();

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id)) // from google-services.json
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            Button googleLoginBtn = findViewById(R.id.btnGoogleLogin); // Button in layout
            googleLoginBtn.setOnClickListener(v -> signInWithGoogle());
        }

        private void signInWithGoogle() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    Toast.makeText(this, "Google Sign-In Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void firebaseAuthWithGoogle(String idToken) {
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUserDataToFirebase(user);
                            startActivity(new Intent(LoginActivity.this, NavigationDrawerViewsActivity.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Firebase Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void saveUserDataToFirebase(FirebaseUser firebaseUser) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

            User user = new User(
                    firebaseUser.getUid(),
                    firebaseUser.getEmail(),
                    firebaseUser.getDisplayName() != null ? firebaseUser.getDisplayName() : "No Name"
            );

            ref.setValue(user);
        }

        public static class User {
            public String uid;
            public String email;
            public String username;

            public User() {} // Required for Firebase

            public User(String uid, String email, String username) {
                this.uid = uid;
                this.email = email;
                this.username = username;
            }
        }
    @Override
    protected void onStart() {
        super.onStart();
        // ✅ Auto-login check
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, NavigationDrawerViewsActivity.class));
            finish();
        }
    }

}

*/
