
package com.noterdoc.ateeq;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends Activity implements OnClickListener {

    TextView mTextOutput;
    EditText mTextInput;
    final static String FILENAME = "NoterDoc.docx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWidgets();
        loadTextFromFile();
    }

    /*
     * loads line-by-line from text file into the textOutput in reverse order
     */
    private void loadTextFromFile() {
        File f = new File(getFilesDir(), FILENAME);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line;
            while ((line = br.readLine()) != null) {
                mTextOutput.setText(line + "\n" + mTextOutput.getText());
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * sets up items displayed on view
     */
    private void setupWidgets() {
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        mTextOutput = (TextView) findViewById(R.id.textView);
        mTextInput = (EditText) findViewById(R.id.textInput);
        mTextOutput.setMovementMethod(new ScrollingMovementMethod());
    }

    /*
     * Handles clicks for NoterDoc.Ateeq
     */
    @Override
    public void onClick(View v) { // view handler
        if (v.getId() == R.id.save) {
            doSaveButtonPress();
        }
    }

    /*
     * OnClick save-button, write textInput to textOuput and save text to file
     */
    private void doSaveButtonPress() { // button handler
        String text = mTextInput.getText().toString();
        mTextInput.setText("");
        mTextOutput.setText(text + "\n" + mTextOutput.getText().toString());
        try {
            FileOutputStream fo = openFileOutput(FILENAME, Context.MODE_APPEND);
            fo.write(text.getBytes());
            fo.write("\n".getBytes());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}