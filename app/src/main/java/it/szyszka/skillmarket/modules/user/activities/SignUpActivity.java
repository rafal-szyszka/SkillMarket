package it.szyszka.skillmarket.modules.user.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.security.HashGenerator;
import it.szyszka.skillmarket.utils.forms.InputValidator;
import it.szyszka.skillmarket.utils.forms.Rule;

/**
 * Created by rafal on 30.09.17.
 */
@EActivity(R.layout.user_sign_up_activity)
public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity_.class.getSimpleName();
    public static final String EMAIL_TAKEN_KEY = "emailTakenKey";
    public static final String NICKNAME_TAKEN_KEY = "nicknameTakenKey";

    public static class Form {
        public static String NICKNAME = "nickname";
        public static String FULL_NAME = "fullname";
        public static String EMAIL = "email";
        public static String PASSWORD = "password";
    }

    @ViewById(R.id.user_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.sign_up_alias)
    TextInputLayout nickname;

    @ViewById(R.id.sign_up_full_name)
    TextInputLayout fullName;

    @ViewById(R.id.sign_up_email)
    TextInputLayout email;

    @ViewById(R.id.sign_up_password)
    TextInputLayout password;

    @ColorRes(R.color.colorErrorRed)
    int errorRed;

    @Click(R.id.sign_up_create_account)
    void createAccount() {
        InputValidator validator = new InputValidator(createInputRules(), this);
        Boolean formIsValid = validator.validate();
        if(formIsValid) launchDetailsActivity();
    }

    @Click(R.id.sign_up_have_account)
    void haveAccount() {
        SignInActivity_.intent(this).start();
    }

    @AfterViews
    void initView() {
        toolbar.setTitle(R.string.app_name);

        initWithExtras();
    }

    private void launchDetailsActivity(){
        startActivity(new Intent(this, SignUpDetailsActivity_.class)
                .putExtra(Form.NICKNAME, nickname.getEditText().getText().toString().trim())
                .putExtra(Form.FULL_NAME, fullName.getEditText().getText().toString().trim())
                .putExtra(Form.EMAIL, email.getEditText().getText().toString().trim())
                .putExtra(Form.PASSWORD, hashPassword())
        );
    }

    private String hashPassword() {
        String hash = HashGenerator.generateSHA256Key(
                password.getEditText().getText().toString()
        );
        Log.i(TAG, "Hashed password.");
        return hash;
    }

    private List<Pair<Rule, TextInputLayout>> createInputRules() {
        List<Pair<Rule, TextInputLayout>> inputs = new ArrayList<>();

        inputs.add(Pair.create(Rule.NOT_EMPTY, nickname));
        inputs.add(Pair.create(Rule.NOT_EMPTY, email));
        inputs.add(Pair.create(Rule.NOT_EMPTY, fullName));
        inputs.add(Pair.create(Rule.NOT_EMPTY, password));

        inputs.add(Pair.create(Rule.NO_WHITE_SPACES, nickname));
        inputs.add(Pair.create(Rule.FULL_NAME, fullName));
        inputs.add(Pair.create(Rule.EMAIL, email));
        inputs.add(Pair.create(Rule.STRONG_PASSWORD, password));

        return inputs;
    }

    private void initWithExtras() {
        Bundle extras = getIntent().getExtras();
        printErrorsIfExists(extras);
    }

    private void printErrorsIfExists(Bundle extras) {
        if(extras != null) {
            Boolean emailTaken = extras.getBoolean(EMAIL_TAKEN_KEY, false);
            Boolean nicknameTaken = extras.getBoolean(NICKNAME_TAKEN_KEY, false);

            if(emailTaken) {
                email.setError(getString(R.string.error_message_email_taken));
            }
            if(nicknameTaken) {
                nickname.setError(getString(R.string.error_message_nickname_taken));
            }
        }
    }

}
