package by.wolearn.addword.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.addword.ui.adapter.AddWordController
import by.wolearn.addword.ui.entities.Item
import by.wolearn.chooser.ui.CategoryChooserFragment
import by.wolearn.core.extensions.mainNavController
import by.wolearn.core.ui.entities.Category
import by.wolearn.core.ui.show
import kotlinx.android.synthetic.main.fragment_add_word.*
import org.koin.android.viewmodel.ext.android.viewModel


class AddWordFragment : Fragment(R.layout.fragment_add_word), AddWordController.Listener {

    private val model: AddWordViewModel by viewModel()

    private lateinit var controller: AddWordController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupViewModel()
        setupNavigationResult()
    }

    override fun onExampleDelete(example: Item.Example) = model.onDeleteExampleClick(example)
    override fun onExampleChanged(example: Item.Example, newValue: String) =
        model.onExampleChanged(example, newValue)

    override fun onAddExampleClick() = model.onAddExampleClick()
    override fun onInputChanged(input: Item.Input, newValue: String) =
        model.onInputChanged(input, newValue)

    override fun onQuizOptionChanged(quizOption: Item.QuizOption, newValue: String) =
        model.onQuizOptionChanged(quizOption, newValue)

    override fun onSubmitClick() = model.onSubmitClick()
    override fun onSelected(selectItem: Item.Select, value: String) =
        model.onSelected(selectItem, value)

    override fun onCategoryClick(categoryItem: Item.Category) {
        mainNavController.navigate(R.id.action_addWordFragment_to_categoryChooserFragment)
    }

    private fun setupRecycler() {
        controller = AddWordController(this)
        content.adapter = controller.adapter
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun setupNavigationResult() {
        mainNavController.currentBackStackEntry?.savedStateHandle?.getLiveData<Category>(
            CategoryChooserFragment.KEY_RESULT
        )?.observe(viewLifecycleOwner, Observer {
            model.onCategoryChoosed(it)
        })
    }

    private fun showState(state: AddWordViewModel.State) {
        when (state) {
            is AddWordViewModel.State.Data -> {
                animator.show(R.id.content)
                controller.items = state.items
            }
            AddWordViewModel.State.Success -> mainNavController.popBackStack()
            AddWordViewModel.State.Error -> animator.show(R.id.error)
            AddWordViewModel.State.Progress -> animator.show(R.id.progress)
        }
    }

}