package pl.lunchbuddy.server.group.api


class GroupNotFoundException private constructor(message: String?) : RuntimeException(message) {


    companion object {

        fun withId(id: String): GroupNotFoundException {
            return GroupNotFoundException("Can't find group with id : $id")
        }
    }


}