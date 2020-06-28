package com.fluffybros.tujefe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fluffybros.tujefe.HomeRecyclerAdapter
import com.fluffybros.tujefe.HomeRecyclerItem
import com.fluffybros.tujefe.MainActivity
import com.fluffybros.tujefe.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel =
        //    ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*homeViewModel.text.observe(
            viewLifecycleOwner,
            Observer {
                recyclerView.text = it
            }
        )*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exampleList = generateHomeRecyclerItems()
        recycler_home.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = HomeRecyclerAdapter(exampleList)
        }
    }

    private fun generateHomeRecyclerItems(): List<HomeRecyclerItem>{
        val list = ArrayList<HomeRecyclerItem>()
        for(i in 0 until 100){
            val item = HomeRecyclerItem(i, "Account $i", "000-000")
            list += item
        }
        return list
    }
}
