package io.github.louistsaitszho.stand_up.feature_task_list;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import timber.log.Timber;

class UniformGapItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "UniGapItemDecoration";

    private int gapInPixels = 0;

    UniformGapItemDecoration(int gapInPixels) {
        Timber.tag(TAG).d("gap in pixels = %s", gapInPixels);
        this.gapInPixels = gapInPixels;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state
    ) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.set(gapInPixels, gapInPixels, gapInPixels, gapInPixels);
        } else {
            outRect.set(gapInPixels, 0, gapInPixels, gapInPixels);
        }
    }
}
