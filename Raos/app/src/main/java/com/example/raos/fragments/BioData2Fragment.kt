package com.example.raos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.raos.R
import com.example.raos.ViewModel.KycViewModel





class bioData2Fragment : Fragment() {
    val kycViewModel: KycViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bio_data2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailEd = view.findViewById<EditText>(R.id.emailEmail)
        val homeEmailEd = view.findViewById<EditText>(R.id.emailHomeEmail)
        val phoneNoEd = view.findViewById<EditText>(R.id.phoneNumberPhone)
        val phoneCellEd = view.findViewById<EditText>(R.id.phoneCellEdt)
        val phoneHome1Ed = view.findViewById<EditText>(R.id.phoneHome1Phone)
        val phoneHome2Ed = view.findViewById<EditText>(R.id.phoneHome2Phone)
        val pPhoneEd = view.findViewById<EditText>(R.id.prefferedPhonePhone)

        val bioData2NextBtn = view.findViewById<Button>(R.id.biodata2NextBtn)

        bioData2NextBtn.setOnClickListener(){
            val email = emailEd.text.toString()
            val homeEmail = homeEmailEd.text.toString()
            val phoneNo = phoneNoEd.text.toString()
            val phoneCell = phoneCellEd.text.toString()
            val phoneHome1 = phoneHome1Ed.text.toString()
            val phoneHome2 = phoneHome2Ed.text.toString()
            val prefferedPhone = pPhoneEd.text.toString()


            if(email.isNotEmpty() && phoneNo.isNotEmpty()){
                kycViewModel.holdCollectedData2(email,
                homeEmail,phoneNo,phoneCell,phoneHome1,phoneHome2,prefferedPhone)
                navigateToBioData3Fragment()
            }else{
                Toast.makeText(requireContext(),"Email & Phone Number Required", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun navigateToBioData3Fragment(){
        findNavController().navigate(R.id.action_bioData2Fragment2_to_bioData3Fragment2)
    }


}