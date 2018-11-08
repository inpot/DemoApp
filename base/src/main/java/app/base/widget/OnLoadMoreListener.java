package app.base.widget;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static app.base.widget.OnLoadMoreListener.ScrollType.SCROLL_DOWN;
import static app.base.widget.OnLoadMoreListener.ScrollType.SCROLL_UP;

public class OnLoadMoreListener extends RecyclerView.OnScrollListener {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SCROLL_UP, SCROLL_DOWN})
    public @interface ScrollType {
        int SCROLL_UP = 0;
        int SCROLL_DOWN = 1;
    }

    private LinearLayoutManager layoutManager;
    private OnScrollListener onScrollListener;
    private OnItemSwitch onItemSwitch;
    private ILoadMore onLoadMore;

    private int lastPosition;

    public OnLoadMoreListener(LinearLayoutManager layoutManager, ILoadMore onLoadMore) {
        this.layoutManager = layoutManager;
        this.onLoadMore = onLoadMore;
    }

    public void setOnItemSwitch(OnItemSwitch onItemSwitch) {
        this.onItemSwitch = onItemSwitch;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (onScrollListener != null)
            onScrollListener.onScrolled(recyclerView, dx, dy);
        final int totalItemCount = layoutManager.getItemCount();
        final int pastItemCount = layoutManager.findFirstVisibleItemPosition();
        final int visibleItemCount = layoutManager.getChildCount();
        final int currentLastPosition = layoutManager.findLastVisibleItemPosition();
        if (lastPosition != currentLastPosition) {
            int scrollType = lastPosition > currentLastPosition ? SCROLL_UP : SCROLL_DOWN;
            if (onItemSwitch != null)
                onItemSwitch.onItemSwitch(lastPosition, currentLastPosition, scrollType);
            lastPosition = currentLastPosition;
        }
        if ((visibleItemCount + pastItemCount) >= totalItemCount) {
            if (onLoadMore != null) {
                onLoadMore.onLoadMore();
            }
        }
    }
}
