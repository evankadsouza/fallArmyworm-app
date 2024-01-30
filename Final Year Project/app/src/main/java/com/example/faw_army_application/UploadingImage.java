package com.example.faw_army_application;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadingImage extends AppCompatActivity {

    public static final String KEY_User_Document1 = "doc1";
    ImageView imageView;
    Button Upload_Btn,Submit;
    TextView imgUp;
    EditText imgPath;

    private String ImageUri;
    private String Document_img1="";
    private static final int CAMERA_PIC_REQUEST = 2000;
    private static final int gallery_req_code=1000; //randoms values are taken

    Uri currPathImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading_image);

       // IDProf=(ImageView)findViewById(R.id.IdProf);

        imageView=(ImageView)findViewById(R.id.selectedImage);
        Upload_Btn=(Button)findViewById(R.id.browse);
        imgUp=(TextView)findViewById(R.id.upimg);
        Submit=(Button)findViewById(R.id.submit);
        imgPath=(EditText)findViewById(R.id.path);

        imgPath.setEnabled(false);
        //Submit.setEnabled(false);

//        if(imgPath.getText()!=null)
//        {
//            Submit.setEnabled(true);
//        }
        selectImage();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imgPath.getText().toString().equals("")){
                    //Submit.setEnabled(false);
                    System.out.println("hekkk"+imgPath.getText());
                    Toast.makeText(UploadingImage.this, "Upload your Image first", LENGTH_SHORT).show();
                }
                else{
                //Submit.setEnabled(true);
                displayResult();
           }

            }
        });
    }

    private void displayResult() {
        Intent intent=new Intent(this,Result.class);
        startActivity(intent);
    }


    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        final int[] checkedItem = {-1};

//        Upload_Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UploadingImage.this);
//                alertDialog.setTitle("Upload Image");
//                final String[] listItems = new String[]{"Take Photo", "Choose from Gallery"};
//                alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        checkedItem[0] = i;
//                        dialogInterface.dismiss();
//
//                        //alertDialog.setTitle("hello");
//                        //upimg.setText(i);
//                        System.out.println("code: "+Integer.toString(i));
//
//                        if(i==0){
//
////                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
////                            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
//
////                            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
////                            photoPickerIntent.setType("image/*");
////                            startActivityForResult(photoPickerIntent, CAMERA_PIC_REQUEST);
//
//
//                            Intent camera_intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); //this will let us use all the app for capturing images
//                            startActivityForResult(camera_intent,CAMERA_PIC_REQUEST);
//
//
//                        }
//                        else if(i==1){
////                            Intent intent = new Intent();
////                            intent.setType("image/*");
////                            intent.setAction(Intent.ACTION_GET_CONTENT);
////                            startActivityForResult(Intent.createChooser(intent,"Choose from Gallery"),SELECT_IMAGE);
//
////                            Intent intent = new Intent(Intent.ACTION_PICK,
////                                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
////                            final int ACTIVITY_SELECT_IMAGE = 1234;
////                            startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
//                            Intent gallery_intent=new Intent(Intent.ACTION_PICK);
//                            gallery_intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                            startActivityForResult(gallery_intent,gallery_req_code);
//
//                            //protected void onActivityResult(int requestCode, int resultCode)
//
//                           // AlertDialog customAlertDialog = alertDialog.create();
//                            //customAlertDialog.dismiss();
//                        }
//                    }
//                });
//                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                AlertDialog customAlertDialog = alertDialog.create();
//                customAlertDialog.show();
//            }
//        });
//

//        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
//
//        alertDialog.setTitle("Upload Image");
//        alertDialog.setIcon(R.drawable.ic_baseline_cloud_upload_24);
//        alertDialog.setMessage("hghgu");
//
//        alertDialog.setButton("helllo",
//                new DialogInterface.OnClickListener(){
//
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(UploadingImage.this, "Hurray", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        alertDialog.show();
//
        Upload_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UploadingImage.this);
                dialogBuilder.setTitle("Upload Image");
                dialogBuilder.setIcon(R.drawable.ic_baseline_cloud_upload_24);
                final String[] listItems = new String[]{"Take Photo", "Choose from Gallery"};
                dialogBuilder.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UploadingImage.this, "Hello", LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        if(i==0){
                            System.out.println("Uploading from camera");
                            Intent camera_intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); //this will let us use all the app for capturing images

                            camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,ImageUri);
                            startActivityForResult(camera_intent,CAMERA_PIC_REQUEST);
//
                        }
                        if(i==1){
                            System.out.println("Uploading from gallery");
                            Intent gallery_intent=new Intent(Intent.ACTION_PICK);
                            gallery_intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gallery_intent,gallery_req_code);
                        }
                    }
                });
                dialogBuilder.show();
            }
        });

    }
        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(requestCode== gallery_req_code){

                imageView.setImageURI(data.getData());
                Toast.makeText(UploadingImage.this, "Succssfullt uploaded", LENGTH_SHORT).show();
                currPathImage = data.getData();

                File finalFile = new File(getRealPathFromURI(currPathImage));



            }

            if(requestCode==CAMERA_PIC_REQUEST){

                Toast.makeText(UploadingImage.this, "Succssfullt uploaded", LENGTH_SHORT).show();
//
                if(data!=null){

                    Bitmap bitmapImage=(Bitmap)data.getExtras().get("data");
                    imageView.setImageBitmap(bitmapImage);

                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(getApplicationContext(), bitmapImage);

                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File finalFile = new File(getRealPathFromURI(tempUri));

                    System.out.println("Path"+ImageUri);


                }

                else{
                    Toast.makeText(this, "Error Uploading Photo", LENGTH_SHORT).show();
                }
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//        return cursor.getString(idx);

        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        System.out.println("Path is .."+path);
        imgPath.setText(path);
        return path;
    }



    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode==CAMERA_PIC_REQUEST && resultCode==RESULT_OK && null !=data)
//        {
//
//            Uri uri = data.getData();
//            String[] prjection ={MediaStore.Images.Media.DATA};
//            Cursor cursor=getContentResolver().query(uri,prjection,null,null,null);
//            cursor.moveToFirst();
//
//            int columnIndex=cursor.getColumnIndex(prjection[0]);
//            String path=cursor.getString(columnIndex);
//            cursor.close();
//
//            Bitmap selectFile = BitmapFactory.decodeFile(path);
//
//
//            Drawable d = new BitmapDrawable(selectFile);
//            imageView.setBackground(d);
//
//
//            // imageView.setImageBitmap(BitmapFactory.decodeFile(path));
//        }
//
//
//    }
//

//    @Override
//    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//
//
//        if (resultCode == RESULT_OK) {
//            try {
//                final Uri imageUri = data.getData();
//                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                imageView.setImageBitmap(selectedImage);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                Toast.makeText(UploadingImage.this, "Something went wrong", Toast.LENGTH_LONG).show();
//            }
//
//        }else {
//            Toast.makeText(UploadingImage.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
//        }
//    }


    }
