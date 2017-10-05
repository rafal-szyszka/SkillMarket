package it.szyszka.skillmarket.modules.user.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import it.szyszka.skillmarket.utils.view.LabeledEditText;
import retrofit2.Call;

/**
 * Created by rafal on 01.10.17.
 */
@EActivity(R.layout.mod_a_sign_up_details)
public class SignUpDetailsActivity extends AppCompatActivity{

    private LabeledEditText input = new LabeledEditText();

    @ViewById(R.id.module_a_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.sign_up_address)
    View address;

    @ViewById(R.id.sign_up_city)
    View city;

    @ViewById(R.id.sign_up_phone_number)
    View phoneNumber;

    Bundle extras;

    @Click(R.id.sign_up_create_account)
    void createAccount() {
        User user = new User();
        setFromBundle(user);
        setFromForm(user);

        signUpNewUser(user);
    }

    private void signUpNewUser(User user) {
        UserService client = APIConfig.getInstance().createUserApiClient();
        Call<UserResponse> response = client.signUpUser(user);

        SignUpUserTask requestHandler = new SignUpUserTask(SignUpDetailsActivity.this);
        requestHandler.execute(response);

    }

    @Click(R.id.sign_up_have_account)
    void signInActivity() {
        SignInActivity_.intent(this).start();
    }

    private void setFromForm(User user) {
        user.setCity(
                input.setNewInput(city)
                    .getEdit().getText().toString()
        );

        user.setMailingAddress(
                input.setNewInput(address)
                    .getEdit().getText().toString()
        );

        user.setPhoneNumber(
                input.setNewInput(phoneNumber)
                    .getEdit().getText().toString()
        );
    }

    private void setFromBundle(User user) {
        readExtras();
        user.setNickname(extras.getString(SignUpActivity.Form.NICKNAME));
        user.setFullName(extras.getString(SignUpActivity.Form.FULL_NAME));
        user.setEmail(extras.getString(SignUpActivity.Form.EMAIL));
        user.setPassword(extras.getString(SignUpActivity.Form.PASSWORD));
    }

    @AfterViews
    void initView() {
        toolbar.setTitle(R.string.app_name);

        input.setNewInput(address)
                .getLabel().setText(R.string.sign_up_address);

        input.setNewInput(city)
                .getLabel().setText(R.string.sign_up_city);

        input.setNewInput(phoneNumber)
                .getLabel().setText(R.string.sign_up_phone_number);

    }

    private void readExtras() {
        extras = getIntent().getExtras();
    }

}
