# LunaInfoPopup

🎯 **LunaInfoPopup**, Android projelerinde kullanıcıya bağlamsal bilgi sunmak için kullanılan, özelleştirilebilir ve hafif bir popup bileşenidir. Basit bir şekilde entegre edilir, modern görünümlüdür ve tamamen özelleştirilebilir yapıdadır.

---

## ✨ Özellikler

- 🎨 Dinamik tema desteği (gündüz/gece)
- 📏 Ayarlanabilir genişlik: `SMALL`, `MEDIUM`, `LARGE`, `FULL`
- 🧱 Köşe yumuşaklığı (radius) ve padding kontrolü
- 🖋 Yazı tipi (`Typeface`) ve yazı boyutu ayarlanabilir
- 🎭 Arka plan rengi veya özel drawable desteği
- 🔼 Üstten veya alttan gösterim üçgeni

---



https://github.com/user-attachments/assets/b6481cea-bc31-4c8f-bfc7-afefe0d36bc1



## 🚀 Kurulum

### 1. `settings.gradle` dosyanıza JitPack’i ekleyin:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
implementation("com.github.mechawisdom:lunainfopopup:1.0.0")

## 🚀 Kullanım
val popup = LunaInfoPopup(this, lifecycle)
    .setCornerRadius(12f)
    .setPaddingDp(16)
    .setBackgroundColor(Color.parseColor("#333333"))
    .setTextColor(Color.WHITE)
    .setWidth(PopupWidth.LARGE)
    .setTextSizeSp(16f)
    .setFontFamily(Typeface.SANS_SERIF)



