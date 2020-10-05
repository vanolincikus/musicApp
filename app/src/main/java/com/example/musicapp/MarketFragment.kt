package com.example.musicapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class MarketFragment : Fragment(), AdapterView.OnItemSelectedListener,
    AdapterView.OnItemClickListener {
    lateinit var quantityField: TextView
    var quantity = 0
    lateinit var spinner: Spinner
    var spinnerArrayList = arrayListOf<String>()
    lateinit var spinerAdapter: ArrayAdapter<Any>
    lateinit var goodsName: String
    var price: Double = 0.0
    lateinit var priceTV: TextView
    var goodsMap = HashMap<String, Double>()
    lateinit var userNameEditText: EditText
    lateinit var mOrder: Order
    lateinit var plusButton: Button
    lateinit var minusButton: Button
    lateinit var addToCard: Button
    private lateinit var mActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_market, container, false)
        mActivity = activity as MainActivity
        spinner = view.findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this
        spinnerArrayList.add("guitar")
        spinnerArrayList.add("piano")
        spinnerArrayList.add("bass")
        spinnerArrayList.add("sax")
        spinnerArrayList.add("cello")
        //defining dropdown
        spinerAdapter = ArrayAdapter(
            mActivity, R.layout.support_simple_spinner_dropdown_item,
            spinnerArrayList as List<Any>
        )
        spinerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        //spinneradapter to spinner
        spinner.adapter = spinerAdapter
        priceTV = view.findViewById(R.id.price)
        quantityField = view.findViewById(R.id.textView)
        plusButton = view.findViewById(R.id.plusButton)
        minusButton = view.findViewById(R.id.minusButton)
        createSpinner()
        createMap()


        userNameEditText = view.findViewById(R.id.editTextTextPersonName)
        addToCard = view.findViewById(R.id.addToCardButton)
        initClicks()
        return view
    }

    fun initClicks() {
        plusButton.setOnClickListener { plusQuantity(this) }
        minusButton.setOnClickListener { deqreaseQuantity(this) }
        addToCard.setOnClickListener { addToCard(this) }
    }

    fun createMap() {
        goodsMap.put("guitar", 100.0)
        goodsMap.put("piano", 300.0)
        goodsMap.put("bass", 150.0)
        goodsMap.put("sax", 400.0)
        goodsMap.put("cello", 700.0)

        goodsName = spinner.onItemClickListener.toString()
    }

    fun createSpinner() {

    }

    fun plusQuantity(view: MarketFragment) {
        quantity = quantity + 1
        quantityField.text = quantity.toString()
        priceTV.text = ("" + quantity * price)

    }

    fun deqreaseQuantity(view: MarketFragment) {
        if (quantity > 0) {
            quantity--
        }
        quantityField.text = quantity.toString()
        priceTV.text = ("" + quantity * price)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        goodsName = spinner.selectedItem.toString()

        price = goodsMap.get(goodsName) as Double


        priceTV.text = ("" + quantity * price)

        var imageView = mActivity.findViewById<ImageView>(R.id.imageView)


        when (goodsName) {
            "guitar" -> imageView.setImageResource(R.drawable.guitar)
            "bass" -> imageView.setImageResource(R.drawable.bass)
            "piano" -> imageView.setImageResource(R.drawable.piano)
            "sax" -> imageView.setImageResource(R.drawable.sax)
            "cello" -> imageView.setImageResource(R.drawable.cello)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    fun addToCard(view: MarketFragment) {
        mOrder = Order()
        mOrder.userName = userNameEditText.text.toString()
        mOrder.goodsname = goodsName
        mOrder.quantity = quantity
        mOrder.orderPrice = price


    }


}