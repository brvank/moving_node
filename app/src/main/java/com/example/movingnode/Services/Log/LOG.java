package com.example.movingnode.Services.Log;

import android.util.Log;

public class LOG {
    public static void d(String message){
        android.util.Log.d("Moving Node(Debug) : ", message);
    }

    public static void e(String message){
        android.util.Log.e("Moving Node(Error) : ", message);
    }

    public static void i(String message){
        android.util.Log.i("Moving Node(Info) : ", message);
    }

    public static void v(String message){
        android.util.Log.v("Moving Node(Verbose) : ", message);
    }
}
