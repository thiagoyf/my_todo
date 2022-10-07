package com.thiagoyf.route.extentions

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.thiagoyf.route.Route

fun Fragment.navigateTo(route: Route, args: Bundle = bundleOf()) {
    findNavController().navigate(route.action, args)
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}

fun View.navigateTo(route: Route, args: Bundle = bundleOf()) {
    findNavController().navigate(route.action, args)
}