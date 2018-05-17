package ${escapeKotlinIdentifiers(packageName)}

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class  ${moduleName?cap_first}PagerAdapter(fragmentManager: FragmentManager):FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        TODO()
    }

    override fun getCount(): Int {
        TODO()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        TODO()
    }

}