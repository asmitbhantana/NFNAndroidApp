 package org.ak.nfn.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_sign_up_one.*
import kotlinx.android.synthetic.main.fragment_sign_up_one.container_layout
import kotlinx.android.synthetic.main.fragment_sign_up_two.*
import org.ak.nfn.R
import org.ak.nfn.databinding.FragmentSignUpTwoBinding
import org.ak.nfn.ui.home.HomeActivity
import org.ak.nfn.utils.hide
import org.ak.nfn.utils.show
import org.ak.nfn.utils.snackbar
import org.ak.nfn.utils.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val TAG = "SignupFragmentTwo"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragmentTwo.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragmentTwo : Fragment(), AuthListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val binding = DataBindingUtil.inflate<FragmentSignUpTwoBinding>(inflater,R.layout.fragment_sign_up_two, container,false)
        binding.viewmodel = viewModel


        viewModel.authListener = this
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragmentTwo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStarted() {
        signup_progress.show()
        createAccountButton.isClickable = false
    }

    override fun onFailure(message: String) {
        container_layout.snackbar(message)
        signup_progress.hide()
        createAccountButton.isClickable = true
    }

    override fun onSuccess(userToken: String) {
        Log.d(TAG, "onSuccess: user is registed!!")
        Intent(activity, LoginActivity::class.java).also{
            it.putExtra("Signup",1)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
        createAccountButton.isClickable = true
    }
}