package presentation.signup

import core.ResourcePath


enum class CurrentProcessesPosition(
    val currentPosition: Int = 0,
    val heading: String = "",
    val subTitle: String = ""
) {
    FillBio(
        currentPosition = 0,
        heading = ResourcePath.String.bioHeading,
        subTitle = ResourcePath.String.bioSubTitle
    ),
    PaymentMethod(
        currentPosition = 1,
        heading = ResourcePath.String.paymentHeading,
        subTitle = ResourcePath.String.paymentSubTitle
    ),
    UploadPhoto(
        currentPosition = 2,
        heading = ResourcePath.String.uploadPhotoHeading,
        subTitle = ResourcePath.String.uploadPhotoSubTitle
    ),
    PhotoPreview(
        currentPosition = 3,
        heading = ResourcePath.String.previewPhotoHeading,
        subTitle = ResourcePath.String.previewPhotoSubTitle
    ),
    Location(
        currentPosition = 4,
        heading = ResourcePath.String.locationHeading,
        subTitle = ResourcePath.String.locationSubTitle
    ),
}

enum class UploadPhotoOption(val icon: String, val label: String) {
    Camera(icon = ResourcePath.Drawable.iconCamera, label = ResourcePath.String.takePhoto),
    Gallery(icon = ResourcePath.Drawable.iconGallery, label = ResourcePath.String.fromGallery),
}
