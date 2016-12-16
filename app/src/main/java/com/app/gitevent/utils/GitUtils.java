package com.app.gitevent.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.gitevent.R;
import com.app.gitevent.customViews.GitTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static android.view.View.GONE;

/**
 * Created by niranjan on 12/7/16.
 */

public class GitUtils {
    //public static String yyyyMMdd = ;  2016-12-07T06:52:16Z
    public static String yyyyMMddhhmmZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String hhmma = "hh:mm a";
    public static String MMMMddyyyy = "MMMM dd, yyyy";

    public static final int DEFAULT_GROUP_ID = 1000;
    public static final int DEFAULT_CHILD_ID = 10000;

    public static int RESULT_PER_PAGE_COUNT = 10;
    public static String STR_USER_NAME = "userName";

    public static String EXTRA_APPOINTMENT_DATETIME_OBJ = "dateTime_slot_obj";

    public final static int VIEW_TYPE_LOAD_MORE = 3000;
    public final static int VIEW_TYPE_EVENTS = 3001;

    private static long miliSecsInOneMin = 60 * 1000;
    private static long miliSecsInOneHour = 60 * miliSecsInOneMin;
    private static long miliSecsInOneDay = 24 * miliSecsInOneHour;
    public static String EXTRA_ACOOUNT_OBJ = "extra_acoount_obj";
    public static String EXTRA_EVENT_OBJ = "extra_event_obj";

    public static Date parseDateString(String dateFormat, String dateString) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
            fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
            return fmt.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String formatDate(String dateFormat, Date date) {
        SimpleDateFormat fmtOut = new SimpleDateFormat(dateFormat);
        return fmtOut.format(date);
    }

    public static String getDayOfWeek(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("EE");
        return fmt.format(date);
    }

    public static String getDateOfMonth(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("d");
        return fmt.format(date);
    }

    public static String getMonthFromDate(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("MMMM");
        return fmt.format(date);
    }

    public static String getTimeAgo(String date, String dateFormat) {
        String timeAgo = "";
        try {
            Date pastDate = parseDateString(date, dateFormat);
            Date now = new Date();
            long diff = now.getTime() - pastDate.getTime();

            if (diff > miliSecsInOneDay)
                timeAgo = TimeUnit.MILLISECONDS.toDays(diff) + " days ago";
            else if (diff > miliSecsInOneHour)
                timeAgo = TimeUnit.MILLISECONDS.toHours(diff) + " hours ago";
            else if (diff > miliSecsInOneMin)
                timeAgo = TimeUnit.MILLISECONDS.toMinutes(diff) + " minutes ago";
            else if (diff < miliSecsInOneMin)
                timeAgo = "Just now";


        } catch (Exception j) {
            j.printStackTrace();
        }
        return timeAgo;
    }


    /**
     * Method help get random color to fill user name initials background for material design
     *
     * @param context instance of context
     * @return will return random color name
     */
    public static int getColorForName(Context context, Character str) {
        int[] colorsArray = context.getResources().getIntArray(R.array.git_event_initials_colors);
        switch (str) {
            case 'A':
            case 'C':
                return colorsArray[0];

            case 'B':
            case 'O':
                return colorsArray[1];


            case 'P':
            case 'N':
                return colorsArray[2];

            case 'D':
            case 'Q':
                return colorsArray[3];


            case 'E':
            case 'R':
                return colorsArray[4];

            case 'F':
            case 'S':
                return colorsArray[5];

            case 'G':
            case 'T':
                return colorsArray[6];

            case 'H':
            case 'U':
                return colorsArray[7];

            case 'I':
            case 'V':
                return colorsArray[8];

            case 'J':
            case 'W':
                return colorsArray[9];

            case 'K':
            case 'X':
                return colorsArray[10];

            case 'L':
            case 'Y':
                return colorsArray[11];

            default:
                return colorsArray[12];

        }
    }

    public static void loadRoundedImageThroughPicasso(final Context mContext, String picURL, final ImageView imgVw, int placeholderDrawable) {
        Drawable drawable = ContextCompat.getDrawable(mContext, placeholderDrawable);

        if (URLUtil.isValidUrl(picURL)) {
            Picasso.with(mContext).load(picURL).placeholder(drawable).error(drawable).transform(new CircleTransform()).into(imgVw, new Callback() {
                @Override
                public void onSuccess() {
                    //imgVw.setBackgroundResource(0);
                }

                @Override
                public void onError() {
                }
            });
        } else {
            Picasso.with(mContext).load(placeholderDrawable).error(drawable).into(imgVw);
        }

    }

    public static void setSwipeRefreshLayoutColor(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorScheme(R.color.holo_green_light, R.color.holo_red_light, R.color.holo_blue_light, R.color.holo_red_light);
    }

    public static boolean checkIfInternetAvialable(Context ctx) {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        isConnected = ni != null && ni.isConnected();
        return isConnected;
    }

    public static boolean isConnected(final Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean isConnected = checkIfInternetAvialable(ctx);
        if (!isConnected) {
            Toast.makeText(ctx, "No internet connnection, please try again later.", Toast.LENGTH_LONG).show();
        }
        return isConnected;
    }

    public static RequestProgressDialog getRequestProgressDialog(Context mContext, String loadingMessage) {
        RequestProgressDialog progressDialog = new RequestProgressDialog(mContext, loadingMessage, 0);
        return progressDialog;
    }

    public static void hideKeyboard(View view, Context context) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setTextIntoTextView(GitTextView txtVw, String text) {
        if (!TextUtils.isEmpty(text)) {
            txtVw.setText(text);
            txtVw.setVisibility(View.VISIBLE);
        } else
            txtVw.setVisibility(GONE);
    }

    public static void setTitleDetailText(String title, String detail, GitTextView txtVw) {
        if (TextUtils.isEmpty(detail)) {
            txtVw.setVisibility(GONE);
            return;
        }
        String finalString = title + " " + detail;
        Spannable sb = new SpannableString(finalString);
        sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtVw.setText(sb);
    }

}
