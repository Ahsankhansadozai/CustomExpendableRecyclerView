package com.example.expendedrecycler.main.models

data class ParentModel(
    var hParentId: String,
    var hParentTitle: String,
    var hIsExpended: Boolean,
    var hChildList: ArrayList<ChildModel>
)
