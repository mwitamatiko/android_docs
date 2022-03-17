package com.example.raos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.raos.R
import com.example.raos.ViewModel.KycViewModel
import com.example.raos.Respository.ApiManager
import com.example.raos.model.UserInfo


class viewOutputFragment : Fragment() {
    val kycViewModel: KycViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_output, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateObTv = view.findViewById<TextView>(R.id.dateofBirthTvv)
        val firstNameTv = view.findViewById<TextView>(R.id.firstNameTvv)
        val middleNameTv = view.findViewById<TextView>(R.id.middleNameTvv)
        val lastNameTv = view.findViewById<TextView>(R.id.lastNameTvv)
        val languageTv = view.findViewById<TextView>(R.id.languageTvv)
        val identityTv = view.findViewById<TextView>(R.id.nationalIdTvv)
        val emailTv = view.findViewById<TextView>(R.id.emailTvv)
        val homeEmailTv = view.findViewById<TextView>(R.id.homeEmailTvv)
        val phoneNoTv = view.findViewById<TextView>(R.id.phoneNoTvv)
        val phoneCellTv = view.findViewById<TextView>(R.id.phoneCellTvv)
        val phoneHomeTv = view.findViewById<TextView>(R.id.phoneHome1Tvv)
        val phoneHome2Tv = view.findViewById<TextView>(R.id.phoneHome2Tvv)
        val preferredPhoneTv = view.findViewById<TextView>(R.id.preferedPhoneTvv)

        val occupationTv = view.findViewById<TextView>(R.id.occupationTvv)
        val educationTv = view.findViewById<TextView>(R.id.educationTvv)

//      val placeOfBirthTv = view.findViewById<TextView>()
        //initializing the submit button
        val submitBtn = view.findViewById<Button>(R.id.submitBtn)

//       Getting now the values from ViewModel
        dateObTv.text =kycViewModel.doB

        firstNameTv.text = kycViewModel.fName
        middleNameTv.text = kycViewModel.mName
        lastNameTv.text = kycViewModel.lName
        languageTv.text = kycViewModel.languagee
        identityTv.text = kycViewModel.nIdentity
        emailTv.text = kycViewModel.eMail
        homeEmailTv.text = kycViewModel.homeEmaill
        phoneNoTv.text = kycViewModel.phoneNoo
        phoneCellTv.text = kycViewModel.pCell
        phoneHomeTv.text = kycViewModel.pHome1
        phoneHome2Tv.text = kycViewModel.pHome2
        preferredPhoneTv.text = kycViewModel.preferredPhone
        occupationTv.text = kycViewModel.occupations
        educationTv.text = kycViewModel.hEducation





        submitBtn.setOnClickListener(){
            val userData = UserInfo(
                id = null,
                dob = "",
                education = "",
                language = "",
                firstName = "",
                middleName = "",
                lastName = "",
                gender = "",
                occupation = "",
                nIdentity = "",
                email = "",
                emailHome = "",
                phoneNo = "",
                phoneCell = "",
                phoneHome = "",
                phoneHome2 = "",
                preferredPhone = "",
                physicalState = "",
                state = "",
                zip = "",


            )
//            userData.dob = dateObTv
            userData.id = id.toInt()
            userData.dob = kycViewModel.doB
            userData.education = kycViewModel.hEducation
            userData.firstName = kycViewModel.fName
            userData.middleName = kycViewModel.mName
            userData.lastName = kycViewModel.lName
//            userData.gender = kycViewModel.genderr
            userData.language = kycViewModel.languagee
            userData.nIdentity = kycViewModel.nIdentity
            userData.emailHome = kycViewModel.homeEmaill
            userData.email = kycViewModel.eMail
            userData.phoneNo = kycViewModel.phoneNoo
            userData.phoneCell = kycViewModel.pCell
            userData.phoneHome = kycViewModel.pHome1
            userData.phoneHome2 =kycViewModel.pHome2
            userData.preferredPhone = kycViewModel.preferredPhone
            userData.occupation = kycViewModel.occupations


            val apiService = ApiManager()


            apiService.collectCustomerDetails(userData) {
                if (it?.id != null){
                    Toast.makeText(requireContext(),"Successfully created customer Data", Toast.LENGTH_SHORT).show()
                }else{

                }
            }

            findNavController().navigate(R.id.submitImages)
//
        }

    }




}