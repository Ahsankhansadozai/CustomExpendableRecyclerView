/*
* Sardar Ahsan Khan
* 3/8/22
*
* */

package com.example.expendedrecycler.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expendedrecycler.databinding.ChildViewBinding
import com.example.expendedrecycler.main.models.ChildModel
import com.example.expendedrecycler.main.viewholder.ChildViewHolder

class ChildAdapter(
    private var hChildView: Boolean = false,
    val hAdapterCallback: (Int, Boolean, Int) -> Unit,
) : RecyclerView.Adapter<ChildViewHolder>() {
    private var hParentId: Int = 0
    private var hChildList = listOf<ChildModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(
            ChildViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val hData = hChildList[position]
        holder.binding.apply {
            hChildName.text = hData.hChildName
            hCheckBox.isChecked = hData.hIsSelected


            if (!hChildView) {
                hNextIcon.visibility = View.GONE
                hCheckBox.visibility = View.VISIBLE
            } else {
                hNextIcon.visibility = View.VISIBLE
                hCheckBox.visibility = View.GONE

            }

            hCheckBox.setOnCheckedChangeListener { _, isChecked ->
                hAdapterCallback(position, isChecked, hParentId)
            }
        }
    }

    override fun getItemCount(): Int {
        return hChildList.size
    }


    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun hSetData(list: List<ChildModel>, position: Int) {
        hChildList = list
        hParentId = position
        notifyDataSetChanged()

    }


}