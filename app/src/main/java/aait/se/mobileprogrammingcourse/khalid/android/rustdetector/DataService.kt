package aait.se.mobileprogrammingcourse.khalid.android.rustdetector

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DataService {
   @POST("/api/rust-data")
   fun addDataAsync(@Body data: RustData) :Deferred<Response<Void>>

}