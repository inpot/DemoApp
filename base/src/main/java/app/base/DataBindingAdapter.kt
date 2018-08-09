package app.base

import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.graphics.drawable.Drawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.ViewTreeObserver
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView

import app.base.di.scope.ListType
import app.base.widget.OnTextChanged
import com.bumptech.glide.Glide


@android.databinding.BindingAdapter(value = ["normalTitleColor", "selectedTitleColor"], requireAll = true)
fun bindTabLayoutTextColor(tabLayout: TabLayout, normalTitleColor: Int, selectedTitleColor: Int) {
    tabLayout.setTabTextColors(normalTitleColor, selectedTitleColor)
}

@android.databinding.BindingAdapter(value = ["android:checked", "onCheckedChangeListener"])
fun bindCheckedState(compoundButton: CompoundButton, checked: Boolean, onCheckedChangeListener: CompoundButton.OnCheckedChangeListener) {
    compoundButton.setOnCheckedChangeListener(null)
    compoundButton.isChecked = checked
    compoundButton.setOnCheckedChangeListener(onCheckedChangeListener)
}

@android.databinding.BindingAdapter(value = ["viewPager", "adapter"])
fun bindTabLayoutToViewPager(tabLayout: TabLayout, viewPagerId: Int, pagerAdapter: PagerAdapter) {
    val viewPager = tabLayout.rootView.findViewById<View>(viewPagerId) as ViewPager
    if (viewPager.adapter == null)
        viewPager.adapter = pagerAdapter
    tabLayout.setupWithViewPager(viewPager)
}

@android.databinding.BindingAdapter(value = ["isDrawerOpen", "drawerGravity"])
fun controlDrawer(drawerLayout: DrawerLayout, isDrawerOpen: Boolean, gravity: Int) {
    if (isDrawerOpen) {
        drawerLayout.openDrawer(gravity)
    } else {
        drawerLayout.closeDrawer(gravity)
    }
}


@android.databinding.BindingAdapter(value = ["blurImageUrl", "placeHolder"])
fun loadImageByUrlBlur(imageView: ImageView, url: String, placeHolder: Drawable) {
    if (TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
                .load(placeHolder)
                .into(imageView)
        return
    }
    Glide.with(imageView.context)
            .load(url)
            .into(imageView)
}

@android.databinding.BindingAdapter(value = ["blurImageUrl", "placeHolder"])
fun loadImageByUrlBlur(imageView: ImageView, url: String, placeHolderRes: Int) {
    if (TextUtils.isEmpty(url)) {
        Glide.with(imageView.context)
                .load(placeHolderRes)
                .into(imageView)
        return
    }
    Glide.with(imageView.context)
            .load(url)
            .into(imageView)
}

@android.databinding.BindingAdapter(value = ["imgUrl", "placeHolder", "showFade"], requireAll = false)
fun loadImageByPath(imageView: ImageView, imgurl: String, placeHolder: Drawable, showFade: Boolean) {
    Glide.with(imageView.context).clear(imageView)
    if (TextUtils.isEmpty(imgurl)) {
        imageView.setImageDrawable(placeHolder)
        return
    }
    if (showFade) {
        Glide.with(imageView.context).load(imgurl).into(imageView)
    } else {
        Glide.with(imageView.context).load(imgurl).into(imageView)
    }
}

@android.databinding.BindingAdapter(value = ["android:src"])
fun setImageResource(imageView: ImageView, res: Int) {
    imageView.setImageResource(res)
}

@android.databinding.BindingAdapter(value = ["android:drawableRight"])
fun setDrawableRight(textView: TextView, res: Int) {
    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, res, 0)
}

@android.databinding.BindingAdapter(value = ["textRes", "textColorRes"], requireAll = false)
fun setTextRes(textView: TextView, textRes: Int, textColorRes: Int) {
    if (textRes > 0)
        textView.setText(textRes)
    if (textColorRes > 0)
        textView.setTextColor(textView.resources.getColor(textColorRes))
}

@android.databinding.BindingAdapter(value = ["onTextChanged"])
fun bindTextChangedListener(editText: EditText, onTextChanged: OnTextChanged) {
    editText.onTextChange { onTextChanged.onTextChanged(it) }
}

