package com.webingate.GameWinR;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.GameWinR.Deo.UsersResponse;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cropper.CropImage;
import cropper.CropImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity {
    DatePickerDialog picker;
    Calendar dateTime = Calendar.getInstance();
    EditText dateofbirth,username,firstname,lastname,adminemail,adminmobileno,password;
    RadioGroup radioSex;
    RadioButton radiogender;
    Button SignUp;
    ImageView profileImageView,edituserphoto;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        initUI();
        initDataBindings();
        initActions();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI()
    {
        profileImageView=(ImageView)findViewById(R.id.profileImageView);
        profileImageView.setImageResource(R.drawable.baseline_image_black_24);
        dateofbirth=(EditText)findViewById(R.id.dateofbirth);
        username=(EditText)findViewById(R.id.username);
        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);
        adminemail=(EditText)findViewById(R.id.adminemail);
        adminmobileno=(EditText)findViewById(R.id.adminmobileno);
        radioSex=(RadioGroup) findViewById(R.id.radioSex);
        SignUp=(Button)findViewById(R.id.SignUp);
        password=(EditText) findViewById(R.id.password);
        edituserphoto=findViewById(R.id.edituserphoto);
        dateofbirth.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new DatePickerDialog(SignUpActivity.this, datePickerDialog, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
           }
       });


    }

    private void initDataBindings()
    {
        initToolbar();
    }

    private void initActions()
    {
        SignUp.setOnClickListener(view -> {

            Toast.makeText(getApplicationContext(), "Clicked on Signup", Toast.LENGTH_SHORT).show();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Username", username.getText().toString());
            jsonObject.addProperty("FirstName", firstname.getText().toString());
            jsonObject.addProperty("lastname", lastname.getText().toString());
            jsonObject.addProperty("Email", adminemail.getText().toString());
            jsonObject.addProperty("Mobilenumber", adminmobileno.getText().toString());
            jsonObject.addProperty("password", password.getText().toString());
            jsonObject.addProperty("dateofbirth", dateofbirth.getText().toString());
            jsonObject.addProperty("UserPhoto","data:" + ApplicationConstants.pic_format + ";base64," +  ApplicationConstants.pic_data);
            jsonObject.addProperty("Active", 1);
            jsonObject.addProperty("flag", "I");

            int selectedId = radioSex.getCheckedRadioButtonId();
            radiogender = findViewById(selectedId);

            jsonObject.addProperty("Gender", ((radiogender.getText().toString().contains("Male"))?1:2));

            CreatingNewAdmin(jsonObject);
        });
        edituserphoto.setOnClickListener(View  -> {
            Toast.makeText(this, "Upload Profile Image", Toast.LENGTH_SHORT).show();
            //Intent intent =new Intent(getContext(),CropingMainActivity.class);
            //startActivity(intent);

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this);
        });

    }

    DatePickerDialog.OnDateSetListener datePickerDialog = (view, year, monthOfYear, dayOfMonth) -> {
        dateTime.set(Calendar.YEAR, year);
        dateTime.set(Calendar.MONTH, monthOfYear);
        dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateDate();
    };
    private void updateDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateofbirth.setText(sdf.format(dateTime.getTime()));
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("SignUp");

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
    public void CreatingNewAdmin(JsonObject jsonObject){
        com.webingate.GameWinR.utils.DataPrepare1.get(this).getrestadapter()
                .CreateUser(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UsersResponse>>() {
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
                    public void onNext(List<UsersResponse> responce) {
                        UsersResponse res= responce.get(0);
                         if(res.getCode()!=null) {
                             DisplayToast(res.getDescription());
                         }else{
                             startActivity(new Intent(SignUpActivity.this,LoginGameActivity.class));
                         }
                    }
                });
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;
        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
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
                    ApplicationConstants.pic_format = "image/jpeg";

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
                    ApplicationConstants.pic_data = Base64.encodeToString(imageBytes, Base64.DEFAULT);
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
                    profileImageView.setImageBitmap(bitmap);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }


        }
    }
}
