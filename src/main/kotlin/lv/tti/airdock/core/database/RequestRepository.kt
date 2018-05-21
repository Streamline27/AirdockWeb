package lv.tti.airdock.core.database

import lv.tti.airdock.core.domain.Request
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface RequestRepository : JpaRepository<Request, Long> {

	@Query("SELECT * FROM Request r WHERE r.author = :id", nativeQuery = true)
	fun findByAuthor(@Param("id") userId : Long) : List<Request>

	fun findAllByStatus(status: Request.Status): List<Request>

	@Transactional
	@Modifying
	@Query("UPDATE Request r SET r.status = :status WHERE r.id = :id")
	fun updateStatus(@Param("id") id: Long, @Param("status") status: Request.Status)


	@Query(
			"""
    		UPDATE Request r
    		SET r.title = :title, r.description = :description
    		WHERE r.id = :id
    		"""
	)
	@Transactional
	@Modifying
	fun updateFieldsById(
			@Param("id") 		  id 		  : Long,
			@Param("title") 	  title 	  : String,
			@Param("description") description : String
	)

}