package com.akinpelumi.crop2cashtest

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.akinpelumi.crop2cashtest.adapter.ExhibitAdapter
import com.akinpelumi.crop2cashtest.callback.ISingleExhibitSelectionCallback
import com.akinpelumi.crop2cashtest.databinding.ActivityMainBinding
import com.akinpelumi.crop2cashtest.model.ExhibitModel
import com.akinpelumi.crop2cashtest.model.ExhibitsLoader
import com.akinpelumi.crop2cashtest.repository.RestExhibitsLoader

class MainActivity : AppCompatActivity(), ExhibitsLoader, ISingleExhibitSelectionCallback {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var exhibitList : List<ExhibitModel> ?= null
    private var exhibits : ExhibitAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exhibitList?.let { viewToDisplay(it.size) }
        initView()
    }
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, container, false)
//
//        //viewToDisplay()
//        //val _listSize = tickets?.itemCount
//        exhibitList?.itemCount?.let { viewToDisplay(it) }
//        initView()
//        return binding.root
//    }
    private fun initView(){
    //call transaction history java class
        if(exhibitList==null){
            binding.contentLoader.visibility = View.VISIBLE

            val restExhibitsLoader = RestExhibitsLoader(this)
            restExhibitsLoader.execute()
        }
        else if(exhibitList != null){
            exhibits = ExhibitAdapter(
                this,
                exhibitList,
                this
            )
            //set adapter on recycler
            binding.exhibitsRecycler.adapter = exhibits
        }

    }
    private fun viewToDisplay(listSize: Int){
        if(listSize == 0){
            binding.exhibitsRecycler.visibility = View.GONE
            binding.emptyExhibit.visibility = View.VISIBLE
        }
        else{
            binding.exhibitsRecycler.visibility = View.VISIBLE
            binding.emptyExhibit.visibility = View.GONE
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun getExhibitList(_exhibitList: MutableList<ExhibitModel>?) {
        when{
            _exhibitList==null ->{
                //hide loader
                binding.contentLoader.visibility = View.GONE
                binding.emptyExhibit.visibility = View.VISIBLE
                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT)
                return
            }
            _exhibitList != null -> {
                exhibitList = _exhibitList
                //instantiate adapter wit record in param
                exhibits = ExhibitAdapter(
                    this,
                    _exhibitList,
                    this
                )
                //set adapter on recycler
                binding.exhibitsRecycler.adapter = exhibits

                //hide loader
                binding.contentLoader.visibility = View.GONE
                binding.emptyExhibit.visibility = View.GONE
                binding.exhibitsRecycler.visibility = View.VISIBLE
            }
        }
    }

    override fun onSelect(item: ExhibitModel?) {
        Toast.makeText(this, item?.title +" image tapped", Toast.LENGTH_SHORT).show()
    }
}