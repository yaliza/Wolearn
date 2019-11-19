package by.wolearn.learning.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.learning.view.adapters.WordCardAdapter
import by.wolearn.learning.viewmodel.LearningViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.fragment_learning.*
import org.koin.android.viewmodel.ext.android.viewModel

class LearningFragment : Fragment(R.layout.fragment_learning), CardStackListener {

    private lateinit var manager: CardStackLayoutManager
    private lateinit var adapter: WordCardAdapter
    val model: LearningViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WordCardAdapter()

        manager = CardStackLayoutManager(activity, this)
        manager.setVisibleCount(2)

        cardStack.adapter = adapter
        cardStack.layoutManager = manager

        model.words.observe(viewLifecycleOwner, Observer { adapter.items = it })
    }

    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardRewound() {}
    override fun onCardSwiped(direction: Direction?) {}
    override fun onCardDisappeared(view: View?, position: Int) {
        model.saveWord(adapter.items[position])
    }

    override fun onCardCanceled() {
        updateWordState(null)
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        updateWordState(direction)
    }

    private fun updateWordState(direction: Direction?) {
        model.changeWordState(adapter.items[manager.topPosition], direction)
        val viewHolder = cardStack.findViewHolderForAdapterPosition(manager.topPosition)
        adapter.changeWordState(viewHolder, manager.topPosition)
    }

}