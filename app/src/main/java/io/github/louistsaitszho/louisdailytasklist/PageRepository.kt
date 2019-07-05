package io.github.louistsaitszho.louisdailytasklist

interface PageRepository {
    fun getSize(): Int
    fun getPageByIndex(index: Int): Page
}

class PageRepositoryImpl : PageRepository {
    private val pageList = listOf(
        Page(TaskState.NOT_DONE),
        Page(TaskState.IN_PROGRESS),
        Page(TaskState.DONE)
    )

    override fun getSize(): Int = pageList.size

    override fun getPageByIndex(index: Int): Page = pageList[index]
}