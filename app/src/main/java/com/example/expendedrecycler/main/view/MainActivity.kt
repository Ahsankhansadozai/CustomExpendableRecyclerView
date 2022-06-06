package com.example.expendedrecycler.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expendedrecycler.databinding.ActivityMainBinding
import com.example.expendedrecycler.main.adapter.ParentAdapter
import com.example.expendedrecycler.main.models.ChildModel
import com.example.expendedrecycler.main.models.ParentModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var hParentAdapter: ParentAdapter
    private lateinit var hParentList: ArrayList<ParentModel>
    private lateinit var hChildList: ArrayList<ChildModel>
    private lateinit var hChildList1: ArrayList<ChildModel>
    private lateinit var hChildList2: ArrayList<ChildModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        hParentList = ArrayList()
        hChildList = ArrayList()
        hChildList1 = ArrayList()
        hChildList2 = ArrayList()


        hPopulateArrayList()

        hSetUpRecycler()

        hOnClickListener()

        setContentView(binding.root)

    }

    private fun hOnClickListener() {
        binding.apply {
            hEnableClick.setOnClickListener {

                hViewCheckBox(true)

            }
        }
    }

    private fun hViewCheckBox(b: Boolean) {

        hParentAdapter.hChangeChildView(b)

    }

    private fun hPopulateArrayList() {

        hChildList.add(ChildModel("111", "Ahsan", false))
        hChildList.add(ChildModel("222", "Khan", true))
        hChildList.add(ChildModel("333", "Sadozai", false))

        hChildList1.add(ChildModel("111", "Ahmed", false))
        hChildList1.add(ChildModel("222", "Ali", true))

        hChildList2.add(ChildModel("111", "Muhammad", false))
        hChildList2.add(ChildModel("222", "Salman", true))


        hParentList.add(
            ParentModel(
                "1", "Ahsan", false, hChildList
            )
        )


        hParentList.add(
            ParentModel(
                "1", "Khan", false, hChildList1
            )
        )


        hParentList.add(
            ParentModel(
                "1", "Sadozai", false, hChildList2
            )
        )


        Timber.d("hParent List Size : ${hParentList.size}")

    }

    private fun hSetUpRecycler() {
        hParentAdapter = ParentAdapter(this) { hParentPosition, isSelected, hChildPosition ->
            //do some thing with this callback


            //hChild -1 means click on parent recycler 
            if (hChildPosition == -1) {
                hParentList[hParentPosition].hIsExpended = !hParentList[hParentPosition].hIsExpended
                hParentAdapter.hSetData(hParentList)
                binding.hMainRecycler.smoothScrollToPosition(hParentAdapter.itemCount - 1)// move recycler to bottom
            } else {

                Timber.d("Parent : $hParentPosition  child : $hChildPosition isSelected $isSelected")

                hParentList[hParentPosition].hChildList[hChildPosition].hIsSelected = isSelected

                hParentAdapter.hSetData(hParentList)


            }

        }
        binding.hMainRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = hParentAdapter
        }

        hParentList.let { hParentAdapter.hSetData(it) }

    }


}