package net.copaba.configurandomipropioendpointenmiservidor.notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Polo on 07/10/16.
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {
    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    public void onTokenRefresh() {
//        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        sendTokenRegistration(token);
    }
    private void sendTokenRegistration(String token){
        Log.d(TAG, token);
    }
}
