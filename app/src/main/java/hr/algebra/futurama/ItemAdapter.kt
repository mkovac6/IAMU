package hr.algebra.futurama

import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.futurama.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemAdapter(private val context: Context, private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        private val tvItem = itemView.findViewById<TextView>(R.id.tvItem)
        fun bind(item: Item) {
            Picasso.get()
                .load(File(item.PicUrl))
                .error(R.drawable.futurama)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvItem.text = item.Name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {

        }
        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle(R.string.delete)
                setMessage(context.getString(R.string.sure) + " '${items[position].Name}'?")
                setIcon(R.drawable.delete)
                setCancelable(true)
                setNegativeButton(R.string.cancel, null)
                setPositiveButton("Ok") { _, _ -> deleteItem(position) }

                show()
            }
            true
        }
        holder.bind(items[position])
    }

    private fun deleteItem(position: Int) {
        val item = items[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(FUTURAMA_PROVIDER_URI, item._id!!),
            null,
            null
        )
        File(item.PicUrl)
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}