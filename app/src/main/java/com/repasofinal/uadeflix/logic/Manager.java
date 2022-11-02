package com.repasofinal.uadeflix.logic;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.backend.cms.CMS_Controller;
import com.repasofinal.uadeflix.backend.cms.Response_CMS_GetCarrouseles;
import com.repasofinal.uadeflix.backend.sso.SSO_Controller;
import com.repasofinal.uadeflix.backend.sso.Response_SSO_Login;
import com.repasofinal.uadeflix.backend.sso.Response_SSO_Logout;
import com.repasofinal.uadeflix.backend.sso.Response_SSO_Register;
import com.repasofinal.uadeflix.backend.suscriptions.Response_Subscriptions_Paquete;
import com.repasofinal.uadeflix.backend.suscriptions.Subscriptions_Controller;
import com.repasofinal.uadeflix.support.ActionV;
import com.repasofinal.uadeflix.support.StaticData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Manager {
    private User currentUser;
    private Movie currentMovie;
    private Subscription[] subscriptions;
    private List<Catalog> catalogs;

    public void SignIn(User newUser, ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        SSO_Controller.SignIn(newUser.getEmail(), newUser.getPassword()).enqueue(new Callback<Response_SSO_Login>() {
            @Override public void onResponse(Call<Response_SSO_Login> call, Response<Response_SSO_Login> response) {
                Response_SSO_Login body = response.body();
                if (body != null) {
                    Log.d("Response","Logged in");
                    String[] tokens = new String[2];
                    tokens[0] = body.getToken();
                    tokens[1] = response.headers().get("Set-Cookie");
                    //tokens[1] = response.headers().get("Set-Cookie").split(";")[0].split("=")[1];
                    currentUser = new User(tokens);

                    SharedPreferences.Editor editor = MainActivity.getContext().getSharedPreferences("Uadeflix", MODE_PRIVATE).edit();
                    editor.putString("session", currentUser.getRefreshToken());
                    editor.apply();

                    AutomaticTokenRefresh();

                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Not logged in");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_SSO_Login> call, Throwable t) {
                Log.d("Response","Fail to log in");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public void SignOut(ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        SSO_Controller.SignOut(currentUser.getRefreshToken()).enqueue(new Callback<Response_SSO_Logout>() {
            @Override public void onResponse(Call<Response_SSO_Logout> call, Response<Response_SSO_Logout> response) {
                Response_SSO_Logout body = response.body();
                if (body != null) {
                    Log.d("Response","Logged out");
                    currentUser = null;
                    SharedPreferences.Editor editor = MainActivity.getContext().getSharedPreferences("Uadeflix", MODE_PRIVATE).edit();
                    editor.putString("session", null);
                    editor.apply();
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Not logged out");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_SSO_Logout> call, Throwable t) {
                Log.d("Response","Fail to log out");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public void SignUp(User newUser, ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        SSO_Controller.SignUp(newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword()).enqueue(new Callback<Response_SSO_Register>() {
            @Override public void onResponse(Call<Response_SSO_Register> call, Response<Response_SSO_Register> response) {
                Response_SSO_Register body = response.body();
                if (body != null) {
                    Log.d("Response","User created");
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","User not created");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_SSO_Register> call, Throwable t) {
                Log.d("Response","Fail to create user");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public void RefreshUser(String refreshToken, ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        SSO_Controller.RefreshUser(refreshToken).enqueue(new Callback<Response_SSO_Login>() {
            @Override public void onResponse(Call<Response_SSO_Login> call, Response<Response_SSO_Login> response) {
                Response_SSO_Login body = response.body();
                if (body != null) {
                    Log.d("Response","User token refreshed");
                    String[] tokens = new String[2];
                    tokens[0] = body.getToken();
                    //tokens[1] = response.headers().get("Set-Cookie");
                    tokens[1] = refreshToken;
                    currentUser = new User(tokens);
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","User token not refreshed");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_SSO_Login> call, Throwable t) {
                Log.d("Response","Fail to refresh token");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public User GetCurrentUser() { return currentUser; }
    public Boolean UpdatePaymentInfo(Card newCard) { return true; }
    public Boolean UpdatePassword(String newPassword) { return true; }
    public void UpdateMovies(ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        CMS_Controller.GetCarrouseles().enqueue(new Callback<Response_CMS_GetCarrouseles>() {
            @Override public void onResponse(Call<Response_CMS_GetCarrouseles> call, Response<Response_CMS_GetCarrouseles> response) {
                Response_CMS_GetCarrouseles body = response.body();
                if (body != null) {
                    Log.d("Response","Movies Updated");
                    catalogs = body.ToCatalogs();
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Movies Not Updated");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_CMS_GetCarrouseles> call, Throwable t) {
                Log.d("Response","Fail to Update Movies");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });


        catalogs = StaticData.GetCatalogs();
    }

    public void SetCurrentMovie(Movie movie){ currentMovie = movie; }
    public Movie GetCurrentMovie() { return currentMovie; }
    public List<Catalog> GetCatalogs() { return catalogs; }

    public Subscription[] GetSubscriptions() { return subscriptions; }
    public void UpdateSubscriptions(ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        Subscriptions_Controller.GetPaquetes().enqueue(new Callback<Response_Subscriptions_Paquete[]>() {
            @Override
            public void onResponse(Call<Response_Subscriptions_Paquete[]> call, Response<Response_Subscriptions_Paquete[]> response) {
                Response_Subscriptions_Paquete[] body = response.body();
                if (body != null) {
                    Log.d("Response","Subscriptions Updated");
                    subscriptions = new Subscription[body.length];
                    for (int i = 0; i < body.length; i++) { subscriptions[i] = body[i].ToSubscription(); }
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Subscriptions Not Updated");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }

            @Override
            public void onFailure(Call<Response_Subscriptions_Paquete[]> call, Throwable t) {
                Log.d("Response","Fail to get subscriptions");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public void CanViewCurrentMovie(ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        Subscriptions_Controller.CanViewFilm(currentUser, currentMovie).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("Response",response.toString());
                Boolean body = response.body();
                if (body != null) {
                    Log.d("Response","Response Ok: " + body);
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Response Not Ok");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Response","Fail to get response");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }

    public void AutomaticTokenRefresh() {
        new Handler().postDelayed(new Runnable() { public void run() {
            if(currentUser == null) {
                Log.d("Response","User Logged Out");
                return;
            }
            if(currentUser.CheckExpiration()) {
                Log.d("Response","Token Not Expired");
                AutomaticTokenRefresh();
                return;
            }
            Log.d("Response","Token Expired");

            RefreshUser(
                currentUser.getRefreshToken(),
                new ActionV() { @Override public void Invoke() { AutomaticTokenRefresh(); } },
                new ActionV() { @Override public void Invoke() { } },
                new ActionV() { @Override public void Invoke() { } }
            );
        } }, 300000);
    }
}
