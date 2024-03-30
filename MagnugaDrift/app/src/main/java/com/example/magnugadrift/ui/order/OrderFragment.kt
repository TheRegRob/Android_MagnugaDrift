package com.example.magnugadrift.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.magnugadrift.R
import com.example.magnugadrift.databinding.OrdersFragmentBinding


class OrderFragment : Fragment(), View.OnClickListener {

    private var _binding: OrdersFragmentBinding? = null
    var sv: LinearLayoutCompat? = null
    private var totalOrders = 0
    lateinit var btnAdd: Button
    lateinit var tvOrders: TextView
    var starter = 65

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel : OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAdd = view.findViewById<Button>(R.id.bt_CloseOrder)
        tvOrders = view.findViewById<TextView>(R.id.tv_ordersNumber)
        sv = view.findViewById<LinearLayoutCompat>(R.id.lc_LayoutCompact)
        totalOrders = viewModel.getOrders().count()
        tvOrders.text = "Ordini: " + totalOrders
        btnAdd.setOnClickListener(this)
        fillOrders()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(OrderViewModel::class.java)
        _binding = OrdersFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        val newCard = activity?.let { CardView(it) }
        if (newCard != null) {
            layoutInflater.inflate(R.layout.orders_cardview, newCard)
            totalOrders++
            tvOrders.text = "Ordini: " + totalOrders
            val cv_name = newCard.findViewById<TextView>(R.id.menu_item_name)
            val current: String = Character.toString(starter++.toChar())
            cv_name.text = "Piatto " + current
            newCard.tag = current
            viewModel.addMenuItem(cv_name.text.toString())
            sv?.addView(newCard)
        }
    }

    private fun fillOrders() {
        for (cv: String in viewModel.getOrders()) {
            val newCard = activity?.let { CardView(it) }
            if (newCard != null) {
                layoutInflater.inflate(R.layout.orders_cardview, newCard)
                val cv_name = newCard.findViewById<TextView>(R.id.menu_item_name)
                cv_name.setText(cv)
                sv?.addView(newCard)
            }


        }
    }
}