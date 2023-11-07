package com.example.ittestapplication.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ittestapplication.databinding.ActivityLaunchBinding
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchBinding
    private lateinit var userList : ArrayList<User>
    private lateinit var sharePreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        autoLogIn()

        binding.registrationButton.setOnClickListener{
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signInButton.setOnClickListener{
            authorization()
        }
    }

    private fun autoLogIn() {
        sharePreference = getSharedPreferences("MY_PRE",Context.MODE_PRIVATE)
        val getUserLogin = sharePreference.getString("LOGIN","")
        val getUserPassword = sharePreference.getString("PASSWORD","")
        val getUserRole = sharePreference.getString("ROLE","")
        if(getUserLogin != "" && getUserPassword != "" && getUserRole != ""){
            Toast.makeText(this, "auto-log-in", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AuthActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun authorization() {
        val login = binding.loginEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        if(login.isNotEmpty() && password.isNotEmpty()){
            val api = ServiceBuilder.buildService(ItTestApi::class.java)
            api.getAuth(login,password).enqueue(object: Callback<ApiResponse<User>>{
                override fun onResponse(
                    call: Call<ApiResponse<User>>,
                    response: Response<ApiResponse<User>>
                ) {
                    if (response.body()!!.success == true){
                        val editor: SharedPreferences.Editor = sharePreference.edit()
                        userList = response.body()!!.data as ArrayList<User>
                        editor.putString("LOGIN",userList[0].login)
                        editor.putString("PASSWORD",userList[0].password)
                        editor.putString("ROLE",userList[0].role.toString())
                        editor.apply()
                        val intent = Intent(this@AuthActivity,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else{
                        Toast.makeText(this@AuthActivity, "User does not exist.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
                    Toast.makeText(this@AuthActivity, "Error", Toast.LENGTH_SHORT).show()
                    Log.e("AuthActivity", t.message.toString())
                }

            })
        } else{
            Toast.makeText(this@AuthActivity, "Sorry, not all fields are filled out.", Toast.LENGTH_SHORT).show()
        }
    }
}