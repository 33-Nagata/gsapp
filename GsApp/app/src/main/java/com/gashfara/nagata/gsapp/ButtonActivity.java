package com.gashfara.nagata.gsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ButtonActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("image");
        String comment = bundle.getString("comment");

        ImageView imageLarge = (ImageView) findViewById(R.id.image_large);
        TextView textDetail = (TextView) findViewById(R.id.text_detail);
        Button button = (Button) findViewById(R.id.back_btn);
        imageLarge.setImageBitmap(bitmap);
        imageLarge.setAdjustViewBounds(true);
        textDetail.setText(comment);
        button.setOnTouchListener(new ViewGroup.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                finish();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
