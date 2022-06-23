package com.vivek.daggerhiltapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivek.daggerhiltapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObservers()
    }

    private fun setUpUI() {
        binding.ProductListRecylerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = ProductListAdapter()
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObservers() {
        mainViewModel.getProduct().observe(this) { productList ->
            productList?.let {
                binding.ProductListRecylerView.apply {
                    with(adapter as ProductListAdapter) {
                        mList = it
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}