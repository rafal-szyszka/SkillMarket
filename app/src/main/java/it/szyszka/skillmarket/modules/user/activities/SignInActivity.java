package it.szyszka.skillmarket.modules.user.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.Properties;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.security.HashGenerator;
import it.szyszka.skillmarket.modules.user.api.UserService;
import it.szyszka.skillmarket.modules.user.model.Credentials;
import it.szyszka.skillmarket.modules.user.model.User;
import it.szyszka.skillmarket.modules.user.tasks.SignInUserTask;
import it.szyszka.skillmarket.utils.PropertiesReader;
import it.szyszka.skillmarket.utils.forms.StringValidator;
import retrofit2.Call;

/**
 * Created by rafal on 30.09.17.
 */
@EActivity(R.layout.user_sign_in_activity)
public class SignInActivity extends AppCompatActivity {

    public static final String MESSAGE_KEY = "messageKey";
    public static final String STATUS_KEY = "status";

    @ViewById(R.id.user_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.sign_in_email)
    TextInputLayout emailInput;

    @ViewById(R.id.sign_in_password)
    TextInputLayout passwordInput;

    @ViewById(R.id.sign_in_message)
    TextView message;

    @ColorRes(R.color.colorErrorRed)
    int errorRed;

    @AfterViews
    public void initView(){
        toolbar.setTitle(R.string.app_name);
        initApi();
        initWithExtras();
    }

    @Click(R.id.sign_in_login)
    void checkForm(){
        if(StringValidator.validEmail(emailInput.getEditText().getText().toString())) {
            emailInput.setErrorEnabled(false);
            signUserIn();
        } else {
            emailInput.setError(getString(R.string.error_message_not_valid_email));
        }
    }

    @Click(R.id.sign_in_sing_up)
    void signUp() {
        SignUpActivity_.intent(getApplicationContext()).start();
    }

    private Credentials createUserCredentials() {
        Credentials credentials = Credentials.instantiate(
                emailInput.getEditText().getText().toString(),
                HashGenerator.generateSHA256Key(passwordInput.getEditText().getText().toString())
        );
        return credentials;
    }

    private void signUserIn() {
        Credentials credentials = createUserCredentials();

        UserService client = APIConfig.getInstance().createUserApiClient();
        Call<User> response = client.signIn(credentials.getBasicAuth(), emailInput.getEditText().getText().toString());

        SignInUserTask task = new SignInUserTask(this);
        task.execute(response);
    }

    private void initWithExtras() {
        Bundle extras = getIntent().getExtras();
        showMessageFromExtra(extras);
    }

    private void showMessageFromExtra(Bundle extras) {
        if(extras != null) {
            if(extras.getBoolean(STATUS_KEY, false)) {
                new MaterialDialog.Builder(this)
                        .title(getString(R.string.success_message))
                        .content(extras.getString(MESSAGE_KEY))
                        .positiveText("OK")
                        .show();
            } else {
                new MaterialDialog.Builder(this)
                        .title(getString(R.string.error_message_failure))
                        .content(extras.getString(MESSAGE_KEY))
                        .positiveText("OK")
                        .show();
            }
        }
    }

    private void initApi() {
        APIConfig.init(
                new PropertiesReader(getAssets(), new Properties())
        );
    }

}