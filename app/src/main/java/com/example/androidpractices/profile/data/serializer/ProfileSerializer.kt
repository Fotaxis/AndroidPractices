package com.example.androidpractices.profile.data.serializer

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.androidpractices.profile.domain.model.ProfileEntity
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class DataSourceProvider(private val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}

object ProfileSerializer : Serializer<ProfileEntity> {
    override val defaultValue: ProfileEntity
        get() = ProfileEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProfileEntity {
        try {
            return ProfileEntity.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: ProfileEntity, output: OutputStream) {
        t.writeTo(output)
    }
}