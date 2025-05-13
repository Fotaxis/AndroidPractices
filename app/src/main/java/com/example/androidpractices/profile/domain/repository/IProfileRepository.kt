package com.example.androidpractices.profile.domain.repository

import com.example.androidpractices.profile.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(
        profilePictureURI: String,
        name: String,
        documentURL: String,
        profession: String
    ): ProfileEntity

    suspend fun observeProfile(): Flow<ProfileEntity>
}