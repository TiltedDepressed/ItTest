package com.example.ittestapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ittestapplication.databinding.ActivityRegistrationBinding
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.registrationButton.setOnClickListener {
            registration()
        }


    }

    private fun registration() {
        val login: String = binding.loginEditText.text.toString().trim()
        val password: String = binding.passwordEditText.text.toString().trim()
        val repeatPassword: String = binding.repeatPasswordEditText.text.toString().trim()
        val firstName: String = binding.nameEditText.text.toString().trim()
        val lastName: String = "empty"
        val middleName: String = "empty"
        if (password == repeatPassword) {
            val api = ServiceBuilder.buildService(ItTestApi::class.java)
            api.createUser(login, password, firstName, lastName, middleName)
                .enqueue(object : Callback<ApiResponse<User>> {
                    override fun onResponse(
                        call: Call<ApiResponse<User>>,
                        response: Response<ApiResponse<User>>
                    ) {
                        if (response.body()!!.success == true) {
                            val intent = Intent(this@RegistrationActivity, AuthActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@RegistrationActivity,
                                "Something goes wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                    override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
                        Log.e("RegistrationActivity", t.message.toString())
                        Toast.makeText(this@RegistrationActivity, "Error", Toast.LENGTH_SHORT)
                            .show()
                    }

                })


        } else {
            Toast.makeText(
                this@RegistrationActivity,
                "password does not equal repeated password",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}