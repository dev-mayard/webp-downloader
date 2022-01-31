# WebP Downloader

### 개발 및 빌드환경

* JDK 11
* kotlin 1.6.10
* gradle wrapper 7.3.2
* webp-imageio:0.1.6
* Intellij IDEA


### 설명
- 아래 두 개의 query parameter 를 받아, url의 이미지를 다운로드 한 후, q의 quality 로 webp 변환을 한 뒤 저장합니다.

|query param|설명|예시|
|------|---|---|
|url|이미지 url (content-type 이 image 여야 함) |url=https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Kotlin_Icon.svg/440px-Kotlin_Icon.svg.png|
|q|변환할 webp 파일의 quality (1 ~ 100사이 정수)|q=95|