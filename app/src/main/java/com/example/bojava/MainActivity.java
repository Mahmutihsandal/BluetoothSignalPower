package com.example.bojava;

import android.Manifest;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mBluetoothAdapter.startDiscovery();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                mDeviceList.add(device.getName() + "\n" + device.getAddress());
                Log.i("BT", device.getName() + "\n" + device.getAddress());
                listView.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, mDeviceList));
            }
        }
    };
}
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // BluetoothManager ve BluetoothAdapter oluştur
//        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        final BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
//
//        // Bluetooth adaptörünün varlığını ve etkin olup olmadığını kontrol et
//        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
//            // Cihaz Bluetooth'u desteklemiyor veya etkin değil
//            TextView textView = findViewById(R.id.textView);
//            textView.setText("Cihaz Bluetooth'u desteklemiyor veya etkin değil");
//        } else {
//            // Bluetooth izinlerini kontrol et
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
//                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
//                // Bluetooth izinleri verilmemiş, kullanıcıdan izin iste
//                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.BLUETOOTH, android.Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_BLUETOOTH_PERMISSION);
//            } else {
//                // Bluetooth izinleri verilmiş, bağlı olan cihazı al
//                getConnectedBluetoothDevice();
//            }
//        }
//    }
//
//    private void getConnectedBluetoothDevice() {
//        // Bluetooth adaptörünü al
//        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        // Bağlı olan cihazı al
//        BluetoothDevice connectedDevice = bluetoothAdapter.getRemoteDevice(bluetoothAdapter.getAddress());
//
//        if (connectedDevice != null) {
//            // Bluetooth izinlerini kontrol et
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
//                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
//                // Bluetooth izinleri verilmemiş, kullanıcıdan izin iste
//                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.BLUETOOTH, android.Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_BLUETOOTH_PERMISSION);
//                return;
//            }
//
//            // Bluetooth cihazının adını almadan önce izinleri kontrol et
//            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                String deviceName = connectedDevice.getAlias();
//                String deviceAddress = connectedDevice.getAddress();
//
//                // TextView'e cihazın adını ve adresini yazdır
//                TextView textView = findViewById(R.id.textView);
//                textView.setText("Cihaz Adı: " + deviceName + "\nCihaz Adresi: " + deviceAddress);
//            } else {
//                // İzin verilmemiş
//                Toast.makeText(this, "Bluetooth cihazının adını almak için gerekli izin verilmemiş.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            // Bağlı cihaz yoksa
//            TextView textView = findViewById(R.id.textView);
//            textView.setText("Bağlı bir cihaz bulunamadı.");
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                // Bluetooth izinleri verilmiş, bağlı olan cihazı al
//                getConnectedBluetoothDevice();
//            } else {
//                // Bluetooth izinleri verilmemiş
//                TextView textView = findViewById(R.id.textView);
//                textView.setText("Bluetooth izinleri verilmedi.");
//            }
//        }
//    }
//}
//




//public class MainActivity extends Activity {
//
//
//    private BluetoothAdapter bluetoothAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Initialize Bluetooth adapter
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (bluetoothAdapter == null) {
//            Log.d("Bluetooth Info", "Bluetooth is not supported on this device.");
//            return;
//        }
//
//        checkBluetoothDeviceInfo();
//    }
//
//    private void checkBluetoothDeviceInfo() {
//        if (bluetoothAdapter != null) {
//
//
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
//                // İzin yok, bu bloğa girme
//
//            return;
//            }
//            String deviceName = bluetoothAdapter.getName();
//            String deviceAddress = bluetoothAdapter.getAddress();
//
//            if (deviceName != null && deviceAddress != null) {
//                Log.d("Bluetooth Info", "Device Name: " + deviceName);
//                Log.d("Bluetooth Info", "Device Address: " + deviceAddress);
//            } else {
//                Log.d("Bluetooth Info", "Bluetooth device name or address is null.");
//            }
//        } else {
//            Log.d("Bluetooth Info", "Bluetooth adapter is null. Bluetooth may not be supported on this device.");
//        }
//    }
//
//}

   // private static final int REQUEST_ENABLE_BT = 1;
// Enable Bluetooth if it's not enabled
//        if (!bluetoothAdapter.isEnabled()) {
//           return;
//        }

//------------
//
//public class MainActivity extends AppCompatActivity {
//    private TextView textViewSignalStrength;
//    private WifiManager wifiManager;
//    private Handler handler = new Handler();
//    private Runnable runnable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        textViewSignalStrength = findViewById(R.id.textViewSignalStrength);
//        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                updateSignalStrength();
//                handler.postDelayed(this, 1000);
//            }
//        };
//        handler.post(runnable);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        handler.removeCallbacks(runnable);
//    }
//    private void updateSignalStrength() {
//        if (wifiManager != null) {
//            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//            int signalStrength = wifiInfo.getRssi();
//            int signalStrengthdBm = calculateSignalStrengthInDbm(signalStrength);
//            textViewSignalStrength.setText("Wi-Fi Sinyal Gücü: " + signalStrengthdBm + " dBm");
//        }
//    }
//    private int calculateSignalStrengthInDbm(int rssi) {
//        int signalStrength = -1;
//        if (rssi <= 0 && rssi >= -50) {
//            signalStrength = -1 * (rssi + 25);
//        } else if (rssi < -50 && rssi >= -90) {
//            signalStrength = -2 * (rssi + 100);
//        }
//        return signalStrength;
//    }
//}