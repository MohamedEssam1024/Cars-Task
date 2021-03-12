package com.essam.carstask.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    protected void showErrorMessage(String error){
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }


    protected void showHideLoader(Boolean showLoader, ProgressBar progressBar){
        if (showLoader){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}