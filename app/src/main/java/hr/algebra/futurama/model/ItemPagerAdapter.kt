package hr.algebra.futurama.model

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.futurama.FUTURAMA_PROVIDER_URI
import hr.algebra.futurama.R
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemPagerAdapter(private val context: Context, private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        val ivRead = itemView.findViewById<ImageView>(R.id.ivRed)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvSpecies = itemView.findViewById<TextView>(R.id.tvSpecies)
        private val tvAge = itemView.findViewById<TextView>(R.id.tvAge)
        private val tvPlanet = itemView.findViewById<TextView>(R.id.tvPlanet)
        fun bind(item: Item) {
            Picasso.get()
                .load(File(item.PicUrl))
                .error(R.drawable.futurama)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
            tvName.text = item.Name
            tvSpecies.text = item.Species
            tvAge.text = item.Age
            tvPlanet.text = item.Planet
            ivRead.setImageResource(if (item.read) R.drawable.green_flag else R.drawable.red_flag)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pager, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.ivRead.setOnClickListener {
            item.read = !item.read
            val uri = ContentUris.withAppendedId(FUTURAMA_PROVIDER_URI, item._id!!)
            val values = ContentValues().apply {
                put(Item::read.name, item.read)
            }
            context.contentResolver.update(
                uri,
                values,
                null,
                null
            )
            notifyItemChanged(position)
        }
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}