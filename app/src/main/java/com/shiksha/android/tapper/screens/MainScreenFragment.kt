package com.shiksha.android.tapper.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shiksha.android.tapper.R
import com.shiksha.android.tapper.viewmodels.CounterViewModel
import com.shiksha.android.tapper.viewmodels.LockViewModel
import com.shiksha.android.tapper.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class MainScreenFragment : BaseFragment(), View.OnClickListener {

    lateinit var mainScreenView: View
    lateinit var counterView: TextView
    lateinit var lockView: ImageView
    lateinit var fadeView:View

    lateinit var counterViewModel: CounterViewModel
    lateinit var lockViewModel: LockViewModel
    var visibility: LockViewModel.Visibility = LockViewModel.Visibility.UNLOCKED
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("sachin_log", "oncreateview ")

        mainScreenView = inflater.inflate(R.layout.mainscreen_layout, container, false)
        counterView = mainScreenView.findViewById(R.id.textView)
        lockView = mainScreenView.findViewById(R.id.imageButton)
        fadeView = mainScreenView.findViewById(R.id.fadeView)

        setupClicks()
        setupViewModel()

        return mainScreenView
    }

    private fun setupViewModel() {
        counterViewModel = ViewModelProvider(this,ViewModelFactory(activity!!.application)).get(CounterViewModel::class.java)
        counterViewModel.getCounterData().observe(this, {
            counterView.text = it.count.toString()
        })

        lockViewModel = ViewModelProvider(this,ViewModelFactory(activity!!.application)).get(LockViewModel::class.java)
        lockViewModel.getLockViewData().observe(this, { visible ->
            visibility = visible
            Log.d("sachin_log", "" + visibility)
            when (visible) {
                LockViewModel.Visibility.LOCKED -> {
                    lockView.setImageResource(R.drawable.lock)
                    fadeView.setBackgroundColor(resources.getColor(R.color.dim))
                }
                LockViewModel.Visibility.UNLOCKED -> {
                    lockView.setImageResource(R.drawable.unlock)
                    fadeView.setBackgroundColor(resources.getColor(R.color.transparent))

                }
            }
        })


    }

    private fun setupClicks() {
        counterView.setOnClickListener(this)
        lockView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val id = v?.id

        when (id) {
            R.id.textView -> {
                var count = counterView.text.toString().toInt()
                if (visibility == LockViewModel.Visibility.UNLOCKED)
                    counterViewModel.setCounterData(++count)
            }

            R.id.imageButton -> {
                if (visibility == LockViewModel.Visibility.LOCKED)
                    lockViewModel.setLockViewData(LockViewModel.Visibility.UNLOCKED)
                else
                    lockViewModel.setLockViewData(LockViewModel.Visibility.LOCKED)
            }
        }
    }


    companion object {
        fun newInstance(): MainScreenFragment {
            return MainScreenFragment()
        }
    }

}