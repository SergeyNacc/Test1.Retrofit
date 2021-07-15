package nacc.sergey.test1retrofit.data

import nacc.sergey.test1retrofit.data.UsersData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/users")

    fun getUserListApi(
        @Query("first_name") first_name: String,
        @Query("last_name") last_name: String
    ): Call<UsersData>
}