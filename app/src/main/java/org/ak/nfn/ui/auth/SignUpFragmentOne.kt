package org.ak.nfn.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_sign_up_one.*

import org.ak.nfn.R
import org.ak.nfn.databinding.FragmentSignUpOneBinding
import org.ak.nfn.utils.snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragmentOne : Fragment(), AuthListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    public var TAG: String? = "SignUpFragmentOne"

    lateinit var binding: FragmentSignUpOneBinding

    val viewModel: AuthViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentSignUpOneBinding>(inflater,R.layout.fragment_sign_up_one, container,false)

        //view models
        binding.viewmodel = viewModel
        viewModel.authListener = this
        Log.d(TAG, "onCreateView: lol in fragments")
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragmentOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragmentOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        container_layout.snackbar(message)
    }

    override fun onSuccess(userToken: String) {
        Navigation.findNavController(binding.root).navigate(R.id.action_signUpFragmentOne_to_signUpFragmentTwo)
//        container_layout.snackbar("Sucess")
    }
}