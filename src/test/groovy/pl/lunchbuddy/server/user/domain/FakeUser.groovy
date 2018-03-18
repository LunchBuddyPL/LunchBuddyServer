package pl.lunchbuddy.server.user.domain

class FakeUser {

	public final User INSTANCE

	FakeUser() {
		this.INSTANCE = new User("name")
	}

}
