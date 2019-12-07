package com.example.tourism.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.HomeActivity;
import com.example.tourism.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    // Declaration
    private EditText edt_LoginEmail;
    private EditText edt_LoginPassword;
    private TextView tv_linkToRegister;

    // FireBase Decrlation
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    // Configure Google Sign In
    private static final int RC_SIGN_IN = 234;
    private static final String TAG = "GoogleSignin";
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth googleAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //// initialization
        edt_LoginEmail = findViewById(R.id.edt_loginEmail);
        edt_LoginPassword = findViewById(R.id.edt_loginPassword);
        Button btn_login = findViewById(R.id.btn_login);
        tv_linkToRegister = findViewById(R.id.tv_linkToSignUpActivity);

        //Button  google LOGIN
        Button btn_google = findViewById(R.id.btn_gmailLogin);
        googleAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInGoogle();
            }
        });
        //Finish google LOGIN

        //Action Go To Register Account
        tv_linkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Reg = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(Reg);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail = edt_LoginEmail.getText().toString();
                String loginPass  = edt_LoginPassword.getText().toString();

                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass))
                {
                    mAuth.signInWithEmailAndPassword(loginEmail , loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {
                                Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(home);
                                finish();

                            }else {
                                @SuppressLint({"NewApi", "LocalSuppress"}) String errorMessage = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(LoginActivity.this, "Error : " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    // Configure Google Sign In
    @Override
    protected void onStart() {
        super.onStart();

        if (googleAuth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "fireBaseAutWithGoogle: " + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        googleAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = googleAuth.getCurrentUser();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "User Successfully Signed in ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // Configure Google Sign In
}
