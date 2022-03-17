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
//import com.example.raos.ViewModel.kycViewModel


class bioData3Fragment : Fragment() {
    val kycViewModel: KycViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bio_data3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val occupationEd = view.findViewById<EditText>(R.id.occupationEdt)
        val higherEducationEd = view.findViewById<EditText>(R.id.educationEdt)
        val placeOfBirthEd = view.findViewById<EditText>(R.id.placeofBirthEdt)
        val physicalAddressEd = view.findViewById<EditText>(R.id.physicalAddressEdt)
        val stateEd = view.findViewById<EditText>(R.id.stateEdt)
        val zipEd = view.findViewById<EditText>(R.id.zipEdt)
        val bioData3NextBtn = view.findViewById<Button>(R.id.biodata3NextBtn)

        bioData3NextBtn.setOnClickListener(){

            val occupation = occupationEd.text.toString()
            val education = higherEducationEd.text.toString()
            val placeOfBirth = placeOfBirthEd.text.toString()
            val physicalAddress = physicalAddressEd.text.toString()
            val state = stateEd.text.toString()
            val zip = zipEd.text.toString()

            if(occupation.isNotEmpty()){
                kycViewModel.holdCOllectedData3(occupation,
                education,placeOfBirth,physicalAddress,state, zip)
                navigateToConfirmDetailsFragment()
            }else{
               Toast.makeText(requireContext(), "Occupation field required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToConfirmDetailsFragment(){
        findNavController().navigate(R.id.action_bioData3Fragment2_to_viewOutputFragment)
    }


}