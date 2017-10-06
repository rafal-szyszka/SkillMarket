package it.szyszka.skillmarket.utils.forms;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Pair;

import java.util.List;

import it.szyszka.skillmarket.R;

import static it.szyszka.skillmarket.utils.forms.StringValidator.validEmail;
import static it.szyszka.skillmarket.utils.forms.StringValidator.validFullName;
import static it.szyszka.skillmarket.utils.forms.StringValidator.validNoWhiteSpaces;
import static it.szyszka.skillmarket.utils.forms.StringValidator.validNotEmpty;
import static it.szyszka.skillmarket.utils.forms.StringValidator.validPassword;

/**
 * Created by rafal on 30.09.17.
 */

public class InputValidator {

    private static final String TAG = InputValidator.class.getSimpleName();
    private List<Pair<Rule, TextInputLayout>> rules;
    private Context context;
    private Boolean isValid;

    public InputValidator(List<Pair<Rule, TextInputLayout>> rules, Context context) {
        this.rules = rules;
        this.context = context;
        isValid = true;
    }

    public Boolean validate() {
        for (Pair<Rule, TextInputLayout> input : rules){
            isValid = validateByRule(input.first, input.second) ? isValid : false;
        }
        return isValid;
    }

    private void markErrors(Boolean isValid, TextInputLayout input, String errorMessage) {
        if(isValid) {
            input.setErrorEnabled(false);
        } else {
            input.setError(errorMessage);
        }
    }

    private Boolean validateByRule(Rule rule, TextInputLayout textInputLayout) {
        String input = textInputLayout.getEditText().getText().toString();
        Boolean result;
        switch (rule) {
            case EMAIL: {
                result = validEmail(input);
                markErrors(result, textInputLayout, context.getString(R.string.error_message_not_valid_email));
                break;
            }
            case NO_WHITE_SPACES: {
                result = validNoWhiteSpaces(input);
                markErrors(result, textInputLayout, context.getString(R.string.error_message_no_white_spaces));
                break;
            }
            case NOT_EMPTY: {
                result = validNotEmpty(input);
                markErrors(result, textInputLayout, context.getString(R.string.error_message_not_empty));
                break;
            }
            case FULL_NAME: {
                result = validFullName(input);
                markErrors(result, textInputLayout, context.getString(R.string.error_message_wrong_full_name));
                break;
            }
            case STRONG_PASSWORD: {
                result = validPassword(input);
                markErrors(result, textInputLayout, context.getString(R.string.error_message_wrong_password));
                break;
            }
            default: {
                throw new IllegalArgumentException("Rule " + rule + " is not supported.");
            }
        }
        return result;
    }

}
