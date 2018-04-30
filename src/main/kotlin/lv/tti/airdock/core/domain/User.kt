package lv.tti.airdock.core.domain

import javax.persistence.*

@Entity(name = "User")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Long? = null,

        @Column(name = "name") var name: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        var role: Role
) {
    enum class Role{
        ADMIN,
        USER
    }
}