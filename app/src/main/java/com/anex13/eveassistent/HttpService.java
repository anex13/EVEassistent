package com.anex13.eveassistent;

import android.app.IntentService;
import android.content.Intent;
import retrofit2.http.POST;

/**
 * Created by it.zavod on 22.11.2016.
 */

public class HttpService extends IntentService {
    static final String ACTION_POST="post";
    public HttpService() {
        super("httpservice");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()) {
            case ACTION_POST:
                break;
            default:
                break;
        }


    }

}
