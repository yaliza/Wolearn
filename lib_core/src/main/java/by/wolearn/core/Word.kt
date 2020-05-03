package by.wolearn.core


data class Word(
    val id: Long,
    val name: String,
    val pos: PartOfSpeech,
    val transcription: String,
    val examples: List<String>,
    val quiz: Quiz
)

class PartOfSpeech(
    val id: Int,
    val name: String
)

class Quiz(
    val options: List<String>,
    val indexOfRight: Int
)