package it.szyszka.skillmarket.modules.user.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.api.APIConfig;
import it.szyszka.skillmarket.modules.user.api.UserResponse;
import it.szyszka.skillmarket.modules.user.api.UserService;
import it.szyszka.skillmarket.modules.user.model.User;
import it.szyszka.skillmarket.modules.user.tasks.SignUpUserTask;
import retrofit2.Call;

/**
 * Created by rafal on 01.10.17.
 */
@EActivity(R.layout.user_sign_up_details_activity)
public class SignUpDetailsActivity extends AppCompatActivity {

    @ViewById(R.id.user_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.sign_up_address)
    TextInputLayout address;

    @ViewById(R.id.sign_up_city)
    TextInputLayout city;

    @ViewById(R.id.sign_up_phone_number)
    TextInputLayout phoneNumber;

    Bundle extras;

    @Click(R.id.sign_up_create_account)
    void createAccount() {
        User user = new User();
        setFromBundle(user);
        setFromForm(user);

        signUpNewUser(user);
    }

    @Click(R.id.sign_up_have_account)
    void signInActivity() {
        SignInActivity_.intent(this).start();
    }

    @AfterViews
    void initView() {
        toolbar.setTitle(R.string.app_name);
    }

    private void signUpNewUser(User user) {
        UserService client = APIConfig.getInstance().createUserApiClient();
        Call<UserResponse> response = client.signUpUser(user);

        SignUpUserTask requestHandler = new SignUpUserTask(SignUpDetailsActivity.this);
        requestHandler.execute(response);

    }

    private void setFromForm(User user) {
        user.setCity(
                city.getEditText().getText().toString()
        );

        user.setMailingAddress(
                address.getEditText().getText().toString()
        );

        user.setPhoneNumber(
                        phoneNumber.getEditText().getText().toString()
        );
    }

    private void setFromBundle(User user) {
        readExtras();
        user.setNickname(extras.getString(SignUpActivity.Form.NICKNAME));
        user.setFullName(extras.getString(SignUpActivity.Form.FULL_NAME));
        user.setEmail(extras.getString(SignUpActivity.Form.EMAIL));
        user.setPassword(extras.getString(SignUpActivity.Form.PASSWORD));
    }

    private void readExtras() {
        extras = getIntent().getExtras();
    }

}
