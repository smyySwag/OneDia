package b1392982.uk.ac.tees.onedia;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

   private static final Pattern PASSWORD_PATTERN = Pattern.compile(
           "^" +   "(?=.*[0-9])" +
                   "(?=.*[a-z])" +
                   "(?=.*[A-Z])" +
                   "(?=.*[@#$%^&+=])" +
                   "(?=\\S+$)" +
                   ".{4,}" +
                   "$");

    private TextInputLayout Email;
    private TextInputLayout Password;
    Button SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        SignUp = findViewById(R.id.signUp);
    }

    private boolean validateEmail() {
        String emailInput = Email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            Email.setError("This field can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Email.setError("Please enter a valid email address.");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {

        String passwordInput = Objects.requireNonNull(Password.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            Password.setError("This field can't be empty.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            Password.setError("The password isn't strong enough.");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }


    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()){
            return;
        }

        String input = "Email: " + Email.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + Password.getEditText().getText().toString();
        input += "\n";

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}


