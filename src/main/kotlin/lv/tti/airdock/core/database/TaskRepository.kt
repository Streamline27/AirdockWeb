package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface TaskRepository : JpaRepository<Task, Long> {

    fun findByUserId(id : Long) : List<Task>

    @Query(
            """
            SELECT t
              FROM Task t LEFT JOIN t.user u LEFT JOIN t.workOrder w
              WHERE (:name = '' OR (u IS NOT NULL AND LOWER(u.name) LIKE LOWER(CONCAT('%',:name,'%'))))
                AND (:workOrder = '' OR (w IS NOT NULL AND LOWER(w.id) LIKE LOWER(:workOrder)))
            """
    )
    fun search(@Param("name") name: String, @Param("workOrder") workOrder: String): List<Task>

    @Query(
            """
            UPDATE Task SET status = :status WHERE id = :id
            """
    )
    @Modifying
    @Transactional
    fun updateTaskStatus(@Param("id") id : Long, @Param("status") status : Task.Status)
}
