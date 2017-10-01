package it.szyszka.skillmarket.utils.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import it.szyszka.skillmarket.R;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by rafal on 01.10.17.
 */
@NoArgsConstructor
public class LabeledEditText {

    @Getter @Setter private TextView label;
    @Getter @Setter private EditText edit;

    public LabeledEditText(View labeledEditText) {
        label = labeledEditText.findViewById(R.id.labeled_input_label);
        edit = labeledEditText.findViewById(R.id.labeled_input_edit);
    }

    public LabeledEditText setNewInput(View labeledEditText) {
        label = labeledEditText.findViewById(R.id.labeled_input_label);
        edit = labeledEditText.findViewById(R.id.labeled_input_edit);
        return this;
    }

}
