package sv.edu.bitlab.pupusap

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sv.edu.bitlab.pupusap.Models.Orden


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OrdenDetalleFragment.PruebaFragmentListener] interface
 * to handle interaction events.
 * Use the [OrdenDetalleFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OrdenDetalleFragment : Fragment() {
  // TODO: Rename and change types of parameters
  private lateinit var orden: Orden
  private var listener: PruebaFragmentListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      orden = it.getParcelable<Orden>(ORDEN)!!
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_detalle_orden, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is PruebaFragmentListener) {
      listener = context
    } else {
      throw RuntimeException(context.toString() + " must implement PruebaFragmentListener")
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   *
   *
   * See the Android Training lesson [Communicating with Other Fragments]
   * (http://developer.android.com/training/basics/fragments/communicating.html)
   * for more information.
   */
  interface PruebaFragmentListener {
    // TODO: Update argument type and name
    fun onFragmentInteraction(uri: Uri)
  }

  companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param orden Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdenDetalleFragment.
     */
    // TODO: Rename and change types and number of parameters

    @JvmStatic
    fun newInstance(orden: Orden) =
      OrdenDetalleFragment().apply {
        arguments = Bundle().apply {
          putParcelable(ORDEN, orden)
        }
      }
  }
}
