package com.example.leaderboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leaderboard.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private var db = Firebase.firestore
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val tvName: TextView = binding.tvHomeName
        val tvPoints: TextView = binding.tvHomePoints

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("USER").document(userId)
        ref.get().addOnSuccessListener {
            if(it != null){
                val name = it.data!!["fullName"].toString()
                val points = it.data!!["points"].toString()

                tvName.text = name
                tvPoints.text = points
            }
        }.addOnFailureListener {
            Toast.makeText(this.context,"Invalid",Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}