package aait.se.mobileprogrammingcourse.khalid.android.rustdetector

import java.io.Serializable

data class RustData(
  var latitude: Double, var longitude: Double, var source: String, var latency: String,
  var lable: String,
  var confidence: Float): Serializable
