package io.github.louistsaitszho.louisdailytasklist

interface PageRepository {
    fun getSize(): Int
    fun getPageByIndex(index: Int): Page
}

class PageRepositoryImpl : PageRepository {
    private val pageList = listOf(
        Page(Task.State.NOT_DONE),
        Page(Task.State.IN_PROGRESS),
        Page(Task.State.DONE)
    )

    override fun getSize(): Int = pageList.size

    override fun getPageByIndex(index: Int): Page = pageList[index]
}