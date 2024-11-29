package com.ng.ngcomposecode.service

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmField

@Serializable
data class BasicBean<T>(
	var data: T?,
	var errorCode: Int,
	var errorMsg: String
)

@Serializable
data class NaviWrapper(
	var articles: MutableList<Article>?,
	var cid: Int,
	var name: String?
)

@Serializable
data class Article(
	@JvmField var adminAdd: Boolean = false,
	var apkLink: String? = "",
	var audit: Int = -1,
	var author: String? = "作者",
	var canEdit: Boolean = false,
	var chapterId: Int = -1,
	var chapterName: String? = "章节",
	var collect: Boolean = false,
	var courseId: Int = -1,
	var desc: String? = "描述",
	var descMd: String? = "描述Md",
	var envelopePic: String? = "图片1",
	var fresh: Boolean = false,
	var host: String? = "https://www.wanandroid.com",
	var id: Int = -1,
	@JvmField var isAdminAdd: Boolean = false,
	var link: String? = "https://www.wanandroid.com",
	var niceDate: String? = "1970-0-0",
	var niceShareDate: String? = "1970-0-0",
	var origin: String? = "",
	var prefix: String? = "",
	var projectLink: String? = "https://www.wanandroid.com",
	var publishTime: Long = 0L,
	var realSuperChapterId: Int = -1,
	var selfVisible: Int = -1,
	var shareDate: String? = "",
	var shareUser: String? = "分享者",
	var superChapterId: Int = -1,
	var superChapterName: String? = "超级分类",
	var tags: MutableList<ArticleTag>? = null,
	var title: String? = "标题",
	var type: Int = -1,
	var userId: Int = -1,
	var visible: Int = -1,
	var zan: Int = -1
)

@Serializable
data class ArticleTag(
	var name: String,
	var url: String
)

