package com.demo.demoapp.feature.airTickets.ui.fragments

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.demo.demoapp.core.common.DepsMap
import com.demo.demoapp.core.common.HasDependencies
import com.demo.demoapp.core.common.findDependencies
import com.demo.demoapp.feature.airTickets.R
import com.demo.demoapp.feature.airTickets.di.AirTicketsNavHostFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerAirTicketsNavHostFragmentComponent
import javax.inject.Inject

class AirTicketsNavHostFragment : NavHostFragment(), HasDependencies {

    @Inject
    override lateinit var depsMap: DepsMap
    private val component: AirTicketsNavHostFragmentComponent by lazy {
        initializeComponent()
    }
    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)
        navHostController.setGraph(R.navigation.air_tickets_navigation)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    private fun initializeComponent(): AirTicketsNavHostFragmentComponent {
        return DaggerAirTicketsNavHostFragmentComponent.factory().create(this, findDependencies())
    }
}