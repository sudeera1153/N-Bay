package com.takg.nbay.domain.model

enum class ItemCondition(val value: String) {
    NEW("New"),
    GOOD_AS_NEW("As good as new"),
    GOOD("Good Condition"),
    FAIR("Fair Condition"),
    NEED_REPAIR("Need Repairs"),
    WORST("Worst Condition")
}