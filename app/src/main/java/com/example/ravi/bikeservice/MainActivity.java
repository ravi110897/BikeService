package com.example.ravi.bikeservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Boolean i = false;
    private Boolean loggedin;
    //EditText number;
    //Button Submit;
    private ImageView image;
    private boolean isSecondTime;

    public static int APP_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkExistingUser();



       /* loggedin = pref.getBoolean("state", false);
        if (loggedin == true) {
            editor.putBoolean("state", true); //open map page
            Intent i = new Intent(this, MapActivity.class);
            startActivity(i);
        }

//        number = (EditText) findViewById(R.id.number);
//        Submit = (Button) findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(MainActivity.this, OtpPage.class);
                c.putExtra("Number", number.getText().toString());
                startActivity(c);
            }
        });



*/

    }

    private void checkExistingUser() {
        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        Log.d(TAG, "checkExistingUser: " + accessToken);
        if (accessToken != null) {
            Intent c = new Intent(getApplicationContext(), Registar.class);
            startActivity(c);
        } else {
            //Handle new or logged out user
            callLoginScreen();
        }

    }

    private void callLoginScreen() {


        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);


    }


    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult1:_out if" + getAccessToken());
        if (getAccessToken()!=null) {
            Log.d(TAG, "onActivityResult1:_if1" + getAccessToken());
            // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            Log.d(TAG, "onActivityResult2:_" + loginResult);
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
//                showErrorActivity(loginResult.getError());
                Log.d(TAG, "onActivityResult3:_");
            } else if (loginResult.wasCancelled()) {
                Log.d(TAG, "onActivityResult4:_");
                toastMessage = "Login Cancelled";
            } else {
                Log.d(TAG, "onActivityResult5:_");
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    Log.d(TAG, "onActivityResult: login....!!!!!!!!1");
                    Intent c = new Intent(getApplicationContext(), Registar.class);
                    startActivity(c);
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...
//                goToMyLoggedInActivity();
            }

            // Surface the result to your user in an appropriate way.
           /* Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();*/
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isSecondTime) {
            finish();
        }
        isSecondTime = true;
    }

    private AccessToken getAccessToken(){
        Log.d(TAG, "getAccessToken: "+AccountKit.getCurrentAccessToken());
       return AccountKit.getCurrentAccessToken();
    }
}
