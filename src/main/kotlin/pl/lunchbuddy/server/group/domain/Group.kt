package pl.lunchbuddy.server.group.domain

import pl.lunchbuddy.server.user.domain.User
import pl.lunchbuddy.server.util.ShortCodeGenerator
import java.util.*


open class Group(val name: String,
                 val mealTime: MealTime,
                 creator: User,
                 defaultOptions: Set<MealOption>) {

    private var members: MutableSet<User> = mutableSetOf()
    private var mealOptions: MutableSet<MealOption> = mutableSetOf()
    val code: GroupCode
    val id: GroupId

    init {
        if (defaultOptions.isEmpty()) throw IllegalArgumentException("Meal options can not be empty")
        addMember(creator)
        id = GroupId(UUID.randomUUID().toString())
        code = generateCode()
        mealOptions.addAll(defaultOptions)
    }

    private fun generateCode() = GroupCode(ShortCodeGenerator(8).generate())

    fun addMember(user: User) {
        members.add(user)
    }

    fun addMealOption(option: MealOption) {
        this.mealOptions.add(option)
    }

    fun getMembers(): Set<User> {
        return Collections.unmodifiableSet(members)
    }

    override fun toString(): String {
        return "Group(name='$name', mealTime=$mealTime, members=$members, mealOptions=$mealOptions, code='$code')"
    }


}

