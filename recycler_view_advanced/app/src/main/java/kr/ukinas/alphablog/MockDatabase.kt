package kr.ukinas.alphablog

class MockDatabase {
    companion object {
        fun createMockData(): ArrayList<Post> {
            val list = ArrayList<Post>()
            for (i in 0 until 300) {
                val imageToSelect = when (i % 5) {
                    0 -> R.drawable.ic_baseline_mood_24
                    1 -> R.drawable.ic_baseline_tablet_mac_24
                    2 -> R.drawable.ic_baseline_thumb_up_24
                    3 -> R.drawable.ic_baseline_water_damage_24
                    else -> R.drawable.ic_baseline_wine_bar_24
                }
                list.add(
                        Post(
                                imageToSelect,
                                "Title of post $i",
                                "Body of post $i"
                        )
                )
            }

            return list
        }
    }
}