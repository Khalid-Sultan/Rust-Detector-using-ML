package aait.se.mobileprogrammingcourse.khalid.android.rustdetector

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataServiceGenerator {
  private val baseUrl = "http://192.168.93.122:5000/"

  fun createDataService(context: Context): DataService? {
    val connected = checkInternet(context)

    if(connected != null && connected ) {
      val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(baseUrl)
      val httpClient = OkHttpClient.Builder()
        .cache(null)
      if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
          .setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(interceptor)
      }
      builder.client(httpClient.build())
      val retrofit = builder.build()
      return retrofit.create(DataService::class.java)
    }
    return null

  }
  companion object{
    fun checkInternet(context: Context): Boolean? {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val activeNetwork: NetworkInfo? =connectivityManager.activeNetworkInfo
      return activeNetwork?.isConnected
    }
  }

}