package com.berkat.kitchenstoryowner.model

import com.google.gson.annotations.SerializedName

data class KitchenResponse(
	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("data")
	val data: KitchenData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class KitchenData(

	@field:SerializedName("address")
	val address: Any? = null,

	@field:SerializedName("coordinate")
	val coordinate: Any? = null,

	@field:SerializedName("ktp_picture")
	val ktpPicture: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("isVerify")
	val isVerify: String? = null,

	@field:SerializedName("token_jwt")
	val tokenJwt: String? = null,

	@field:SerializedName("npwp_number")
	val npwpNumber: Any? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("npwp_name")
	val npwpName: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("ktp_name")
	val ktpName: String? = null,

	@field:SerializedName("bank_account")
	val bankAccount: Any? = null
)


