package by.wolearn.learning.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.Snackbar
import by.wolearn.core.show
import by.wolearn.learning.ui.adapters.WordCardAdapter
import by.wolearn.learning.ui.entities.WordItem
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import kotlinx.android.synthetic.main.fragment_learning.*
import org.koin.android.viewmodel.ext.android.viewModel

class LearningFragment : Fragment(R.layout.fragment_learning), WordCardAdapter.WordCardListener,
    CardStackListener {
    private lateinit var manager: CardStackLayoutManager
    private lateinit var adapter: WordCardAdapter

    val model: LearningViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclewView()
        setupView()
    }

    override fun onCardCanceled() = updateWordState(null)
    override fun onCardDragging(direction: Direction?, ratio: Float) = updateWordState(direction)
    override fun onMemorizeWord(wordItem: WordItem) = model.swipeAndMemorizeWord(wordItem)
    override fun onUnmemorizeWord(wordItem: WordItem) = model.swipeAndForgetWord(wordItem)
    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardRewound() {}
    override fun onCardSwiped(direction: Direction?) {}
    override fun onCardDisappeared(view: View?, position: Int) {
        model.saveWord(adapter.items[position], manager.cardStackState.direction)
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
        model.swipeCard.observe(viewLifecycleOwner, Observer { swipeCard(it) })
    }

    private fun setupView() {
        view?.findViewById<Button>(R.id.retry)?.setOnClickListener { model.loadWords() }
    }

    private fun setupRecyclewView() {
        adapter = WordCardAdapter(this)
        manager = CardStackLayoutManager(activity, this)
        manager.setVisibleCount(2)
        cardStack.adapter = adapter
        cardStack.layoutManager = manager
    }

    private fun updateWordState(direction: Direction?) {
        val viewHolder = cardStack.findViewHolderForAdapterPosition(manager.topPosition)
        adapter.updateViewHolderWithDirection(viewHolder, direction)
    }

    private fun swipeCard(direction: Direction?) {
        val setting = SwipeAnimationSetting.Builder().setDirection(direction).build()
        manager.setSwipeAnimationSetting(setting)
        cardStack.swipe()
    }

    private fun showState(state: LearningViewModel.State) {
        when (state) {
            is LearningViewModel.State.Data -> {
                adapter.items = state.items
                animator.show(R.id.content)
            }
            is LearningViewModel.State.Error -> {
                Snackbar.make(view, state.message, Snackbar.LENGTH_LONG)?.show()
                animator.show(R.id.error)
            }
            LearningViewModel.State.Progress -> animator.show(R.id.progress)
            LearningViewModel.State.UnknownError -> animator.show(R.id.error)
        }
    }

}