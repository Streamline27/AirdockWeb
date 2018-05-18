package lv.tti.airdock.core.domain

import lv.tti.airdock.core.domain.Request.Status.*
import java.util.*
import javax.persistence.*

@Entity(name = "Request")
data class Request (
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		var id: Long? = null,

		@Column(name = "title")       var title: String,
		@Column(name = "description") var description: String,

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "AUTHOR", referencedColumnName = "ID")
		var author: User? = null,


		@Column(name = "creation_date") var creationDate: Date = Date(),

		@Enumerated(EnumType.STRING)
		@Column(name = "status") var status : Status = PENDING
) {
	enum class Status {
		PENDING,
		ACCEPTED,
		CANCELLED
	}
}
