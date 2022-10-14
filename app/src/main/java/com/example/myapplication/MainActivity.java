package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnOpenInGoogleMaps(View view) {
        EditText teamAddress = (EditText) findViewById(R.id.teamAddressField);
// Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q=" + teamAddress.getText());
// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps
        mapIntent.setPackage("com.google.android.apps.maps");
// Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }

    public void OnSetAvatarButton(View view) {
//Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) return;
//Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.imageView);
//Figuring out the correct image
        String drawableName;
        switch (data.getIntExtra("imageID", R.id.bulls)) {
            case R.id.bulls:
                drawableName = "bulls";
                break;
            case R.id.knicks:
                drawableName = "knicks";
                break;
            case R.id.lakers:
                drawableName = "lakers";
                break;
            case R.id.magic:
                drawableName = "magic";
                break;
            case R.id.nets:
                drawableName = "nets";
                break;
            case R.id.raptors:
                drawableName = "raptors";
                break;
            default:
                drawableName = "strawhat";
                break;
        }
        int imageResource = getResources().getIdentifier("@drawable/"+drawableName, null, getPackageName());
        avatarImage.setImageDrawable(getDrawable(imageResource));
    }
}

