package com.ninetwozero.flipthatmessage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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

    private ShareActionProvider shareActionProvider;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        shareActionProvider.setShareIntent(createNewShareIntent(getString(R.string.tableflip)));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                if (shareActionProvider != null) {
                    shareActionProvider.setShareIntent(createNewShareIntent(editable.toString()));
                }
                flipButton.setEnabled(editable.length() > 0);
            }
        };
    }

    private Intent createNewShareIntent(final String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        return shareIntent;
    }
}
