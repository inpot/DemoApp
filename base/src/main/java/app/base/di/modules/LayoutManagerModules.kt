package app.base.di.modules

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.base.di.scope.ListType
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by daniel on 17-10-18.
 */
@Module
object LayoutManagerModules {
    @JvmStatic
    @Provides
    @Named(ListType.VERTICAL)
    fun provideVerticalLayout( context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

    @JvmStatic
    @Provides
    @Named(ListType.HORIZONTAL)
    fun provideHorizontalLayout( context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

    @JvmStatic
    @Provides
    @Named(ListType.GRID)
    fun provideGridLayout( context: Context): RecyclerView.LayoutManager = GridLayoutManager(context, 5)
}