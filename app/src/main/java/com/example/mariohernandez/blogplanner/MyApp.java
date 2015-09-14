package com.example.mariohernandez.blogplanner;

import android.app.Application;

public class MyApp extends Application {
  
  @Override
  public void onCreate() {
    TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/bodoni.ttc");
  }
}