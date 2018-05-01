package lv.tti.airdock.core.domain

import javax.persistence.*

@Entity(name = "Credentials")
class Credentials(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id : Long? = null,

        @Column(name = "login") var login: String,
        @Column(name = "password") var password: String,

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        var user: User
)