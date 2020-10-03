package com.futureyakutia.hackathon.appeal.generator

import android.content.Context
import android.os.Environment
import com.futureyakutia.hackathon.R
import com.futureyakutia.hackathon.appeal.Appeal
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class AppealGenerator(private val appeal: Appeal, private val context: Context) {

    companion object {
        const val TEXT_HOLDER_MEDIUM = "_____________________________________________"
        const val TEXT_HOLDER_SMALL = "_____________________"
    }

    fun generateDocx(): XWPFDocument {
        val fileIS = context.resources.openRawResource(R.raw.base_appeal)
        val document = XWPFDocument(fileIS)
        fileIS.close()
        val paragraphs: List<XWPFParagraph> = document.paragraphs
        paragraphs.forEachIndexed { _, paragraph ->
            paragraph.runs.forEach {   run ->
                when {
                    run.text().equals(AppealHolders.POLICE_STATION.holder, true) -> {
                        run.setText(TEXT_HOLDER_MEDIUM, 0)
                    }
                    run.text().equals(AppealHolders.OFFICER_NAME.holder, true) -> {
                        run.setText(TEXT_HOLDER_MEDIUM, 0)
                    }
                    run.text().equals(AppealHolders.USER_NAME.holder, true) -> {
                        run.setText(TEXT_HOLDER_MEDIUM, 0)
                    }
                    run.text().equals(AppealHolders.USER_ADDRESS.holder, true) -> {
                        run.setText(TEXT_HOLDER_MEDIUM, 0)
                    }
                    run.text().equals(AppealHolders.USER_JOB.holder, true) -> {
                        run.setText(TEXT_HOLDER_MEDIUM, 0)
                    }
                    run.text().equals(AppealHolders.COMMITMENT_DATE.holder, true) -> {
                        val text = appeal.animalAbuse.commitmentDate
                        run.setText(text, 0)
                    }
                    run.text().equals(AppealHolders.COMMITMENT_PLACE.holder, true) -> {
                        val text = appeal.animalAbuse.commitmentPlace
                        run.setText(text, 0)
                    }
                    run.text().equals(AppealHolders.COMMITMENT_TIME.holder, true) -> {
                        val text = appeal.animalAbuse.commitmentTime
                        run.setText(text, 0)
                    }
                    run.text().equals(AppealHolders.CASE_DESCRIPTION.holder, true) -> {
                        val text = appeal.animalAbuse.caseDescriptionByUser
                        run.setText(text, 0)
                    }
                    run.text().equals(AppealHolders.CHILDREN_ARE_WITNESSES.holder, true) -> {
                        if (appeal.animalAbuse.childrenWitnesses) {
                            val text = "Более того, свидетелями данных событий стали дети"
                            run.setText(text, 0)
                        }
                    }
                    run.text().equals(AppealHolders.SUSPECT.holder, true) -> {
                        val text = if (appeal.animalAbuse.suspect.isNotBlank()) {
                            appeal.animalAbuse.suspect
                        } else {
                            "Неизвестный мне"
                        }
                        run.setText(text, 0)
                    }
                    run.text().equals(AppealHolders.APPEAL_DATE.holder, true) -> {
                        val format = SimpleDateFormat("dd.MM.yyyy")
                        val text = format.format(Date())
                        run.setText(text, 0)
                    }
                    run.text().equals(AppealHolders.SIGNATURE.holder, true) -> {
                        run.setText(TEXT_HOLDER_SMALL, 0)
                    }
                    run.text().equals(AppealHolders.SIGNATURE.holder, true) -> {
                        run.setText(TEXT_HOLDER_SMALL, 0)
                    }
                }
            }
        }
        return document
    }

    fun saveDocumentInDownloads(document: XWPFDocument): File {
        val dir = Environment.getExternalStorageDirectory()
        val newFile = File("${dir.path}/Download/appeal_${appeal.animalAbuse.clause.id}.docx")
        if (!newFile.exists()) {
            newFile.createNewFile()
        }
        val fos = FileOutputStream(newFile)
        document.write(fos)
        fos.close()
        return newFile
    }

    fun getAppealId() = appeal.animalAbuse.clause.id
}
