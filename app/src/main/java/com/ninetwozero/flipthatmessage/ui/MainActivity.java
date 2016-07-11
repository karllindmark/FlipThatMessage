package com.ninetwozero.flipthatmessage.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ninetwozero.flipthatmessage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.input)
    public EditText input;

    @BindView(R.id.button_flip)
    public Button flipButton;

    private TextWatcher textWatcher = createTextWatcher();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        input.addTextChangedListener(textWatcher);
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.removeTextChangedListener(textWatcher);
                input.setText(new StringBuilder(input.getText()).reverse().toString());
                input.addTextChangedListener(textWatcher);
            }
        });
        flipButton.setEnabled(input.length() > 0);
    }

    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                flipButton.setEnabled(editable.length() > 0);
            }
        };
    }
}
