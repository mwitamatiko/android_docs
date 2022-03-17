package com.example.raos.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.raos.R
import com.example.raos.ViewModel.KycViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance


class bioData1Fragment : Fragment() {
    val kycViewModel: KycViewModel by activityViewModels()
    val cal = getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bio_data1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateOfBirthEd = view.findViewById<EditText>(R.id.custDoBEdt)
        val firstNameEd = view.findViewById<EditText>(R.id.custFirstNameEdt)
        val middleNameEd = view.findViewById<EditText>(R.id.custMiddleNameEdt)
        val lastNameEd = view.findViewById<EditText>(R.id.custLastNameEdt)
        val languageEd = view.findViewById<EditText>(R.id.languageEdt)
        val genderEd = view.findViewById<RadioGroup>(R.id.genderEdt)
        val maleEd = view.findViewById<RadioButton>(R.id.maleBtn)
        val femaleEd = view.findViewById<RadioButton>(R.id.femaleBtn)
        val identityEd = view.findViewById<EditText>(R.id.idNumber)
        val genderText = view.findViewById<TextView>(R.id.genderTextView)
        val bioData1NextBtn = view.findViewById<Button>(R.id.next1Btn)


        //Setting the datePicker for dateOf Birth
         val dateSetListener = object: DatePickerDialog.OnDateSetListener{
             override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int,
                                    dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                 cal.set(Calendar.MONTH,monthOfYear)
                 cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                 getDateView(cal,dateOfBirthEd)

             }
         }

        dateOfBirthEd.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                DatePickerDialog(requireContext(),dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })



        bioData1NextBtn.setOnClickListener(){
            val doB = dateOfBirthEd.text.toString()
            val firstName = firstNameEd.text.toString()
            val middleName = middleNameEd.text.toString()
            val lastName = lastNameEd.text.toString()
            val language = languageEd.text.toString()
            val nationalIdentity = identityEd.text.toString()
            val gender = genderText.text.toString()
            genderEd.setOnCheckedChangeListener{ genderEd, i ->
                var rb = view.findViewById<RadioButton>(i)
                if(rb!=null)
                    genderText.setText(rb.text.toString())
            }

            if(firstName.isNotEmpty() && lastName.isNotEmpty()){

                kycViewModel.holdCollectedData1(doB,firstName,middleName,lastName,language,gender,nationalIdentity)

                navigateBioData2Fragment()
            }else{
                Toast.makeText(requireContext(),"First Name & Last Name Required!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun getDateView(cal: Calendar, dateOfBirthEd: EditText) {
        val myFormat ="MM-dd-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        dateOfBirthEd.setText(sdf.format(cal.time))



    }

    fun navigateBioData2Fragment(){
      findNavController().navigate(R.id.action_bioData1Fragment2_to_bioData2Fragment2)
    }




}




