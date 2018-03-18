package pl.lunchbuddy.server.user.api


class UserNotFoundException private constructor(message: String?) : RuntimeException(message) {


    companion object {

        fun withId(id: String): UserNotFoundException {
            return UserNotFoundException("Can't find user with id : $id")
        }
    }


}