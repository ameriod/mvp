package me.ameriod.lib.mvp.app;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewEndlessScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    private static final int VISIBLE_THRESHOLD = 10;
    private OnScrollToEndListener listener;

    private LinearLayoutManager linearLayoutManager;

    public RecyclerViewEndlessScrollListener(@NonNull LinearLayoutManager linearLayoutManager,
                                             @NonNull OnScrollToEndListener listener) {
        this.linearLayoutManager = linearLayoutManager;
        this.listener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            listener.onScrolledToEnd();
            loading = true;
        }
    }

    public interface OnScrollToEndListener {
        void onScrolledToEnd();
    }

}
