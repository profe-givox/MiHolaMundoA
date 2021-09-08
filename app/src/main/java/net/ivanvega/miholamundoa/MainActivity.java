package net.ivanvega.miholamundoa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtCajaTexto;
    Button btn, btnInvAct;

    /*
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        // Handle the Intent

                        Toast.makeText(MainActivity.this,
                                intent.getStringExtra("token"),
                                Toast.LENGTH_LONG
                                ).show();

                    }
                }
            });*/

    public void btnLogCat_click(View v){
        Log.e("MiMensajeEnLogCat", "Paso por el onclick y se imprimio este" +
                "mensaje en la LogCat");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCajaTexto = findViewById(R.id.txt1);
        btn = findViewById(R.id.btn);
        btnInvAct = findViewById(R.id.btnInvocarActivity);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtCajaTexto.getText().toString();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            }
        });

        btnInvAct.setOnClickListener(view -> {
            Intent intent =
                    new Intent(getApplicationContext(),
                            SecondActivity.class);

            intent.putExtra("parnombre",
                    txtCajaTexto.getText().toString());


            startActivity(intent);

        });
    }

    public void btnInvActResult_click(View v){
        Intent intent =
                new Intent(getApplicationContext(),
                        SecondActivity.class);

        intent.putExtra("parnombre",
                txtCajaTexto.getText().toString());

        startActivityForResult(intent, 1000);

        // The launcher with the Intent you want to start
        /*
        mStartForResult.launch(new Intent(this,
                SecondActivity.class));*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000){
            if(resultCode==RESULT_OK){
                Toast.makeText(MainActivity.this,
                        data.getStringExtra("token"),
                        Toast.LENGTH_LONG).show();
            }
        }

    }
}