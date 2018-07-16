package mohkarmon.a4moc.lbj;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import mohkarmon.a4moc.lbj.Models.ConnectedUser;
import mohkarmon.a4moc.lbj.Models.User;
import mohkarmon.a4moc.lbj.R;
import mohkarmon.a4moc.lbj.Screens.APIClient;
import mohkarmon.a4moc.lbj.Screens.Connected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private APIEndpointInterface apiEndpointInterface;
    private PrefUtils prefUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefUtils = new PrefUtils(this);
        apiEndpointInterface = APIClient.getClient().create(APIEndpointInterface.class);

        if(prefUtils.getGOToken() != null){
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    //   .requestIdToken(prefUtils.getGOToken())
                    .requestServerAuthCode("147401436499-8v2712bn2amuldvnc6pkm5kfsak52eqg.apps.googleusercontent.com", false)
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
            googleSignInClient.silentSignIn()
                    .addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
                        @Override
                        public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                            handleSignInResult(task);

                        }
                    });
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            checkLoggedInOrReg(account.getEmail());
        } catch (ApiException e) {

        }
    }

    private void checkLoggedInOrReg(final String email) {
        Call<User> call = apiEndpointInterface.getUser(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {

                    ConnectedUser cUser = ConnectedUser.getInstance();
                    cUser.setUsername(response.body().getUsername());
                    cUser.setUserid(response.body().getId());
                    cUser.setAuthType(response.body().getAuthMethod());
                    cUser.setEmail(response.body().getEmail());
                    cUser.setNbSold(response.body().getNbSold());
                    cUser.setNbAds(response.body().getNbAds());

                    Intent intent = new Intent(MainActivity.this, Connected.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("result", t.toString());

            }
        });

    }

}
