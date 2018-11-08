package app.base.widget

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by daniel on 17-12-23.
 */
interface ILoadMore {
    fun onLoadMore()
}

interface IRefresh {
    fun onRefresh()
}

interface OnScrollListener {
    fun onScrollStateChanged(recyleView: RecyclerView, newState: Int);
    fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int);
}
interface OnItemSwitch{
    fun onItemSwitch(oldPosition:Int, newPosition:Int, @OnLoadMoreListener.ScrollType scrollType:Int);
}