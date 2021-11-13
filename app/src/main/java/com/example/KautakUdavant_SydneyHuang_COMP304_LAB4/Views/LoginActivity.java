package com.example.KautakUdavant_SydneyHuang_COMP304_LAB4.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.KautakUdavant_SydneyHuang_COMP304_LAB4.Models.Nurse;
import com.example.KautakUdavant_SydneyHuang_COMP304_LAB4.R;
import com.example.KautakUdavant_SydneyHuang_COMP304_LAB4.ViewModels.NurseViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText nurseId;
    private EditText nursePassword;
    private NurseViewModel nurseViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nurseId=findViewById(R.id.etNurseId);
        nursePassword=findViewById(R.id.etNursePassword);

        nurseViewModel= new ViewModelProvider(this).get(NurseViewModel.class);
        nurseViewModel.allNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
            }
        });
    }

    public void signIn(View view){
        if(nurseViewModel.checkLogin(Integer.valueOf(nurseId.getText().toString()), nursePassword.getText().toString()))
        {
            // for editing nurse id
            SharedPreferences sharedPreferences= getSharedPreferences("NurseID",MODE_PRIVATE);
            SharedPreferences.Editor editor =sharedPreferences.edit();
            editor.putString("NurseID", nurseId.getText().toString());
            editor.commit();


            Intent intent=new Intent(this,PatientActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this,"Your record does not exist", Toast.LENGTH_LONG).show();
        }
    }
    public void registerNurse(View view){
        Intent intent=new Intent(this,NurseRegistrationActivity.class);
        startActivity(intent);

    }
}
