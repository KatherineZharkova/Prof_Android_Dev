package ru.cocovella.prof_android_dev.view.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*
import ru.cocovella.prof_android_dev.R
import ru.cocovella.prof_android_dev.model.data.SearchResult


class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<SearchResult>
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<SearchResult>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: SearchResult) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                val text = data.text
                val imageUrl = data.meanings?.get(0)?.imageUrl
                val translation = data.meanings?.get(0)?.translation?.text
//                Log.e("ImageURL", "$text ~ $translation ~ $imageUrl")

                itemView.word_recycler_item.text = text
                itemView.translation_recycler_item.text = translation
                if(imageUrl != null) {
                    itemView.word_image.visibility = VISIBLE
//                    Picasso.with(itemView.context)
//                        .load(imageUrl)
//                        .centerCrop()
//                        .placeholder(R.drawable.ic_delete_text)
//                        .error(R.drawable.ic_launcher_background)
//                        .into(itemView.word_image)
                }
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: SearchResult) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: SearchResult)
    }
}
