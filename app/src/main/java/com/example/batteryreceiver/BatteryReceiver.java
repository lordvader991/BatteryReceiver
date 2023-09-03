package com.example.batteryreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {
    private TextView EstadoBateriaTxt;
    public BatteryReceiver(TextView EstadoBateriaTxt){
        this.EstadoBateriaTxt = EstadoBateriaTxt;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(Intent.ACTION_BATTERY_CHANGED.equals(action)){
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
            int Porcentaje = (level * 100) / scale;
            String EstadoBateria = "";
            if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                EstadoBateria = "Batería Cargando";
            } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                EstadoBateria = "Batería descargandose";
            } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                EstadoBateria = "Batería Completa";
            } else{
                EstadoBateria = "Error, revise su cargador";
            }
            EstadoBateriaTxt.setText(EstadoBateria);
            if(Porcentaje <= 20){
                EstadoBateriaTxt.setText("Cargue su celular, Batería menos del 20%");
            }
        } //recordatorio, no es necesario en el manifest pero lo pongo por si el cargador no funciona
    }
}
