package id.modana.com.adapter

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.modana.com.R
import id.modana.com.model.MenuList
import kotlinx.android.synthetic.main.item_layout_menu.view.*
import java.util.*

class MenuListAdapter(context: Context, private val images: ArrayList<MenuList>) :
    RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {

    var mContext: Context = context
    private val onClickListener: View.OnClickListener
    private lateinit var menuClickListener: OnMenuClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val position = v.tag as Int
            menuClickListener.onChannelClick(position)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.imageView.setImageDrawable(ResourcesCompat.getDrawable(mContext.resources, item.icon, null))
        holder.titleView.text = item.title

        with(holder.imageView) {
            tag = position
            setOnClickListener(onClickListener)
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.thumbnail
        val titleView: TextView = view.title
    }

    fun setOnMenuClickListener(listener: OnMenuClickListener) {
        menuClickListener = listener
    }

    interface OnMenuClickListener {
        fun onChannelClick(position: Int)
    }


}