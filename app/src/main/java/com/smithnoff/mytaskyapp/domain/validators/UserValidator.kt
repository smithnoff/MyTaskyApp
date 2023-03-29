package com.smithnoff.mytaskyapp.domain.validators

import com.smithnoff.mytaskyapp.data.models.RegisterUserRequest
import com.smithnoff.mytaskyapp.utils.ValidationResult
import com.smithnoff.mytaskyapp.utils.validateEmail
import com.smithnoff.mytaskyapp.utils.validateLength
import com.smithnoff.mytaskyapp.utils.validatePassword
import javax.inject.Inject

class UserValidator @Inject constructor() {

    fun registerUserTestValid(userInfo: RegisterUserRequest): ValidationResult {

        with(userInfo) {
            val (isValid, errorMesage) = if (!validateLength(fullName)) {
                false to
                        "Name should be at least 4 characters"
            } else {
                if (!validateEmail(email)) {
                    false to
                            "Email is invalid"
                } else {
                    if (!validatePassword(password)) {
                        false to
                                "The password needs to be at least 9 characters long and contains at least 1 Uppercase, 1 Lowercase and 1 number"
                    } else {
                        true to
                                ""
                    }
                }
            }
            return if(isValid){
                ValidationResult.Success
            }else{
                ValidationResult.Failure(errorMesage)
            }
        }
    }
}