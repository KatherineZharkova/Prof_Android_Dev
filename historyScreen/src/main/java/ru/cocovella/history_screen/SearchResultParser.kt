package ru.cocovella.history_screen

import ru.cocovella.repo.model.data.AppState
import ru.cocovella.repo.model.data.userdata.DataModel
import ru.cocovella.repo.model.data.userdata.Meaning

fun parseLocalSearchResults(data: AppState): AppState = AppState.Success(mapResult(data, false))

private fun mapResult(data: AppState, isOnline: Boolean): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(data: AppState.Success, isOnline: Boolean, newDataModels: ArrayList<DataModel>) {
    val dataModels: List<DataModel> = data.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(
                    DataModel(
                        searchResult.text,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (dataModel.text.isNotBlank() && dataModel.meanings.isNotEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        for (meaning in dataModel.meanings) {
            if (meaning.translatedMeaning.translatedMeaning.isBlank()) {
                newMeanings.add(
                    Meaning(
                        meaning.translatedMeaning,
                        meaning.imageUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(
                DataModel(
                    dataModel.text,
                    newMeanings
                )
            )
        }
    }
}
