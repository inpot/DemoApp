package app.base;

import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import app.base.di.scope.ListType;
import app.base.widget.OnTextChanged;

import static com.bumptech.glide.Glide.with;

public final class BindingAdapter {

    @android.databinding.BindingAdapter(value = {"normalTitleColor", "selectedTitleColor"}, requireAll = true)
    public static void bindTabLayoutTextColor(TabLayout tabLayout, int normalTitleColor, int selectedTitleColor) {
        tabLayout.setTabTextColors(normalTitleColor, selectedTitleColor);
    }

    @android.databinding.BindingAdapter(value = {"android:checked", "onCheckedChangeListener"})
    public static void bindCheckedState(CompoundButton compoundButton, boolean checked, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        compoundButton.setOnCheckedChangeListener(null);
        compoundButton.setChecked(checked);
        compoundButton.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @android.databinding.BindingAdapter(value = {"viewPager", "adapter"})
    public static void bindTabLayoutToViewPager(TabLayout tabLayout, int viewPagerId, PagerAdapter pagerAdapter) {
        ViewPager viewPager = (ViewPager) tabLayout.getRootView().findViewById(viewPagerId);
        if (viewPager.getAdapter() == null)
            viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @android.databinding.BindingAdapter(value = {"isDrawerOpen", "drawerGravity"})
    public static void controlDrawer(DrawerLayout drawerLayout, boolean isDrawerOpen, int gravity) {
        if (isDrawerOpen) {
            drawerLayout.openDrawer(gravity);
        } else {
            drawerLayout.closeDrawer(gravity);
        }
    }


    @android.databinding.BindingAdapter(value = {"blurImageUrl", "placeHolder"})
    public static void loadImageByUrlBlur(ImageView imageView, String url, Drawable placeHolder) {
        if (TextUtils.isEmpty(url)) {
            with(imageView.getContext())
                    .load(placeHolder)
                    .into(imageView);
            return;
        }
        with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @android.databinding.BindingAdapter(value = {"blurImageUrl", "placeHolder"})
    public static void loadImageByUrlBlur(ImageView imageView, String url, int placeHolderRes) {
        if (TextUtils.isEmpty(url)) {
            with(imageView.getContext())
                    .load(placeHolderRes)
                    .into(imageView);
            return;
        }
        with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @android.databinding.BindingAdapter(value = {"imgUrl", "placeHolder", "showFade"}, requireAll = false)
    public static void loadImageByPath(ImageView imageView, String imgurl, Drawable placeHolder, boolean showFade) {
        with(imageView.getContext()).clear(imageView);
        if (TextUtils.isEmpty(imgurl)) {
            imageView.setImageDrawable(placeHolder);
            return;
        }
        if (showFade) {
            with(imageView.getContext()).load(imgurl).into(imageView);
        } else {
            with(imageView.getContext()).load(imgurl).into(imageView);
        }
    }

    @android.databinding.BindingAdapter(value = {"android:src"})
    public static void setImageResource(ImageView imageView, int res) {
        imageView.setImageResource(res);
    }

    @android.databinding.BindingAdapter(value = {"android:drawableRight"})
    public static void setDrawableRight(TextView textView, int res) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, res, 0);
    }

    @android.databinding.BindingAdapter(value = {"textRes", "textColorRes"}, requireAll = false)
    public static void setTextRes(TextView textView, int textRes, int textColorRes) {
        if (textRes > 0)
            textView.setText(textRes);
        if (textColorRes > 0)
            textView.setTextColor(textView.getResources().getColor(textColorRes));
    }

    @android.databinding.BindingAdapter(value = {"onTextChanged"})
    public static void bindTextChangedListener(final EditText editText, final OnTextChanged onTextChanged) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onTextChanged.onTextChanged(s.toString());
            }
        });
    }

    @android.databinding.BindingAdapter(value = {"expand", "withAnim"})
    public static void setAppBarLayoutExpended(AppBarLayout appBarLayout, boolean expand, boolean withAnim) {
        appBarLayout.setExpanded(expand, withAnim);
    }

    @android.databinding.BindingAdapter(value = {"layoutManager", "adapter", "onScrollListener"} )
    public static void bindLoadMoreRecyclerView(RecyclerView recyclerView,
                                                RecyclerView.LayoutManager layoutManager,
                                                RecyclerView.Adapter adapter,
                                                RecyclerView.OnScrollListener onScrollListener) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @android.databinding.BindingAdapter(value = {"showPassword"})
    public static void bindEditTextInputType(EditText editText, boolean showPassword) {
        editText.setInputType(showPassword ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        editText.setSelection(editText.getText() == null ? 0 : editText.getText().length());
    }

    @android.databinding.BindingAdapter(value = "orientation")
    public static void bindRecyclerView(RecyclerView recyclerView, String orientation) {
        int ori = TextUtils.equals(orientation, ListType.HORIZONTAL) ? RecyclerView.HORIZONTAL: RecyclerView.VERTICAL;
        switch (orientation) {
            default:
            case ListType.HORIZONTAL:
                ori = RecyclerView.HORIZONTAL;
                break;
            case ListType.VERTICAL:
                ori = RecyclerView.VERTICAL;
                break;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), ori, false));
    }

    @android.databinding.BindingAdapter(value = "requestFocus")
    public static void bindRequestFocusEvent(EditText editText, boolean requestFocus) {
        if (requestFocus) {
            editText.requestFocusFromTouch();
        } else {
            editText.clearFocus();
        }
    }

    /**
     * two way binding for refreshing attr
     *
     *
     */
    @android.databinding.BindingAdapter(value = {"refreshing", "refreshListener","refreshingAttrChanged"}, requireAll = false)
    public static void bindSwipRefreshingState(SwipeRefreshLayout swipeRefreshLayout, boolean refreshing, SwipeRefreshLayout.OnRefreshListener onRefreshListener,InverseBindingListener bindingListener) {
        swipeRefreshLayout.setRefreshing(refreshing);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if(onRefreshListener!=null){
                onRefreshListener.onRefresh();
            }
            bindingListener.onChange();

        });
    }

    @InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
    public static boolean bindingIsRefresing(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout.isRefreshing();
    }

    @android.databinding.BindingAdapter(value = {"drawerVisible"})
    public static void toggleDrawer(DrawerLayout drawerLayout, boolean drawerVisible) {
        if (drawerVisible) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                return;
            drawerLayout.openDrawer(GravityCompat.START, true);
        } else {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                return;
            drawerLayout.closeDrawer(GravityCompat.START, true);
        }
    }

    @android.databinding.BindingAdapter(value = {"icon", "msg"})
    public static void bindEmptyViewDrawable(TextView textView, int iconRes, int msgRes) {
        if (iconRes != 0)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, iconRes, 0, 0);
        if (msgRes != 0)
            textView.setText(msgRes);
    }


    @android.databinding.BindingAdapter(value = {"globalLayoutListener"})
    public static void bindOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    @android.databinding.BindingAdapter(value = {"colorSchemeResources"})
    public static void bingSwipRefeshSchemaColor(SwipeRefreshLayout refreshLayout, int colorSchemeResources) {
        int[] colors = {colorSchemeResources};
        refreshLayout.setColorSchemeColors(colors);
    }


    @android.databinding.BindingAdapter(value = {"adapter"})
    public static void bingGridView(GridView gridView, ListAdapter adapter) {
        gridView.setAdapter(adapter);
    }
}

