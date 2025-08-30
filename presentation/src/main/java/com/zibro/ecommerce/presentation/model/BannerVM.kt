package com.zibro.ecommerce.presentation.model

import com.zibro.ecommerce.domain.model.Banner
import com.zibro.ecommerce.presentation.delegate.BannerDelegate

class BannerVM(
    model : Banner,
    bannerDelegate: BannerDelegate
) : PresentationVM(model), BannerDelegate by bannerDelegate {

}