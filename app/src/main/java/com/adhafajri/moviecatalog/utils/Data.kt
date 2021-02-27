package com.adhafajri.moviecatalog.utils

import com.adhafajri.moviecatalog.data.source.local.entity.*

object Data {

    fun generateCatalogs(): ArrayList<CatalogEntity> {
        val catalogs = ArrayList<CatalogEntity>()

        catalogs.add(
            CatalogEntity(
                "c1",
                Constant.MOVIE,
                "The Godfather",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iVZ3JAcAjmguGPnRNfWFOtLHOuY.jpg",
                "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c2",
                Constant.MOVIE,
                "Inception",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c3",
                Constant.MOVIE,
                "The Dark Knight",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c4",
                Constant.MOVIE,
                "Star Wars: Episode V - The Empire Strikes Back",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s12hAKDCui6S8GIyRIaz6oKe5OW.jpg",
                "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c5",
                Constant.MOVIE,
                "The Matrix",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, " +
                        "he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c6",
                Constant.TV_SHOW,
                "The Falcon and the Winter Soldier",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "Following the events of “Avengers: Endgame”, the Falcon, " +
                        "Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c7",
                Constant.TV_SHOW,
                "WandaVision",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "Blends the style of classic sitcoms with the MCU in which Wanda Maximoff and Vision -two " +
                        "super-powered beings living their ideal suburban lives-begin to suspect that everything is not as it seems.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c8",
                Constant.TV_SHOW,
                "Chernobyl",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hlLXScreenplaytOPT6RRnjiUmoxyG1LTFi.jpg",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c9",
                Constant.TV_SHOW,
                "Game of Thrones",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c10",
                Constant.TV_SHOW,
                "Sherlock",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
            )
        )

        return catalogs
    }

    fun generatePersons(): ArrayList<PersonEntity> {
        val persons = ArrayList<PersonEntity>()

        persons.add(
            PersonEntity(
                "p1",
                "Francis Ford Coppola"
            )
        )
        persons.add(
            PersonEntity(
                "p2",
                "Mario Puzo"
            )
        )
        persons.add(
            PersonEntity(
                "p3",
                "Christopher Nolan"
            )
        )
        persons.add(
            PersonEntity(
                "p4",
                "Christopher Nolan",
            )
        )
        persons.add(
            PersonEntity(
                "p5",
                "Bill Finger"
            )
        )
        persons.add(
            PersonEntity(
                "p6",
                "Bob Kane"
            )
        )
        persons.add(
            PersonEntity(
                "p7",
                "Jerry Robinson"
            )
        )
        persons.add(
            PersonEntity(
                "p8",
                "Jonathan Nolan"
            )
        )
        persons.add(
            PersonEntity(
                "p9",
                "David S. Goyer"
            )
        )
        persons.add(
            PersonEntity(
                "p10",
                "Irvin Kershner"
            )
        )
        persons.add(
            PersonEntity(
                "p11",
                "Lawrence Kasdan"
            )
        )
        persons.add(
            PersonEntity(
                "p12",
                "Leigh Brackett"
            )
        )
        persons.add(
            PersonEntity(
                "p13",
                "George Lucas"
            )
        )
        persons.add(
            PersonEntity(
                "p14",
                "Lana Wachowski"
            )
        )
        persons.add(
            PersonEntity(
                "p15",
                "Lilly Wachowski"
            )
        )
        persons.add(
            PersonEntity(
                "p16",
                "Malcolm Spellman"
            )
        )
        persons.add(
            PersonEntity(
                "p17",
                "Jac Schaeffer"
            )
        )
        persons.add(
            PersonEntity(
                "p18",
                "Craig Mazin"
            )
        )
        persons.add(
            PersonEntity(
                "p19",
                "David Benioff"
            )
        )
        persons.add(
            PersonEntity(
                "p20",
                "D. B. Weiss"
            )
        )
        persons.add(
            PersonEntity(
                "p21",
                "Mark Gatiss"
            )
        )
        persons.add(
            PersonEntity(
                "p22",
                "Steven Moffat"
            )
        )

        return persons
    }

    fun generatePersonJobs(): ArrayList<PersonJobEntity> {
        val personJobs = ArrayList<PersonJobEntity>()

        personJobs.add(
            PersonJobEntity(
                "p1",
                "Francis Ford Coppola",
                "Director"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p1",
                "Francis Ford Coppola",
                "Writer"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p2",
                "Mario Puzo",
                "Novel"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p5",
                "p2",
                "Screenplay"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p6",
                "p3",
                "Directors"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p7",
                "p3",
                "Writer"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p7",
                "p4",
                "Directors"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p8",
                "p4",
                "Screenplay"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p9",
                "p4",
                "Story"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p10",
                "p5",
                "Characters"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p1",
                "p6",
                "Characters"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p2",
                "p7",
                "Characters"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p3",
                "p8",
                "Screenplay"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p4",
                "p9",
                "Story"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p5",
                "p10",
                "Directors"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p7",
                "p11",
                "Screenplay"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p8",
                "p12",
                "Screenplay"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p9",
                "p13",
                "Story"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p0",
                "p14",
                "Directors"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p1",
                "p14",
                "Writer"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p2",
                "p15",
                "Directors"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p3",
                "p15",
                "Writer"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p4",
                "p16",
                "Creator"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p5",
                "p17",
                "Creator"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p6",
                "p18",
                "Creator"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p7",
                "p19",
                "Creator"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p8",
                "p20",
                "Creator"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p9",
                "p21",
                "Creator"
            )
        )
        personJobs.add(
            PersonJobEntity(
                "p0",
                "p22",
                "Creator"
            )
        )

        return personJobs
    }

    fun generateVideos(): ArrayList<VideoEntity> {
        val videoList = ArrayList<VideoEntity>()

        videoList.add(
            VideoEntity(
                "v1",
                "8UVNT4wvIGY",
                "YouTube"
            )
        )
        videoList.add(
            VideoEntity(
                "v2",
                "R5VD98U9Zto",
                "YouTube"
            )
        )

        return videoList
    }

    fun generateCredits(): ArrayList<CreditEntity> {
        val creditList = ArrayList<CreditEntity>()

        creditList.add(
            CreditEntity(
                "c1",
                PersonEntity(
                    "p1",
                    "Francis Ford Coppola"
                )
            )
        )
        creditList.add(
            CreditEntity(
                "c2",
                PersonEntity(
                    "p2",
                    "Mario Puzo"
                )
            )
        )

        return creditList
    }
}