package com.anex13.eveassistent.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        ((TextView) view.findViewById(R.id.mail_from_string)).setText(mail.getFromName());
        ((TextView) view.findViewById(R.id.mail_subj_string)).setText(mail.getSubject());
        ((TextView) view.findViewById(R.id.mail_date_string)).setText(mail.getTimestamp());
        final ImageView userpic = (ImageView) view.findViewById(R.id.user_pic);
        final String userpicurl = CS.BASE_URL_IMG + CS.CHAR_URL_IMG + mail.getFromID() + CS.IMG_SIZE_128 + ".jpg";
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



    }
}
//// TODO: 07.12.2016 запилить размеры картинок в стринги по дпи
