package com.ocram.qichwadic.features.favorites.domain

import com.ocram.qichwadic.core.domain.model.DefinitionModel

interface FavoriteRepository {

    suspend fun getFavorites(): List<DefinitionModel>

    suspend fun addFavorite(favorite: DefinitionModel): Long

    suspend fun removeFavorite(favorite: DefinitionModel): Int

    suspend fun clearFavorites(): Int

}