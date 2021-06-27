package com.khoiron14.moviecatalogue.utils

import com.khoiron14.moviecatalogue.data.source.remote.response.Movie
import com.khoiron14.moviecatalogue.data.source.remote.response.TvShow

/**
 * Created by khoiron14 on 11/18/2019.
 */
class DataDummy {

    fun generateDummyMovie(): ArrayList<Movie> {

        val movies = arrayListOf<Movie>()

        movies.add(
            Movie(
                330457,
                "Frozen II",
                "5.1",
                "2019-11-20",
                "Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.",
                "/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg",
                "/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg"
            )
        )

        movies.add(
            Movie(
                475557,
                "Joker",
                "8.4",
                "2019-10-02",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg"
            )
        )

        movies.add(
            Movie(
                290859,
                "Terminator: Dark Fate",
                "6.4",
                "2019-10-23",
                "More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani's survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope.",
                "/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg",
                "/a6cDxdwaQIFjSkXf7uskg78ZyTq.jpg"
            )
        )

        movies.add(
            Movie(
                486589,
                "Red Shoes and the Seven Dwarfs",
                "6.4",
                "2019-07-25",
                "Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.",
                "/MBiKqTsouYqAACLYNDadsjhhC0.jpg",
                "/bga3i5jcejBekr2FCGJga1fYCh.jpg"
            )
        )

        movies.add(
            Movie(
                449924,
                "葉問4",
                "0",
                "2019-12-20",
                "Ip Man 4 is an upcoming Hong Kong biographical martial arts film directed by Wilson Yip and produced by Raymond Wong. It is the fourth in the Ip Man film series based on the life of the Wing Chun grandmaster of the same name and features Donnie Yen reprising the role. The film began production in April 2018 and ended in July the same year.",
                "/vn94LlNrbUWIZZyAdmvUepFBeaY.jpg",
                "/ekP6EVxL81lZ4ivcqPsoZ72rY0h.jpg"
            )
        )

        movies.add(
            Movie(
                429617,
                "Spider-Man: Far from Home",
                "7.6",
                "2019-06-28",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg"
            )
        )

        movies.add(
            Movie(
                420818,
                "The Lion King",
                "7.1",
                "2019-07-12",
                "Simba idolizes his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
                "/nRXO2SnOA75OsWhNhXstHB8ZmI3.jpg"
            )
        )

        movies.add(
            Movie(
                423204,
                "Angel Has Fallen",
                "5.8",
                "2019-08-21",
                "After the events in the previous film, Secret Service agent Mike Banning finds himself framed for an assassination attempt on the President. Pursued by his own agency and the FBI, Banning races to clear his name and uncover the real terrorist threat which has set its sights on Air Force One.",
                "/fapXd3v9qTcNBTm39ZC4KUVQDNf.jpg",
                "/k2WyDw2NTUIWnuEs5gT7wgrCQg6.jpg"
            )
        )

        movies.add(
            Movie(
                458897,
                "Charlie's Angels",
                "6.9",
                "2019-11-14",
                "When a systems engineer blows the whistle on a dangerous technology, Charlie's Angels from across the globe are called into action, putting their lives on the line to protect society.",
                "/36YiDLw3IWkQyhfJnCjG2GCNgg9.jpg",
                "/P7QwQE8HJaB5bIIACyRdXVV7gO.jpg"
            )
        )

        movies.add(
            Movie(
                453405,
                "Gemini Man",
                "5.7",
                "2019-10-02",
                "Henry Brogen, an aging assassin tries to get out of the business but finds himself in the ultimate battle: fighting his own clone who is 25 years younger than him and at the peak of his abilities.",
                "/uTALxjQU8e1lhmNjP9nnJ3t2pRU.jpg",
                "/c3F4P2oauA7IQmy4hM0OmRt2W7d.jpg"
            )
        )

        return movies
    }

    fun generateDummyTvShow(): ArrayList<TvShow> {

        val tvShows = arrayListOf<TvShow>()

        tvShows.add(
            TvShow(
                82856,
                "The Mandalorian",
                "7.7",
                "2019-11-12",
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic.",
                "/BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg"
            )
        )

        tvShows.add(
            TvShow(
                60625,
                "Rick and Morty",
                "8.6",
                "2013-12-02",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "/mzzHr6g1yvZ05Mc7hNj3tUdy2bM.jpg"
            )
        )

        tvShows.add(
            TvShow(
                68507,
                "His Dark Materials",
                "7.8",
                "2019-11-03",
                "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
                "/xOjRNnQw5hqR1EULJ2iHkGwJVA4.jpg",
                "/9yKCJTOh9m3Lol2RY3kw99QPH6x.jpg"
            )
        )

        tvShows.add(
            TvShow(
                1412,
                "Arrow",
                "5.8",
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg"
            )
        )

        tvShows.add(
            TvShow(
                71641,
                "4 Blocks",
                "5.6",
                "2017-05-08",
                "Based in Neukölln, Berlin Toni manages the daily business of dealing with the Arabic gangs and ends up wanting to leave his old life behind for his family, but as expected, its never that simple.",
                "/jVObyxtNxuPbG5czuKvm7pW56EV.jpg",
                "/jEdce7g6VZHMoJ7DANX8NFQkVAW.jpg"
            )
        )

        tvShows.add(
            TvShow(
                456,
                "The Simpsons",
                "7.2",
                "1989-12-17",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "/f5uNbUC76oowt5mt5J9QlqrIYQ6.jpg"
            )
        )

        tvShows.add(
            TvShow(
                62286,
                "Fear the Walking Dead",
                "6.3",
                "2015-08-23",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
                "/7r4FieFH6Eh6S55hi8c9LOiFENg.jpg"
            )
        )

        tvShows.add(
            TvShow(
                60735,
                "The Flash",
                "6.7",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg"
            )
        )

        tvShows.add(
            TvShow(
                2734,
                "Law & Order: Special Victims Unit",
                "6.5",
                "1999-09-20",
                "In the criminal justice system, sexually-based offenses are considered especially heinous. In New York City, the dedicated detectives who investigate these vicious felonies are members of an elite squad known as the Special Victims Unit. These are their stories.",
                "/6t6r1VGQTTQecN4V0sZeqsmdU9g.jpg",
                "/cD9PxbrdWYgL7MUpD9eOYuiSe2P.jpg"
            )
        )

        tvShows.add(
            TvShow(
                1622,
                "Supernatural",
                "7.4",
                "2005-09-13",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "/KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg"
            )
        )

        return tvShows
    }
}