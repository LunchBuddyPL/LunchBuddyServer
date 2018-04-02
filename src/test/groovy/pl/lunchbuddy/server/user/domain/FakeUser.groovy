package pl.lunchbuddy.server.user.domain

class FakeUser {

	public final User INSTANCE

	FakeUser(String name) {
		this.INSTANCE = new User(name)
	}

}
