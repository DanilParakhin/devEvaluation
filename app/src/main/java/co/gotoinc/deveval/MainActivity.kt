package co.gotoinc.deveval

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import co.gotoinc.deveval.databinding.ActivityMainBinding
import co.gotoinc.deveval.viewmodel.MainViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbar)

        binding.vm = vm

        vm.liveErrorEvent.observe(this, Observer { Toast.makeText(this, it, LENGTH_LONG).show() })
        vm.liveSelectedList.observe(this, Observer { toolbar.title = "${it.count()}" })

//        swipeRefresh.setOnRefreshListener {  }
    }

}
