package com.example.appsos.getDataSOS;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.appsos.dataObject.DataContact;

import java.util.ArrayList;
import java.util.List;

public class sms {
    public void init(ArrayList<String> strMessage, List<DataContact> number, Context context){
        try {
            for (int i=0;i < number.size(); i++){
                String strNumber = number.get(i).getPhoneNumber().trim();
                SmsManager mySms = SmsManager.getDefault();
                mySms.sendMultipartTextMessage(strNumber,null,strMessage,null,null);
                Toast.makeText(context, "sent: " + strNumber, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("in sms: ",""+e.toString());
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}