package dev.aaa1115910.biliapi.entity.user

import java.util.Date

data class SpaceVideo(
    val aid: Int,
    val bvid: String,
    val title: String,
    val cover: String,
    val author: String,
    val duration: Int,
    val play: Int,
    val danmaku: Int,
    val publishDate: Date
) {
    companion object {
        fun fromSpaceVideoItem(spaceVideoItem: dev.aaa1115910.biliapi.http.entity.user.WebSpaceVideoData.SpaceVideoListItem.VListItem) =
            SpaceVideo(
                aid = spaceVideoItem.aid,
                bvid = spaceVideoItem.bvid,
                title = spaceVideoItem.title,
                cover = spaceVideoItem.pic,
                author = spaceVideoItem.author,
                duration = convertMmSsToSeconds(spaceVideoItem.length),
                play = spaceVideoItem.play,
                danmaku = spaceVideoItem.videoReview,
                publishDate = Date(spaceVideoItem.created * 1000L)
            )

        fun fromSpaceVideoItem(spaceVideoItem: dev.aaa1115910.biliapi.http.entity.user.AppSpaceVideoData.SpaceVideoItem) =
            SpaceVideo(
                aid = spaceVideoItem.param.toInt(),
                bvid = spaceVideoItem.bvid,
                title = spaceVideoItem.title,
                cover = spaceVideoItem.cover,
                author = spaceVideoItem.author,
                duration = spaceVideoItem.duration,
                play = spaceVideoItem.play,
                danmaku = spaceVideoItem.danmaku,
                publishDate = Date(spaceVideoItem.ctime * 1000L)
            )
    }
}

private fun convertMmSsToSeconds(time: String): Int {
    val parts = time.split(":")
    val minutes = parts[0].toInt()
    val seconds = parts[1].toInt()
    return (minutes * 60) + seconds
}

enum class SpaceVideoOrder(val value: String) {
    PubDate("pubdate"), Click("click")
}