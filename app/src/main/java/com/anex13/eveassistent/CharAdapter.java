package com.anex13.eveassistent;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by it.zavod on 28.11.2016.
 */

public class CharAdapter extends CursorAdapter {
    private Context mContext;

    public CharAdapter(Context context, Cursor c) {
        super(context, c, 0);
        this.mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.char_personal_data, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final CharDBClass character = new CharDBClass(cursor);
        ((TextView) view.findViewById(R.id.char_name)).setText(character.getCharName());
        ((TextView) view.findViewById(R.id.char_corp)).setText(character.getCorpName());
        ((TextView) view.findViewById(R.id.char_birthday)).setText(character.getBirthday());
        final ImageView userpic = (ImageView) view.findViewById(R.id.user_pic);
        final String userpicurl = CS.BASE_URL_IMG + CS.CHAR_URL_IMG + character.getCharID() + CS.IMG_SIZE_512 + ".jpg";
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
        final String corppicurl = CS.BASE_URL_IMG + CS.CORP_URL_IMG + character.getCorpID() + CS.IMG_SIZE_128 + ".png";
        final ImageView corppic = (ImageView) view.findViewById(R.id.corp_pic);
        Picasso.with(context)
                .load(corppicurl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(corppic, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(corppicurl)
                                .into(corppic, new Callback() {
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


        // Picasso.with(context)
        //         .load(corppicurl)
        //         .into(corppic);
        // ((ImageView) view.findViewById(R.id.corp_pic)).setImageDrawable(Drawable.createFromPath(character.getCorpLogoUrl()));
        /*switch1.setChecked(server.getAlarm()!=0);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (server.getAlarm()!=0){
                    server.setAlarm(0);
                    server.setState(2);}
                else
                    server.setAlarm(1);
                final Uri uri = ContentUris.withAppendedId(SRVContentProvider.SERVERS_CONTENT_URI, server.getId());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mContext.getContentResolver().update(uri,server.toContentValues(),null,null);
                    }
                }).start();
            }
        });*/
    }
}
