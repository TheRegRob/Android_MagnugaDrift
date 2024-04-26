package com.example.magnugadrift.ui.order

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.magnugadrift.R
import com.example.magnugadrift.classes.Order.MagnugaOrderItem
import com.example.magnugadrift.databinding.FragmentOrdersBinding
import com.example.magnugadrift.ui.activities.MagnuItemDetailsActivity
import com.example.magnugadrift.ui.activities.NewOrderActivity


class OrderFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentOrdersBinding? = null
    var sv: LinearLayoutCompat? = null
    private var totalOrders = 0
    lateinit var btnAdd: Button
    lateinit var ivAnim: ImageView
    lateinit var handler: Handler
    var starter = 65

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel : OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAdd = view.findViewById<Button>(R.id.btNewOrder)
        ivAnim = view.findViewById<ImageView>(R.id.ivNewOrderAnim)
        btnAdd.setOnClickListener(this)
        handler = Handler(Looper.getMainLooper())
        runnable.run()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(OrderViewModel::class.java)
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btNewOrder -> {
                val intent = Intent(activity, NewOrderActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private var runnable = object : Runnable{
        override fun run() {
            ivAnim.animate().scaleX(1.3f).scaleY(1.3f).alpha(0f).setDuration(1000)
                .withEndAction{
                    ivAnim.scaleX = 1f
                    ivAnim.scaleY = 1f
                    ivAnim.alpha = 1f
                }
            handler.postDelayed(this, 1500)
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