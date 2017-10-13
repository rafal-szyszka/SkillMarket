package it.szyszka.skillmarket.utils.forms;

import android.util.Pair;

import java.util.Map;

/**
 * Created by rafal on 30.09.17.
 */

public interface Validator<T> {

    Map<T, Pair<Rule, Boolean>> validate();

}
