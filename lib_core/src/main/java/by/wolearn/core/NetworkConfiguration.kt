package by.wolearn.core


object NetworkConfiguration {

    //const val BASE_URL = "https://secret-caverns-46687.herokuapp.com/"
    private const val BASE_URL = "http://192.168.0.104:8080/"

    const val BASE_CATEGORIES_URL = "${BASE_URL}v1/"
    const val BASE_WORDS_URL = "${BASE_URL}v1/"
    const val BASE_PROFILE_URL = "${BASE_URL}v1/"
    const val BASE_HISTORY_URL = "${BASE_URL}v1/"
    const val BASE_AUTH_URL = "${BASE_URL}v1/"
    const val BASE_PART_OF_SPEECHES = "${BASE_URL}v1/"
    const val BASE_WORD_INSERT_URL = "${BASE_URL}v1/"
    const val BASE_CATEGORIES_TEACHERS_URL = "${BASE_URL}v1/"
    const val BASE_SETTINGS_URL = "${BASE_URL}v1/"
}