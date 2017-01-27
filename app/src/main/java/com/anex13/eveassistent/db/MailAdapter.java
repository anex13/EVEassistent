package com.anex13.eveassistent.db;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.widget.CursorAdapter;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.anex13.eveassistent.CS;
import com.anex13.eveassistent.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by it.zavod on 07.12.2016.
 */

public class MailAdapter extends CursorAdapter {
    private Context mContext;

    public MailAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.mail_item_inflater, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final MailDBClass mail = new MailDBClass(cursor);
        ((TextView) view.findViewById(R.id.mail_from_string)).setText("From: " + mail.getFromName());
        ((TextView) view.findViewById(R.id.mail_subj_string)).setText("Subject: " + mail.getSubject());
        String date = mail.getTimestamp().replace("T", " ").replace("Z", "");
        ((TextView) view.findViewById(R.id.mail_date_string)).setText(date);
        final ImageView userpic = (ImageView) view.findViewById(R.id.mail_from_img);
        final String userpicurl = CS.BASE_URL_IMG + CS.CHAR_URL_IMG + mail.getFromID() + CS.IMG_SIZE_64 + ".jpg";
        Picasso.with(context)
                .load(userpicurl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(userpic, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(userpicurl)
                                .into(userpic, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
        TextView tv = (TextView) view.findViewById(R.id.mail_item_body);
        //tv.setBackgroundColor(0x00ffffff);
        //tv.setInitialScale(70);
        //tv.loadData(mail.getBody(),"text/html; charset=UTF-8",null);
        //tv.setText(mail.getBody().replaceAll("<br>","\n").replaceAll("<[^>]*>", ""));
        if (Build.VERSION.SDK_INT >= 24)
            tv.setText(Html.fromHtml(mail.getBody(), Html.FROM_HTML_MODE_LEGACY));
        else
            tv.setText(Html.fromHtml(mail.getBody()));
        tv.setVisibility(View.GONE);


    }
}
