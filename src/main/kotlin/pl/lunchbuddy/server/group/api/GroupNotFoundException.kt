package pl.lunchbuddy.server.group.api


class GroupNotFoundException private constructor(message: String?) : RuntimeException(message) {


    companion object {

        fun withCode(code: String): GroupNotFoundException {
            return GroupNotFoundException("Can't find group with code : $code")
        }
    }


}