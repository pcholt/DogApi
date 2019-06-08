package com.example.myapplication

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val vm : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        vm.animal.observe(this, Observer {
            Glide.with(this)
                    .load(it)
                    .into(imageView)
                    .onLoadFailed(resources.getDrawable21(R.drawable.ic_error_black_24dp))
        })
        vm.error.observe(this, Observer {
            Toast.makeText(this, it, LENGTH_LONG).show()
        })
        fab.setOnClickListener {
            vm.fetch()
        }
    }

}

private fun Resources.getDrawable21(i: Int): Drawable? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getDrawable(i, null)
    }
    else {
        getDrawable(i)
    }

