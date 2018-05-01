package lv.tti.airdock.database

import lv.tti.airdock.core.database.CredentialsRepository
import lv.tti.airdock.core.database.UserRepository
import lv.tti.airdock.core.domain.Credentials
import lv.tti.airdock.core.domain.User
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CredentialsTest {

    @Autowired lateinit var credentialsRepository : CredentialsRepository
    @Autowired lateinit var userRepository: UserRepository

    @Test
    fun `when credentials is saved with user id on find should have that user id`() {

        val user = user()
        val credentials = Credentials(
                login = "User",
                password = "123",
                user = user
        )

        userRepository.save(user)
        credentialsRepository.save(credentials)

        val credentialsFromDb = credentialsRepository.findByUserId(user.id!!).get()

        Assert.assertTrue(credentialsFromDb.user.equals(user))
    }

    @Test
    fun `should be able to find user by id from credentials`() {

        val user = user()
        val credentials = Credentials(
                login = "User",
                password = "123",
                user = user
        )

        userRepository.save(user)
        credentialsRepository.save(credentials)

        val userFromDb = userRepository.findById(credentials.user.id!!).get()

        Assert.assertTrue(user.equals(userFromDb))
    }

    @Test
    fun `can retrieve credentials by username`() {

        val user = user()
        val credentials = Credentials(
                login = "User",
                password = "123",
                user = user
        )

        userRepository.save(user)
        credentialsRepository.save(credentials)

        val credentialsFromDb = credentialsRepository.findByLogin(credentials.login).get()

        Assert.assertTrue(credentialsFromDb.id == credentials.id)
    }

    private fun user() =
            User(
                name = "Vasja",
                role = User.Role.ADMIN)
}