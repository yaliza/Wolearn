package by.wolearn.learning.backend.entities


data class Word(
    val id: Long,
    val name: String,
    val pos: String,
    val transcription: String,
    val examples: List<String>,
    val quiz: Quiz
)