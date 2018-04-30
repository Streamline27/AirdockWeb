package lv.tti.airdock.database

import lv.tti.airdock.core.database.TaskRepository
import org.junit.Assert.*
import lv.tti.airdock.core.database.UserRepository
import lv.tti.airdock.core.domain.Task
import lv.tti.airdock.core.domain.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class EntityTest {

    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var taskRepository: TaskRepository

    @Test
    fun `when user is persisted hibernate puts id in it`(){

        val user = User(
            name = "Vasja",
            role = User.Role.ADMIN
        )

        userRepository.save(user)

        assertNotNull(user.id)
    }

    @Test
    fun `when task is persisted hibernate puts id in it`(){

        val task = Task(
                title = "Title",
                description = "Description"
        )

        taskRepository.save(task)

        assertNotNull(task.id)
    }

    @Test
    fun `when task is persisted with user it can be fetched by user id`(){

        val user = User(
                name = "Vasja",
                role = User.Role.ADMIN
        )

        val task = Task(
                title = "Title",
                description = "Description",
                user = user
        )

        userRepository.save(user)
        taskRepository.save(task)

        val userList = taskRepository.findByUserId(user.id?: 0)

        assertTrue(userList.size == 1)
        assertTrue(userList.first().user?.id === user.id)
    }

}