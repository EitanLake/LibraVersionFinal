package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations.map
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




class maps : Fragment(), OnMapReadyCallback {


    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mMap: GoogleMap


    companion object {
        const val REQUEST_CODE_LOCATION = 0
        var mapFragment: SupportMapFragment? = null

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            maps().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_maps, container, false)

        mapFragment = childFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        createMarker()
        enableLocation()
    }

    private fun createMarker() {
        val coordinate = LatLng(-34.739515, -58.2837682)
        mMap.addMarker(MarkerOptions().position(coordinate).title("centro CIQO")


        );

        val coordinate2 = LatLng(-34.8223253877551, -58.468488)
        mMap.addMarker(MarkerOptions().position(coordinate2).title("psicologia para adultos"))


        val coordinate3 = LatLng(-34.8223253877551, -58.468487)
        mMap.addMarker(
            MarkerOptions().position(coordinate3).title("Psicóloga Noemí Santín Monte Grande")
        )


        val coordinate4 = LatLng(-34.81844, -58.45625)
        mMap.addMarker(
            MarkerOptions().position(coordinate4).title("Licenciado Monteperto Alejandro")
        )

        val coordinate5 = LatLng(-34.8124963, -58.461287)
        mMap.addMarker(MarkerOptions().position(coordinate5).title("Gonzalez Fraguas Mariel A."))

        val coordinate6 = LatLng(-34.8073886, -58.4377632)
        mMap.addMarker(MarkerOptions().position(coordinate6).title("Lic Perri Francisca Maria"))

        val coordinate7 = LatLng(-34.8113426, -58.4651539)
        mMap.addMarker(
            MarkerOptions().position(coordinate7).title("Centro Terapeutico Mirar para Ver")
        )

        val coordinate8 = LatLng(-34.61315, -58.37723)
        mMap.addMarker(MarkerOptions().position(coordinate8).title("Psicologa Mariela Celaya"))

        val coordinate9 = LatLng(-34.4946077, -58.763531)
        mMap.addMarker(MarkerOptions().position(coordinate9).title("Lic. Martinez - Psicología"))

        val coordinate10 = LatLng(-34.7048543, -58.3938108)
        mMap.addMarker(MarkerOptions().position(coordinate10).title("Psicolanus"))

        val coordinate11 = LatLng(-34.6948863, -58.5036495)
        mMap.addMarker(MarkerOptions().position(coordinate11).title("Acompañante Terapéutico"))

        val coordinate12 = LatLng(-34.6900768, -58.7314305)
        mMap.addMarker(MarkerOptions().position(coordinate12).title("GRAFOEDUCATIVA"))

        val coordinate13 = LatLng(-34.61315, -58.37723)
        mMap.addMarker(MarkerOptions().position(coordinate13).title("centro de psicología moron"))


    }

    //metodo para el permiso
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    private fun enableLocation() {
// el mapa se inicio...?
        if (!::mMap.isInitialized) return

        //requiere los permisos activados
        if (isPermissionsGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }


}