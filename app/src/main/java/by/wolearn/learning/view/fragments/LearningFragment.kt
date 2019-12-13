package by.wolearn.learning.view.fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.learning.view.adapters.WordCardAdapter
import by.wolearn.learning.view.adapters.WordCardListener
import by.wolearn.learning.view.entities.WordItem
import by.wolearn.learning.viewmodel.LearningViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import kotlinx.android.synthetic.main.fragment_learning.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LearningFragment : Fragment(R.layout.fragment_learning), CardStackListener {

    val args: LearningFragmentArgs by navArgs()
    val textToSpeech by inject<TextToSpeech>()
    private lateinit var manager: CardStackLayoutManager
    private lateinit var adapter: WordCardAdapter
    private val wordCardListener = object : WordCardListener {
        override fun onPronounce(wordItem: WordItem) {
            textToSpeech.speak(wordItem.word.name, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        override fun onCloseWord() {
            mainNavController.popBackStack()
        }

        override fun onMemorizeWord(wordItem: WordItem) {
            swipeAndSave(wordItem, Direction.Right)
        }

        override fun onUnmemorizeWord(wordItem: WordItem) {
            swipeAndSave(wordItem, Direction.Left)
        }
    }

    val model: LearningViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.isRepeatingMode = args.isRepeating
        model.loadWords()
        adapter = WordCardAdapter(wordCardListener)

        manager = CardStackLayoutManager(activity, this)
        manager.setVisibleCount(2)

        cardStack.adapter = adapter
        cardStack.layoutManager = manager

        model.words.observe(viewLifecycleOwner, object : ResourceObserver<List<WordItem>>() {
            override fun onLoad() = showProgress(true)
            override fun onSuccess(data: List<WordItem>?) = data?.let { adapter.items = data }
            override fun onError(error: Resource.Error<List<WordItem>>) {
                showError(error)
                mainNavController.popBackStack()
            }
        })
    }

    override fun onCardAppeared(view: View?, position: Int) {}
    override fun onCardRewound() {}
    override fun onCardSwiped(direction: Direction?) {}
    override fun onCardDisappeared(view: View?, position: Int) {
        model.saveWord(adapter.items[position], manager.cardStackState.direction)
    }

    override fun onCardCanceled() {
        updateWordState(null)
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        updateWordState(direction)
    }

    private fun updateWordState(direction: Direction?) {
        val viewHolder = cardStack.findViewHolderForAdapterPosition(manager.topPosition)
        adapter.updateWordCardButtons(viewHolder, direction)
    }

    private fun swipeAndSave(wordItem: WordItem, direction: Direction) {
        val setting = SwipeAnimationSetting.Builder().setDirection(direction).build()
        manager.setSwipeAnimationSetting(setting)
        cardStack.swipe()
        model.saveWord(wordItem, direction)
    }

}