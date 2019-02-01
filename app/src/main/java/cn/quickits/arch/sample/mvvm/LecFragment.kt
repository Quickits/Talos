package cn.quickits.arch.sample.mvvm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Chronometer
import cn.quickits.arch.mvvm.QLceViewFragment

class LecFragment : QLceViewFragment<Long, UserLceViewModel, Chronometer>() {

    override fun bindLayoutId(): Int = R.layout.fragment_lce

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorView.setOnClickListener {
            viewModel.load(false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.load(false)
    }

    override fun createViewModel(): UserLceViewModel =
            ViewModelProviders
                    .of(activity!!)
                    .get(UserLceViewModel::class.java)

    override fun showContent(content: Long?) {
        super.showContent(content)
        content ?: return

        contentView.base = content
        contentView.start()
    }

}