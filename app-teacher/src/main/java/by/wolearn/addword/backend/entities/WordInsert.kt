package by.wolearn.addword.backend.entities

data class WordInsert(
    var name: String = "",
    var transcription: String = "",
    var posId: Int = -1,
    var categoryId: Int = -1,
    var quiz: QuizInsert = QuizInsert(),
    var examples: MutableList<String> = mutableListOf("")
)

data class QuizInsert(
    var options: MutableList<String> = MutableList(4, { "" }),
    var indexOfRight: Int = 0
)