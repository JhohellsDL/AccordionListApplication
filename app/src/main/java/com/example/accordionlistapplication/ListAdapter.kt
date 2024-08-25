package com.example.accordionlistapplication

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.example.accordionlistapplication.databinding.RowItemListBinding

class ListAdapter(
    private val items: List<ItemList>,
    private val onClickItem: (ItemList) -> Unit
) : RecyclerView.Adapter<ItemListViewHolder>() {

    private var expandedPosition: Int? = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val binding =
            RowItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
        val isExpanded = expandedPosition == position
        holder.itemView.setOnClickListener {
            handleItemClick(holder, position, isExpanded)
            onClickItem(item)
        }
        holder.expandableText.visibility = if (isExpanded) View.VISIBLE else View.GONE

    }

    private fun handleItemClick(
        holder: ItemListViewHolder,
        position: Int,
        isExpanded: Boolean
    ) {
        val previousExpandedPosition = expandedPosition
        val isSameItem = position == expandedPosition

        when {
            isExpanded || isSameItem -> {
                expandedPosition = RecyclerView.NO_POSITION
                collapseView(holder.expandableText)
                //holder.expandableText.visibility = View.GONE
                holder.imageIcon.setImageResource(R.drawable.arriba)
            }
            else -> {
                expandedPosition = position
                if (previousExpandedPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousExpandedPosition!!)
                }
                expandView(holder.expandableText)
                holder.imageIcon.setImageResource(R.drawable.abajo)
            }
        }
        notifyItemChanged(previousExpandedPosition ?: position)
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int = items.size

    private fun expandView(view: TextView) {
        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = view.measuredHeight

        view.layoutParams.height = 0
        view.visibility = View.VISIBLE

        val animator = ValueAnimator.ofInt(0, targetHeight).apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                view.layoutParams.height = animation.animatedValue as Int
                view.requestLayout()
            }
            doOnEnd {
                view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
        /*animator.addUpdateListener { animation ->
            animation.duration = 300
            animator.interpolator = AccelerateDecelerateInterpolator()
            view.layoutParams.height = animation.animatedValue as Int
            view.requestLayout()
        }*/
        animator.start()
    }
    private fun collapseView(view: TextView) {
        val initialHeight = view.measuredHeight

        val animator = ValueAnimator.ofInt(initialHeight, 0).apply {
            duration = 300
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                val value = animation.animatedValue as Int
                view.layoutParams.height = value
                view.requestLayout()
                if (value == 0) {
                    view.visibility = View.GONE
                }
            }
        }
        animator.start()
    }

}