package com.hehe.ui;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hehe.R;
import com.hehe.utils.KeyboardUtil;
import com.hehe.view.KeyboardNormol;

public class MainActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_main);

        edit = (EditText) this.findViewById(R.id.edit);
        edit.setInputType(InputType.TYPE_NULL);
        KeyboardNormol mKeyboardNormol1 = new KeyboardNormol(this, this, this.edit,  R.id.keyboard_view1);
        mKeyboardNormol1.showKeyboard();
    }
}