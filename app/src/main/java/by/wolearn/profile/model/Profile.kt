package by.wolearn.profile.model


class Profile(
    val statistics: Statistics,
    val info: ProfileInfo
)

class ProfileInfo(
    val id: Int,
    val name: String,
    val surname: String
)

class Statistics(
    val total: Int,
    val today: Int,
    val categories: Int
)