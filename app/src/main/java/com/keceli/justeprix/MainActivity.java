package com.keceli.justeprix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtnumber = null;
    private Button btnCompare =  null;
    private TextView lblResult = null;
    private ProgressBar pgbScore = null;
    private TextView lblOutput = null;
    private int searchedValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnumber = (EditText) findViewById(R.id.editTextNumber);
        btnCompare = (Button) findViewById(R.id.btnCompare);
        lblResult = (TextView) findViewById(R.id.lblresult);
        pgbScore = (ProgressBar) findViewById(R.id.pgbScore);
        lblOutput = (TextView) findViewById(R.id.lblhistory);


        init();

        btnCompare.setOnClickListener(btnCompareListener);

    }

    /**
     * Initilizaton for all games.
     */
    private void init(){
        score = 0;
        /* Get a value between 1 and 100 */
        searchedValue = 1 + (int)  (Math.random() * 100 );
        Log.i("INFO", "Searched value : " + searchedValue);
        txtnumber.setText( "" );
        pgbScore.setProgress( score );
        lblResult.setText( "" );
        lblOutput.setText( "" );
        txtnumber.requestFocus();
    }
    /**
     * Event manager at the click of a button (Here we have an unique button named "Compare"
     *  with the id : btnCompare
     *  Here we use an Interface from View called OnClickListener
     *  So We need to define the method onClick Overrided.
     */
    private View.OnClickListener btnCompareListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            Here is an example of a log  first argument is a tag (useful when you filter),
            second the message
            */
            Log.i("INFO", "Button clicked");

            String strNumber = txtnumber.getText().toString();
            if ( strNumber.equals( "" ) ) return;

            int enteredValue = Integer.parseInt( strNumber );
            lblOutput.append( strNumber + "\r\n" );
            pgbScore.incrementProgressBy( 1 );
            score++;

            if ( enteredValue == searchedValue ) {
                congratulations();
            } else if ( enteredValue < searchedValue ) {
                lblResult.setText( R.string.strGreater );
            } else {
                lblResult.setText( R.string.strLower );
            }

            txtnumber.setText( "" );
            txtnumber.requestFocus();


        }
    }; /** WARNING we need to add ";" because here we declare an attribute btnCompareListener **/

    private void congratulations() {
        lblResult.setText(R.string.strCongratulations);

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strMessage, score));

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });
    }




}