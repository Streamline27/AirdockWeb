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

		@Column(name = "start_date") var start: Date?,
		@Column(name = "end_date") var end: Date?,

		@ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        var user: User? = null
)
