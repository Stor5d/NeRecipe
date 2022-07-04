package ru.stor.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.*
import ru.stor.nerecipe.databinding.FilterFragmentSwitchBinding
import ru.stor.nerecipe.viewModel.RecipeViewModel

class FilterFragment : Fragment() {

    private lateinit var binding: FilterFragmentSwitchBinding
    private val viewModel by activityViewModels<RecipeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FilterFragmentSwitchBinding.inflate(
        layoutInflater, container, false
    ).also { it ->
        binding = it

        with(binding) {
            switchEuropean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EUROPEAN)
            switchAsian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_ASIAN)
            switchPanAsian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_PAN_ASIAN)
            switchEastern.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EASTERN)
            switchAmerican.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_AMERICAN)
            switchRussian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_RUSSIAN)
            switchMediterranean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_MEDITERANEAN)
        }

        with(binding) {

            switchEuropean.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_EUROPEAN,
                    switchEuropean.isChecked
                )
            }
            switchAsian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_ASIAN,
                    switchAsian.isChecked
                )
            }
            switchPanAsian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_PAN_ASIAN,
                    switchPanAsian.isChecked
                )
            }
            switchEastern.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_EASTERN,
                    switchEastern.isChecked
                )
            }
            switchAmerican.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_AMERICAN,
                    switchAmerican.isChecked
                )
            }
            switchRussian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_RUSSIAN,
                    switchRussian.isChecked
                )
            }
            switchMediterranean.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_MEDITERANEAN,
                    switchMediterranean.isChecked
                )
            }
        }

    }.root

    companion object {
        const val KEY_STATE_SWITCH_EUROPEAN = "european"
        const val KEY_STATE_SWITCH_ASIAN = "asian"
        const val KEY_STATE_SWITCH_PAN_ASIAN = "pan_asian"
        const val KEY_STATE_SWITCH_EASTERN = "eastern"
        const val KEY_STATE_SWITCH_AMERICAN = "american"
        const val KEY_STATE_SWITCH_RUSSIAN = "russian"
        const val KEY_STATE_SWITCH_MEDITERANEAN = "mediterranean"
    }
}

