package net.ivanvega.miholamundoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    EditText txtU;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        txtU = findViewById(R.id.txtUser);

        Intent intentpar = getIntent();
        String par =
                intentpar.getStringExtra("parnombre");
        txtU.setText(par);
    }


    public void btnOk_click(View v) {
        if (txtU.getText().toString().equals("admin")) {
            //setResult(RESULT_OK);

            Intent datos = new Intent();
            datos.putExtra("token", "432kjhk2jcl24k3j5lk32jlk3242lk");
            setResult(RESULT_OK, datos);

            finish();

        }
    }

}
