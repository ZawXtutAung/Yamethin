package com.zawxtutaung.yamethin.noti;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by zawxtutaung on 12/28/2016.
 */

public class FirebaseMyId extends FirebaseInstanceIdService {
    private static final String TAG = "MyAndroidFCMIIDService";

    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
    }

}