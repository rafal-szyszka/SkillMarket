package it.szyszka.skillmarket.modules.user.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.modules.user.model.User;
import it.szyszka.skillmarket.utils.forms.InputValidator;
import it.szyszka.skillmarket.utils.forms.Rule;
import it.szyszka.skillmarket.utils.view.LabeledEditText;

/**
 * Created by rafal on 30.09.17.
 */
@EActivity(R.layout.mod_a_sign_up)
public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity_.class.getSimpleName();

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
                .putExtra(Form.PASSWORD, getTextValueFromEditText((EditText) password.findViewById(R.id.labeled_input_edit)))
        );
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

    }

}
