package com.example.capstone

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.capstone.bottomMenu.adminReservationView
import com.example.capstone.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class navigation : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        val user = FirebaseAuth.getInstance().currentUser
        val bottomNavigationView = binding.bottomNavigationView
        replace(Home())



//        val profile = findViewById<ImageButton>(R.id.profileView)
//
//        profile.setOnClickListener {
//            replace(Profile())
//        }

        if (user?.email == LoginPage.ADMIN_EMAIL) {
    // Admin user, load admin layout
            val menuInflater = MenuInflater(this)
    menuInflater.inflate(R.menu.adminmenu, bottomNavigationView.menu)
        } else {
    // Regular user, load regular layout
            bottomNavigationView.inflateMenu(R.menu.menu_tab)

}



        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replace(Home())
                R.id.calendar -> replace(calendar())
//                R.id.admincalendar -> {val launch = Intent(this, AdminCalendarUpdate::class.java)
//                    startActivity(launch)}
                R.id.admincalendar -> replace(AdminCalendarView())
                R.id.upload -> {val launch = Intent(this, UserReport::class.java)
                    startActivity(launch)}
                R.id.navigation_read_report -> replace(AdminCheck())
                R.id.menu -> replace(bottomMenuBurger())
                R.id.appointments -> {val launch = Intent(this, adminReservationView::class.java)
                    startActivity(launch)}
//                R.id.message -> replace(user_send_message())
//                R.id.profile -> replace(Profile())

                else ->{

                }
            }
            true
        }


    }



    private fun replace(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()

    }




}

