package com.futureyakutia.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.appeal.generator.AppealGenerator
import com.futureyakutia.hackathon.model.SharingManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.appeal_created_layout.*
import javax.inject.Inject

@AndroidEntryPoint
class AppealGeneratedFragment : NavHostFragment() {

    @Inject
    lateinit var appealGenerator: AppealGenerator

    @Inject
    lateinit var sharingManager: SharingManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.appeal_created_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appeal_created_layout_linearlayout_button_download.setOnClickListener {
            val document = appealGenerator.generateDocx()
            appealGenerator.saveDocumentInDownloads(document)
            Toast.makeText(requireContext(), "Заявление загружено в /sdcard/Download", Toast.LENGTH_SHORT).show()
        }
        appeal_created_layout_textview_button_share.setOnClickListener {
            val document = appealGenerator.generateDocx()
            appealGenerator.saveDocumentInDownloads(document)
            val appealId = appealGenerator.appealId
            // todo решить, будем шарить фотку или док файл
            //sharingManager.shareFile(requireContext(), "appeal_$appealId.docx")
            sharingManager.sharePhoto(requireContext(), ContextCompat.getDrawable(requireContext(), R.drawable.share_image))
        }
    }
}
