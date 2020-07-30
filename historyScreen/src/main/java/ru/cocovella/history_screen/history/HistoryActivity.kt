package ru.cocovella.history_screen.history

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.scope.currentScope
import ru.cocovella.history_screen.R
import ru.cocovella.history_screen.loadModules
import ru.cocovella.prof_android_dev.utils.ui.viewById
import ru.cocovella.prof_android_dev.view.base.BaseActivity
import ru.cocovella.repo.model.data.AppState
import ru.cocovella.repo.model.data.userdata.DataModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override val layoutRes = R.layout.activity_history
    private val historyRecyclerView by viewById<RecyclerView>(R.id.history_activity_recyclerview)
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }
    private val onListItemClickListener = object : HistoryAdapter.OnListItemClickListener {
        override fun onItemClick(data: DataModel) {
            Toast.makeText(this@HistoryActivity,
                "on click: ${data.text}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        check (historyRecyclerView.adapter == null) { "The ViewModel should be initialised first" }
        loadModules()
        val viewModel: HistoryViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        historyRecyclerView.adapter = adapter
    }

}
