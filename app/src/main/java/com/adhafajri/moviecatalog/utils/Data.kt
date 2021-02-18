package com.adhafajri.moviecatalog.utils

import com.adhafajri.moviecatalog.data.CatalogEntity
import com.adhafajri.moviecatalog.data.PersonEntity

object Data {

    fun generateCatalogs(): ArrayList<CatalogEntity> {
        val catalogs = ArrayList<CatalogEntity>()

        catalogs.add(
            CatalogEntity(
                "c1",
                "movie",
                "The Godfather",
                "1972",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iVZ3JAcAjmguGPnRNfWFOtLHOuY.jpg",
                "https://www.youtube.com/watch?v=1x0GpEZnwa8",
                "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c2",
                "movie",
                "Inception",
                "2010",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                "https://www.youtube.com/watch?v=YoHD9XEInc0",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c3",
                "movie",
                "The Dark Knight",
                "2008",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                "https://www.youtube.com/watch?v=EXeTwQWrcwY",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c4",
                "movie",
                "Star Wars: Episode V - The Empire Strikes Back",
                "1980",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s12hAKDCui6S8GIyRIaz6oKe5OW.jpg",
                "https://www.youtube.com/watch?v=JNwNXF9Y6kY",
                "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c5",
                "movie",
                "The Matrix",
                "1999",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
                "https://www.youtube.com/watch?v=vKQi3bBA1y8",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, " +
                        "he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c6",
                "tv_show",
                "The Falcon and the Winter Soldier",
                "2021",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "https://www.youtube.com/watch?v=IWBsDaFWyTE",
                "Following the events of “Avengers: Endgame”, the Falcon, " +
                        "Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c7",
                "tv_show",
                "WandaVision",
                "2021",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "https://www.youtube.com/watch?v=sj9J2ecsSpo",
                "Blends the style of classic sitcoms with the MCU in which Wanda Maximoff and Vision -two " +
                        "super-powered beings living their ideal suburban lives-begin to suspect that everything is not as it seems.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c8",
                "tv_show",
                "Chernobyl",
                "2019",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hlLXt2tOPT6RRnjiUmoxyG1LTFi.jpg",
                "https://www.youtube.com/watch?v=s9APLXM9Ei8",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c9",
                "tv_show",
                "Game of Thrones",
                "2011",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "https://www.youtube.com/watch?v=bjqEWgDVPe0",
                "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
            )
        )
        catalogs.add(
            CatalogEntity(
                "c10",
                "tv_show",
                "Sherlock",
                "2010",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg",
                "https://www.youtube.com/watch?v=xK7S9mrFWL4",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
            )
        )

        return catalogs
    }

    fun generatePersons(): ArrayList<PersonEntity> {
        val persons = ArrayList<PersonEntity>()

        persons.add(
            PersonEntity(
                "c1p1",
                "c1",
                "Francis Ford Coppola"
            )
        )
        persons.add(
            PersonEntity(
                "c1p2",
                "c1",
                "Mario Puzo"
            )
        )
        persons.add(
            PersonEntity(
                "c2p1",
                "c2",
                "Christopher Nolan"
            )
        )
        persons.add(
            PersonEntity(
                "c3p1",
                "c3",
                "Christopher Nolan"
            )
        )
        persons.add(
            PersonEntity(
                "c3p2",
                "c3",
                "Bill Finger"
            )
        )
        persons.add(
            PersonEntity(
                "c3p3",
                "c3",
                "Bob Kane"
            )
        )
        persons.add(
            PersonEntity(
                "c3p4",
                "c3",
                "Jerry Robinson"
            )
        )
        persons.add(
            PersonEntity(
                "c3p5",
                "c3",
                "Jonathan Nolan"
            )
        )
        persons.add(
            PersonEntity(
                "c3p6",
                "c3",
                "David S. Goyer"
            )
        )
        persons.add(
            PersonEntity(
                "c4p1",
                "c4",
                "Irvin Kershner"
            )
        )
        persons.add(
            PersonEntity(
                "c4p2",
                "c4",
                "Lawrence Kasdan"
            )
        )
        persons.add(
            PersonEntity(
                "c4p3",
                "c4",
                "Leigh Brackett"
            )
        )
        persons.add(
            PersonEntity(
                "c4p4",
                "c4",
                "George Lucas"
            )
        )
        persons.add(
            PersonEntity(
                "c5p1",
                "c5",
                "Lana Wachowski"
            )
        )
        persons.add(
            PersonEntity(
                "c5p2",
                "c5",
                "Lilly Wachowski"
            )
        )
        persons.add(
            PersonEntity(
                "c6p1",
                "c6",
                "Malcolm Spellman"
            )
        )
        persons.add(
            PersonEntity(
                "c7p1",
                "c7",
                "Jac Schaeffer"
            )
        )
        persons.add(
            PersonEntity(
                "c8p1",
                "c8",
                "Craig Mazin"
            )
        )
        persons.add(
            PersonEntity(
                "c9p1",
                "c9",
                "David Benioff"
            )
        )
        persons.add(
            PersonEntity(
                "c9p2",
                "c9",
                "D. B. Weiss"
            )
        )
        persons.add(
            PersonEntity(
                "c10p1",
                "c10",
                "Mark Gatiss"
            )
        )
        persons.add(
            PersonEntity(
                "c10p2",
                "c10",
                "Steven Moffat"
            )
        )
        
        return persons
    }
}