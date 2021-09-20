package com.example.lab5.ui.Login.CreateLogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.lab5.DeudoresApp
import com.example.lab5.data.dao.DebtorSessionDao
import com.example.lab5.data.entities.DebtorSession

import com.example.lab5.databinding.*
import com.example.lab5.ui.Login.Login.LoginActivity
import kotlinx.android.synthetic.main.create_login_activity.*
import kotlinx.android.synthetic.main.fragment_create.*
import java.sql.Types.NULL


class CreateLoginActivity : AppCompatActivity() {

    private lateinit var binding: CreateLoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateLoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener(){
            if(editPassText.text.toString() == editPassText2.text.toString()) {
                val userName = mailEditText.text.toString()
                val userPass = editPassText.text.toString()


                createDebtorLogin(userName,userPass)

                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
            else{
                Toast.makeText(applicationContext, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun createDebtorLogin(userName: String, userPass: String) {
        val debtorSession = DebtorSession(id = NULL, user_login = userName, pass_login = userPass)
        val debtorSessionDAO : DebtorSessionDao = DeudoresApp.database2.DebtorSessionDao()
        debtorSessionDAO.createUserLogin(debtorSession)


    }

}