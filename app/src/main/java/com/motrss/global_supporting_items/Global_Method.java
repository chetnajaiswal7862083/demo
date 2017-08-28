package com.motrss.global_supporting_items;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.maps.model.LatLng;


import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

/**
 * Created by hp on 17-05-2016.
 */
public class Global_Method {
    private Context context;


    public Global_Method(Context context) {
        this.context = context;

    }

    public boolean isConnectedtoInternet() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public String convertBitmapToString(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, stream); //compress to which format you want.
        byte[] byte_arr = stream.toByteArray();
        return Base64.encodeToString(byte_arr, Base64.DEFAULT);

    }
    public static Bitmap decodeImage(String input) {
        byte[] decodeBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.length);

    }

    public void showToast(Context context, String _showMessage) {
        Toast toast = Toast.makeText(context, _showMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showAlertdialog(Context _alertContext, String _message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(_alertContext
        ).create();

        // Setting Dialog Title
        alertDialog.setTitle(_message);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

                alertDialog.hide();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }


    public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context) {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }
/*

    public void showLayoutToast(Context context2, String _message) {


        LayoutInflater inflater = (LayoutInflater) context2
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.toast_layout,
                null);
        TextView text = (TextView) layout.findViewById(R.id.text1);
        text.setText(_message);
        Toast toast = new Toast(context2);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    public void showLayoutToastError(Context context2, String _message) {


        LayoutInflater inflater = (LayoutInflater) context2
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.toast_layout_error,
                null);
        TextView text = (TextView) layout.findViewById(R.id.text1);
        text.setText(_message);
        Toast toast = new Toast(context2);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    //................................................................................


    public double calculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371000;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        System.out.println("calculationByDistance....  "+lat1+"   "+lat2+"   "+lon1+"   "+lon2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c;
        // = 2 * Math.asin(Math.sqrt(a));
        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
     //  DecimalFormat df2 = new DecimalFormat("#.####");
//       // double meterr = Double.parseDouble(df2.format(meter));
       // double meterr = Double.parseDouble(df2.format(753,6786786));
        return valueResult;
    }
*/




}
