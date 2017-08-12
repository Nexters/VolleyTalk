package com.teamnexters.volleytalk;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by MIN on 2017. 8. 12..
 */

public class WriteActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 17;
    private String selectedImagePath;

    private ImageView iv_selected_photo_write;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        ImageView iv_get_photo_write = (ImageView) findViewById(R.id.iv_get_photo_write);
        iv_get_photo_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        iv_selected_photo_write = (ImageView) findViewById(R.id.iv_selected_photo_write);
        iv_selected_photo_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //선택한 이미지 없애기
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);

                iv_selected_photo_write.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImageUri)
                        .apply(RequestOptions.centerCropTransform())
                        .into(iv_selected_photo_write);

            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            //close하면 재선택 불가. 나중에 수정 필요.
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }


}
