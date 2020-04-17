package dev.hugozammit.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.hugozammit.mvvm.R
import dev.hugozammit.mvvm.R.*
import dev.hugozammit.mvvm.model.ProductFamily
import dev.hugozammit.mvvm.viewmodel.ProductViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val productListModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_vehicle_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)

        productListModel.getProducts()
        productListModel.listOfProducts.observe(
            this,
            Observer(function = fun(productList: List<ProductFamily>?) {
                productList?.let {

                    val productListAdapter: ProductListAdapter = ProductListAdapter(productList)
                    recyclerView.adapter = productListAdapter
                    productListAdapter.setItemClickListener(object :
                        ProductListAdapter.ItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            val newFragment = ProductDetailFragment.newInstance(productList[position])
                            val transaction = fragmentManager!!.beginTransaction()
                            transaction.replace(R.id.frag_container, newFragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                    })
                }
            })
        )
    }

}