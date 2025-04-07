package com.goody.dalda.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.oyj.domain.model.PostEntity

data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val isActive: Boolean,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
    )

    override fun writeToParcel(
        parcel: Parcel,
        flags: Int,
    ) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeByte(if (isActive) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}

fun PostEntity.toAppModel(): Post {
    return Post(
        id = this.id,
        title = this.title,
        content = this.content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        isActive = this.isActive
    )
}

fun List<PostEntity>.toAppModel(): List<Post> {
    return this.map { postDomain ->
        postDomain.toAppModel()
    }
}
