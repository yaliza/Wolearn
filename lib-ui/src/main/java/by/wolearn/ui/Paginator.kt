package by.wolearn.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class Paginator private constructor(
    private val recyclerView: RecyclerView,
    private val listener: Pager
) : RecyclerView.OnScrollListener() {

    private val linearLayoutManager: LinearLayoutManager =
        recyclerView.layoutManager as LinearLayoutManager

    interface Pager {
        fun onNextPage()
        fun isLastPage(): Boolean
        fun shouldLoadNextPage(): Boolean
    }

    override fun onScrolled(
        recyclerView: RecyclerView,
        horizontalScrollPosition: Int,
        verticalScrollPosition: Int
    ) {
        super.onScrolled(recyclerView, horizontalScrollPosition, verticalScrollPosition)

        if (isVisibleLastItems() && listener.shouldLoadNextPage() && !listener.isLastPage()) {
            listener.onNextPage()
        }
    }

    private fun isVisibleLastItems(): Boolean {
        val currentTotalItemsCount = linearLayoutManager.itemCount
        val visibleItemsCount = linearLayoutManager.childCount
        val visibleItemsPosition = linearLayoutManager.findFirstVisibleItemPosition()
        return currentTotalItemsCount - visibleItemsCount <= visibleItemsPosition + SCROLL_OFFSET_ITEMS_COUNT
    }

    companion object {
        private const val SCROLL_OFFSET_ITEMS_COUNT = 1

        fun bind(recyclerView: RecyclerView, listener: Pager): Paginator {
            if (recyclerView.layoutManager is LinearLayoutManager) {
                val easyPaginator = Paginator(recyclerView, listener)
                recyclerView.addOnScrollListener(easyPaginator)
                return easyPaginator
            } else {
                throw IllegalStateException(
                    "Recyclerview must have " + LinearLayoutManager::class.java.simpleName + " not " +
                            (recyclerView.layoutManager?.javaClass?.simpleName
                                ?: "null Layout Manager")
                )
            }
        }

        fun unbind(recyclerView: RecyclerView, paginator: Paginator?) {
            paginator?.let {
                recyclerView.removeOnScrollListener(it)
            }
        }
    }

    fun unbind() {
        recyclerView.removeOnScrollListener(this)
    }
}