package lv.tti.airdock.core.domain

import java.util.*
import javax.persistence.*

@Entity(name = "Task")
data class Task(
		@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Long? = null,

		@Column(name = "title")       var title: String,
		@Column(name = "description") var description: String,

		@Column(name = "start_date") 	var startDate: Date? = null,
		@Column(name = "end_date") 		var endDate: Date? = null,
		@Column(name = "creation_date") var creationDate: Date? = null,

		@Enumerated(EnumType.STRING)
		@Column(name = "status") var status : Status? = null,

		@ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        var user: User? = null,

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "WORK_ORDER_ID", referencedColumnName = "id")
		var workOrder: WorkOrder? = null
) {
	enum class Status{
		TODO,
		IN_PROGRESS,
		DONE,
		LATER
	}
}
