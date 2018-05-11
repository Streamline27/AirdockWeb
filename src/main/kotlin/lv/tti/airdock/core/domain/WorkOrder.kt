package lv.tti.airdock.core.domain

import javax.persistence.*

@Entity(name = "WORK_ORDER")
data class WorkOrder (
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		var id: Long? = null,

		@Column(name = "TITLE") var title: String,
		@Column(name = "DESCRIPTION") var description: String
)