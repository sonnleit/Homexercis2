package at.fh.swengb.sonnleitner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Person (val name: String, val profileImagePath: String)