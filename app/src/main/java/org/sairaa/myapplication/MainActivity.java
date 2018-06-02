package org.sairaa.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView phoneNo = findViewById(R.id.contact_image);
        TextView phoneText = findViewById(R.id.contact);

        ImageView location = findViewById(R.id.location_image);
        TextView locationText = findViewById(R.id.address);

        phoneNo.setOnClickListener(this);
        phoneText.setOnClickListener(this);

        location.setOnClickListener(this);
        locationText.setOnClickListener(this);

        int images[] = {R.drawable.roger,R.drawable.serena,R.drawable.rafael};

        viewFlipper = findViewById(R.id.image_flip);


        for(int image:images){
            setViewFlipper(image);
        }
    }

    public void setViewFlipper(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000); //4 sec
        viewFlipper.setAutoStart(true);

        //Animation
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.contact:
                switchToDialIntent();
                break;
            case R.id.contact_image:
                switchToDialIntent();
                break;
            case R.id.address:
                switchToMapIntent();
                break;
            case R.id.location_image:
                switchToMapIntent();
                break;

        }
    }

    private void switchToDialIntent() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +getString(R.string.phoneNO)));
        startActivity(intent);
    }

    private void switchToMapIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:17.744775, 83.311122?z=63 m"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
