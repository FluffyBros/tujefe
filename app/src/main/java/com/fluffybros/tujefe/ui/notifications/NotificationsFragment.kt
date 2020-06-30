package com.fluffybros.tujefe.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fluffybros.tujefe.R
import com.fluffybros.tujefe.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val notificationsViewModel: NotificationsViewModel by lazy {
        ViewModelProvider(this).get(NotificationsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNotificationsBinding.bind(view)

        notificationsViewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                binding.textNotifications.text = it
            }
        )
    }
}
