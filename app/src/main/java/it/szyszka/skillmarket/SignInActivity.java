package it.szyszka.skillmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_b_user_profile);

        View view = findViewById(R.id.user_profile_email);
        ImageView img = view.findViewById(R.id.user_info_img);
        TextView txt = view.findViewById(R.id.user_info_text);

        txt.setText("rafal@szyszka.it");

        view = findViewById(R.id.user_profile_location);
        img = view.findViewById(R.id.user_info_img);
        img.setImageResource(R.drawable.ic_location);
        txt = view.findViewById(R.id.user_info_text);
        txt.setText("Wrocław");

    }
}
