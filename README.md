<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
<!-- [![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url] -->

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/briancatraguna/SixPackTeamApp/">
    <img src="assets/logo.png" alt="Logo">
  </a>
  <h3 align="center">An Emergency Service App</h3>
  <p align="center">
    by Six Pack (B21-CAP0031)
    <br />
    <a href="https://github.com/briancatraguna/SixPackTeamApp/wiki"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/briancatraguna/SixPackTeamApp/">View Demo</a>
    ·
    <a href="https://github.com/briancatraguna/SixPackTeamApp/">Video Presentation</a>
    ·
    <a href="https://bit.ly/sixpackslides">Slide Deck</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#flow">Flow</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#directory-guide">Directory Guide</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
<!--     <li><a href="#contributing">Contributing</a></li> -->
    <li><a href="#license">License</a></li>
    <li><a href="#our-team">Our Team</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>
<!--  -->


<!-- ABOUT THE PROJECT -->
## 🎉 About The Project

Did you know that a huge amount of emergency cases remain unhandled? Some of the victims may lose their lives because of the long response time of the emergency service. While emergency services must always be a priority, we are here to help Indonesia to have a better emergency service. 🇮🇩

Our app implements machine learning technology to correctly generate reports from user voice and automatically send it to the responser app. Our superior technology can accelerate responders to respond appropriately according to the user’s needs and could save many lives.

### 💁‍♀️ Flow
<p align='center'>
  <a href="https://github.com/briancatraguna/SixPackTeamApp/"> <img src="assets/our-flow.png" alt="Our Flow"> </a>
</p>

### 🏗️ Built With

* [Tensorflow](https://tensorflow.com)
* [Android Studio](https://developer.android.com/studio/)
* [Google Compute Engine](https://cloud.google.com/compute)
* [Flask](https://flask.palletsprojects.com)
<!-- *  -->


<!-- DIRECTORY GUIDE -->
## 🦮 Directory Guide

No. | Dir | Details
--- | --- | ---
1 | `EmergencyApp` | User application: for emergency reporting, see news, and see tips while in emergency situation.
2 | `ResponderApp` | Reponder application: the app used for responders to read the automated reports and update status on the emergency case.
3 | `assets` | Contains all images for this repository.
4 | `build_df` | Processing data from scraping result, generate train and test data for Machine Learning
5 | `classification`| Classify user report to its label.
6 | `data` | Consists all data: raw, preprocessed, filtered, data for classification and NER.
7 | `flask` | Contains the code to create an API to do ML inferences by the Android App.
8 | `ner` | Extract entities from user's voice transcription.
9 | `scraping` | Gathered data or scraping through this [website](https://lapor.go.id).
<!--  -->


<!-- GETTING STARTED -->
## ⭐ Getting Started

To get a local copy up and running follow these simple example steps.
<br>
#### DISCLAIMER: This app will only work when the admin of the cloud server has opened the SSH connection to the android in order to run ML inferences. But you can set up your own virtual machine in your own cloud console.</br>

### ❗ Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* Android emulator or device
* Internet connection
* Location services (GPS)
* Setup cloud server: for more info about setting up the cloud server, click this [link](https://github.com/briancatraguna/SixPackTeamApp/tree/master/flask)

### Connection to the Cloud Server in Android Studio
Once you have followed the instructions in how to setup the cloud server you must:
1. Go to your app level `build.gradle`
2. Modify this part: (`YOUR_NER_BASE_URL`,`YOUR_CLASSIFICATION_BASE_URL`)
```
android {
    .....

    defaultConfig {
        .....
        buildConfigField('String','NER_BASE_URL','"YOUR_NER_BASE_URL"')
        buildConfigField('String','CLASSIFICATION_BASE_URL','"YOUR_CLASSIFICATION_BASE_RUL"')
        .....
    }
    .....
}
```

### ⚙️ Installation
There are two ways in which you can install this app.

Firstly you can fork this repository and run either one of these projects:
- `EmergencyApp`
- `ResponderApp`
On your android studio to be installed in your emulator/device

Or you can download our APK at [this link](https://drive.google.com/drive/folders/1lILNDEo_bthNN-wvuQXULXfjTwbsNP_-?usp=sharing)
<!--  -->


<!-- USAGE EXAMPLES -->
## 📱 Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://github.com/briancatraguna/SixPackTeamApp/wiki)_
<!--  -->

<!-- CONTRIBUTING -->
<!-- ## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request -->

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.
<!--  -->


<!-- OUR TEAM -->
## 🧑‍🤝‍🧑 Our Team
` insert picture here`
 name | github | linkedin 
 ---  |  ---   | ---      
 Annisa Nuri Nabila | <a href="https://github.com/annisann"> <img src="assets/github.svg" alt="GitHub"></a> | <a href="https://www.linkedin.com/in/annisann"><img src="assets/linkedin.svg" alt="LinkedIn"> </a>
Brian Mohammed Catraguna | <a href="https://github.com/briancatraguna"><img src="assets/github.svg" alt="GitHub"></a> | <a href="https://www.linkedin.com/in/"><img src="assets/linkedin.svg" alt="LinkedIn"></a>
Fadia Hanifa Suwandoko | <a href="https://github.com/fadiahanifa"><img src="assets/github.svg" alt="GitHub"></a> | <a href="https://www.linkedin.com/in/"><img src="assets/linkedin.svg" alt="LinkedIn"></a>
Michael Wijaya | <a href="https://github.com/Michael-Wijayaa"><img src="assets/github.svg" alt="GitHub"></a> | <a href="https://www.linkedin.com/in/"><img src="assets/linkedin.svg" alt="LinkedIn"></a>
Rahmat Syawaludin | <a href="https://github.com/rahmatsywldn"><img src="assets/github.svg" alt="GitHub"></a> | <a href="https://www.linkedin.com/in/rahmatsywldn"><img src="assets/linkedin.svg" alt="LinkedIn"></a>
Septin Lutfiyatul Munawaroh | <a href="https://github.com/septinlutf"><img src="assets/github.svg" alt="GitHub"></a> | <a href="https://www.linkedin.com/in/septinlutf"><img src="assets/linkedin.svg" alt="LinkedIn"></a>
<!--  -->


<!-- ACKNOWLEDGEMENTS -->
## 💌 Acknowledgements
* [Img Shields](https://shields.io)
* [Choose an Open Source License](https://choosealicense.com)
* [README Template](https://github.com/othneildrew/Best-README-Template/)

```
1. Hossain, M. M., Sharmin, M., & Ahmed, S. (2018). Bangladesh Emergency Services. Proceedings of the 1st ACM SIGCAS Conference on Computing and Sustainable Societies. https://doi.org/10.1145/3209811.3209870
2. Edillo, Shallom & Garrote, Pamela & Domingo, Lucky & Malapit, Arianne & Fabito, Bernie. (2017). A mobile based emergency reporting application for the Philippine National Police Emergency Hotline 911: A case for the development of i911. 1-4. 10.23919/ICMU.2017.8330110
3. Klein, B., Laiseca, X., Casado-Mansilla, D., López-de-Ipiña, D., & Nespral, A. P. (2012). Detection and Extracting of Emergency Knowledge from Twitter Streams. Ubiquitous Computing and Ambient Intelligence, 462–469. https://doi.org/10.1007/978-3-642-35377-2_64 
```
<p align="center">
  <h3 align="center">Special thanks to Bangkit Academy 2021 🤍
  </h3>
</p>
<p align="center">
  <a href="https://grow.google/intl/id_id/bangkit/">
    <img src="assets/bangkit.png" alt="Bangkit Academy" height=315 width=600>
  </a>
</p>
<!--  -->


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
