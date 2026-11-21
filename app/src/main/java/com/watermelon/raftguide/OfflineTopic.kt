package com.watermelon.raftguide

/**
 * Offline assistant search topics are embedded in the APK.
 * Keep answers conservative: when exact recipe quantities are not verified,
 * direct users to the in-game recipe card rather than inventing amounts.
 */
internal data class OfflineTopic(
    val title: String,
    val answer: String,
    val keywords: List<String>
)
