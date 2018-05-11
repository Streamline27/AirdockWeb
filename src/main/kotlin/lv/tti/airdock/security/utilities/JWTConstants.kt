package lv.tti.airdock.security.utilities

val SECRET = "SecretKeyToGenJWTs"
val EXPIRATION_TIME: Long = 864_000_000 // 10 days
val TOKEN_PREFIX = "Bearer "
val HEADER_STRING = "Authorization"