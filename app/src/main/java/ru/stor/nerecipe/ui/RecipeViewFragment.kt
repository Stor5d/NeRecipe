package ru.stor.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.stor.nerecipe.databinding.RecipeViewFragmentBinding

import ru.stor.nerecipe.viewModel.RecipeViewModel

class RecipeViewFragment : Fragment() {


    private val viewModel by activityViewModels<RecipeViewModel>()
   // private val args by navArgs<RecipeViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecipeViewFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->
        viewModel.getCurrentRecipe().observe(viewLifecycleOwner) { recipe ->
            binding.recipeName.text = recipe.title
            binding.authorName.text = recipe.author
            binding.category.text = recipe.id.toString()
        }


//        binding.ok.setOnClickListener {
//            onOkButtonClicked(binding)
//        }
    }.root

//    private fun onOkButtonClicked(binding: RecipeContentFragmentBinding) {
//        val text = binding.edit.text
//        if (!text.isNullOrBlank()) {
//            val resultBundle = Bundle(1)
//            resultBundle.putString(RESULT_KEY, text.toString())
//            setFragmentResult(REQUEST_KEY, resultBundle)
//        }
//        val direction = RecipeContentFragmentDirections.actionPostContentFragmentToFeedFragment()
//        findNavController().navigate(direction)
//    }

    companion object {
        const val REQUEST_KEY = "requestKey"
        const val RESULT_KEY = "resultKey"

    }


//    ImageView image = (ImageView) findViewById( R.id.icon );
//    image.setImageResource(0);
//    Uri photoUri = data.getData();
//    String[] filePathColumn = { MediaStore.Images.Media.DATA };
//    Cursor cursor = getContentResolver().query(photoUri,filePathColumn, null, null, null );
//    cursor.moveToFirst();
//    int columnIndex = cursor.getColumnIndex(filePathColumn[0] );
//    String filePath = cursor.getString( columnIndex );
//    cursor.close();
//    try
//    {
//        Bitmap bMap = BitmapFactory.decodeFile(filePath );
//        Bitmap bitmapsimplesize = Bitmap.createScaledBitmap(bMap, bMap.getWidth() / size, bMap.getHeight() / size, true);
//        bMap.recycle();
//        image.setImageBitmap(bitmapsimplesize);
//    }
//    catch ( Exception e )
//    {
//        e.printStackTrace();
//    }
}