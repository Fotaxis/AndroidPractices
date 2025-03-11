package com.example.androidpractices.gameList.data.mock

import com.example.androidpractices.gameList.domain.entity.*

object GamesData {
    val gamesShort = listOf(
        GameShortEntity(
            58764,
            "Outer Wilds",
            false,
            "2019-05-29",
            "https://media.rawg.io/media/games/9f4/9f418898f5415668ca47b5f4ab1ecfeb.jpg",
            listOf(Platform("PC"), Platform("Xbox One"),
                Platform("PlayStation 4"), Platform("Nintendo Switch"))
        ),
        GameShortEntity(
            3498,
            "Grand Theft Auto V",
            false,
            "2013-09-17",
            "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
            listOf(Platform("PC"), Platform("Xbox 360"),
                Platform("PlayStation 3"), Platform("Xbox One"),
                Platform("PlayStation 4"), Platform("PlayStation 5"),
                Platform("Xbox Series S/X"))
        ),
        GameShortEntity(
            3328,
            "The Witcher 3: Wild Hunt",
            false,
            "2015-05-18",
            "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
            listOf(Platform("PC"), Platform("Xbox One"),
                Platform("PlayStation 4"), Platform("PlayStation 5"),
                Platform("Xbox Series S/X"))
        ),
        GameShortEntity(
            4200,
            "Portal 2",
            false,
            "2011-04-18",
            "https://media.rawg.io/media/games/2ba/2bac0e87cf45e5b508f227d281c9252a.jpg",
            listOf(Platform("PC"), Platform("Xbox 360"),
                Platform("PlayStation 3"), Platform("Xbox One"),
                Platform("PlayStation 4"))
        ),
        GameShortEntity(
            4291,
            "Counter-Strike: Global Offensive",
            false,
            "2012-08-21",
            "https://media.rawg.io/media/games/736/73619bd336c894d6941d926bfd563946.jpg",
            listOf(Platform("PC"), Platform("Xbox 360"),
                Platform("PlayStation 3"))
        )
    )
    val gamesFull = listOf(
        GameFullEntity(
            58764,
            "Outer Wilds",
            "<p>Outer Wilds is an open world mystery about a solar system trapped in an endless time loop.<br />\\nWelcome to the Space Program!<br />\\nYou&#39;re the newest recruit of Outer Wilds Ventures, a fledgling space program searching for answers in a strange, constantly evolving solar system.</p>\\n<p>Mysteries of the Solar System...<br />\\nWhat lurks in the heart of the ominous Dark Bramble? Who built the alien ruins on the Moon? Can the endless time loop be stopped? Answers await you in the most dangerous reaches of space.</p>\\n<p>A World That Changes Over Time<br />\\nThe planets of Outer Wilds are packed with hidden locations that change with the passage of time. Visit an underground city of before it&#39;s swallowed by sand, or explore the surface of a planet as it crumbles beneath your feet. Every secret is guarded by hazardous environments and natural catastrophes.</p>\\n<p>Grab Your Intergalactic Hiking Gear!<br />\\nStrap on your hiking boots, check your oxygen levels, and get ready to venture into space. Use a variety of unique gadgets to probe your surroundings, track down mysterious signals, decipher ancient alien writing, and roast the perfect marshmallow.</p>",
            "2019-05-29",
            false,
            listOf(Rating(85, Platform("Xbox one")), Rating(82, Platform("Playstation 4")),
                Rating(85, Platform("PC"))),
            "https://media.rawg.io/media/games/9f4/9f418898f5415668ca47b5f4ab1ecfeb.jpg",
            listOf(Platform("PC"), Platform("Xbox One"),
                Platform("PlayStation 4"), Platform("Nintendo Switch")),
            84,
            listOf("Mobius Digital"),
            "Annapurna Interactive",
            listOf(Genre("Adventure"), Genre("Pazzle"), Genre("Indie")),
            listOf("Space", "Surreal")
            ),
        GameFullEntity(
            3498,
            "Grand Theft Auto V",
            "<p>Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update. <br />\\nSimultaneous storytelling from three unique perspectives: <br />\\nFollow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from. <br />\\nGTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.</p>\\n<p>Español<br />\\nRockstar Games se hizo más grande desde su entrega anterior de la serie. Obtienes la construcción del mundo complicada y realista de Liberty City de GTA4 en el escenario de Los Santos, un viejo favorito de los fans, GTA San Andreas. 561 vehículos diferentes (incluidos todos los transportes que puede operar) y la cantidad aumenta con cada actualización.<br />\\nNarración simultánea desde tres perspectivas únicas:<br />\\nSigue a Michael, ex-criminal que vive su vida de ocio lejos del pasado, Franklin, un niño que busca un futuro mejor, y Trevor, el pasado exacto del que Michael está tratando de huir.<br />\\nGTA Online proporcionará muchos desafíos adicionales incluso para los jugadores experimentados, recién llegados del modo historia. Ahora tendrás otros jugadores cerca que pueden ayudarte con la misma probabilidad que arruinar tu misión. Los jugadores pueden experimentar todas las mecánicas de GTA actualizadas a través del personaje personalizable único, y el contenido de la comunidad combinado con el sistema de nivelación tiende a mantener a todos ocupados y comprometidos.</p>",
            "2013-09-17",
            false,
            listOf(Rating(97, Platform("Xbox 360")),
                Rating(97, Platform("Xbox one")),
                Rating(79, Platform("Xbox Series S/X")),
                Rating(97, Platform("PlayStation 3")),
                Rating(82, Platform("Playstation 4")),
                Rating(81, Platform("Playstation 5")),
                Rating(96, Platform("PC"))),
            "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
            listOf(Platform("PC"), Platform("Xbox 360"),
                Platform("PlayStation 3"), Platform("Xbox One"),
                Platform("PlayStation 4"), Platform("PlayStation 5"),
                Platform("Xbox Series S/X")),
            92,
            listOf("Rockstar North"),
            "Rockstar Games",
            listOf(Genre("Action")),
            listOf("Singleplayer", "Multiplayer", "Great Soundtrack", "Open World")
        ),
        GameFullEntity(
            3328,
            "The Witcher 3: Wild Hunt",
            "<p>The third game in a series, it holds nothing back from the player. Open world adventures of the renowned monster slayer Geralt of Rivia are now even on a larger scale. Following the source material more accurately, this time Geralt is trying to find the child of the prophecy, Ciri while making a quick coin from various contracts on the side. Great attention to the world building above all creates an immersive story, where your decisions will shape the world around you.</p>\n<p>CD Project Red are infamous for the amount of work they put into their games, and it shows, because aside from classic third-person action RPG base game they provided 2 massive DLCs with unique questlines and 16 smaller DLCs, containing extra quests and items.</p>\n<p>Players praise the game for its atmosphere and a wide open world that finds the balance between fantasy elements and realistic and believable mechanics, and the game deserved numerous awards for every aspect of the game, from music to direction.</p>",
            "2015-05-18",
            false,
            listOf(Rating(91, Platform("Xbox one")),
                Rating(92, Platform("Playstation 4")),
                Rating(93, Platform("PC"))),
            "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
            listOf(Platform("PC"), Platform("Xbox One"),
                Platform("PlayStation 4"), Platform("PlayStation 5"),
                Platform("Xbox Series S/X")),
            92,
            listOf("CD PROJEKT RED"),
            "CD PROJEKT RED",
            listOf(Genre("Action"), Genre("RPG")),
            listOf("Singleplayer", "Atmospheric", "Open World")

        )
    )
}