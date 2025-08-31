package com.zibro.ecommerce.presentation.model

import com.zibro.ecommerce.domain.model.BannerList
import com.zibro.ecommerce.presentation.delegate.BannerDelegate

class BannerListVM(
    model : BannerList,
    private val bannerDelegate: BannerDelegate
) : PresentationVM<BannerList>(model){
    fun openBannerList(bannerId: String) {
        bannerDelegate.openBanner(bannerId)
        sendBannerListLog()
    }

    private fun sendBannerListLog() {

    }
}