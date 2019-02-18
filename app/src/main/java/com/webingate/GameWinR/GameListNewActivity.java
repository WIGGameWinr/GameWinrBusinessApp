package com.webingate.GameWinR;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.GameWinR.Deo.GameResponce;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import com.webingate.giftsolution.activity.businessapp.Deo.RegisterBusinessUsers;
//import com.webingate.giftsolution.driverapplication.ApplicationConstants;
//import com.webingate.giftsolution.fragment.businessAppDriverUserInfoFragment;
//import com.webingate.giftsolution.fragment.businessAppUploadDocsFragment;
//import cropper.CropImage;
//import com.webingate.paysmartbusinessapp.pa;

public class GameListNewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatePickerDialog picker;
    Calendar dateTime = Calendar.getInstance();

    ImageView gameimage,uploadbtn;
    Button btnsubmit;
    List<GameResponce> res;
    EditText gametitle;
    List<String> typegroupslist = new ArrayList<String>();
    EditText s_size,gametime,editgamedesc,edittextmaxmembers,edittextentryfee,edittextgameid,edittextgamepasword;
    ArrayAdapter arlist;
    Toast toast;
    int pos;
    Spinner categoryspinner;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_adding);
        categoryspinner=findViewById(R.id.categoryspinner);
        categoryspinner.setOnItemSelectedListener(this);

        s_size=findViewById(R.id.s_size);
        gametime=findViewById(R.id.gametime);
        editgamedesc=findViewById(R.id.editgamedesc);
        edittextmaxmembers=findViewById(R.id.edittextmaxmembers);
        edittextentryfee=findViewById(R.id.edittextentryfee);
        edittextgameid=findViewById(R.id.edittextgameid);
        edittextgamepasword=findViewById(R.id.edittextgamepasword);
        initData();
        initUI();
        initDataBinding();
        initActions();
        GetTypeGroups(2);
        s_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(GameListNewActivity.this, datePickerDialog, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        gametime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(GameListNewActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                                gametime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
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
    private void initData() {

    }
    private void initUI() {

        initToolbar();
        gameimage=(ImageView)findViewById(R.id.gameimage);
        gameimage.setImageResource(R.drawable.baseline_image_black_24);
        btnsubmit=(Button)findViewById(R.id.btnsubmit);
        uploadbtn=(ImageView)findViewById(R.id.uploadbtn);
        gametitle=findViewById(R.id.gametitle);

    }
    private void initDataBinding() {

    }

    private void initActions() {
        btnsubmit.setOnClickListener(view ->{
            Toast.makeText(getApplicationContext(), "Successfully Added New Game", Toast.LENGTH_SHORT).show();


            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("GameImage","data:" + com.webingate.GameWinR.ApplicationConstants.pic_format + ";base64," +  com.webingate.GameWinR.ApplicationConstants.pic_data);
            jsonObject.addProperty("GameTitle", gametitle.getText().toString());
            jsonObject.addProperty("EntryFee", edittextentryfee.getText().toString());
            jsonObject.addProperty("MaxMembers", edittextmaxmembers.getText().toString());
            jsonObject.addProperty("GameDate", s_size.getText().toString());
            jsonObject.addProperty("Gametime", gametime.getText().toString());
            jsonObject.addProperty("GameDescription", editgamedesc.getText().toString());
            jsonObject.addProperty("GameCategory", res.get(pos).getId());
            jsonObject.addProperty("GameId", edittextgameid.getText().toString());
            jsonObject.addProperty("GamePassword", edittextgamepasword.getText().toString());
            jsonObject.addProperty("flag", "I");
            CreateNewGame(jsonObject);
        });

        uploadbtn.setOnClickListener(view ->{
            Toast.makeText(getApplicationContext(), "Clicked Upload Image", Toast.LENGTH_SHORT).show();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this);
        });
    }
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Add New Game");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Bitmap bitmap=null;
            if (resultCode == -1) {
                try {
                    Uri uri = result.getUri();
                    Uri uri1=data.getData();
                    bitmap = BitmapFactory.decodeFile(uri.getPath());
                    com.webingate.GameWinR.ApplicationConstants.pic_format = "image/jpeg";

                    //getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    String encodedImage = Base64.encodeToString(stringBuilder.toString().getBytes(), Base64.DEFAULT);
//                    ApplicationConstants.pic_data = encodedImage;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    com.webingate.GameWinR.ApplicationConstants.pic_data = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
                            .show();

                    Toast.makeText(this, "Cropping successful, URI: " + result.getUri(), Toast.LENGTH_LONG)
                            .show();
//                ephoto=(ImageView) findViewById(R.id.Edituserphoto);
//                ephoto.setImageURI(result.getUri());
                    // ephoto=(ImageView) findViewById(R.id.profileImageView);
                    //ephoto.setImageURI(result.getUri());
                    gameimage.setImageBitmap(bitmap);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }


        }
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;
        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }
    public void CreateNewGame(JsonObject jsonObject){
        com.webingate.GameWinR.utils.DataPrepare1.get(this).getrestadapter()
                .CreatNewGame(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GameResponce>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully SignUp");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                            DisplayToast("Unable to Login");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<GameResponce> responce) {
                        GameResponce res= responce.get(0);
                        if(res.getCode()!=null) {
                            DisplayToast(res.getDescription());
                        }else{
                            startActivity(new Intent(GameListNewActivity.this,GameListActivity.class));
                        }
                    }
                });
    }
    public void GetTypeGroups(int groupid){
        com.webingate.GameWinR.utils.DataPrepare1.get(this).getrestadapter()
                .GetTypeGroupId(groupid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GameResponce>>() {
                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully SignUp");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                            DisplayToast("Unable to Login");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<GameResponce> responce) {
                       res= responce;

                        for (int i = 0; i < res.size(); i++) {
                            typegroupslist.add(res.get(i).getName());
                        }

                        arlist=new ArrayAdapter(GameListNewActivity.this,android.R.layout.simple_spinner_item,typegroupslist);
                        arlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        categoryspinner.setAdapter(arlist);
                    }
                });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // loginasOption = position;
       pos=position;
       // DisplayToast("selected"+tt.get(position).getName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // loginasOption = -1;
    }
    DatePickerDialog.OnDateSetListener datePickerDialog = (view, year, monthOfYear, dayOfMonth) -> {
        dateTime.set(Calendar.YEAR, year);
        dateTime.set(Calendar.MONTH, monthOfYear);
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateDate();
    };
    private void updateDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        s_size.setText(sdf.format(dateTime.getTime()));
    }
}
