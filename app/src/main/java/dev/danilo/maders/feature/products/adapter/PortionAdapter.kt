package dev.danilo.maders.feature.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.danilo.maders.R
import dev.danilo.maders.databinding.AdapterPortionBinding
import dev.danilo.maders.model.Portion

class PortionAdapter(
    private val list: List<Portion>,
    private val onPortionClicked: OnPortionClicked
) :
    RecyclerView.Adapter<PortionAdapter.PortionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_portion, parent, false)
        return PortionViewHolder(view)
    }

    inner class PortionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AdapterPortionBinding.bind(view)
    }

    override fun onBindViewHolder(holder: PortionViewHolder, position: Int) {
        val portion = list[position]
        holder.binding.run {
            tvPortion.text = portion.name
            tvPrice.text = portion.price.toString()
            Glide.with(root.context)
                .load(portion.imageUrl)
                .into(ivPortion)
            root.setOnClickListener {
                onPortionClicked.onPortionClicked(portion)
            }
        }
    }

    override fun getItemCount() = list.size
}
