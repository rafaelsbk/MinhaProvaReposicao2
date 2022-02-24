package com.example.minhaprovareposicao

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.minhaprovareposicao.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentHomeBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var readPermissionOk = false
    private var writePermissionOk = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider (this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this







        ///// PERMISSAO PRA ACESSAR O STORAGE EXTERNO
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permissions -> readPermissionOk = permissions[Manifest.permission.READ_EXTERNAL_STORAGE]?:readPermissionOk
            writePermissionOk = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE]?: writePermissionOk
        }
        requestPermission()
    }
    private fun requestPermission(){
        readPermissionOk = ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        writePermissionOk = ContextCompat.checkSelfPermission(
            this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest : MutableList<String> = ArrayList()

        if(!readPermissionOk){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)

        }

        if(!writePermissionOk){
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        }
        if (permissionRequest.isNotEmpty()){
            permissionLauncher.launch((permissionRequest.toTypedArray()))
        }
    }

}