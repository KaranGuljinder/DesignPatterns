package mohkarmon.a4moc.lbj.Screens;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import mohkarmon.a4moc.lbj.APIEndpointInterface;
import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.Item;
import mohkarmon.a4moc.lbj.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAd extends Fragment {

    private MaterialEditText itemName, itemDescription, price,itemPNumber;
    private ImageView itemImg;
    private Spinner itemState;
    private APIEndpointInterface apiEndpointInterface;
    private ConnectedUser cUser;
    private String imgUrl;

    private Bitmap itemPic = null;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    private AmazonS3 s3;
    private final int PICK_IMAGE_REQUEST = 71;
    private int catID;
    private TransferUtility transferUtility;
    public AddAd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_ad, container, false);
        itemName = rootView.findViewById(R.id.addItemName);
        itemDescription = rootView.findViewById(R.id.addItemDescription);
        itemImg = rootView.findViewById(R.id.addItemPhoto);
        itemPNumber = rootView.findViewById(R.id.addItemPhone);
        price = rootView.findViewById(R.id.addItemPrice);
        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);
        cUser = ConnectedUser.getInstance();
        catID = getArguments().getInt("catID");
        TextView addItem = rootView.findViewById(R.id.additemButton);
        TextView cancel = rootView.findViewById(R.id.addItemCancel);

        itemState = rootView.findViewById(R.id.addItemState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.itemState, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemState.setAdapter(adapter);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();

            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAd();
            }
        });
        credentialsProvider();
        itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        setTransferUtility();

        return rootView;
    }

    private void addAd() {
        Item item = new Item(itemName.getText().toString(),"Paris",itemDescription.getText().toString(),itemState.getSelectedItem().toString(),Integer.parseInt(price.getText().toString()),imgUrl,catID,cUser.getUserid(),itemPNumber.getText().toString());
        Call<Item> call = apiEndpointInterface.createItem(item);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    private void credentialsProvider(){
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getContext(),"us-east-1:f586c6a6-c0e4-456c-bfa3-aa26a33092be",
        Regions.US_EAST_1 // Region
    );

        setAmazonS3Client(credentialsProvider);
    }

    private void setAmazonS3Client(CognitoCachingCredentialsProvider credentialsProvider){
        s3 = new AmazonS3Client(credentialsProvider);
        s3.setRegion(Region.getRegion(Regions.US_EAST_1));

    }

    private void setTransferUtility(){

        transferUtility = new TransferUtility(s3, getContext());
    }
    private void selectFile(){
        checkPermissionREAD_EXTERNAL_STORAGE(getContext());
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null ) {

            try {
                itemPic = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Uri tempUri = getImageUri(getContext(), itemPic);

            File finalFile = new File(getRealPathFromURI(tempUri));
            uploadFile(finalFile);


          /*  CropImage.activity(filePath)
                    .setAspectRatio(1, 1)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(480,480)
                    .start(getActivity());*/
        }
       /* if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                Log.d("IMG",String.valueOf(resultCode));
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                Log.d("IMG","uploadok");


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d("IMG",result.getError().getMessage());

            }
        }*/

    }


    private void uploadFile(File fp){
        ObjectMetadata myObjectMetadata = new ObjectMetadata();
        myObjectMetadata.setContentType("image/png");
        String mediaUrl = fp.getName();
        imgUrl = "https://s3.amazonaws.com/lbjapp/Images/"+mediaUrl;
        TransferObserver observer = transferUtility.upload("lbjapp","Images/"+mediaUrl,
                fp);
        Log.d("IMG",mediaUrl);
        observer.setTransferListener(new transferObserverListener());
    }


    private class transferObserverListener implements TransferListener {
        @Override
        public void onStateChanged(int id, TransferState state) {
            if (state == TransferState.COMPLETED) {

                itemImg.setImageBitmap(itemPic);
            }
        }

        @Override
        public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {


        }

        @Override
        public void onError(int id, Exception ex) {
        Log.d("IMG",ex.getMessage());
        }
    }
    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    private void checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    showDialog(context
                    );
                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
            } else {
            }

        } else {
        }
    }
    private void showDialog(final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage("External storage" + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }
}