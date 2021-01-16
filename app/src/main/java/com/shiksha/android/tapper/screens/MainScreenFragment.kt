package com.shiksha.android.tapper.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shiksha.android.tapper.R
import com.shiksha.android.tapper.viewmodels.CounterViewModel
import com.shiksha.android.tapper.viewmodels.LockViewModel
import com.shiksha.android.tapper.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainScreenFragment : BaseFragment(), View.OnClickListener {

    lateinit var mainScreenView: View
    lateinit var counterView: TextView
    lateinit var minusView: TextView
    lateinit var plusView: TextView
    lateinit var lockView: ImageView
    lateinit var fadeView:View
    lateinit var taskEditText: EditText
    lateinit var updateBtn: Button
    lateinit var taskName:TextView

    lateinit var counterViewModel: CounterViewModel
    lateinit var lockViewModel: LockViewModel
    var io = Dispatchers.IO


    var visibility: LockViewModel.Visibility = LockViewModel.Visibility.UNLOCKED
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("sachin_log", "oncreateview ")

        mainScreenView = inflater.inflate(R.layout.mainscreen_layout, container, false)
        counterView = mainScreenView.findViewById(R.id.textView)
        plusView = mainScreenView.findViewById(R.id.plusView)
        minusView = mainScreenView.findViewById(R.id.minusView)
        lockView = mainScreenView.findViewById(R.id.imageButton)
        fadeView = mainScreenView.findViewById(R.id.fadeView)
        taskEditText = mainScreenView.findViewById(R.id.taskEditView)
        updateBtn = mainScreenView.findViewById(R.id.updateBtn)
        taskName = mainScreenView.findViewById(R.id.taskName)

        setupClicks()
        setupViewModel()

        return mainScreenView
    }

    private fun setupViewModel() {
        counterViewModel = ViewModelProvider(this,ViewModelFactory(activity!!.application)).get(CounterViewModel::class.java)
        counterViewModel.getCounterData().observe(this, {
            counterView.text = it.count.toString()
            taskName.text = it.name.toString()

        })

        lockViewModel = ViewModelProvider(this,ViewModelFactory(activity!!.application)).get(LockViewModel::class.java)
        lockViewModel.getLockViewData().observe(this, { visible ->
            visibility = visible
            Log.d("sachin_log", "" + visibility)
            when (visible) {
                LockViewModel.Visibility.LOCKED -> {
                    lockView.setImageResource(R.drawable.lock)
                    fadeView.setBackgroundColor(resources.getColor(R.color.dim))
                    taskEditText.isEnabled = false
                    updateBtn.isEnabled = false
                }
                LockViewModel.Visibility.UNLOCKED -> {
                    lockView.setImageResource(R.drawable.unlock)
                    fadeView.setBackgroundColor(resources.getColor(R.color.transparent))
                    fadeView.visibility = View.INVISIBLE
                    taskEditText.isEnabled = true
                    updateBtn.isEnabled = true
                }
            }
        })


    }

    private fun setupClicks() {
        plusView.setOnClickListener(this)
        minusView.setOnClickListener(this)
        lockView.setOnClickListener(this)
        updateBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val id = v?.id

        when (id) {
            R.id.plusView -> {
                Log.d("sachin_log","plus view")
                var count = counterView.text.toString().toInt()
                if (visibility == LockViewModel.Visibility.UNLOCKED) {
                        counterViewModel.setCounterData(++count)

                }
            }
            R.id.minusView -> {
                Log.d("sachin_log","minus view")
                var count = counterView.text.toString().toInt()
                if(count==0) return
                if (visibility == LockViewModel.Visibility.UNLOCKED) {
                    counterViewModel.setCounterData(--count)

                }
            }

            R.id.imageButton -> {
                if (visibility == LockViewModel.Visibility.LOCKED)
                    lockViewModel.setLockViewData(LockViewModel.Visibility.UNLOCKED)
                else
                    lockViewModel.setLockViewData(LockViewModel.Visibility.LOCKED)
            }
            R.id.updateBtn->{
                if(taskEditText.text.isNotEmpty()){
                        counterViewModel.setTaskName(taskEditText.text.toString())
                }
            }

        }
    }


    companion object {
        fun newInstance(): MainScreenFragment {
            return MainScreenFragment()
        }
    }

}