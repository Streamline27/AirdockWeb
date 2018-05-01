package lv.tti.airdock.database

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
class UserTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `when user is persisted hibernate puts id in it`(){
        val user = User(
                name = "Vasja",
                role = User.Role.ADMIN
        )

        userRepository.save(user)

        Assert.assertNotNull(user.id)
    }

}