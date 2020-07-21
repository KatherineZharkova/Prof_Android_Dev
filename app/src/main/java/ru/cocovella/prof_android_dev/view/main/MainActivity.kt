package ru.cocovella.prof_android_dev.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.cocovella.prof_android_dev.R
import ru.cocovella.prof_android_dev.model.data.AppState
import ru.cocovella.prof_android_dev.model.data.DataModel
import ru.cocovella.prof_android_dev.utils.convertMeaningsToString
import ru.cocovella.prof_android_dev.utils.network.isOnline
import ru.cocovella.prof_android_dev.view.base.BaseActivity
import ru.cocovella.prof_android_dev.view.description.DescriptionActivity
import ru.cocovella.prof_android_dev.view.history.HistoryActivity
import ru.cocovella.prof_android_dev.view.main.adapter.MainAdapter

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74m54328"

class MainActivity : BaseActivity<AppState, MainInteractor>()  {

    override val model: MainViewModel by viewModel()
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener = View.OnClickListener {
        SearchDialogFragment.newInstance().apply {
            setOnSearchClickListener(onSearchClickListener)
            show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }
    private val onListItemClickListener = object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )

            }
        }
    private val onSearchClickListener = object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        if (main_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialized first.")
        }
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        search_fab.setOnClickListener(fabClickListener)
        main_activity_recyclerview.adapter = adapter
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
