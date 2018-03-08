package pl.lunchbuddy.server.group.domain

import pl.lunchbuddy.server.user.domain.User
import java.util.*


class Group(val name: String, creator: User, defaultOptions: Set<MealOption>) {

    private var members: MutableSet<User> = mutableSetOf()
    private var mealOptions: MutableSet<MealOption> = mutableSetOf()
    val code: String


    init {
        addMember(creator)
        code = generateCode()
        if(defaultOptions.isEmpty()) throw IllegalArgumentException("Meal options can not be empty")
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
        return "Group(name='$name', members=$members, mealOptions=$mealOptions, code='$code')"
    }


}

