package by.wolearn.learning.model


data class Word(
    val name: String,
    val definitions: List<String>,
    val pos: String,
    val transcription: String
)