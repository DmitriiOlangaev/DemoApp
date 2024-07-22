package com.demo.demoapp.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.demo.demoapp.core.common.DepsMap
import com.demo.demoapp.core.common.HasDependencies
import com.demo.demoapp.view.DemoApp
import com.demo.demoapp.view.R
import com.demo.demoapp.view.databinding.ActivityMainBinding
import com.demo.demoapp.view.di.MainActivityComponent
import javax.inject.Inject

internal class MainActivity : AppCompatActivity(), HasDependencies {
    @Inject
    override lateinit var depsMap: DepsMap

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val mainActivityComponent: MainActivityComponent by lazy {
        initializeMainActivityComponent()
    }

    private fun initializeMainActivityComponent(): MainActivityComponent =
        (application as DemoApp).applicationComponent.getMainActivityComponentFactory()
            .create(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController =
            (supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment).navController
        binding.bnvMain.setupWithNavController(navController)
    }
}