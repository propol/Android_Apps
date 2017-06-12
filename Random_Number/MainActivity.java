package com.example.prodromos.randomnumber_vrmkls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final String ATTEMPTS_KEY = "ATTEMPTS";
    private static final String SECRETNUMBER_KEY = "SECRETNUMBER";
    private static final String USERNUMBER_KEY = "USERNUMBER";
    private int secretNumber;
    private int attempts=5;
    private int textNumber;
    private boolean foundNumber = false;
    Button   mButtonLock;
    Button   mButtonRestart;
    EditText mEdit;
    TextView mSecret;
    TextView mText;


    public MainActivity(){
        Random rand = new Random();
        secretNumber = rand.nextInt(100) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState != null) {
            attempts = savedInstanceState.getInt(ATTEMPTS_KEY);
            secretNumber = savedInstanceState.getInt(SECRETNUMBER_KEY);
            textNumber = savedInstanceState.getInt(USERNUMBER_KEY);

        }

        updateScreen();


    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(ATTEMPTS_KEY, attempts);
        bundle.putInt(SECRETNUMBER_KEY , secretNumber);
        bundle.putInt(USERNUMBER_KEY, textNumber);
    }

    private void updateScreen(){


        mText = (TextView) findViewById(R.id.attemptsNumber);
        String a = String.valueOf(attempts);
        mText.setText(a);

        mButtonLock = (Button)findViewById(R.id.lockAnswerButton);
        mButtonRestart = (Button) findViewById(R.id.restartButton);
        mEdit   = (EditText)findViewById(R.id.inputNumber);


        pressRestart();

        checkNumber();
    }

    private void pressRestart(){
        mButtonRestart.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(i);


                    }
                });
    }

    private void checkNumber(){


        mButtonLock.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", mEdit.getText().toString());

                        try {
                            textNumber = Integer.valueOf(mEdit.getText().toString());
                            foundNumber=checkIfFound(textNumber);
                        } catch (NumberFormatException e) {
                            notANumber();
                        }

                    }
                });
    }

    private void notANumber(){
        Toast.makeText(this, this.getString(R.string.notanumber) , Toast.LENGTH_SHORT).show();
    }

    private boolean checkIfFound(int textNumber){

        if(checkIfEligible(textNumber)) {

            if (secretNumber == textNumber) {
                mEdit.setEnabled(false);
                mButtonLock.setEnabled(false);
                mSecret   = (TextView)findViewById(R.id.secretNumber);
                String s = String.valueOf(secretNumber);
                mSecret.setText(s);
                Toast.makeText(this, this.getString(R.string.congratulations) , Toast.LENGTH_SHORT).show();
                return true;
            } else {
                if (attempts > 1) {
                    if(textNumber>secretNumber){
                        Toast.makeText(this, this.getString(R.string.biggernumber), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, this.getString(R.string.smallernumber), Toast.LENGTH_SHORT).show();
                    }
                    attempts--;
                    if (attempts == 1) {
                        Toast.makeText(this, this.getString(R.string.oneattempt), Toast.LENGTH_SHORT).show();
                    }
                    decreaseAttempts();
                } else {
                    attempts--;
                    decreaseAttempts();
                    mSecret   = (TextView)findViewById(R.id.secretNumber);
                    String s = String.valueOf(secretNumber);
                    mSecret.setText(s);
                    Toast.makeText(this, this.getString(R.string.youlost), Toast.LENGTH_SHORT).show();
                    mEdit.setEnabled(false);
                    mButtonLock.setEnabled(false);
                }
                return false;
            }
        }else{return false;}
    }

    private void decreaseAttempts(){

        String a = String.valueOf(attempts);
        mText.setText(a);

    }

    private boolean checkIfEligible(int textNumber){

        if(textNumber>=1 && textNumber<=100){
            return true;
        }else{
            Toast.makeText(this, this.getString(R.string.validnumbers), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
