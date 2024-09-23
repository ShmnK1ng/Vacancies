package com.example.presentation.stub

import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.base.viewBinding
import com.example.presentation.databinding.FragmentStubBinding

class StubFragment : Fragment(R.layout.fragment_stub) {

    val binding by viewBinding { FragmentStubBinding.bind(it) }
}