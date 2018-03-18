package pl.lunchbuddy.server.group.domain

import pl.lunchbuddy.server.user.domain.User
import java.util.*


class Group(val name: String,
            val mealTime: MealTime,
            creator: User,
            defaultOptions: Set<MealOption>) {

    private var members: MutableSet<User> = mutableSetOf()
    private var mealOptions: MutableSet<MealOption> = mutableSetOf()
    val code: String

    init {
        if (defaultOptions.isEmpty()) throw IllegalArgumentException("Meal options can not be empty")
        addMember(creator)
        code = generateCode()
        mealOptions.addAll(defaultOptions)
    }

    private fun generateCode(): String {
        return UUID.randomUUID().toString()
    }

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

