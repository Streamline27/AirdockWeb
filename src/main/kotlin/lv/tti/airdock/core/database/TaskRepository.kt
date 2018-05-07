package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TaskRepository : JpaRepository<Task, Long> {

    fun findByUserId(id : Long) : List<Task>

    @Query("SELECT t FROM Task t WHERE t.user IS NOT NULL AND LOWER(t.user.name) LIKE LOWER(:name)")
    fun search(@Param("name") name: String): List<Task>
}