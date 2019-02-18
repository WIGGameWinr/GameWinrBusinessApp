package com.webingate.GameWinR;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentHistoryDetailsActivity extends AppCompatActivity {

    private TextView toggleTextView, toggleTextView1;
    private LinearLayout detailsLinearLayout, detailsLinearLayout1;
    private Boolean isOpen1 = false;
    private Boolean isOpen2 = false;
    private ImageView copyImageView1, copyImageView2;
    private TextView transactionNoTextView1, transactionNoTextView2;


    //region override methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_history_details_activity);

        initUI();

        initActions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Init Functions

    private void initUI() {
        initToolbar();

        toggleTextView = findViewById(R.id.toggleTextView);
        detailsLinearLayout = findViewById(R.id.detailsLinearLayout);
        copyImageView1 = findViewById(R.id.copyImageView1);
        transactionNoTextView1 = findViewById(R.id.transactionNoTextView1);

        detailsLinearLayout.setVisibility(View.GONE);

        toggleTextView1 = findViewById(R.id.toggleTextView1);
        detailsLinearLayout1 = findViewById(R.id.detailsLinearLayout1);
        copyImageView2 = findViewById(R.id.copyImageView2);
        transactionNoTextView2 = findViewById(R.id.transactionNoTextView2);

        detailsLinearLayout1.setVisibility(View.GONE);

    }

    private void initActions() {

        toggleTextView.setOnClickListener(view -> {
            if(!isOpen1) {
                detailsLinearLayout.setVisibility(View.VISIBLE);
                toggleTextView.setText("Hide Details");
                isOpen1 = true;
            } else {
                detailsLinearLayout.setVisibility(View.GONE);
                isOpen1 = false;
                toggleTextView.setText("Show Details");
            }
        });

        toggleTextView1.setOnClickListener(view -> {
            if(!isOpen2) {
                detailsLinearLayout1.setVisibility(View.VISIBLE);
                isOpen2 = true;
                toggleTextView1.setText("Hide Details");
            } else {
                detailsLinearLayout1.setVisibility(View.GONE);
                isOpen2 = false;
                toggleTextView1.setText("Show Details");
            }
        });

        copyImageView1.setOnClickListener(view -> {
            ClipboardManager cManager = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cData = ClipData.newPlainText("text", transactionNoTextView1.getText());
            assert cManager != null;
            cManager.setPrimaryClip(cData);
            Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
        });

        copyImageView2.setOnClickListener(view -> {
            ClipboardManager cManager = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cData = ClipData.newPlainText("text", transactionNoTextView2.getText());
            assert cManager != null;
            cManager.setPrimaryClip(cData);
            Toast.makeText(getApplicationContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
        });

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Payment History Details");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        }catch (Exception e){
            Log.e("TEAMPS","Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set display home as up enabled.");
        }

    }
    //endregion
}
