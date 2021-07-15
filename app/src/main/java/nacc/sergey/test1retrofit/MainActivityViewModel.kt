package nacc.sergey.test1retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nacc.sergey.test1retrofit.data.ApiInterface
import nacc.sergey.test1retrofit.data.RetrofitInterface
import nacc.sergey.test1retrofit.data.UsersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var recyclerListData: MutableLiveData<UsersData> = MutableLiveData()

    fun getUserListObserverable() : MutableLiveData<UsersData> {
        return recyclerListData
    }

    fun getUserList() {
        val retrofitInstance = RetrofitInterface.getRetrofitInstance().create(ApiInterface::class.java)
        val call = retrofitInstance.getUserListApi("first_name", "last_name")

        call.enqueue(object : Callback<UsersData>{

            override fun onFailure(call: Call<UsersData>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<UsersData>, response: Response<UsersData>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }

}