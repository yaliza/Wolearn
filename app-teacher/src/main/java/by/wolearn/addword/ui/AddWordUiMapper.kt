package by.wolearn.addword.ui

import by.wolearn.R
import by.wolearn.addword.backend.entities.PartOfSpeech
import by.wolearn.addword.backend.entities.WordInsert
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.ApiException
import by.wolearn.core.BackendErrors
import by.wolearn.core.replace
import by.wolearn.core.ui.entities.Category
import kotlin.math.hypot


class AddWordUiMapper {

    val wordInsert = WordInsert()
    private var items: MutableList<Item> = mutableListOf()
    private var partOfSpeeches: List<PartOfSpeech> = emptyList()

    fun getInitialItems(partOfSpeeches: List<PartOfSpeech>): List<Item> {
        this.partOfSpeeches = partOfSpeeches
        initItems()
        return items
    }

    fun onDeleteExampleClick(example: Item.Example): List<Item> {
        var index = items.indexOf(example)
        if (index == -1) return items
        wordInsert.examples.removeAt(example.num - 1)
        items.remove(example)
        while (true) {
            if (index == items.size) break
            val item = (items[index] as? Item.Example) ?: break
            items[index] = item.copy("${EXAMPLE}_${item.num - 1}", num = item.num - 1)
            index++
        }
        if (items.getOrNull(index) is Item.ButtonAddExample) {
            return items
        }
        items.add(index, Item.ButtonAddExample(ADD_EXAMPLE))
        return items
    }

    fun onAddExampleClick(): List<Item> {
        val index = items.indexOfLast { it is Item.Example }
        if (index == -1) return items
        val lastExample = (items[index] as? Item.Example) ?: return items
        if (lastExample.num == 4) items.removeAt(index + 1)
        items.add(
            index + 1,
            Item.Example("${EXAMPLE}_${lastExample.num + 1}", "", lastExample.num + 1, true)
        )
        wordInsert.examples.add("")
        return items
    }

    fun onExampleChanged(example: Item.Example, newValue: String) {
        wordInsert.examples[example.num - 1] = newValue
        val index = items.indexOfLast { it is Item.Example && it.id == example.id }
        if (index == -1) return
        items.removeAt(index)
        items.add(index, example.copy(value = newValue))
    }

    fun onCategoryChoosed(category: Category): List<Item> {
        wordInsert.categoryId = category.id
        val index = items.indexOfLast { it is Item.Category }
        val item = items[index] as? Item.Category ?: return items
        val newItem = item.copy(value = category)
        items.removeAt(index)
        items.add(index, newItem)
        return items
    }


    fun handleError(apiException: ApiException): List<Item> {
        clearErrors()
        when (apiException.code) {
            BackendErrors.WORDINSERT_WORD_ERROR_1 -> {
                val item = items.find { (it is Item.Input) && it.id == INPUT_WORD } as? Item.Input
                    ?: return items
                items.replace(item, item.copy(error = apiException.message))
            }
            BackendErrors.WORDINSERT_TRANSCRIPTION_ERROR_2 -> {
                val item =
                    items.find { (it is Item.Input) && it.id == INPUT_TRANSCRIPTION } as? Item.Input
                        ?: return items
                items.replace(item, item.copy(error = apiException.message))
            }
            BackendErrors.WORDINSERT_POS_ERROR_3 -> {
                val item =
                    items.find { (it is Item.Select) && it.id == SELECT_PART_OF_SPEECHES } as? Item.Select
                        ?: return items
                items.replace(item, item.copy(error = apiException.message))
            }
            BackendErrors.WORDINSERT_CATEGORY_ERROR_4 -> {
                val item =
                    items.find { (it is Item.Category) && it.id == CATEGORY } as? Item.Category
                        ?: return items
                items.replace(item, item.copy(error = apiException.message))
            }
            BackendErrors.WORDINSERT_EXAMPLES_ERROR_5 -> {
                val item = items.find { (it is Item.Example) } as? Item.Example
                    ?: return items
                items.replace(item, item.copy(error = apiException.message))
            }
            BackendErrors.WORDINSERT_QUIZ_ERROR_6 -> {
                val item =
                    items.find { (it is Item.QuizOption) } as? Item.QuizOption ?: return items
                items.replace(item, item.copy(error = apiException.message))
            }
        }
        return items
    }

