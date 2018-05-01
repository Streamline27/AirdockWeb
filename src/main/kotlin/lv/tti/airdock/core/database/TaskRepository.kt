package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {

    fun findByUserId(id : Long) : List<Task>
}