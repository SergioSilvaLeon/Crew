package com.nearsoft.commonlibrary.model

import org.springframework.data.annotation.Id

import java.io.Serializable

data class Task(@Id
                var id: String?,
                var name: String?,
                var category: String?) : Serializable
