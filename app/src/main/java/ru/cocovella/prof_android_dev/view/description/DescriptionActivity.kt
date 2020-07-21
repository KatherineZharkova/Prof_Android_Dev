package ru.cocovella.prof_android_dev.view.description

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description.*
import ru.cocovella.prof_android_dev.R
import ru.cocovella.prof_android_dev.utils.network.isOnline
import ru.cocovella.prof_android_dev.utils.ui.AlertDialogFragment

class DescriptionActivity : AppCompatActivity() {

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "8c7dff57"
        private const val WORD_EXTRA = "word_extra"
        private const val DESCRIPTION_EXTRA = "desc_extra"
        private const val URL_EXTRA = "url_extra"

        fun getIntent(context: Context, word: String, description: String, url: String?) =
            Intent(context, DescriptionActivity::class.java).apply {
                putExtra(WORD_EXTRA, word)
                putExtra(DESCRIPTION_EXTRA, description)
                putExtra(URL_EXTRA, url)
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        setActionbarHomeButtonAsUp()
        description_swipe_refresh_layout.setOnRefreshListener{ startLoadingOrShowError() }
        setData()
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startLoadingOrShowError() {
        if (isOnline(applicationContext)) {
            setData()
        } else {
            AlertDialogFragment
                .newInstance(
                    getString(R.string.dialog_title_device_is_offline),
                    getString(R.string.dialog_message_device_is_offline)
                )
                .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun setData() {
        val bundle = intent.extras
        description_header.text = bundle?.getString(WORD_EXTRA)
        description_text.text = bundle?.getString(DESCRIPTION_EXTRA)
        val imageLink = bundle?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
//            usePicassoToLoadPhoto(description_image, imageLink)
            useGlideToLoadPhoto(description_image, imageLink)
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (description_swipe_refresh_layout.isRefreshing) {
            description_swipe_refresh_layout.isRefreshing = false
        }
    }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String) {
        Picasso.with(applicationContext).load("https:$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    stopRefreshAnimationIfNeeded()
                }

                override fun onError() {
                    stopRefreshAnimationIfNeeded()
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }

    private fun useGlideToLoadPhoto(imageView: ImageView, imageLink: String) {
        Glide.with(imageView)
            .load("https:$imageLink")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshAnimationIfNeeded()
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopRefreshAnimationIfNeeded()
                    return false
                }
            })
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_no_photo_vector)
                    .centerCrop()
            )
            .into(imageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
