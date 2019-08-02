package com.kongzue.holderviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kongzue.holderview.HolderView;

public class MainActivity extends AppCompatActivity {
    
    private Button btnLoading;
    private Button btnBadNet;
    private Button btnErrorData;
    private Button btnEmptyData;
    private Button btnSuccess;
    private HolderView holdView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnLoading = findViewById(R.id.btn_loading);
        btnBadNet = findViewById(R.id.btn_badNet);
        btnErrorData = findViewById(R.id.btn_errorData);
        btnEmptyData = findViewById(R.id.btn_emptyData);
        btnSuccess = findViewById(R.id.btn_success);
        holdView = findViewById(R.id.holdView);
        
        holdView.showWait();
    
        btnLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdView.showWait();
            }
        });
    
        btnBadNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdView.showBadNet();
            }
        });
        
        btnErrorData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdView.showError();
            }
        });
        
        btnEmptyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdView.showEmpty();
            }
        });
        
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdView.showSuccess();
            }
        });
    }
}
