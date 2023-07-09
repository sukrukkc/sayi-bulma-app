package com.example.say_bulma;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText sayigirisi;
    private TextView tahminsayisi, sontahmin, girilentahmin;
    private Button tahminet, tekrardene;

    int rastgelesayi;
    int toplamTahmin = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        girilentahmin = (TextView) findViewById(R.id.girilentahminsayisitext);
        sayigirisi = (EditText) findViewById(R.id.tahminedittext);
        tahminsayisi = (TextView) findViewById(R.id.yapilantahmintext);
        sontahmin = (TextView) findViewById(R.id.sontahmintext);
        tekrardene = (Button) findViewById(R.id.tekrarbutton);
        tahminet = (Button) findViewById(R.id.tahminbutton);
        rastgelesayi = (int) (Math.random() * 101);


        tahminet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tahminStr = sayigirisi.getText().toString();

                if (!tahminStr.isEmpty()) {
                    int tahmin = Integer.parseInt(tahminStr);
                    toplamTahmin++;

                    if (tahmin == rastgelesayi) {
                        sontahmin.setText("Tebrikler, doğru tahmin!");
                        sayigirisi.getText().clear();
                        showAlertDialog("Tebrikler!", "Doğru tahmin yaptınız.");

                    } else if (tahmin >= 100) {
                        showAlertDialog("Uyarı","Lütfen 0 - 100 arasında bir sayı giriniz.");
                        tahminsayisi.setText("Yapılan Tahmin" + tahminStr);
                        sontahmin.setText("0 ile 100 arasında sayı dene.");
                        sayigirisi.getText().clear();


                    } else if (tahmin < rastgelesayi) {
                        tahminsayisi.setText("Yapılan Tahmin: " + tahminStr);

                        sontahmin.setText("Daha yüksek bir sayı dene.");
                        sayigirisi.getText().clear();

                    } else {
                        tahminsayisi.setText("Yapılan Tahmin: " + tahminStr);

                        sontahmin.setText("Daha düşük bir sayı dene.");
                        sayigirisi.getText().clear();
                    }
                    girilentahmin.setText("Girilen tahmin sayısı :" + toplamTahmin);
                }
            }
        });
        tekrardene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toplamTahmin = 0;
                rastgelesayi = (int) (Math.random() * 101);
                sontahmin.setText("Son tahmininiz");
                tahminsayisi.setText("Bir sayı giriniz");
                girilentahmin.setText("Tahmin Sayısı : " + 0);

            }
        });
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

