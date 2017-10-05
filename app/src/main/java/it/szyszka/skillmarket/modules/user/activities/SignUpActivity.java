package it.szyszka.skillmarket.modules.user.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.security.HashGenerator;
import it.szyszka.skillmarket.utils.PropertiesReader;
import it.szyszka.skillmarket.utils.forms.InputValidator;
import it.szyszka.skillmarket.utils.forms.Rule;
import it.szyszka.skillmarket.utils.view.LabeledEditText;

/**
 * Created by rafal on 30.09.17.
 */
@EActivity(R.layout.mod_a_sign_up)
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

    @ViewById(R.id.module_a_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.sign_up_alias)
    View nickname;

    @ViewById(R.id.sign_up_full_name)
    View fullName;

    @ViewById(R.id.sign_up_email)
    View email;

    @ViewById(R.id.sign_up_password)
    View password;

    @ColorRes(R.color.errorRed)
    int errorRed;

    @Click(R.id.sign_up_toggle_password)
    void togglePasswordView() {
        ((EditText)password.findViewById(R.id.labeled_input_edit)).setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }

    @Click(R.id.sign_up_create_account)
    void createAccount() {
        InputValidator validator = new InputValidator(getInputs(), this);
        Boolean formIsValid = validator.validate(errorRed);
        if(formIsValid) launchDetailsActivity();
    }

    private void launchDetailsActivity(){
        startActivity(new Intent(this, SignUpDetailsActivity_.class)
                .putExtra(Form.NICKNAME, getTextValueFromEditText((EditText) nickname.findViewById(R.id.labeled_input_edit)))
                .putExtra(Form.FULL_NAME, getTextValueFromEditText((EditText) fullName.findViewById(R.id.labeled_input_edit)))
                .putExtra(Form.EMAIL, getTextValueFromEditText((EditText) email.findViewById(R.id.labeled_input_edit)))
                .putExtra(Form.PASSWORD, hashPassword())
        );
    }

    private String hashPassword() {
        String hash = HashGenerator.generateSHA256Key(
                getTextValueFromEditText((EditText) password.findViewById(R.id.labeled_input_edit))
        );
        Log.i(TAG, "Hashed password: " + hash);
        return hash;
    }

    private String getTextValueFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    private List<Pair<Rule, View>> getInputs() {
        List<Pair<Rule, View>> inputs = new ArrayList<>();

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

    @Click(R.id.sign_up_have_account)
    void haveAccount() {
        SignInActivity_.intent(this).start();
    }

    @AfterViews
    void initView() {
        toolbar.setTitle(R.string.app_name);

        LabeledEditText input = new LabeledEditText();
        input.setNewInput(nickname)
                .getLabel().setText(R.string.sign_up_nickname_text);

        input.setNewInput(fullName)
                .getLabel().setText(R.string.sign_up_full_name_text);

        input.setNewInput(email)
                .getLabel().setText(R.string.sign_up_email_text);
        input.getEdit().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        initWithExtras(input);
    }

    private void initWithExtras(LabeledEditText input) {
        Bundle extras = getIntent().getExtras();
        printErrorsIfExists(extras, input);
    }

    private void printErrorsIfExists(Bundle extras, LabeledEditText input) {
        if(extras != null) {
            Boolean emailTaken = extras.getBoolean(EMAIL_TAKEN_KEY, false);
            Boolean nicknameTaken = extras.getBoolean(NICKNAME_TAKEN_KEY, false);

            if(emailTaken) {
                input.setNewInput(email);
                input.getLabel().setTextColor(errorRed);
                input.getLabel().append(" " + getString(R.string.error_message_email_taken));
            }
            if(nicknameTaken) {
                input.setNewInput(nickname);
                input.getLabel().setTextColor(errorRed);
                input.getLabel().append(" " + getString(R.string.error_message_nickname_taken));
            }
        }
    }

}
