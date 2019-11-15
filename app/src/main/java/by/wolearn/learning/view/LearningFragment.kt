package by.wolearn.learning.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.learning.viewmodel.LearningViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.fragment_learning.*
import org.koin.android.viewmodel.ext.android.viewModel

class LearningFragment : Fragment(R.layout.fragment_learning) {

    val model: LearningViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a = object : CardStackListener {
            override fun onCardDisappeared(view: View?, position: Int) {}
            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.d("LOOOG", "dragging $direction")
            }
            override fun onCardSwiped(direction: Direction?) {
                Log.d("LOOOG", "swiped")
            }
            override fun onCardCanceled() {}
            override fun onCardAppeared(view: View?, position: Int) {}
            override fun onCardRewound() {}
        }

        val manager = CardStackLayoutManager(activity, a)
        manager.setVisibleCount(2)
        cardStack.layoutManager = manager

        val adapter = WordCardAdapter()
        cardStack.adapter = adapter
        model.words.observe(viewLifecycleOwner, Observer { adapter.items = it })
    }

}