/*
* Sardar Ahsan Khan
* 3/8/22
*
* */

package com.example.expendedrecycler.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expendedrecycler.databinding.ParentViewBinding
import com.example.expendedrecycler.main.models.ChildModel
import com.example.expendedrecycler.main.models.ParentModel
import com.example.expendedrecycler.main.viewholder.ParentViewHolder
import timber.log.Timber

class ParentAdapter(
    private val context: Context,
    val hAdapterCallback: (Int, Boolean, Int) -> Unit,

    ) : RecyclerView.Adapter<ParentViewHolder>() {
    private lateinit var hChildAdapter: ChildAdapter
    private var hParentList = listOf<ParentModel>()
    private var hChildView: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(
            ParentViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val hData = hParentList[position]
        holder.binding.apply {
            hParentHeading.text = hData.hParentTitle

            if (hData.hChildList.size > 0) {

                hSetUpChildRecycler(hChildRecyclerView, hData.hChildList, position)

            }

            if (hData.hIsExpended) {
                hChildView.visibility = View.VISIBLE
            } else {
                hChildView.visibility = View.GONE
            }

            hExpandLayout.setOnClickListener {
                hAdapterCallback(position, false, -1)

            }

        }


    }

    override fun getItemCount(): Int {
        return hParentList.size
    }


    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun hSetData(list: List<ParentModel>) {
        hParentList = list
        notifyDataSetChanged()

    }


    private fun hSetUpChildRecycler(
        hChildRecyclerView: RecyclerView,
        hChildList: ArrayList<ChildModel>,
        position: Int
    ) {
        hChildAdapter = ChildAdapter(hChildView) { hChildPosition, isChecked, hParentPosition ->
            //do some thing with this callback

            hAdapterCallback(hParentPosition, isChecked, hChildPosition)

            Timber.d("Parent Position : $hParentPosition   child position :$hChildPosition isChecker: $isChecked")

        }
        hChildRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hChildAdapter
        }

        hChildList.let { hChildAdapter.hSetData(hChildList, position) }

    }

    fun hChangeChildView(b: Boolean) {
        hChildView = b
        notifyDataSetChanged()
    }


}