package com.webingate.GameWinR.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webingate.GameWinR.R;


public class BookingHistoryFragment extends Fragment {

    private TextView statusTextView1;
    private TextView statusTextView2;
    private TextView statusTextView3;
//    private ImageView copyImageView1;
//    private ImageView copyImageView2;
//    private ImageView copyImageView3;

    private TextView transNoTextView1;
    private TextView transNoTextView2;
    private TextView transNoTextView3;

    private String status;

    public BookingHistoryFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public BookingHistoryFragment(String status) {
        // Required empty public constructor
        this.status = status;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.booking_history_fragment, container, false);
        initUI(view);
        initActions();
        return view;
    }

    private void initUI(View view) {
        statusTextView1 = view.findViewById(R.id.statusTextView1);
        statusTextView1.setText(this.status);

        statusTextView2 = view.findViewById(R.id.statusTextView2);
        statusTextView2.setText(this.status);

        statusTextView3 = view.findViewById(R.id.statusTextView3);
        statusTextView3.setText(this.status);

//        copyImageView1 = view.findViewById(R.id.copyImageView1);
//        copyImageView2 = view.findViewById(R.id.copyImageView2);
//        copyImageView3 = view.findViewById(R.id.copyImageView3);

        transNoTextView1 = view.findViewById(R.id.transNoTextView1);
        transNoTextView2 = view.findViewById(R.id.transNoTextView2);
        transNoTextView3 = view.findViewById(R.id.transNoTextView3);

    }

    private void initActions() {
//        copyImageView1.setOnClickListener(view -> {
//            ClipboardManager cManager = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData cData = ClipData.newPlainText("text", transNoTextView1.getText());
//            assert cManager != null;
//            cManager.setPrimaryClip(cData);
//            Toast.makeText(getContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
//        });
//
//        copyImageView2.setOnClickListener(view -> {
//            ClipboardManager cManager = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData cData = ClipData.newPlainText("text", transNoTextView2.getText());
//            assert cManager != null;
//            cManager.setPrimaryClip(cData);
//            Toast.makeText(getContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
//        });
//
//        copyImageView3.setOnClickListener(view -> {
//            ClipboardManager cManager = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData cData = ClipData.newPlainText("text", transNoTextView3.getText());
//            assert cManager != null;
//            cManager.setPrimaryClip(cData);
//            Toast.makeText(getContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
//        });
    }


}
