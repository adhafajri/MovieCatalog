package com.adhafajri.moviecatalog.utils

import com.adhafajri.moviecatalog.data.CatalogEntity

object Data {

    fun generateCatalogs(type: String): ArrayList<CatalogEntity> {
        val catalogs = ArrayList<CatalogEntity>()

        when (type) {
            Constant.MOVIES -> {
                catalogs.add(CatalogEntity("m1",
                    "The Godfather",
                    "1972",
                    "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR3,0,182,268_AL_.jpg",
                    "https://www.imdb.com/video/vi1348706585?playlistId=tt0068646&ref_=tt_ov_vi",
                    "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
                    "Francis Ford Coppola",
                    "Mario Puzo, Francis Ford Coppola",
                    "Marlon Brando, Al Pacino, James Caan"))
                catalogs.add(CatalogEntity("m2",
                    "Inception",
                    "2010",
                    "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=YoHD9XEInc0",
                    "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                    "Christopher Nolan",
                    "Christopher Nolan",
                    "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page"))
                catalogs.add(CatalogEntity("m3",
                    "The Dark Knight",
                    "2008",
                    "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=EXeTwQWrcwY",
                    "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                    "Christopher Nolan",
                    "Jonathan Nolan (screenplay), Christopher Nolan (screenplay)",
                    "Christian Bale, Heath Ledger, Aaron Eckhart"))
                catalogs.add(CatalogEntity("m4",
                    "Star Wars: Episode V - The Empire Strikes Back",
                    "1980",
                    "https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=JNwNXF9Y6kY",
                    "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.",
                    "Irvin Kershner",
                    "Leigh Brackett (screenplay by), Lawrence Kasdan (screenplay by)",
                    "Mark Hamill, Harrison Ford, Carrie Fisher"))
                catalogs.add(CatalogEntity("m4",
                    "The Matrix",
                    "1999",
                    "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=vKQi3bBA1y8",
                    "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
                    "Lana Wachowski, Lilly Wachowski",
                    "Lana Wachowski, Lilly Wachowski",
                    "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss"))
            }
            Constant.SHOWS -> {
                catalogs.add(CatalogEntity("s1",
                    "The Falcon and the Winter Soldier",
                    "2021",
                    "https://m.media-amazon.com/images/M/MV5BNTU5YmNiYmItM2VjZC00MGNjLWE1YjEtOGUzMDU5OGZkYjA5XkEyXkFqcGdeQXVyNzEzNjU1NDg@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=IWBsDaFWyTE",
                    "TV series centering on Marvel Cinematic Universe characters Falcon and The Winter Soldier.",
                    "",
                    "",
                    "Daniel Brühl, Desmond Chiam, John Gettier"))
                catalogs.add(CatalogEntity("s2",
                    "WandaVision",
                    "2021",
                    "https://m.media-amazon.com/images/M/MV5BYjJiZmE5ZDgtYWUxZi00MWI1LTg2MmEtZmUwZGE2YzRkNTE5XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=sj9J2ecsSpo",
                    "Blends the style of classic sitcoms with the MCU in which Wanda Maximoff and Vision -two super-powered beings living their ideal suburban lives-begin to suspect that everything is not as it seems.",
                    "Matt Shakman",
                    "",
                    "Elizabeth Olsen, Paul Bettany, Teyonah Parris"))
                catalogs.add(CatalogEntity("s3",
                    "Chernobyl",
                    "2019",
                    "https://m.media-amazon.com/images/M/MV5BZGQ2YmMxZmEtYjI5OS00NzlkLTlkNTEtYWMyMzkyMzc2MDU5XkEyXkFqcGdeQXVyMzQ2MDI5NjU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.youtube.com/watch?v=s9APLXM9Ei8",
                    "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
                    "Johan Renck",
                    "Craig Mazin",
                    "Jessie Buckley, Jared Harris, Stellan Skarsgård"))
                catalogs.add(CatalogEntity("s4",
                    "Game of Thrones",
                    "2011",
                    "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_UY268_CR7,0,182,268_AL_.jpg",
                    "https://www.imdb.com/video/vi1509866521?playlistId=tt0944947&ref_=tt_ov_vi",
                    "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
                    "David Benioff, D.B. Weiss",
                    "",
                    "Emilia Clarke, Peter Dinklage, Kit Harington"))
                catalogs.add(CatalogEntity("s5",
                    "Sherlock",
                    "2010",
                    "https://m.media-amazon.com/images/M/MV5BMWY3NTljMjEtYzRiMi00NWM2LTkzNjItZTVmZjE0MTdjMjJhL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTQ4NTc5OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
                    "https://www.imdb.com/video/vi1741338137?playlistId=tt1475582&ref_=tt_ov_vi",
                    "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                    "Mark Gatiss, Steven Moffat",
                    "",
                    "Benedict Cumberbatch, Martin Freeman, Una Stubbs"))
            }
        }

        return catalogs
    }
}