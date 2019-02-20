package eu.marcocattaneo.samplecoroutines

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    private val progressObserver = Observer<Boolean> { visible -> progressBar.visibility = if (visible == true) View.VISIBLE else View.GONE }

    private val toastObserver = Observer<String> { Toast.makeText(this, it?:"message-is-null", Toast.LENGTH_LONG).show() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mainViewModel.progressLiveData.observe(this, this.progressObserver)
        this.mainViewModel.toastLiveData.observe(this, this.toastObserver)

        process.setOnClickListener {
            this.mainViewModel.execute()
        }

    }
}
