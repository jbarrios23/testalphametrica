package com.android.testalphametrica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * class RegisterActivity let Make register of user in app.
 */
public class RegisterActivity extends AppCompatActivity {
    public static String CLASS_TAG=RegisterActivity.class.getSimpleName();
    public Button register;
    public TextView link_register;
    public EditText name;
    public EditText phone;
    public EditText dateBirthday;
    public EditText email;
    public EditText password;
    public EditText confirm_password;
    public String name_register;
    public String phone_register;
    public String date_register;
    public String email_register;
    public String password_register;
    public String confirm_password_register;
    public CheckBox One;
    public CheckBox Two;
    public CheckBox Tree;
    public CheckBox Four;
    public CheckBox checkBoxAgree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.button_register);
        link_register=findViewById(R.id.link_register);
        name=findViewById(R.id.nameRegister);
        phone=findViewById(R.id.phoneRegister);
        dateBirthday=findViewById(R.id.dateRegister);
        email=findViewById(R.id.emailRegister);
        password=findViewById(R.id.passRegister);
        confirm_password=findViewById(R.id.cpassRegister);
        One=findViewById(R.id.checkOne);
        Two=findViewById(R.id.checkTwo);
        Tree=findViewById(R.id.checkTree);
        Four=findViewById(R.id.checkFour);
        checkBoxAgree=findViewById(R.id.checkAgree);

        link_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();
            }
        });

        dateBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(CLASS_TAG,"CharSequence "+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(CLASS_TAG,"Editable "+editable);
                One.setChecked(editable.toString().length()>=8);
                Two.setChecked(Utils.checkStringCapital(editable.toString()));
                Tree.setChecked(Utils.checkStringHasNumber(editable.toString()));
                Four.setChecked(Utils.checkStringHasSpecialCharacter(editable.toString()));
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment(dateBirthday);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Method for validate field of register.
     */
    private void validateFields() {
        name_register=name.getText().toString();
        phone_register=phone.getText().toString();
        date_register=dateBirthday.getText().toString();;
        email_register=email.getText().toString();;
        password_register=password.getText().toString();;
        confirm_password_register=confirm_password.getText().toString();

        if (name_register.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone_register.matches("")) {
            Toast.makeText(this, "You did not enter a Phone", Toast.LENGTH_SHORT).show();
            return;
        }
        if (date_register.matches("")) {
            Toast.makeText(this, "You did not enter a date Birthday", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email_register.matches("")) {
            Toast.makeText(this, "You did not enter a email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password_register.matches("")) {
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirm_password_register.matches("")) {
            Toast.makeText(this, "You did not enter a confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Utils.isValidMail(email_register)){
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Utils.isValidMobile(phone_register)){
            Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Utils.isValidPassAndConfirm(password_register,confirm_password_register)){
            Toast.makeText(this, "Password and Confirm Password Not matches! ", Toast.LENGTH_LONG).show();
            return;
        }
        if(!One.isChecked()){
            Toast.makeText(this, "Password At least 8 characters! ", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Two.isChecked()){
            Toast.makeText(this, "Password At least one capital letter! ", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Tree.isChecked()){
            Toast.makeText(this, "Password At least one number! ", Toast.LENGTH_LONG).show();
            return;
        }

        if(!Four.isChecked()){
            Toast.makeText(this, "Password At least one special character @$!%*#?&! ", Toast.LENGTH_LONG).show();
            return;
        }
        if(!checkBoxAgree.isChecked()){
            Toast.makeText(this, "You must be Agree with Term and Cond ", Toast.LENGTH_LONG).show();
            return;
        }

        prepareDataToSendService(name_register,phone_register,date_register,password_register,email_register);

    }

    private void prepareDataToSendService(String name_register, String phone_register,
                                         String date_register, String password_register,
                                         String email_register) {
        JSONObject jsonObjectBody=new JSONObject();
        try {
            jsonObjectBody.put("Name",name_register);
            jsonObjectBody.put("Phone",phone_register);
            jsonObjectBody.put("Date",date_register);
            jsonObjectBody.put("Password",password_register);
            jsonObjectBody.put("Email",email_register);
            Log.d(CLASS_TAG,"JSON Body "+jsonObjectBody.toString());
            goToLoginActivity();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void goToLoginActivity() {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        intent.putExtra("username",email_register);
        startActivity(intent);
        RegisterActivity.this.finish();
    }

    /**
     * class DatePickerFragment let Get birthday date of new user.
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        public EditText provDate;
        public DatePickerFragment(EditText editText) {
            this.provDate=editText;

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            //String dateResult=day + "/" + (month + 1) + "/" + year;
            String dateResult=(month + 1) + "/" + day + "/" + year;
            Log.e(CLASS_TAG,"Date B "+dateResult);
            provDate.setText(dateResult);
        }

    }
}