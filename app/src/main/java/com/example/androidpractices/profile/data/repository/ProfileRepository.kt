package com.example.androidpractices.profile.data.repository

import androidx.datastore.core.DataStore
import com.example.androidpractices.profile.domain.model.ProfileEntity
import com.example.androidpractices.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class ProfileRepository : IProfileRepository {
    private val dataStore: DataStore<ProfileEntity> by inject(
        DataStore::class.java,
        named("profile")
    )

    override suspend fun observeProfile(): Flow<ProfileEntity> = dataStore.data

    override suspend fun getProfile(): ProfileEntity? = dataStore.data.firstOrNull()

    override suspend fun setProfile(
        profilePictureURI: String, name: String, documentURL: String, profession: String
    ): ProfileEntity = dataStore.updateData {
        it.toBuilder().apply {
            this.profilePictureUri = profilePictureURI
            this.name = name
            this.documentUrl = documentURL
            this.profession = profession
        }.build()
    }
}