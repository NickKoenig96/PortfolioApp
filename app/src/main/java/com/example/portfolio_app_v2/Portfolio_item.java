package com.example.portfolio_app_v2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Portfolio_item extends AppCompatActivity {

    public static final String TAG = "TAG";
    FrameLayout frameLayout;
    ImageView image;
    TextView title;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frameLayout = findViewById(R.id.frameLayout);

        Intent i = getIntent();
        Bundle data = i.getExtras();
        Portfolio v = (Portfolio) data.getSerializable("portfolioData");

        getSupportActionBar().setTitle(v.getTitle());

        title = findViewById(R.id.videoTitle);
        desc = findViewById(R.id.videoDesc);
        image = findViewById(R.id.imageView);

         title.setText(v.getTitle());
        desc.setText(v.getDescription());



        final String shareTitle = (v.getTitle());
         final String  shareDesc = (v.getDescription());
         final String shareLink = (v.getLink());


        final String imageItem = (v.getImage());
        byte[] imageAsBytes = Base64.decode(imageItem.getBytes(), Base64.DEFAULT);
        image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0 , imageAsBytes.length));



        Button share = (Button) findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND); // IMPLICIT INTENT
                i.putExtra(Intent.EXTRA_SUBJECT, shareTitle);
                i.putExtra(Intent.EXTRA_TEXT,shareTitle +" "+ shareDesc +" "+ shareLink);
                i.setType("text/html");


                if (i.resolveActivity(v.getContext().getPackageManager()) != null) {
                    v.getContext().startActivity(i);
                }
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
