package com.example.taskapp.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


import java.util.concurrent.TimeUnit;



public class PhoneActivity extends AppCompatActivity {

LottieAnimationView lottieAnimationView;
    private EditText editNumber;
    private EditText sms;
    private String id;
    private Button onSms;
    private boolean isCodeSend;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verifyPhoneNumber;

    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityphone);

        sms = findViewById(R.id.smsCode);
        onSms = findViewById(R.id.onSmsClick);
        editNumber = findViewById(R.id.lolloo);
   lottieAnimationView=findViewById(R.id.lotiemyy);











        verifyPhoneNumber = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                if (isCodeSend) {
                } else {
                    singIn(phoneAuthCredential);
                }

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("2", "onVerificationCompleted" + e.getMessage());


            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                id = s;
                isCodeSend = true;

            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);

            }
        };
    }

    private void singIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                         startActivity(new Intent(PhoneActivity.this, MainActivity.class));
                            Toast.makeText(PhoneActivity.this, "Нажали кнопку", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(PhoneActivity.this, "провал", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




    public void onClick(View view) {
        hideKeyboard(PhoneActivity.this);
        String number = editNumber.getText().toString().trim();
        if (number.isEmpty()){
            editNumber.setError("Номер не введен");
            editNumber.requestFocus();

            if (number!=null) {
                lottieAnimationView.pauseAnimation();
            }            return;
        }

        if (number.length() < 10){
            editNumber.setError("Неправильный номер телефона");
            editNumber.requestFocus();
            lottieAnimationView.playAnimation();
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60,
                TimeUnit.SECONDS, this, verifyPhoneNumber);
        editNumber.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        sms.setVisibility(View.VISIBLE);
        onSms.setVisibility(View.VISIBLE);
    }

    public void onCodeClic(View view) {
        String code = sms.getText().toString();
        if (code.isEmpty()){
            sms.setError("СМС код не введен");
            sms.requestFocus();
            return;
        }
        PhoneAuthCredential phoneCredential = PhoneAuthProvider.getCredential(id, code);
        singIn(phoneCredential);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();

        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




        }














