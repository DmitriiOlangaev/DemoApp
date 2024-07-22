package com.demo.demoapp.core.common

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.Result
import kotlin.coroutines.cancellation.CancellationException

inline fun <reified D : Dependencies> Fragment.findDependencies(): D {
    return findDependenciesByClass(D::class.java)
}

@Suppress("UNCHECKED_CAST")
fun <D : Dependencies> Fragment.findDependenciesByClass(clazz: Class<D>): D {
    return parents
        .mapNotNull { it.depsMap[clazz] }
        .firstOrNull() as D?
        ?: throw IllegalStateException("No Dependencies $clazz in ${allParents.joinToString()}")
}

private val Fragment.parents: Iterable<HasDependencies>
    get() = allParents.mapNotNull { it as? HasDependencies }


private val Fragment.allParents: Iterable<Any>
    get() = object : Iterable<Any> {
        override fun iterator() = object : Iterator<Any> {
            private var currentParentFragment: Fragment? = parentFragment
            private var parentActivity: Activity? = activity
            private var parentApplication: Application? = parentActivity?.application

            override fun hasNext() =
                currentParentFragment != null || parentActivity != null || parentApplication != null

            override fun next(): Any {
                currentParentFragment?.let { parent ->
                    currentParentFragment = parent.parentFragment
                    return parent
                }

                parentActivity?.let { parent ->
                    parentActivity = null
                    return parent
                }

                parentApplication?.let { parent ->
                    parentApplication = null
                    return parent
                }

                throw NoSuchElementException()
            }
        }
    }

suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Result.failure(exception)
}

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}


fun View.setVisibleOrGone(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Fragment.setCollector(f: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, f)
    }
}