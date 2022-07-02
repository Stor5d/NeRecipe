package ru.stor.nerecipe.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import ru.stor.nerecipe.R
import ru.stor.nerecipe.databinding.StageContentFragmentBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel
import java.io.FileOutputStream


class StageCreateFragment : Fragment(R.layout.stage_content_fragment) {

    private lateinit var binding: StageContentFragmentBinding
    private val viewModel by activityViewModels<RecipeViewModel>()
    private var uriPhoto: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StageContentFragmentBinding.inflate(layoutInflater, container, false)

        val imageView = binding.imageStage
//        Glide.with(this).load("https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg")
//            .override(1000, 1000).into(imageView)

        with(binding) {
            saveStage.setOnClickListener { onSaveButtonClicked() }
            imageStage.setOnClickListener {}
        }



        imageView.setOnClickListener {
//            val intent = Intent().apply {
//                putExtra("key1", "none")
//                action = Intent.ACTION_GET_CONTENT
//                type = "image/*"
//            }
            result.launch("image/*")
            //  val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
            //  startActivityForResult(shareIntent, IMAGE_KEY)
        }


//        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//        new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    // There are no request codes
//                    Intent data = result.getData();
//                    doSomeOperations();
//                }
//            }
//        });
//
//        public void openSomeActivityForResult() {
//            Intent intent = new Intent(this, SomeActivity.class);
//            someActivityResultLauncher.launch(intent);
//        }


        return binding.root
    }

    private val result =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            uriPhoto = it
            Glide.with(this).load(it).override(1000, 1000).into(binding.imageStage)
        }


    private fun onSaveButtonClicked() {
        val content = binding.editTextStage.text
        if (!content.isNullOrBlank()) {
            val resultBundle = Bundle(2)
            resultBundle.putString(STAGE_CONTENT_KEY, content.toString())
            resultBundle.putString(STAGE_URI_PHOTO_KEY, uriPhoto.toString())
            setFragmentResult(ADD_STAGE_REQUEST_KEY, resultBundle)
        }
        findNavController().navigate(R.id.recipeCreateFragment2)
    }

    fun write(fileName: String?, bitmap: Bitmap) {
        val outputStream: FileOutputStream
        try {
            outputStream = context!!.openFileOutput(fileName, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.close()
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }


    companion object {

        const val STAGE_CONTENT_KEY = "stageContentKey"
        const val STAGE_URI_PHOTO_KEY = "uriPhotoKey"
        const val ADD_STAGE_REQUEST_KEY = "addStageRequestKey"


    }
}