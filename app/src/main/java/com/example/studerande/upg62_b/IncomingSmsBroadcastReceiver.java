package com.example.studerande.upg62_b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.style.UpdateLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Studerande on 2017-03-18.
 */

public class IncomingSmsBroadcastReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (intent != null && SMS_RECEIVED.equals(intent.getAction())) {
            final SmsMessage smsMessage = extractSmsMessage(intent);
            processMessage(context, smsMessage);

        }

    }

    private SmsMessage extractSmsMessage(final Intent intent) {

        final Bundle pudsBundle = intent.getExtras();
        final Object[] pdus = (Object[]) pudsBundle.get("pdus");
        String format = pudsBundle.getString("format");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0], format);
            return smsMessage;
        }
        else {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
            return smsMessage;
        }
    }

    private void processMessage(final Context context, final SmsMessage smsMessage) {

        String msg = smsMessage.getMessageBody();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        // MainActivity m = new MainActivity();
       // m.setMsg(msg);
    }

}