package com.archirayan.starmakerapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Base64;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.archirayan.starmakerapp.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit.mime.TypedFile;

@SuppressLint("SimpleDateFormat")
public class Util {

    private static final String TAG = Util.class.getSimpleName();
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static void setRegularFace(Context context, TextView textView) {
        Typeface mFontRegular = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");

        textView.setTypeface(mFontRegular);
    }

    public static void setMediumFace(Context context, TextView textView) {
        Typeface mFont = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Medium.ttf");

        textView.setTypeface(mFont);
    }

    public static void setLightFace(Context context, TextView textView) {
        Typeface mFontLight = Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Light.ttf");

        textView.setTypeface(mFontLight);
    }


    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c.getTime());
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static String locatToUTC(String dtStart) {
        String newDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date localTime = format.parse(dtStart);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gmtTime = new Date(sdf.format(localTime));
            newDate = format.format(gmtTime);
            return newDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String changeDOBFormate(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = " d" + " MMM" + "," + " yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String locatToUTCForLocalMsg(String dtStart) {
        String newDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date localTime = format.parse(dtStart);
            sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date gmtTime = new Date(sdf2.format(localTime));
            newDate = format.format(gmtTime);
            return newDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static void makeAlertSingleButtons(final AppCompatActivity appCompatActivity, String title) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(appCompatActivity);
        alertDialogBuilder.setMessage(title);
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public static void makeAlertTwoButtons(final AppCompatActivity appCompatActivity, String yesButton, String noButton) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(appCompatActivity);
        alertDialogBuilder.setMessage("Kindly grant all permission, we respect your privacy and data!");
        alertDialogBuilder.setPositiveButton(yesButton,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {

                            appCompatActivity.startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", appCompatActivity.getPackageName(), null)));
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton(noButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


    public static String capitalFirstLatter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static Bitmap decodeFile(File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 512;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    public static String diffBetweenTwoDates(Date startDate, Date endDate) {

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;


        String returnDate;
        if (elapsedDays == 0) {

            SimpleDateFormat spf = new SimpleDateFormat("hh:mm aaa");

            String retunString = spf.format(startDate);

            returnDate = retunString;
        } else {

            SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yy");

            String retunString = spf.format(startDate);

            returnDate = retunString;

        }

        System.out.printf("%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

        return returnDate;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static boolean checkFileSize(String filePath) {
        TypedFile file = new TypedFile("image/*", new File(filePath));
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;
        Log.e("fileSizeInMB", "::" + fileSizeInMB);
        if (fileSizeInMB >= 1) {
            return false;
        }
        return true;
    }

    public static String utcToLocalTime1(String dtStart) {
        if (dtStart != null && dtStart.length() > 5) {
            String newDate = "";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat(" d" + " MMM" + "," + " yyyy hh:mm a");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date localTime = format.parse(dtStart);
                newDate = sdf.format(localTime);
                return newDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return newDate;
        }
        return "";
    }

    public static String utcToLocalTime2(String dtStart) {
        if (dtStart != null && dtStart.length() > 5) {
            String newDate = "";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date localTime = format.parse(dtStart);
                newDate = sdf.format(localTime);
                return newDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return newDate;
        }
        return "";
    }

    public static String utcToLocalNewTime(String dtStart) {
        if (dtStart != null && dtStart.length() > 5) {
            String newDate = "";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat(" d" + " MMM" + "," + " hh:mm a");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                Date localTime = format.parse(dtStart);
                newDate = sdf.format(localTime);
                return newDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return newDate;
        }
        return "";
    }


    public static boolean checkPermission(String permission, Context context) {
        int res = context.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    //Getting lat long
    public static String ReadSharePrefrence(Context context, String key) {
        @SuppressWarnings("static-access")
        SharedPreferences read_data = null;
        try {

            read_data = context.getSharedPreferences(
                    Constant.SHRED_PR.SHARE_PREF, context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return read_data.getString(key, "");
    }

    public static void WriteSharePrefrence(Context context, String key,
                                           String values) {
        @SuppressWarnings("static-access")
        SharedPreferences write_Data = context.getSharedPreferences(
                Constant.SHRED_PR.SHARE_PREF, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = write_Data.edit();
        editor.putString(key, values);
        editor.commit();
        editor.apply();
    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }

//
//    public static String ReadSharePrefrenceFCM(Context context, String key) {
//        @SuppressWarnings("static-access")
//        SharedPreferences read_data = context.getSharedPreferences(
//                Constant.SHRED_PR.SHARE_PREF_FCM, context.MODE_PRIVATE);
//
//        return read_data.getString(key, "");
//    }


//    public static void WriteSharePrefrenceFCM(Context context, String key,
//                                              String values)
//    {
//        @SuppressWarnings("static-access")
//        SharedPreferences write_Data = context.getSharedPreferences(
//                Constant.SHRED_PR.SHARE_PREF_FCM, context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = write_Data.edit();
//        editor.putString(key, values);
//        editor.commit();
//        editor.apply();
//    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String getString(InputStream in) {
        InputStreamReader is = new InputStreamReader(in);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(is);
        try {
            String read = br.readLine();
            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR WHILE PARSING RESPONSE TO STRING :: " + e.getMessage());
        } finally {
            try {
                if (is != null) is.close();
                if (br != null) br.close();
            } catch (Exception e) {
            }
        }
        return sb.toString();
    }

}