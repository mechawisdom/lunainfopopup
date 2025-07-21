# LunaInfoPopup

Android için kolay kullanımlı, özelleştirilebilir bilgi popup kütüphanesi.

---

## Kullanım

### 1. Repository ekleme

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.mechawisdom:lunainfopopup:1.0.0'
}


val popup = LunaInfoPopup(this, lifecycle)
    .setCornerRadius(12f)
    .setBackgroundColor(Color.parseColor("#333333"))
    .setTextColor(Color.WHITE)
    .setWidth(PopupWidth.LARGE)
    .setTextSizeSp(16f)
    .setFontFamily(Typeface.SANS_SERIF)

popup.showPopup("Bilgi mesajı", anchorView)
