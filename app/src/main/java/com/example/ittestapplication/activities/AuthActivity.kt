package com.example.ittestapplication.activities

import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrationButton.setOnClickListener{
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signInButton.setOnClickListener{
            authorization()
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