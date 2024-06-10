package com.demo.demoapp.feature.airTickets.ui.viewControllers

import android.os.Bundle
import androidx.fragment.app.Fragment

internal abstract class FragmentViewController<T : Fragment>(protected val fragment: T) {
    open fun onCreate(): Unit = Unit

    open fun onStart(): Unit = Unit

    open fun onResume(): Unit = Unit

    open fun onPause(): Unit = Unit
    open fun onDestroy(): Unit = Unit

    open fun onSaveInstanceState(outState: Bundle): Unit = Unit

    open fun onViewStateRestored(savedInstanceState: Bundle?): Unit = Unit


}