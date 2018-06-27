package cn.quickits.arch.sample.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import cn.quickits.arch.mvvm.QLceViewFragment

class LecFragment : QLceViewFragment<Long, UserLceViewModel, Chronometer>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lce, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorView.setOnClickListener {
            viewModel.load(false)
        }
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