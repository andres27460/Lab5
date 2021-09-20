package com.example.lab5.ui.Login.Login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.lab5.DeudoresApp
import com.example.lab5.MainActivity
import com.example.lab5.data.dao.DebtorSessionDao
import com.example.lab5.data.entities.Debtor
import com.example.lab5.data.entities.DebtorSession

import com.example.lab5.databinding.LoginActivityBinding
import com.example.lab5.ui.Login.CreateLogin.CreateLoginActivity
import com.example.lab5.ui.detail.DetailFragmentArgs
import kotlinx.android.synthetic.main.login_activity.*
import java.sql.Types.NULL

class LoginActivity : AppCompatActivity()  {


    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener(){
            val i = Intent(this, CreateLoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

        binding.button.setOnClickListener(){
            val debtorSessionDao: DebtorSessionDao = DeudoresApp.database2.DebtorSessionDao()
            val debtorSession: DebtorSession = debtorSessionDao.readUserLogin(mailEditText.text.toString())
            if(debtorSession != null && debtorSession.pass_login == editTextTextPassword.text.toString()){
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            }
            else{
                Toast.makeText(applicationContext, "Datos incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }





}