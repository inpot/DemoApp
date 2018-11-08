package ${escapeKotlinIdentifiers(packageName)}

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

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