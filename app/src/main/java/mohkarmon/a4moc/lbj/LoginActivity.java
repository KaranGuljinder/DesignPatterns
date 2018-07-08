package mohkarmon.a4moc.lbj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import mohkarmon.a4moc.lbj.Models.User;
import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Screens.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final int RC_SIGN_IN = 573;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton googleLoginButton;
    private final String TAG = "LOGIN";
    private PrefUtils prefUtils;
    private APIEndpointInterface apiEndpointInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.setApplicationId("228954734545096");
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        prefUtils = new PrefUtils(this);
        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("147401436499-8v2712bn2amuldvnc6pkm5kfsak52eqg.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleLoginButton =  findViewById(R.id.btn_login_google);
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignIn();
            }
        });

    }


    private void GoogleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String googleToken = account.getIdToken();
            prefUtils.saveGOAccessToken(googleToken);
            checkLoggedInOrReg(account.getEmail(),account.getGivenName());

        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void RegisterUser(final String email,String username ,final String authType){
        User regUser= new User(username,email,authType);

        Call<User> call = apiEndpointInterface.registerUser(regUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ConnectedUser cUsr = ConnectedUser.getInstance();
                cUsr.setEmail(email);
                cUsr.setAuthType(authType);
                cUsr.setUserid(response.body().getId());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("hi",t.toString());

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //  updateUI(account);
    }

    private void checkLoggedInOrReg(final String email, final String username){
        Call<User> call = apiEndpointInterface.getUser(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                assert response.body() != null;
                if(response.body() != null){



                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                }
                else {
                    RegisterUser(email,username, "Google");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("result",t.toString());

            }
        });

    }

}
