package nacc.sergey.test1retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import nacc.sergey.test1retrofit.data.ApiConstants.IMAGE_URL
import nacc.sergey.test1retrofit.data.Data
import nacc.sergey.test1retrofit.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserDetails()

    }

    private fun setUserDetails() {

        //Получаем из переданного бандла
        val user = arguments?.get("user") as Data

        //Устанавливаем картинку
        Glide.with(this)
            .load(IMAGE_URL + user.avatar)
            .into(binding.avatar)

        //Устанавливаем описание
        binding.textFirstName.text = user.firstName
        binding.textLastName.text = user.lastName
        binding.textEmail.text = user.email
    }

}