@android.databinding.BindingAdapter(value = ["expand", "withAnim"])
fun setAppBarLayoutExpended(appBarLayout: AppBarLayout, expand: Boolean, withAnim: Boolean) {
    appBarLayout.setExpanded(expand, withAnim)
}

@android.databinding.BindingAdapter(value = ["layoutManager", "adapter", "onScrollListener"])
fun bindLoadMoreRecyclerView(recyclerView: RecyclerView,
                             layoutManager: RecyclerView.LayoutManager,
                             adapter: RecyclerView.Adapter<*>,
                             onScrollListener: RecyclerView.OnScrollListener) {
    recyclerView.adapter = adapter
    recyclerView.layoutManager = layoutManager
    recyclerView.addOnScrollListener(onScrollListener)
}

@android.databinding.BindingAdapter(value = ["showPassword"])
fun bindEditTextInputType(editText: EditText, showPassword: Boolean) {
    editText.inputType = if (showPassword) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
    editText.setSelection(if (editText.text == null) 0 else editText.text.length)
}

@android.databinding.BindingAdapter(value = "orientation")
fun bindRecyclerView(recyclerView: RecyclerView, orientation: String) {
    var ori = if (TextUtils.equals(orientation, ListType.HORIZONTAL)) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL
    when (orientation) {
        ListType.HORIZONTAL -> ori = RecyclerView.HORIZONTAL
        ListType.VERTICAL -> ori = RecyclerView.VERTICAL
        else -> ori = RecyclerView.HORIZONTAL
    }
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, ori, false)
}

@android.databinding.BindingAdapter(value = "requestFocus")
fun bindRequestFocusEvent(editText: EditText, requestFocus: Boolean) {
    if (requestFocus) {
        editText.requestFocusFromTouch()
    } else {
        editText.clearFocus()
    }
}

/**
 * two way binding for refreshing attr
 *
 *
 */
@android.databinding.BindingAdapter(value = ["refreshing", "refreshListener", "refreshingAttrChanged"], requireAll = false)
fun bindSwipRefreshingState(swipeRefreshLayout: SwipeRefreshLayout, refreshing: Boolean, onRefreshListener: SwipeRefreshLayout.OnRefreshListener?, bindingListener: InverseBindingListener) {
    swipeRefreshLayout.isRefreshing = refreshing
    swipeRefreshLayout.setOnRefreshListener {
        onRefreshListener?.onRefresh()
        bindingListener.onChange()

    }
}

@InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
fun bindingIsRefresing(swipeRefreshLayout: SwipeRefreshLayout): Boolean {
    return swipeRefreshLayout.isRefreshing
}

@android.databinding.BindingAdapter(value = ["drawerVisible"])
fun toggleDrawer(drawerLayout: DrawerLayout, drawerVisible: Boolean) {
    if (drawerVisible) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            return
        drawerLayout.openDrawer(GravityCompat.START, true)
    } else {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START))
            return
        drawerLayout.closeDrawer(GravityCompat.START, true)
    }
}

@android.databinding.BindingAdapter(value = ["icon", "msg"])
fun bindEmptyViewDrawable(textView: TextView, iconRes: Int, msgRes: Int) {
    if (iconRes != 0)
        textView.setCompoundDrawablesWithIntrinsicBounds(0, iconRes, 0, 0)
    if (msgRes != 0)
        textView.setText(msgRes)
}


@android.databinding.BindingAdapter(value = ["globalLayoutListener"])
fun bindOnGlobalLayoutListener(view: View, onGlobalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener) {
    view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
}

@android.databinding.BindingAdapter(value = ["colorSchemeResources"])
fun bingSwipRefeshSchemaColor(refreshLayout: SwipeRefreshLayout, colorSchemeResources: Int) {
    val colors = intArrayOf(colorSchemeResources)
    refreshLayout.setColorSchemeColors(*colors)
}


@android.databinding.BindingAdapter(value = ["adapter"])
fun bingGridView(gridView: GridView, adapter: ListAdapter) {
    gridView.adapter = adapter
    adapter.apply {  }
}

fun EditText.onTextChange(body :(txt:String?) -> Unit){
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            body.invoke(s?.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })


}