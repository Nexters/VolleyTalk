package com.teamnexters.volleytalk;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teamnexters.volleytalk.tool.NetworkModel;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class WriteActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 17;
    private String selectedImagePath;
    private Uri selectedImageUri;

    private ImageView iv_selected_photo_write;
    private TextView tv_upload_write;
    private EditText et_title_write, et_contents_write;
    private Cursor cursor;

    private String type;
    private String seq;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        seq = intent.getStringExtra("seq");

        ImageView iv_get_photo_write = (ImageView) findViewById(R.id.iv_get_photo_write);
        iv_get_photo_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });

        iv_selected_photo_write = (ImageView) findViewById(R.id.iv_selected_photo_write);
        iv_selected_photo_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //선택한 이미지 없애기
            }
        });

        et_title_write = (EditText) findViewById(R.id.et_title_write);
        et_contents_write = (EditText) findViewById(R.id.et_contents_write);

        tv_upload_write = (TextView) findViewById(R.id.tv_upload_write);
        tv_upload_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPost();
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                selectedImagePath = selectedImageUri.getPath();

                Log.e("PATH TEST", selectedImageUri.getPath());
                Log.e("PATH TEST", selectedImageUri.getEncodedPath());


                iv_selected_photo_write.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImageUri)
                        .apply(RequestOptions.centerCropTransform())
                        .into(iv_selected_photo_write);

            }
        }
    }

    private static String getRealPathFromURI_UpToAPI19(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor != null) {
            cursor.close();
        }
    }

    protected void uploadPost() {
        //permission 확인 필요.
        MultipartBody.Part imagePart = null;

        RequestBody requestType = RequestBody.create(MediaType.parse("multipart/form-data"), type);
        RequestBody requestSeq = RequestBody.create(MediaType.parse("multipart/form-data"), seq);
        RequestBody requestTitle = RequestBody.create(MediaType.parse("multipart/form-data"), et_title_write.getText().toString());
        RequestBody requestContents = RequestBody.create(MediaType.parse("multipart/form-data"), et_contents_write.getText().toString());

        if(selectedImageUri != null) {
            File file = new File(getRealPathFromURI_UpToAPI19(getApplicationContext(), selectedImageUri));
            Log.e("FILENAME", file.getName());

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            imagePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        }

        NetworkModel networkModel = NetworkModel.retrofit.create(NetworkModel.class);

       Call<ResForm<DefaultData>> call = networkModel.uploadPost(imagePart, requestType, requestSeq, requestTitle, requestContents);
       call.enqueue(new Callback<ResForm<DefaultData>>() {
           @Override
           public void onResponse(Call<ResForm<DefaultData>> call, Response<ResForm<DefaultData>> response) {
               ResForm<DefaultData> data = response.body();
               if (data.getStatus().equals("true")) {
                   Toast.makeText(getApplicationContext(), "글이 정상적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
                   finish();
               } else {

               }
           }

           @Override
           public void onFailure(Call<ResForm<DefaultData>> call, Throwable t) {

           }
       });
    }
}
