package com.example.tourism.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tourism.MainActivity;
import com.example.tourism.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private CircleImageView ImageUser;
    private EditText edt_NamwUserReg, edt_EmailUserReg, edt_PasswordUserReg;

    //fireBase Declaration
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Uri mainImageURI = null;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private String uid = reference.push().getKey();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private String user_id = firebaseAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //// initialization ::: :::
        ImageUser = findViewById(R.id.circle_image_profile);
        edt_EmailUserReg = findViewById(R.id.edt_RegisterEmail);
        edt_NamwUserReg = findViewById(R.id.edt_RegisterName);
        edt_PasswordUserReg = findViewById(R.id.edt_RegisterPassword);
        Button btn_JoinUs = findViewById(R.id.btn_Register);

        //Action Button SAVA DATA

        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("CheckResult")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        String name = task.getResult().getString("name");
                        String image = task.getResult().getString("image");

                        edt_NamwUserReg.setText(name);

                        RequestOptions placeholderRequest = new RequestOptions();
                        placeholderRequest.placeholder(R.drawable.add_image);
                        Glide.with(RegisterActivity.this).setDefaultRequestOptions(placeholderRequest).load(image).into(ImageUser);
                    }

                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(RegisterActivity.this, "FireStore Retrieve Error: " + errorMessage, Toast.LENGTH_SHORT).show();

                }

            }
        });

        ////

        btn_JoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = edt_NamwUserReg.getText().toString();
                String email = edt_EmailUserReg.getText().toString();
                String password = edt_PasswordUserReg.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(userName) && mainImageURI != null) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                String errorMassage = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, "Error : " + errorMassage, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

                if (mainImageURI == null) {
                    Toast.makeText(RegisterActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
                    return;
                }

                storageReference.child("profileImages").child(user_id).putFile(mainImageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()) {
                            //getImageUrl
                            storageReference.child("profileImages").child(user_id).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    if (task.isSuccessful()) {

                                        String url = task.getResult().toString();
                                        // irebaseFireStore Save Data
                                        Map<String, String> userMap = new HashMap<>();
                                        userMap.put("name", userName);
                                        userMap.put("image", url);

                                        firebaseFirestore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                                    startActivity(i);

                                                } else {
                                                    String errorMessage = task.getException().getMessage();
                                                    Toast.makeText(RegisterActivity.this, "FireStore Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            });

                        } else {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }


    //@Override onActivityResult method in your activity to get crop result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mainImageURI = result.getUri();
                ImageUser.setImageURI(mainImageURI);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, "Error : " + error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
