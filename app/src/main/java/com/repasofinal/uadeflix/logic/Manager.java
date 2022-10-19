package com.repasofinal.uadeflix.logic;

import android.util.Log;

import com.repasofinal.uadeflix.backend.cms.CMS_Controller;
import com.repasofinal.uadeflix.backend.cms.Response_CMS_Carrousel;
import com.repasofinal.uadeflix.backend.cms.Response_CMS_GetCarrouseles;
import com.repasofinal.uadeflix.backend.sso.SSO_Controller;
import com.repasofinal.uadeflix.backend.sso.Response_SSO_Login;
import com.repasofinal.uadeflix.backend.sso.Response_SSO_Logout;
import com.repasofinal.uadeflix.backend.sso.Response_SSO_Register;
import com.repasofinal.uadeflix.support.ActionV;
import com.repasofinal.uadeflix.support.StaticData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Manager {
    private User currentUser;
    private Movie currentMovie;
    private List<Catalog> catalogs;

    public void SignIn(User newUser, ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        SSO_Controller.SignIn(newUser.getEmail(), newUser.getPassword()).enqueue(new Callback<Response_SSO_Login>() {
            @Override public void onResponse(Call<Response_SSO_Login> call, Response<Response_SSO_Login> response) {
                Response_SSO_Login body = response.body();
                if (body != null) {
                    Log.d("Response","Logged in");
                    String[] tokens = new String[2];
                    tokens[0] = body.getToken();
                    tokens[1] = response.headers().get("Set-Cookie").split(";")[0].split("=")[1];
                    currentUser = new User(tokens);
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
        SSO_Controller.SignOut().enqueue(new Callback<Response_SSO_Logout>() {
            @Override public void onResponse(Call<Response_SSO_Logout> call, Response<Response_SSO_Logout> response) {
                Response_SSO_Logout body = response.body();
                if (body != null) {
                    Log.d("Response","Logged out");
                    currentUser = null;
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
}