    fun onInputChanged(input: Item.Input, newValue: String) {
        when (input.id) {
            INPUT_WORD -> wordInsert.name = newValue
            INPUT_TRANSCRIPTION -> wordInsert.transcription = newValue
        }
        val oldValue =
            items.find { it is Item.Input && it.id == input.id } as? Item.Input
                ?: return
        items.replace(oldValue, oldValue.copy(value = newValue))
    }

    fun onQuizOptionChanged(quizOption: Item.QuizOption, newValue: String) {
        wordInsert.quiz.options[quizOption.position] = newValue
        val oldValue =
            items.find { it is Item.QuizOption && it.id == quizOption.id } as? Item.QuizOption
                ?: return
        items.replace(oldValue, oldValue.copy(value = newValue))
    }

    fun onSelected(select: Item.Select, newValue: String) {
        val partOfSpeechIndex = partOfSpeeches.indexOfLast { it.name == newValue }
        val partOfSpeech = partOfSpeeches.getOrNull(partOfSpeechIndex)
        wordInsert.posId = partOfSpeech?.id ?: -1
        val oldValue =
            items.findLast { it is Item.Select && it.id == select.id } as? Item.Select ?: return
        items.replace(oldValue, oldValue.copy(value = newValue))
    }

    private fun clearErrors() {
        val itemsToReplace = items.filter {
            (it is Item.Input && it.error != null) || (it is Item.Select && it.error != null) || (it is Item.Example && it.error != null)
        }
        itemsToReplace.forEach {
            when (it) {
                is Item.Input -> items.replace(it, it.copy(error = null))
                is Item.Category -> items.replace(it, it.copy(error = null))
                is Item.Select -> items.replace(it, it.copy(error = null))
            }
        }
    }

    private fun initItems() {
        items = mutableListOf(
            Item.Title(TITLE_BASIC_INFO, R.string.addword_title_basic),
            Item.Input(INPUT_WORD, "", R.string.addword_hint_word),
            Item.Input(INPUT_TRANSCRIPTION, "", R.string.addword_hint_transcription),
            getPartOfSpeechItem(),
            Item.Category(CATEGORY),
            Item.Title(TITLE_EXAMPLES, R.string.addword_title_examples),
            Item.Example("${EXAMPLE}_1", "", 1),
            Item.ButtonAddExample(ADD_EXAMPLE),
            Item.Title(TITLE_QUIZ, R.string.addword_title_quiz),
            Item.QuizOption("${QUIZ_OPTION}_1", 0, true),
            Item.QuizOption("${QUIZ_OPTION}_2", 1),
            Item.QuizOption("${QUIZ_OPTION}_3", 2),
            Item.QuizOption("${QUIZ_OPTION}_4", 3),
            Item.Submit(SUBMIT)
        )
    }

    private fun getPartOfSpeechItem(): Item {
        val pos = partOfSpeeches.map { it.name }
        return Item.Select(SELECT_PART_OF_SPEECHES, R.string.addword_part_of_speech_hint, pos)
    }

    companion object {
        const val TITLE_BASIC_INFO = "basic_info"
        const val TITLE_EXAMPLES = "examples"
        const val TITLE_QUIZ = "quiz"

        const val INPUT_WORD = "word"
        const val INPUT_TRANSCRIPTION = "transcription"

        const val QUIZ_OPTION = "quiz_option"

        const val CATEGORY = "category"

        const val EXAMPLE = "example"

        const val SUBMIT = "submit"

        const val ADD_EXAMPLE = "add_example"

        const val SELECT_PART_OF_SPEECHES = "part_of_speeches"
    }

}