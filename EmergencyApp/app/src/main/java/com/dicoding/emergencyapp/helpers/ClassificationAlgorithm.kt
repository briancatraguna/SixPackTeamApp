package com.dicoding.emergencyapp.helpers

class ClassificationAlgorithm(private val rawString: String) {

    companion object{
        private const val crime = "Crime"
        private const val medical = "Medical"
        private const val fire = "Fire"
        private const val naturalDisaster = "Natural Disaster"
        private const val trafficAccident = "Traffic Accident"
        private const val unknown = "Unknown"
    }

    fun getClass(): String{
        val wordList: List<String> = getList()
        var result: String = unknown
        for (word in wordList){
            if (word in crimeKeyWords){
                result =  crime
            } else if (word in medicalKeyWords){
                result = medical
            } else if (word in fireKeyWords){
                result =  fire
            } else if (word in naturalDisasterKeyWords){
                result = naturalDisaster
            } else if (word in trafficAccidentKeyWords){
                result = trafficAccident
            }
        }
        return result
    }

    private fun getList(): List<String>{
        val processedString = rawString.toLowerCase()
        val words = processedString.split("\\s+".toRegex()).map { word ->
            word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
        }
        return words
    }

    private val crimeKeyWords = listOf<String>(
        "kejahatan",
        "perampok",
        "culik",
        "penculikan",
        "begal",
        "senjata",
        "tawuran",
        "copet"
    )

    private val medicalKeyWords = listOf<String>(
        "sakit",
        "luka",
        "pedih",
        "berdarah",
        "darah",
        "kritis",
        "perih",
        "cidera",
        "cedera",
    )

    private val fireKeyWords = listOf<String>(
        "kebakaran",
        "api",
        "terbakar",
        "panas",
        "memadamkan",
        "bakar",
        "meledak",
        "ledakan",
    )

    private val naturalDisasterKeyWords = listOf<String>(
        "Bencana",
        "Gempa",
        "Tsunami",
        "Letusan",
        "Gunung",
        "Meletus",
        "Alam",
        "Angin"
    )

    private val trafficAccidentKeyWords = listOf<String>(
        "Tabrakan",
        "Mobil",
        "Truk",
        "Motor",
        "Tabrak",
    )


}