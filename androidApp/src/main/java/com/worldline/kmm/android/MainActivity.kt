package com.worldline.kmm.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.worldline.kmm.android.databinding.ActivityMainBinding
import com.worldline.kmm.core.Error
import com.worldline.kmm.core.Poi
import com.worldline.kmm.ui.logic.poilistvm.PoiListState
import com.worldline.kmm.ui.logic.poilistvm.PoiListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vm by lazy { PoiListViewModel() }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter = PoiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect {
                    when (it) {
                        is PoiListState.Error -> showError(it.error)
                        PoiListState.InProgress -> showProgress()
                        is PoiListState.Success -> showSuccess(it.pois)
                    }
                }
            }
        }


        with(binding.pois) {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        vm.attach()
    }

    private fun showSuccess(pois: List<Poi>) {
        updateVisibility(error = View.GONE, success = View.VISIBLE, View.GONE)
        adapter.replace(pois.toMutableList())
    }

    private fun showError(error: Error) {
        updateVisibility(error = View.VISIBLE, success = View.GONE, View.GONE)
        binding.errorText.text = error.toString()
    }

    private fun showProgress() {
        updateVisibility(error = View.GONE, success = View.GONE, View.VISIBLE)
    }

    private fun updateVisibility(error: Int, success: Int, progress: Int) {
        binding.error.visibility = error
        binding.success.visibility = success
        binding.progress.visibility = progress
    }

    override fun onDestroy() {
        super.onDestroy()

        vm.detach()
    }
}
