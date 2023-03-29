package com.smithnoff.mytaskyapp.utils

import android.graphics.drawable.Drawable
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputLayout
import com.smithnoff.mytaskyapp.R

fun EditText.changeDrawableRight( hasDrawable:Boolean, drawable: Drawable?){
    if(hasDrawable){
        this.setCompoundDrawablesWithIntrinsicBounds(null,null, drawable,null)
    }else{
        this.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null)
    }
}

fun EditText.changeBackgroundError(hasError:Boolean, inputLayout:TextInputLayout){
    inputLayout.background =  if(hasError){
        ResourcesCompat.getDrawable(
            this.resources,
            R.drawable.shape_edittext_gray_background,
            null
        )
    }else{
       ResourcesCompat.getDrawable(
            this.resources,
            R.drawable.shape_edittext_gray_background_error,
            null
        )
    }
}
