package nacc.sergey.test1retrofit

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import nacc.sergey.test1retrofit.data.Data
import nacc.sergey.test1retrofit.data.UsersData
import nacc.sergey.test1retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MyAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decorator)

            myAdapter = MyAdapter(object : MyAdapter.OnItemClickListener {
                override fun click(data: Data) {
                    (Activity() as MainActivity).launchDetailsFragment(data)
                }
            })
            adapter = myAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObserverable().observe(this, Observer<UsersData> {
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result...", Toast.LENGTH_LONG).show()
            } else {
                myAdapter.items = it.data.toMutableList()
                myAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUserList()
    }

    private fun launchDetailsFragment(data: Data) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем в "посылку"
        bundle.putParcelable("user", data)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.recyclerView, fragment)
            .addToBackStack(null)
            .commit()
    }
}