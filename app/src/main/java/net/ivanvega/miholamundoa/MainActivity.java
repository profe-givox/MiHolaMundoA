package net.ivanvega.miholamundoa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtCajaTexto;
    Button btn, btnInvAct, btnIntentExplicito;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                    Toast.makeText(getApplicationContext(),
                            uri.toString(), Toast.LENGTH_LONG).show();
                }
            });


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
            });

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

        btnIntentExplicito = findViewById(R.id.btnIE);
        btnIntentExplicito.setOnClickListener(view -> {
           //Intent intent = new Intent(Intent.ACTION_VIEW);
           //Intent intent = new Intent(Intent.ACTION_PICK);
           //intent.setData(Uri.parse("https://developer.android.com"));
           //intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

//            // Map point based on address
//            Uri location =
//                    Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
//// Or map point based on latitude/longitude
//// Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);


            //startActivity(intent);
//            startActivity(mapIntent);

            /*
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            // The intent does not have a URI, so declare the "text/plain" MIME type
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jan@example.com"}); // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");

            // You can also attach multiple items by passing an ArrayList of Uris

            startActivity(emailIntent);*/

            /*
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Mensaje");

            startActivity(intent);*/

                mGetContent.launch("image/*");


        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CICLOVIDA" , "paso por onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLOVIDA" , "paso por onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CICLOVIDA" , "paso por onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CICLOVIDA" , "paso por onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CICLOVIDA" , "paso por onDestroy()");
    }

    public void btnInvActResult_click(View v){
        /*Intent intent =
                new Intent(getApplicationContext(),
                        SecondActivity.class);

        intent.putExtra("parnombre",
                txtCajaTexto.getText().toString());

        startActivityForResult(intent, 1000);*/

        // The launcher with the Intent you want to start
        mStartForResult.launch(new Intent(this,
                SecondActivity.class));

    }

}