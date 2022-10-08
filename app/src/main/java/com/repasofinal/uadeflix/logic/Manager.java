package com.repasofinal.uadeflix.logic;

import android.util.Log;

import com.repasofinal.uadeflix.backend.Controller;
import com.repasofinal.uadeflix.backend.Response_Login;
import com.repasofinal.uadeflix.backend.Response_Logout;
import com.repasofinal.uadeflix.backend.Response_Register;
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
        Controller.SignIn(newUser.getEmail(), newUser.getPassword()).enqueue(new Callback<Response_Login>() {
            @Override public void onResponse(Call<Response_Login> call, Response<Response_Login> response) {
                Response_Login body = response.body();
                if (body != null) {
                    Log.d("Response","Logged in");
                    currentUser = new User(body.getToken());
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Not logged in");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_Login> call, Throwable t) {
                Log.d("Response","Fail to log in");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public void SignOut(ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        Controller.SignOut().enqueue(new Callback<Response_Logout>() {
            @Override public void onResponse(Call<Response_Logout> call, Response<Response_Logout> response) {
                Response_Logout body = response.body();
                if (body != null) {
                    Log.d("Response","Logged out");
                    currentUser = null;
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","Not logged out");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_Logout> call, Throwable t) {
                Log.d("Response","Fail to log out");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public void SignUp(User newUser, ActionV onStatusOk, ActionV onStatusError, ActionV onStatusFail) {
        Controller.SignUp(newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword()).enqueue(new Callback<Response_Register>() {
            @Override public void onResponse(Call<Response_Register> call, Response<Response_Register> response) {
                Response_Register body = response.body();
                if (body != null) {
                    Log.d("Response","User created");
                    if(onStatusOk != null) { onStatusOk.Invoke(); }
                } else {
                    Log.d("Response","User not created");
                    if(onStatusError != null) { onStatusError.Invoke(); }
                }
            }
            @Override public void onFailure(Call<Response_Register> call, Throwable t) {
                Log.d("Response","Fail to create user");
                if(onStatusFail != null) { onStatusFail.Invoke(); }
            }
        });
    }
    public Boolean UpdatePaymentInfo(Card newCard) { return true; }
    public Boolean UpdatePassword(String newPassword) { return true; }
    public Boolean UpdateMovies() {
        catalogs = StaticData.GetCatalogs();
        return true;
    }

    public void SetCurrentMovie(Movie movie){ currentMovie = movie; }
    public Movie GetCurrentMovie() { return currentMovie; }
    public List<Catalog> GetCatalogs() { return catalogs; }
}
