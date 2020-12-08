package ru.cocovella.repo

import ru.cocovella.repo.model.data.dto.SearchResultDto

class RepositoryImplementation(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> = dataSource.getData(word)
}
