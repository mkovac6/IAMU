package hr.algebra.futurama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.futurama.databinding.ActivityItemPagerBinding
import hr.algebra.futurama.framework.fetchItems
import hr.algebra.futurama.model.Item
import hr.algebra.futurama.model.ItemPagerAdapter

const val ITEM_POSITION = "hr.algebra.futurama.item_position"

class ItemPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemPagerBinding
    private lateinit var items: MutableList<Item>
    private var itemPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initPager() {
        items = fetchItems()
        itemPosition = intent.getIntExtra(ITEM_POSITION, 0)
        binding.viewPager.adapter = ItemPagerAdapter(this, items)
        binding.viewPager.currentItem = itemPosition
    }
}