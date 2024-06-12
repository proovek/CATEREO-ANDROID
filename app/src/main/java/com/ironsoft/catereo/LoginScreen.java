package com.ironsoft.catereo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ironsoft.catereo.R;

import com.ironsoft.catereo.api.ApiClient;
import com.ironsoft.catereo.api.ApiService;
import com.ironsoft.catereo.api.Helpers.LoginCredentials;
import com.ironsoft.catereo.api.Helpers.LoginDetails;
import com.ironsoft.catereo.api.Helpers.UserManager;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginScreen extends AppCompatActivity {

    private ImageView logoImageView;
    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton loginButton;
    Retrofit retrofit = ApiClient.getAuthService();
    ApiService apiService = retrofit.create(ApiService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        // Inicjalizacja widoków
        logoImageView = findViewById(R.id.logoImageView);
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String login = loginEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Wyświetlenie Toast
            makeLogin(login,password);
        });
    }

    public void makeLogin(String username, String password){
        LoginCredentials credentials = new LoginCredentials(username, password);

        Call<LoginDetails> call = apiService.login(credentials);
        loginButton.setText("Logowanie...");
        hideKeyboard(this);
        call.enqueue(new Callback<LoginDetails>() {
            @Override
            public void onResponse(Call<LoginDetails> call, Response<LoginDetails> response) {
                if (response.isSuccessful()) {
                    LoginDetails loginDetails = response.body();
                    UserManager.saveUserData(LoginScreen.this, loginDetails.getAccessToken(), loginDetails.getExpiration(), loginDetails.getUser().getUserId(), loginDetails.getUser().getUserName(), loginDetails.getUser().getUserEmail(), loginDetails.getUser().getPhone(), loginDetails.getUser().getDisplayName(), loginDetails.getUser().getPosition(), loginDetails.getUser().getRole());
                    Toast.makeText(LoginScreen.this, "Logowanie pomyślne...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginScreen.this, "Podano błędne dane logowania.", Toast.LENGTH_SHORT).show();
                    loginButton.setText("Zaloguj");
                }
            }

            @Override
            public void onFailure(Call<LoginDetails> call, Throwable t) {
                Toast.makeText(LoginScreen.this, "Wystąpił błąd: " + t, Toast.LENGTH_SHORT).show();
                loginButton.setText("Zaloguj");
            }
        });
    }
    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        // Znajdź widok, który ma obecnie fokus w aktywności
        View view = activity.getCurrentFocus();
        // Jeśli żaden widok nie ma fokusu, utwórz nowy, aby klawiatura mogła zostać ukryta
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
