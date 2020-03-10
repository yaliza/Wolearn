package by.wolearn.learning.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.utils.Snackbar
import by.wolearn.core.utils.show
import by.wolearn.learning.ui.adapters.WordCardAdapter
import by.wolearn.learning.ui.entities.WordItem
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import kotlinx.android.synthetic.main.fragment_learning.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LearningFragment : Fragment(R.layout.fragment_learning), WordCardAdapter.WordCardListener,
    CardStackListener {

    val textToSpeech by inject<TextToSpeech>()
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
    override fun onMemorizeWord(wordItem: WordItem) = model.saveWord(wordItem, Direction.Right)
    override fun onUnmemorizeWord(wordItem: WordItem) = model.saveWord(wordItem, Direction.Left)
    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardRewound() {}
    override fun onCardSwiped(direction: Direction?) {}
    override fun onCardDisappeared(view: View?, position: Int) =
        model.saveWord(adapter.items[position], manager.cardStackState.direction)

    override fun onPronounce(wordItem: WordItem) {
        textToSpeech.speak(wordItem.word.name, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
        model.swipeCard.observe(viewLifecycleOwner, Observer { swipeCard(it) })
    }


    private fun setupView() {
        retry.setOnClickListener { model.loadWords() }
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
                animator.show(R.id.cardStack)
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