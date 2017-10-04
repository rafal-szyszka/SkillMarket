package it.szyszka.skillmarket.utils.forms;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.szyszka.skillmarket.R;
import it.szyszka.skillmarket.utils.view.LabeledEditText;

import static it.szyszka.skillmarket.utils.forms.Rule.EMAIL;
import static it.szyszka.skillmarket.utils.forms.Rule.FULL_NAME;
import static it.szyszka.skillmarket.utils.forms.Rule.NOT_EMPTY;
import static it.szyszka.skillmarket.utils.forms.Rule.NO_WHITE_SPACES;
import static it.szyszka.skillmarket.utils.forms.Rule.STRONG_PASSWORD;
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
    private List<Pair<Rule, View>> rules;
    private Context context;

    public InputValidator(List<Pair<Rule, View>> rules, Context context) {
        this.rules = rules;
        this.context = context;
    }

    public Boolean validate(int errorColor) {
        List<Pair<TextView, Pair<Rule, Boolean>>> result = validateForm();
        Boolean rulesOk = true;
        for(Pair<TextView, Pair<Rule, Boolean>> entry : result) {
            if(!entry.second.second) {
                setColoredMessage(entry, errorColor);
                rulesOk = false;
            }
        }
        return rulesOk;
    }

    private void setColoredMessage(Pair<TextView, Pair<Rule, Boolean>> entry, int errorColor) {
        TextView textView = entry.first;
        textView.setTextColor(errorColor);
        switch (entry.second.first) {
            case EMAIL: {
                textView.append(" \"" + context.getResources().getString(R.string.info_message_wrong_email) + "\"");
                break;
            }
            case FULL_NAME: {
                textView.append(" \"" + context.getResources().getString(R.string.info_message_wrong_full_name) + "\"");
                break;
            }
            case STRONG_PASSWORD: {
                textView.append(" \"" + context.getResources().getString(R.string.info_message_wrong_password) + "\"");
                break;
            }
            case NO_WHITE_SPACES: {
                textView.append(" \"" + context.getResources().getString(R.string.info_message_no_white_spaces) + "\"");
                break;
            }
            case NOT_EMPTY: {
                textView.append(" \"" + context.getResources().getString(R.string.info_message_not_empty) + "\"");
                break;
            }
            default: {
                break;
            }

        }
    }

    public List<Pair<TextView, Pair<Rule, Boolean>>> validateForm() {
        List<Pair<TextView, Pair<Rule, Boolean>>> result = new ArrayList<>();
        LabeledEditText input = new LabeledEditText();
        for(Pair<Rule, View> tuple : rules) {
            Rule rule = tuple.first;
            View view = tuple.second;

            input.setNewInput(view);

            String text = input.getEdit().getText().toString();

            System.out.println("Checking: _" + text + "_" + rule);
            result.add(Pair.create(input.getLabel(), validateByRule(rule, text)));
        }
        return result;
    }

    private Pair<Rule, Boolean> validateByRule(Rule rule, String input) {
        Boolean result;
        switch (rule) {
            case EMAIL: {
                result = validEmail(input);
                Log.i(TAG, "Validated email: " + input + " " + result);
                return Pair.create(EMAIL, result);
            }
            case NO_WHITE_SPACES: {
                result = validNoWhiteSpaces(input);
                Log.i(TAG, "Validated no whites: " + input + " " + result);
                return Pair.create(NO_WHITE_SPACES, result);
            }
            case NOT_EMPTY: {
                result = validNotEmpty(input);
                Log.i(TAG, "Validated no empties: " + input + " " + result);
                return Pair.create(NOT_EMPTY, result);
            }
            case FULL_NAME: {
                result = validFullName(input);
                Log.i(TAG, "Validated full name: " + input + " " + result);
                return Pair.create(FULL_NAME, result);
            }
            case STRONG_PASSWORD: {
                result = validPassword(input);
                Log.i(TAG, "Validated password: " + result);
                return Pair.create(STRONG_PASSWORD, result);
            }
            default:
                throw new IllegalArgumentException("Rule " + rule + " is not supported.");
        }
    }

}
