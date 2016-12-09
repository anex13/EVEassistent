package com.anex13.eveassistent.dbAdapters;

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
import com.anex13.eveassistent.CharDBClass;
import com.anex13.eveassistent.R;
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
        ((TextView) view.findViewById(R.id.adapter_char_id)).setText(Integer.toString(character.getCharID()));
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



    }
}
//// TODO: 07.12.2016 запилить размеры картинок в стринги по дпи