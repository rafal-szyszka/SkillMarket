package it.szyszka.skillmarket.modules.user.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import it.szyszka.skillmarket.R;

/**
 * Created by rafal on 30.09.17.
 */
@EActivity(R.layout.mod_a_sign_in)
public class SignInActivity extends AppCompatActivity {

    @ViewById(R.id.module_a_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.sign_in_email)
    EditText emailInput;

    @ViewById(R.id.sign_in_password)
    EditText passwordInput;

    @Click(R.id.sign_in_login)
    void signInUser(){
        //// TODO: 30.09.17
    }

    @Click(R.id.sign_in_sing_up)
    void signUp() {
        //// TODO: 30.09.17 move user to another activity
        SignUpActivity_.intent(getApplicationContext()).start();
    }

    @AfterViews
    public void initView(){
        toolbar.setTitle(R.string.app_name);
    }
}
