package by.wolearn.addword.ui.adapter.viewholders

import android.text.Editable
import by.wolearn.R
import by.wolearn.addword.ui.entities.Item
import by.wolearn.core.ui.BaseTextWatcher
import by.wolearn.core.ui.getSupportDrawable
import by.wolearn.ui.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@EpoxyModelClass(layout = R.layout.item_addword_quiz_option)
abstract class QuizOptionViewHolder : EpoxyModelWithHolder<QuizOptionViewHolder.Holder>() {

    @EpoxyAttribute
    lateinit var option: Item.QuizOption

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        setupAppearance(holder)
        holder.editText.addTextChangedListener(object : BaseTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                listener.onQuizOptionChanged(option, s.toString())
            }
        })
        holder.layout.error = option.error
    }

    private fun setupAppearance(holder: Holder) {
        if (option.isRight) {
            holder.layout.startIconDrawable =
                holder.context?.getSupportDrawable(R.drawable.ic_check)
            holder.layout.hint = holder.context?.getString(R.string.addword_hint_quiz_right_option)
        } else {
            holder.layout.startIconDrawable =
                holder.context?.getSupportDrawable(R.drawable.ic_cancel)
            holder.layout.hint = holder.context?.getString(
                R.string.addword_hint_quiz_incorrect_option, option.position
            )
        }
    }

    private fun setupListeners() {

    }

    class Holder : KotlinEpoxyHolder() {
        val editText by bind<TextInputEditText>(R.id.value)
        val layout by bind<TextInputLayout>(R.id.layout)
    }

    interface Listener {
        fun onQuizOptionChanged(quizOption: Item.QuizOption, newValue: String)
    }

}