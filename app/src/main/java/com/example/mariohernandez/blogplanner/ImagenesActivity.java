package com.example.mariohernandez.blogplanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class ImagenesActivity extends ActionBarActivity {


    private ImageView img;
    private int cuad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

        overrideFonts(getBaseContext(), findViewById(R.id.principal));

        findViewById(R.id.btn_sig).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((EditText)findViewById(R.id.objetivo)).setText(MyProperties.getInstance().getObjetivo());



        findViewById(R.id.img2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        findViewById(R.id.img1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.head_img));
        getSupportActionBar().setTitle("");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {

                if (requestCode == 1 ) {
                    Bundle extras = data.getExtras();
                    bitmap = (Bitmap) extras.get("data");

                }else {

                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                }

                if(cuad == 0) {
                    img = (ImageView) findViewById(R.id.cuadro1);
                    cuad ++;
                }else if(cuad == 1){
                    img = (ImageView) findViewById(R.id.cuadro2);
                    cuad ++;
                }else if(cuad == 2){
                    img = (ImageView) findViewById(R.id.cuadro3);
                    cuad ++;
                }else if(cuad == 3){
                    img = (ImageView) findViewById(R.id.cuadro4);
                    cuad ++;
                }else if(cuad == 4){
                    img = (ImageView) findViewById(R.id.cuadro5);
                    cuad ++;
                }else if(cuad == 5){
                    img = (ImageView) findViewById(R.id.cuadro6);
                    cuad = 0;
                }

                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_imagenes, menu);
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

    private void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/bodoni.ttc"));
            }
        } catch (Exception e) {
        }
    }
}
