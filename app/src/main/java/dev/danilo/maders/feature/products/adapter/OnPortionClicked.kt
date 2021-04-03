package dev.danilo.maders.feature.products.adapter

import dev.danilo.maders.model.Portion

interface OnPortionClicked {
    fun onPortionClicked(portion: Portion)
}
