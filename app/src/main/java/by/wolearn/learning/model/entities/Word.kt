package by.wolearn.learning.model.entities


data class Word(
    val id: Int,
    val name: String,
    val pos: String,
    val transcription: String,
    val examples: List<String>,
    val quiz: Quiz,
    val isMemorized: Boolean = false
)